package com.forgeMQ.forgeMQ.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (
    name = "partitions",
    uniqueConstraints = {
        @jakarta.persistence.UniqueConstraint(columnNames = {"topic_id", "partition_number"}),
        @jakarta.persistence.UniqueConstraint(columnNames = {"partition_number"})
    })
public class Partition {
    @Id 
    @GeneratedValue (strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn (name = "topic_id", nullable = false)
    private Topic topic;
    @Column (name = "partition_number", nullable = false)
    private Integer partitionNumber;
    public Partition() {
    }
    public Partition(Topic topic, Integer partitionNumber) {
        this.topic = topic;
        this.partitionNumber = partitionNumber;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Topic getTopic() {
        return topic;
    }
    public void setTopic(Topic topic) {
        this.topic = topic;
    }
    public Integer getPartitionNumber() {
        return partitionNumber;
    }
    public void setPartitionNumber(Integer partitionNumber) {
        this.partitionNumber = partitionNumber;
    }

}
