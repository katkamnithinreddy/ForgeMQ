package com.forgeMQ.forgeMQ.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (
    name = "consumer_partition_assignments"
)
public class ConsumerPartitionAssignment {
    @Id 
    @GeneratedValue (strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn (name = "consumer_id", nullable = false)
    private Consumer consumer;

    @ManyToOne (fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn (name = "consumer_group_id", nullable = false)
    private ConsumerGroup consumerGroup;

    @ManyToOne (fetch = jakarta.persistence.FetchType.LAZY)
    @JoinColumn (name = "partition_id", nullable = false)
    private Partition partition;

    public ConsumerPartitionAssignment() {
    }
    public ConsumerPartitionAssignment(Consumer consumer, ConsumerGroup consumerGroup, Partition partition) {
        this.consumer = consumer;
        this.consumerGroup = consumerGroup;
        this.partition = partition;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Consumer getConsumer() {
        return consumer;
    }
    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }
    public ConsumerGroup getConsumerGroup() {
        return consumerGroup;
    }
    public void setConsumerGroup(ConsumerGroup consumerGroup) {
        this.consumerGroup = consumerGroup;
    }
    public Partition getPartition() {
        return partition;
    }
    public void setPartition(Partition partition) {
        this.partition = partition;
    }
    public boolean existsByConsumerGroupIdAndPartitionId(Long consumerGroupId, Long partitionId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsByConsumerGroupIdAndPartitionId'");
    }



    
}
