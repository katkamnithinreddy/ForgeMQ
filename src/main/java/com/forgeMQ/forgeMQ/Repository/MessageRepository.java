package com.forgeMQ.forgeMQ.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forgeMQ.forgeMQ.Entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
    
}
