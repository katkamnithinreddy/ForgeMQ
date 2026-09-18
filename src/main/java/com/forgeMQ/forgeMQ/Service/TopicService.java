package com.forgeMQ.forgeMQ.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.forgeMQ.forgeMQ.DTOs.TopicRequestDTO;
import com.forgeMQ.forgeMQ.Entity.Topic;
import com.forgeMQ.forgeMQ.Repository.TopicRepository;

@Service 
public class TopicService {
    @Autowired 
    TopicRepository topicRepository;

    public Topic createTopic(TopicRequestDTO requestDTO) {
        String name = requestDTO.getName().trim().toLowerCase();
        if(topicRepository.existsByName(name)){
            throw new RuntimeException("Topic with name " + name + " already exists");
        }
        Topic topic = new Topic(name);
        return topicRepository.save(topic);
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public Topic getTopicById(Long id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topic with id " + id + " not found"));
    }

    public void deleteTopic(Long id) {
        if(!topicRepository.existsById(id)){
            throw new RuntimeException("Topic with id " + id + " not found");
        }
        topicRepository.deleteById(id);
    }
}
