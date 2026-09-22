package com.forgeMQ.forgeMQ.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name = "consumer_offsets")
public class ConsumerOffset {
    @Id 
    @GeneratedValue (strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @OneToOne 
    @JoinColumn (name = "consumer_id", nullable = false)
    private Consumer consumer;
    @Column (name="current_offset", nullable = false)
    private Long offset;
    @Column (nullable = false)
    private LocalDateTime updatedAt;
    public ConsumerOffset(Consumer consumer) {
        this.consumer = consumer;
        this.offset = 0L;
        this.updatedAt = LocalDateTime.now();
    }
    public ConsumerOffset() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Consumer getConsumer() {
        return consumer;
    }
    public Long getOffset() {
        return offset;
    }
    public void setOffset(Long offset) {
        this.offset = offset;
        this.updatedAt = LocalDateTime.now();
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
