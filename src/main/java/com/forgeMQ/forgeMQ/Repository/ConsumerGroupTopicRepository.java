package com.forgeMQ.forgeMQ.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forgeMQ.forgeMQ.Entity.ConsumerGroupTopic;

public interface ConsumerGroupTopicRepository extends JpaRepository<ConsumerGroupTopic, Long> {
    boolean existsByConsumerGroupIdAndTopicId(Long consumerGroupId, Long topicId);
    
}
