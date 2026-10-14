package com.forgeMQ.forgeMQ.DTOs;

public class PartitionResponseDTO {
    private Long id;
    private Long topicId;
    private Integer partitionNumber;
    public PartitionResponseDTO(Long id, Long topicId, Integer partitionNumber) {
        this.id = id;
        this.topicId = topicId;
        this.partitionNumber = partitionNumber;
    }
    public Long getId() {
        return id;
    }
    public Long getTopicId() {
        return topicId;
    }
    public Integer getPartitionNumber() {
        return partitionNumber;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }
    public void setPartitionNumber(Integer partitionNumber) {
        this.partitionNumber = partitionNumber;
    }
}
