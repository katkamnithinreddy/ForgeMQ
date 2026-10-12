package com.forgeMQ.forgeMQ.DTOs;

import jakarta.validation.constraints.NotBlank;

public class ConsumerGroupRequestDTO {
    @NotBlank  (message = "Name must not be blank")
    private String names;

    public String getName() {
        return names;
    }

}
