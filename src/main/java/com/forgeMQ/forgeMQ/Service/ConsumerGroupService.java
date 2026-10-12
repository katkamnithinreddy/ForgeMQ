package com.forgeMQ.forgeMQ.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.forgeMQ.forgeMQ.Entity.ConsumerGroup;
import com.forgeMQ.forgeMQ.Repository.ConsumerGroupRepository;

@Service 
public class ConsumerGroupService {
    @Autowired 
    private ConsumerGroupRepository consumerGroupRepository;

    public ConsumerGroup createGroup(String names){
        if (consumerGroupRepository.existsByNames(names)){
            throw new RuntimeException("Consumer Group already exists: " + names);
        }
        ConsumerGroup cg=new ConsumerGroup(names);
        return consumerGroupRepository.save(cg);

    }
    public List<ConsumerGroup> getAllConsumerGroups(){
        return consumerGroupRepository.findAll();
    }

}
