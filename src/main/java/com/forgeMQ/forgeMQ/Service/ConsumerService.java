package com.forgeMQ.forgeMQ.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forgeMQ.forgeMQ.DTOs.ConsumerRequestDTO;
import com.forgeMQ.forgeMQ.DTOs.ConsumerResponseDTO;
import com.forgeMQ.forgeMQ.Entity.Consumer;
import com.forgeMQ.forgeMQ.Entity.ConsumerOffset;
import com.forgeMQ.forgeMQ.Entity.Message;
import com.forgeMQ.forgeMQ.Entity.Topic;
import com.forgeMQ.forgeMQ.Repository.ConsumerOffsetRepository;
import com.forgeMQ.forgeMQ.Repository.ConsumerRepository;
import com.forgeMQ.forgeMQ.Repository.MessageRepository;
import com.forgeMQ.forgeMQ.Repository.TopicRepository;

@Service 
public class ConsumerService {

    @Autowired 
    private ConsumerRepository consumerRepository;
    @Autowired 
    private TopicRepository topicReposityory;
    @Autowired 
    private ConsumerOffsetRepository consumerOffsetRepository;
    @Autowired 
    private MessageRepository messageRepository;

    public ConsumerResponseDTO createConsumer(ConsumerRequestDTO consumerRequestDTO) {
        Optional<Topic> topicOptional = topicReposityory.findByName(consumerRequestDTO.getTopicname());
        if (topicOptional.isPresent()) {
            Topic topic = topicOptional.get();
            Consumer consumer = new Consumer(
                consumerRequestDTO.getName(),
                topic);
            consumerRepository.save(consumer);
            ConsumerOffset consumerOffset = new ConsumerOffset(consumer);
            consumerOffsetRepository.save(consumerOffset);
            return new ConsumerResponseDTO(
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
                        consumer.getName(),
                        consumer.getTopic(),
                        consumer.getCreatedAt().toString()))
                .toList();
    }

    public List<ConsumerResponseDTO> getConsumersByTopicName(String topicname) {
        List<Consumer> consumers = consumerRepository.findByTopicName(topicname);
        return consumers.stream()
                .map(consumer -> new ConsumerResponseDTO(
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
        return messageRepository.findByTopicAndIdGreaterThan(consumer.getTopic(),consumerOffset.getOffset());
    }
}