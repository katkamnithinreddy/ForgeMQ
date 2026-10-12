package com.forgeMQ.forgeMQ.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forgeMQ.forgeMQ.DTOs.AckRequestDTO;
import com.forgeMQ.forgeMQ.DTOs.ConsumerRequestDTO;
import com.forgeMQ.forgeMQ.DTOs.ConsumerResponseDTO;
import com.forgeMQ.forgeMQ.Entity.Consumer;
import com.forgeMQ.forgeMQ.Entity.ConsumerGroup;
import com.forgeMQ.forgeMQ.Entity.ConsumerMessageAttempt;
import com.forgeMQ.forgeMQ.Entity.ConsumerOffset;
import com.forgeMQ.forgeMQ.Entity.DeadLetterQueue;
import com.forgeMQ.forgeMQ.Entity.Message;
import com.forgeMQ.forgeMQ.Entity.ProcessingStatus;
import com.forgeMQ.forgeMQ.Entity.Topic;
import com.forgeMQ.forgeMQ.Repository.ConsumerGroupRepository;
import com.forgeMQ.forgeMQ.Repository.ConsumerMessageAttemptRepository;
import com.forgeMQ.forgeMQ.Repository.ConsumerOffsetRepository;
import com.forgeMQ.forgeMQ.Repository.ConsumerRepository;
import com.forgeMQ.forgeMQ.Repository.DeadLetterQueueRepository;
import com.forgeMQ.forgeMQ.Repository.MessageRepository;
import com.forgeMQ.forgeMQ.Repository.TopicRepository;


@Service 
public class ConsumerService {

    private static final int MAX_RETRIES=5;
    private static final long MAX_DELAY_SECONDS=60;

    @Autowired 
    private ConsumerRepository consumerRepository;
    @Autowired 
    private TopicRepository topicReposityory;
    @Autowired 
    private ConsumerOffsetRepository consumerOffsetRepository;
    @Autowired 
    private MessageRepository messageRepository;
    @Autowired 
    private ConsumerMessageAttemptRepository consumerMessageAttemptRepository;
    @Autowired 
    private DeadLetterQueueRepository deadLetterQueueRepository;
    @Autowired 
    private ConsumerGroupRepository consumerGroupRepository;


    private LocalDateTime BackOffDelay(int retryCount){
        long delaySeconds=(long) Math.pow(2,retryCount);
        delaySeconds=Math.min(delaySeconds,MAX_DELAY_SECONDS);
        return LocalDateTime.now().plusSeconds(delaySeconds);
    }

    public ConsumerResponseDTO createConsumer(ConsumerRequestDTO consumerRequestDTO) {
        Optional<Topic> topicOptional = topicReposityory.findByName(consumerRequestDTO.getTopicname());
        Optional<ConsumerGroup> consumerGroupOptional = consumerGroupRepository.findByNames(consumerRequestDTO.getConsumerGroupName());
        if (topicOptional.isPresent() && consumerGroupOptional.isPresent()) {
            Topic topic = topicOptional.get();
            ConsumerGroup consumerGroup = consumerGroupOptional.get();
            Consumer consumer = new Consumer(
                consumerRequestDTO.getName(),
                topic);
            consumer.setConsumerGroup(consumerGroup);
            consumerRepository.save(consumer);
            ConsumerOffset consumerOffset = new ConsumerOffset(consumer);
            consumerOffsetRepository.save(consumerOffset);
            return new ConsumerResponseDTO(
                consumer.getId(),
                consumer.getName(),
                consumer.getTopic(),
                consumer.getCreatedAt().toString());
        } else {
            throw new RuntimeException("Topic not found with name: " + consumerRequestDTO.getTopicname());
        }
    }

    public List<ConsumerResponseDTO> getAllConsumers() {
        List<Consumer> consumers = consumerRepository.findAll();
        return consumers.stream()
                .map(consumer -> new ConsumerResponseDTO(
                        consumer.getId(),
                        consumer.getName(),
                        consumer.getTopic(),
                        consumer.getCreatedAt().toString()))
                .toList();
    }

    public List<ConsumerResponseDTO> getConsumersByTopicName(String topicname) {
        List<Consumer> consumers = consumerRepository.findByTopicName(topicname);
        return consumers.stream()
                .map(consumer -> new ConsumerResponseDTO(
                        consumer.getId(),
                        consumer.getName(),
                        consumer.getTopic(),
                        consumer.getCreatedAt().toString()))
                .toList();
    }

