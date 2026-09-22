package com.forgeMQ.forgeMQ.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forgeMQ.forgeMQ.Entity.Consumer;

public interface ConsumerRepository extends JpaRepository<Consumer,Long> {

    boolean existsByName(String name);

    boolean findByName(String name);

    List<Consumer> findByTopicName(String topicname);
    
}
