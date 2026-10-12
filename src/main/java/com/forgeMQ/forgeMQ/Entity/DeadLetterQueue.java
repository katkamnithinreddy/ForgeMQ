package com.forgeMQ.forgeMQ.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "dead_letter_queue")
public class DeadLetterQueue{
    @Id 
    @GeneratedValue (strategy = jakarta.persistence.GenerationType.IDENTITY)
    long id;
    @ManyToOne
    @JoinColumn(name = "message_id", referencedColumnName = "id")
    private Message message;
    @ManyToOne
    @JoinColumn(name = "consumer_id", referencedColumnName = "id")  
    private Consumer consumer;
    @Column(nullable = false)
    private int retryCount;
    @Column(columnDefinition = "TEXT")
    private String reason;
    @Column(nullable = false)
    private LocalDateTime FailedAt;

    public DeadLetterQueue() {
    }
    public DeadLetterQueue(Message message, Consumer consumer, int retryCount, String reason) {
        this.message = message;
        this.consumer = consumer;
        this.retryCount = retryCount;
        this.reason = reason;
        this.FailedAt = LocalDateTime.now();
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Message getMessage() {
        return message;
    }
    public void setMessage(Message message) {
        this.message = message;
    }
    public Consumer getConsumer() {
        return consumer;
    }
    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }
    public int getRetryCount() {
        return retryCount;
    }
    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public LocalDateTime getFaliedAt() {
        return FailedAt;
    }
    public void setFailesAt(LocalDateTime FailedAt){
        this.FailedAt=FailedAt;
    }





}