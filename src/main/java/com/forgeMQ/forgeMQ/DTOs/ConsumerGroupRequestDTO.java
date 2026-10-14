package com.forgeMQ.forgeMQ.DTOs;

import jakarta.validation.constraints.NotBlank;

public class ConsumerGroupRequestDTO {
    @NotBlank  (message = "Name must not be blank")
    private String name;

    public String getName() {
        return name;
    }

}
