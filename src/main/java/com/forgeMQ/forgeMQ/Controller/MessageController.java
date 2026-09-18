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

import com.forgeMQ.forgeMQ.DTOs.PublishMessageRequestDTO;
import com.forgeMQ.forgeMQ.Entity.Message;
import com.forgeMQ.forgeMQ.Service.MessageService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/api/topics")
public class MessageController {
    @Autowired 
    private MessageService messageService;

    @PostMapping ("/{topicName}/messages")
    public ResponseEntity<Message> publishMessage(@PathVariable String topicName, @Valid @RequestBody PublishMessageRequestDTO requestDTO) {
        Message message = messageService.publishMessage(topicName, requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping ("/{topicName}/messages")
    public ResponseEntity<List<Message>> getMessagesByTopic(@PathVariable String topicName) {
        List<Message> messages = messageService.getMessagesByTopic(topicName);
        return ResponseEntity.ok(messages);
    }
    
}
