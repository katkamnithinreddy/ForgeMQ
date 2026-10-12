package com.forgeMQ.forgeMQ.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name="consumer_group_topics",uniqueConstraints = @jakarta.persistence.UniqueConstraint (columnNames = {"consumer_group_id","topic_id"}))
public class ConsumerGroupTopic {
    @Id 
    @GeneratedValue (strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @ManyToOne 
    @JoinColumn (name = "consumer_group_id", nullable = false)
    private ConsumerGroup consumerGroup;
    @ManyToOne 
    @JoinColumn (name = "topic_id", nullable = false)
    private Topic topic;

    public ConsumerGroupTopic() {
    }
    public ConsumerGroupTopic(ConsumerGroup consumerGroup, Topic topic) {
        this.consumerGroup = consumerGroup;
        this.topic = topic;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public ConsumerGroup getConsumerGroup() {
        return consumerGroup;
    }
    public void setConsumerGroup(ConsumerGroup consumerGroup) {
        this.consumerGroup = consumerGroup;
    }
    public Topic getTopic() {
        return topic;
    }
    public void setTopic(Topic topic) {
        this.topic = topic;
    }

}
