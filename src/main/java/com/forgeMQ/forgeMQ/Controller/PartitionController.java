package com.forgeMQ.forgeMQ.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forgeMQ.forgeMQ.DTOs.PartitionRequestDTO;
import com.forgeMQ.forgeMQ.DTOs.PartitionResponseDTO;
import com.forgeMQ.forgeMQ.Service.PartitionService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/api/partitions")
public class PartitionController {
    @Autowired 
    private PartitionService partitionService;

    @PostMapping
    public ResponseEntity<PartitionResponseDTO> createPartition(@RequestBody @Valid PartitionRequestDTO request){
        return ResponseEntity.ok(partitionService.createPartition(request.getTopicId(),request.getPartitionNumber()));
    }

    @GetMapping 
    public ResponseEntity<List<PartitionResponseDTO>> getAllPartitions(){
        return ResponseEntity.ok(partitionService.getAllPartitions());
    }
    @GetMapping ("/topic/{topicId}")
    public ResponseEntity<List<PartitionResponseDTO>> getPartitionsByTopicId(@PathVariable Long topicId){
        return ResponseEntity.ok(partitionService.getPartitionsByTopicId(topicId));
    }

}
