package com.forgeMQ.forgeMQ.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forgeMQ.forgeMQ.Entity.Consumer;
import com.forgeMQ.forgeMQ.Entity.ConsumerOffset;

public interface ConsumerOffsetRepository extends JpaRepository<ConsumerOffset,Long> {

    Optional<ConsumerOffset> findByConsumer(Consumer consumer);
    
}
