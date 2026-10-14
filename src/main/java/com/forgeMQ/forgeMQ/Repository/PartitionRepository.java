package com.forgeMQ.forgeMQ.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forgeMQ.forgeMQ.Entity.Partition;

public interface PartitionRepository extends JpaRepository<Partition, Long> {
    boolean existsByTopicIdAndPartitionNumber(Long topicId, Integer partitionNumber);

    List<Partition> findByTopicId(Long topicId);

    List<Partition> findByTopicIdAndPartitionNumber(Long topicId, Integer partitionNumber);
    
}
