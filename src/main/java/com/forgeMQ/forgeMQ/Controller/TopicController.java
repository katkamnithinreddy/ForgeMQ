package com.forgeMQ.forgeMQ.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forgeMQ.forgeMQ.DTOs.TopicRequestDTO;
import com.forgeMQ.forgeMQ.Entity.Topic;
import com.forgeMQ.forgeMQ.Service.TopicService;

@RestController 
@RequestMapping ("/api/topics")
public class TopicController {
    @Autowired 
    TopicService topicService;


    @PostMapping
    public ResponseEntity<Topic> createTopic(@RequestBody TopicRequestDTO requestDTO) {
        Topic topic = topicService.createTopic(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(topic);
    }
    @GetMapping
    public ResponseEntity<List<Topic>> getAllTopics() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        return ResponseEntity.ok(topicService.getTopicById(id));
    }

    @GetMapping ("/delete/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }

}
