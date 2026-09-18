package com.forgeMQ.forgeMQ.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.forgeMQ.forgeMQ.DTOs.PublishMessageRequestDTO;
import com.forgeMQ.forgeMQ.Entity.Message;
import com.forgeMQ.forgeMQ.Entity.Topic;
import com.forgeMQ.forgeMQ.Repository.MessageRepository;
import com.forgeMQ.forgeMQ.Repository.TopicRepository;

@Service 
public class MessageService {
    @Autowired 
    MessageRepository messageRepository;
    @Autowired 
    TopicRepository topicRepository;

    public Message publishMessage(String topicName,PublishMessageRequestDTO requestDTO) {
        Topic topic = topicRepository.findByName(topicName)
                .orElseThrow(() -> new RuntimeException("Topic with name " + topicName + " not found"));
        Message message = new Message(topic,requestDTO.getPayload());
        return messageRepository.save(message);
    }
    
}
