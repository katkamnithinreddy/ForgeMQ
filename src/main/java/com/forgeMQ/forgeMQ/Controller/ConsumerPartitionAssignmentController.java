package com.forgeMQ.forgeMQ.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forgeMQ.forgeMQ.Entity.ConsumerPartitionAssignment;
import com.forgeMQ.forgeMQ.Service.ConsumerPartitionAssignmentService;

@RestController 
@RequestMapping ("/api/consumer-partition-assignments")
public class ConsumerPartitionAssignmentController {
    @Autowired 
    private ConsumerPartitionAssignmentService consumerGroupAssignmentService;

    @PostMapping ("/assign")
    public ResponseEntity<ConsumerPartitionAssignment> assignConsumerToPartition(@RequestParam Long consumerGroupId, @RequestParam Long consumerId, @RequestParam Long partitionId){
        return ResponseEntity.ok(consumerGroupAssignmentService.assignConsumerToPartition(consumerGroupId, consumerId, partitionId));
    }
    


}
