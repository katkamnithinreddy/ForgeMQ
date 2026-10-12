package com.forgeMQ.forgeMQ.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forgeMQ.forgeMQ.Entity.ConsumerGroup;

public interface ConsumerGroupRepository extends JpaRepository<ConsumerGroup,Long> {
    Optional<ConsumerGroup> findByNames(String names);
    Optional<ConsumerGroup> findById(Long id);
    boolean existsByNames(String names);
}
