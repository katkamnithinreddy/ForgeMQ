package com.forgeMQ.forgeMQ.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ConsumerRequestDTO {
    @NotBlank 
    @Size (max = 1000 , message = "Name must not exceed 1000 characters")
    private String name;
    @NotBlank
    private String topicname;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTopicname() {
        return topicname;
    }
    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }
}
