package com.forgeMQ.forgeMQ.DTOs;

import jakarta.validation.constraints.NotNull;

public class PartitionRequestDTO {
    @NotNull (message = "Topic ID must not be blank")
    private Long topicId;
    @NotNull (message = "Partition number must not be null")
    private Integer partitionNumber;

    public Long getTopicId() {
        return topicId;
    }

    public Integer getPartitionNumber() {
        return partitionNumber;
    }
}
