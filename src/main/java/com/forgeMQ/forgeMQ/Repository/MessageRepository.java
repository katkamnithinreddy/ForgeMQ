package com.forgeMQ.forgeMQ.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forgeMQ.forgeMQ.Entity.Message;
import com.forgeMQ.forgeMQ.Entity.Topic;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByTopic(Topic topic);
    
}
