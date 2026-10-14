package com.forgeMQ.forgeMQ.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forgeMQ.forgeMQ.Entity.Consumer;
import com.forgeMQ.forgeMQ.Entity.ConsumerGroup;
import com.forgeMQ.forgeMQ.Entity.ConsumerPartitionAssignment;
import com.forgeMQ.forgeMQ.Entity.Partition;
import com.forgeMQ.forgeMQ.Entity.Topic;
import com.forgeMQ.forgeMQ.Repository.ConsumerGroupRepository;
import com.forgeMQ.forgeMQ.Repository.ConsumerGroupTopicRepository;
import com.forgeMQ.forgeMQ.Repository.ConsumerPartitionAssignmentRepository;
import com.forgeMQ.forgeMQ.Repository.ConsumerRepository;
import com.forgeMQ.forgeMQ.Repository.PartitionRepository;

@Service 
public class ConsumerPartitionAssignmentService {

    @Autowired 
    private ConsumerGroupRepository consumerGroupRepository;
    @Autowired 
    private ConsumerRepository consumerRepository;
    @Autowired 
    private PartitionRepository partitionRepository;
    @Autowired 
    private ConsumerPartitionAssignmentRepository consumerPartitionAssignmentRepository;
    @Autowired 
    private ConsumerGroupTopicRepository consumerGroupTopicRepository;

    public ConsumerPartitionAssignment assignConsumerToPartition(Long consumerGroupId, Long consumerId,Long partitionId){
        //check if consumer group exists
        ConsumerGroup consumerGroup=consumerGroupRepository.findById(consumerGroupId).orElseThrow(()->new IllegalArgumentException("Consumer group with id "+consumerGroupId+" not found"));
        //check if consumer exists
        Consumer consumer=consumerRepository.findById(consumerId).orElseThrow(()->new IllegalArgumentException("Consumer with id "+consumerId+" not found"));
        //check if partition exists
        Partition partition=partitionRepository.findById(partitionId).orElseThrow(()->new IllegalArgumentException("Partition with id "+partitionId+" not found"));
        //check if consumer belongs to consumer group
        if(!consumer.getConsumerGroup().getId().equals(consumerGroupId)){
            throw new IllegalArgumentException("Consumer with id "+consumerId+" does not belong to consumer group with id "+consumerGroupId);
        }
        //check if consumer group is subscribed to the topic of the partition
        Topic partitionTopic=partition.getTopic();
        if(!consumerGroupTopicRepository.existsByConsumerGroupIdAndTopicId(consumerGroupId, partitionTopic.getId())){
            throw new IllegalArgumentException("Consumer group with id "+consumerGroupId+" is not subscribed to the topic with id "+partitionTopic.getId());
        }
        //check if partition is already assigned to consumer group
        if(consumerPartitionAssignmentRepository.existsByConsumerGroupIdAndPartitionId(consumerGroupId, partitionId)){
            throw new IllegalArgumentException("Partition with id "+partitionId+" is already assigned to consumer group with id "+consumerGroupId);
        }
        //consumer topic must mactch partition topic
        if(consumer.getTopic().getId()!=partition.getTopic().getId()){
            throw new IllegalArgumentException("Consumer with id "+consumerId+" and partition with id "+partitionId+" do not belong to the same topic");
        }
        ConsumerPartitionAssignment assignment=new ConsumerPartitionAssignment(consumer, consumerGroup, partition);
        consumerPartitionAssignmentRepository.save(assignment);
        return assignment;
    }
    
}
