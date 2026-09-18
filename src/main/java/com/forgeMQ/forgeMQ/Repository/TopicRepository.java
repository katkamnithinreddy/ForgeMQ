package com.forgeMQ.forgeMQ.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.forgeMQ.forgeMQ.Entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findByName(String name);
    boolean existsByName(String name);
}
