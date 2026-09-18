package com.forgeMQ.forgeMQ.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PublishMessageRequestDTO {
    @NotBlank (message = "Payload cannot be blank") 
    @Size ( max = 1000, message = "Payload must be less than 1000 characters")
    private String payload;
    public String getPayload() {
        return payload;
    }
    public void setPayload(String payload) {
        this.payload = payload;
    }
}
