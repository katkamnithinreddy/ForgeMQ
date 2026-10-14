package com.forgeMQ.forgeMQ.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forgeMQ.forgeMQ.Entity.ConsumerPartitionAssignment;

public interface  ConsumerPartitionAssignmentRepository extends JpaRepository<ConsumerPartitionAssignment, Long> {
    boolean existsByConsumerIdAndPartitionId(Long consumerId, Long partitionId);
    Optional<ConsumerPartitionAssignmentRepository> findByConsumerIdAndPartitionId(Long consumerId, Long partitionId);
    List<ConsumerPartitionAssignmentRepository> findByConsumerId(Long consumerId);
    Optional<ConsumerPartitionAssignmentRepository> findByConsumerGroupId(Long consumerGroupId);
    boolean existsByConsumerGroupIdAndPartitionId(Long consumerGroupId, Long partitionId);
    
    
}
