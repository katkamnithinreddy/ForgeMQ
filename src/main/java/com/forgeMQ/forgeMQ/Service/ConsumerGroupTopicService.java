package com.forgeMQ.forgeMQ.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.forgeMQ.forgeMQ.Entity.ConsumerGroup;
import com.forgeMQ.forgeMQ.Entity.ConsumerGroupTopic;
import com.forgeMQ.forgeMQ.Entity.Topic;
import com.forgeMQ.forgeMQ.Repository.ConsumerGroupRepository;
import com.forgeMQ.forgeMQ.Repository.ConsumerGroupTopicRepository;
import com.forgeMQ.forgeMQ.Repository.TopicRepository;

@Service 
public class ConsumerGroupTopicService {

    @Autowired 
    private ConsumerGroupTopicRepository consumerGroupTopicRepository;
    @Autowired 
    private ConsumerGroupRepository consumerGroupRepository;
    @Autowired
    private TopicRepository topicRepository;

    public ConsumerGroupTopic subscribeConsumerGroupToTopic(Long consumerGroupId, String topicName) {
        ConsumerGroup consumerGroup = consumerGroupRepository.findById(consumerGroupId)
                .orElseThrow(() -> new RuntimeException("Consumer Group not found with ID: " + consumerGroupId));
        Topic topic = topicRepository.findByName(topicName)
                .orElseThrow(() -> new RuntimeException("Topic not found with name: " + topicName));
        if (consumerGroupTopicRepository.existsByConsumerGroupIdAndTopicId(consumerGroupId, topic.getId())) {
            throw new RuntimeException("Consumer Group is already subscribed to the topic.");
        }
        ConsumerGroupTopic consumerGroupTopic = new ConsumerGroupTopic(consumerGroup, topic);
        return consumerGroupTopicRepository.save(consumerGroupTopic);
    }
    
    
}
