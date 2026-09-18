package com.forgeMQ.forgeMQ.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TopicRequestDTO {
    
    @NotBlank (message = "Topic name cannot be blank")
    @Size(min = 3, max = 100, message = "Topic name must be between 3 and 100 characters")
    private String name; 
    public TopicRequestDTO() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
