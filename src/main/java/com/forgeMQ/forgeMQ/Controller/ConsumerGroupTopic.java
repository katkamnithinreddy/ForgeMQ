package com.forgeMQ.forgeMQ.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forgeMQ.forgeMQ.Service.ConsumerGroupTopicService;

@RestController 
@RequestMapping ("/api/consumer-group-topics")
public class ConsumerGroupTopic {
    @Autowired 
    private ConsumerGroupTopicService consumerGroupTopicService;

    @PostMapping ("/{consumergroupid}/{topicname}/subscribe")
    public void subscribeConsumerGroupToTopic(@PathVariable Long consumergroupid, @PathVariable String topicname) {
        consumerGroupTopicService.subscribeConsumerGroupToTopic(consumergroupid, topicname);
    }
    

}
