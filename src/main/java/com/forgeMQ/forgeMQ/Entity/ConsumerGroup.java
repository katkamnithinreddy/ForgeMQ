package com.forgeMQ.forgeMQ.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity 
@Table (name="consumer_groups", uniqueConstraints = @UniqueConstraint (columnNames = "names"))
public class ConsumerGroup {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable=false , unique=true)
    private String names;

    @Column (nullable = false)
    private LocalDateTime createdAt;

    public ConsumerGroup(){
    }

    public ConsumerGroup(String names){
        this.names=names;
        this.createdAt=LocalDateTime.now();
    }
    public Long getId(){
        return Id;
    }
    public String getName(){
        return names;
    }
    public void setName(String names){
        this.names=names;
    }
    public LocalDateTime getCreatedAT(){
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }

    


}
