package com.forgeMQ.forgeMQ.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity 
@Table (name = "messages" ,uniqueConstraints = {@UniqueConstraint(columnNames = {"topic_id", "payload"})})
public class Message {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne 
    @JoinColumn (name = "topic_id", nullable = false)
    private Topic topic;
    @Column (nullable = false, columnDefinition = "TEXT")
    private String payload;
    @ManyToOne 
    @JoinColumn (name = "partition_id")
    private Partition partition;
    @Column (nullable = false)
    private LocalDateTime createdAt;

    private int retryCount;

    public Message() {
    }
    public Message(Topic topic, String payload) {
        this.topic = topic;
        this.payload = payload;
        this.createdAt = LocalDateTime.now();
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Topic getTopic() {
        return topic;
    }
    public void setTopic(Topic topic) {
        this.topic = topic;
    }
    public String getPayload() {
        return payload;
    }
    public void setPayload(String payload) {
        this.payload = payload;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public int getRetryCount() {
        return retryCount;
    }
    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }
    public Partition getPartition() {
        return partition;
    }
    public void setPartition(Partition partition) {
        this.partition = partition;
    }



}
