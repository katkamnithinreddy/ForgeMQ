package com.forgeMQ.forgeMQ.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forgeMQ.forgeMQ.DTOs.PartitionResponseDTO;
import com.forgeMQ.forgeMQ.Entity.Partition;
import com.forgeMQ.forgeMQ.Entity.Topic;
import com.forgeMQ.forgeMQ.Repository.PartitionRepository;
import com.forgeMQ.forgeMQ.Repository.TopicRepository;

@Service 
public class PartitionService {
    @Autowired 
    private PartitionRepository partitionRepository;
    @Autowired 
    private TopicRepository topicRepository;

    public PartitionResponseDTO createPartition(Long topicId, Integer partitionNumber){
        Topic topic =topicRepository.findById(topicId).orElseThrow(()->new IllegalArgumentException("Topic with id "+topicId+" not found"));
        if(partitionRepository.existsByTopicIdAndPartitionNumber(topicId,partitionNumber)){
            throw new IllegalArgumentException("Partition with the same topicId and partitionNumber already exists");
        }
        Partition partition=new Partition();
        partition.setTopic(topic);
        partition.setPartitionNumber(partitionNumber);
        return new PartitionResponseDTO(partitionRepository.save(partition).getId(),topicId,partitionNumber);
    }
    public List<PartitionResponseDTO> getAllPartitions(){
        List<Partition> partitions=partitionRepository.findAll();
        return partitions.stream().map(partition->new PartitionResponseDTO(partition.getId(),partition.getTopic().getId(),partition.getPartitionNumber())).toList();
    }
    public List<PartitionResponseDTO> getPartitionsByTopicId(Long topicId) {
        List<Partition> partitions = partitionRepository.findByTopicId(topicId);
        return partitions.stream().map(partition -> new PartitionResponseDTO(partition.getId(), partition.getTopic().getId(), partition.getPartitionNumber())).toList();
    }
}
