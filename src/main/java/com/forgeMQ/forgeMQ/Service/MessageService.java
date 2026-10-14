package com.forgeMQ.forgeMQ.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.forgeMQ.forgeMQ.DTOs.PublishMessageRequestDTO;
import com.forgeMQ.forgeMQ.Entity.Message;
import com.forgeMQ.forgeMQ.Entity.Partition;
import com.forgeMQ.forgeMQ.Entity.Topic;
import com.forgeMQ.forgeMQ.Repository.MessageRepository;
import com.forgeMQ.forgeMQ.Repository.PartitionRepository;
import com.forgeMQ.forgeMQ.Repository.TopicRepository;

@Service 
public class MessageService {
    @Autowired 
    MessageRepository messageRepository;
    @Autowired 
    TopicRepository topicRepository;
    @Autowired 
    private PartitionRepository partitionRepository;

    public Message publishMessage(String topicName,PublishMessageRequestDTO requestDTO) {
        Topic topic = topicRepository.findByName(topicName)
                .orElseThrow(() -> new RuntimeException("Topic with name " + topicName + " not found"));
        List<Partition> partitions = partitionRepository.findByTopicId(topic.getId());
        if (partitions.isEmpty()) {
            throw new RuntimeException("No partitions found for topic with name " + topicName);
        }
        int partitionIndex;
        if(requestDTO.getMessageKey()!=null && !requestDTO.getMessageKey().isEmpty()){
            partitionIndex = Math.abs(requestDTO.getMessageKey().hashCode()) % partitions.size();
        } else {
            partitionIndex = 0;
        } // Default to the first partition if no message key is provided
        Partition partition = partitions.get(partitionIndex); // For simplicity, we are just picking the first partition. You can implement a more sophisticated partition selection logic if needed.
        Message message = new Message(topic,requestDTO.getPayload());
        message.setPartition(partition);
        return messageRepository.save(message);
    }
    
    public List<Message> getMessagesByTopic(String topicName) {
        Topic topic = topicRepository.findByName(topicName)
                .orElseThrow(() -> new RuntimeException("Topic with name " + topicName + " not found"));
        return messageRepository.findAllByTopic(topic);
    }
}
