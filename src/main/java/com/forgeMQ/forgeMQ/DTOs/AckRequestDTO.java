package com.forgeMQ.forgeMQ.DTOs;

public class AckRequestDTO {

    private Long messageId;
    private String errorMessage;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}