package com.forgeMQ.forgeMQ.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forgeMQ.forgeMQ.DTOs.ConsumerGroupRequestDTO;
import com.forgeMQ.forgeMQ.Service.ConsumerGroupService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/api/consumer-groups")
public class ConsumerGroup {
    @Autowired 
    private ConsumerGroupService consumerGroupService;

    @PostMapping
    public ResponseEntity<com.forgeMQ.forgeMQ.Entity.ConsumerGroup> createGroup(@RequestBody @Valid ConsumerGroupRequestDTO request){
        return ResponseEntity.ok(consumerGroupService.createGroup(request.getName()));
    }

    @GetMapping 
    public ResponseEntity<java.util.List<com.forgeMQ.forgeMQ.Entity.ConsumerGroup>> getAllConsumerGroups(){
        return ResponseEntity.ok(consumerGroupService.getAllConsumerGroups());
    }
}
