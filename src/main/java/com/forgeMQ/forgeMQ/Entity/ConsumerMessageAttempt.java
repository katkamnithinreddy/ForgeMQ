package com.forgeMQ.forgeMQ.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity 
@Table (name = "consumer_message_attempts",uniqueConstraints = {@UniqueConstraint(columnNames = {"consumer_id", "message_id"})})
public class ConsumerMessageAttempt {
    @Id 
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @ManyToOne 
    @JoinColumn (name = "consumer_id", nullable = false)
    Consumer consumer;
    @ManyToOne 
    @JoinColumn (name = "message_id", nullable = false)
    Message message;
    @Enumerated (jakarta.persistence.EnumType.STRING)
    @Column ( nullable = false)
    private ProcessingStatus status; 
    private int retryCount; 
    @Column(nullable = false )
    private LocalDateTime deliveredAt;
    private LocalDateTime nextRetryAt;
    private LocalDateTime acknowledgedAt;
    private String lastErrorMessage;

    public ConsumerMessageAttempt() {
    }
    public ConsumerMessageAttempt(Consumer consumer, Message message) {
        this.consumer = consumer;
        this.message = message;
        this.status = ProcessingStatus.DELIVERED;
        this.retryCount = 0;
        this.deliveredAt = LocalDateTime.now();
        this.acknowledgedAt = null;
        this.lastErrorMessage = null;
    }
    public Consumer getConsumer() {
        return consumer;
    }
    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public Message getMessage() {
        return message;
    }
    public void setMessage(Message message) {
        this.message = message;
    }
    public ProcessingStatus getStatus() {
        return status;
    }
    public void setStatus(ProcessingStatus status) {
        this.status = status;
    }
    public int getRetryCount() {
        return retryCount;
    }
    public void incrementRetryCount() {
        this.retryCount++;
    }
    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }
    public void setDeliveredAt(LocalDateTime deliveredAt) {
        this.deliveredAt = deliveredAt;
    }
    public LocalDateTime getNextRetryAt(){
        return nextRetryAt;
    }
    public void setNextRetryAt(LocalDateTime nextRetryAt){
        this.nextRetryAt=nextRetryAt;
    }
    public LocalDateTime getAcknowledgedAt() {
        return acknowledgedAt;
    }
    public void setAcknowledgedAt(LocalDateTime acknowledgedAt) {
        this.acknowledgedAt = acknowledgedAt;
    }
    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
    public void setLastErrorMessage(String lastErrorMessage) {
        this.lastErrorMessage = lastErrorMessage;
    }
    
}
