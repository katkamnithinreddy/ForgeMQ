package com.forgeMQ.forgeMQ.DTOs;

import com.forgeMQ.forgeMQ.Entity.Topic;

public class ConsumerResponseDTO {
    private Long id;
    private String name;
    private Topic topic;
    private String createdAt;

    public ConsumerResponseDTO() {
    }
    public ConsumerResponseDTO(String name, Topic topic, String createdAt) {
        this.name = name;
        this.topic = topic;
        this.createdAt = createdAt;
    }
    public ConsumerResponseDTO(Long id, String name, Topic topic, String createdAt) {
        this.id = id;
        this.name = name;
        this.topic = topic;
        this.createdAt = createdAt;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getTopic() {
        return topic.getId();
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
