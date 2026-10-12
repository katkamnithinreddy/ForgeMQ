package com.forgeMQ.forgeMQ.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forgeMQ.forgeMQ.DTOs.AckRequestDTO;
import com.forgeMQ.forgeMQ.DTOs.ConsumerRequestDTO;
import com.forgeMQ.forgeMQ.DTOs.ConsumerResponseDTO;
import com.forgeMQ.forgeMQ.Entity.Message;
import com.forgeMQ.forgeMQ.Service.ConsumerService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/api")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @PostMapping("/consumers")
    public ResponseEntity<ConsumerResponseDTO> createConsumer(@Valid @RequestBody ConsumerRequestDTO consumerRequestDTO) {
        ConsumerResponseDTO consumerResponseDTO = consumerService.createConsumer(consumerRequestDTO);
        return ResponseEntity.ok().body(consumerResponseDTO);
    }
    @GetMapping("/consumers")
    public ResponseEntity<List<ConsumerResponseDTO>> getAllConsumers() {
        List<ConsumerResponseDTO> consumers = consumerService.getAllConsumers();
        return ResponseEntity.ok().body(consumers);
    }  

    @GetMapping ("/{topicname}/consumers")
    public ResponseEntity<List<ConsumerResponseDTO>> getConsumersByTopicName(@PathVariable String topicname) {
        List<ConsumerResponseDTO> consumers = consumerService.getConsumersByTopicName(topicname);
        return ResponseEntity.ok().body(consumers);
    } 

    @GetMapping("/consumers/{consumerId}/messages")
    public ResponseEntity<List<Message>> getMessagesForConsumer(@PathVariable Long consumerId) {
        List<Message> messages = consumerService.getMessagesForConsumer(consumerId);
        return ResponseEntity.ok().body(messages);
    }

    @DeleteMapping ("/consumers/{consumerId}")
    public ResponseEntity<Void> deleteConsumer(@PathVariable Long consumerId) {
        consumerService.deleteConsumer(consumerId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping ("/consumers/{consumerId}/ack")
    public ResponseEntity<String> acknowledgeMessages(@PathVariable Long consumerId,@Valid @RequestBody AckRequestDTO ackRequestDTO) {
        consumerService.acknowledgeMessages(consumerId, ackRequestDTO);
        return ResponseEntity.ok().body("Messages acknowledged for consumer with id: " + consumerId);
    }

    @PostMapping ("/consumers/{consumerId}/nack")
    public ResponseEntity<String> negativeAcknowledgeMessages(@PathVariable Long consumerId,@Valid @RequestBody AckRequestDTO ackRequestDTO) {
        consumerService.negativeAcknowledgeMessages(consumerId, ackRequestDTO);
        return ResponseEntity.ok().body("Messages negatively acknowledged for consumer with id: " + consumerId);
    }

    
}