    public List<Message>  getMessagesForConsumer(Long consumerId) {
        Consumer consumer = consumerRepository.findById(consumerId)
                .orElseThrow(() -> new RuntimeException("Consumer not found with id: " + consumerId));
        ConsumerOffset consumerOffset = consumerOffsetRepository.findByConsumer(consumer)
                .orElseThrow(() -> new RuntimeException("Consumer offset not found for consumer with id: " + consumerId));
        List<Message> messages= messageRepository.findByTopicAndIdGreaterThan(consumer.getTopic(),consumerOffset.getOffset());
        for(Message message: messages){
            Optional<ConsumerMessageAttempt> attemptOptional = consumerMessageAttemptRepository.findByConsumerIdAndMessageId(consumerId, message.getId());
            if (attemptOptional.isEmpty()) {
                ConsumerMessageAttempt attempt = new ConsumerMessageAttempt(consumer, message);
                consumerMessageAttemptRepository.save(attempt);
            }
        }
        return messages;
    }

    public void deleteConsumer(Long consumerId) {
        Consumer consumer = consumerRepository.findById(consumerId)
                .orElseThrow(() -> new RuntimeException("Consumer not found with id: " + consumerId));
        consumerRepository.delete(consumer);
    }

    public void acknowledgeMessages(Long consumerId,AckRequestDTO ackRequestDTO) {
        Consumer consumer = consumerRepository.findById(consumerId)
                .orElseThrow(() -> new RuntimeException("Consumer not found with id: " + consumerId));
        ConsumerOffset consumerOffset = consumerOffsetRepository.findByConsumer(consumer)
                .orElseThrow(() -> new RuntimeException("Consumer offset not found for consumer with id: " + consumerId)); 
        Message message = messageRepository.findById(ackRequestDTO.getMessageId())
                .orElseThrow(() -> new RuntimeException("Message not found with id: " + ackRequestDTO.getMessageId()));
        if (!message.getTopic().equals(consumer.getTopic())) {
            throw new RuntimeException("Message with id: " + ackRequestDTO.getMessageId() + " does not belong to the topic of consumer with id: " + consumerId);
        }
        if (message.getId() <= consumerOffset.getOffset()) {
            throw new RuntimeException("Message with id: " + ackRequestDTO.getMessageId() + " has already been acknowledged by consumer with id: " + consumerId);
        }
        consumerOffset.setOffset(message.getId());
        consumerMessageAttemptRepository.findByConsumerIdAndMessageId(consumerId, message.getId())
                .ifPresent(attempt -> {
                    attempt.setStatus(ProcessingStatus.ACKED);
                    consumerMessageAttemptRepository.save(attempt);
                });
        consumerOffsetRepository.save(consumerOffset);
    }

    public void negativeAcknowledgeMessages(Long consumerId, AckRequestDTO ackRequestDTO) {
        Consumer consumer = consumerRepository.findById(consumerId)
                .orElseThrow(() -> new RuntimeException("Consumer not found with id: " + consumerId));
        Message message = messageRepository.findById(ackRequestDTO.getMessageId())
                .orElseThrow(() -> new RuntimeException("Message not found with id: " + ackRequestDTO.getMessageId()));
            
        //checking message and consumer topics.
        if (!message.getTopic().equals(consumer.getTopic())) {
            throw new RuntimeException("Message with id: " + ackRequestDTO.getMessageId() + " does not belong to the topic of consumer with id: " + consumerId);
        }
        ConsumerMessageAttempt attempt=consumerMessageAttemptRepository.findByConsumerIdAndMessageId(consumerId,ackRequestDTO.getMessageId()).orElseThrow(()-> new RuntimeException("Consumer offset not found for consumer with id: " + consumerId));
        if(attempt.getRetryCount()>=MAX_RETRIES){
            DeadLetterQueue dlq=new DeadLetterQueue();
            dlq.setMessage(message);
            dlq.setConsumer(consumer);
            dlq.setRetryCount(attempt.getRetryCount());
            dlq.setReason(attempt.getLastErrorMessage());
            dlq.setFailesAt(LocalDateTime.now());
            deadLetterQueueRepository.save(dlq);
            attempt.setStatus(ProcessingStatus.FAILED);
        }
        else{
            attempt.setNextRetryAt(BackOffDelay(attempt.getRetryCount()));
            attempt.incrementRetryCount();
            attempt.setStatus(ProcessingStatus.RETRYING);
        }
        attempt.setLastErrorMessage(ackRequestDTO.getErrorMessage());
        consumerMessageAttemptRepository.save(attempt);
    }
}