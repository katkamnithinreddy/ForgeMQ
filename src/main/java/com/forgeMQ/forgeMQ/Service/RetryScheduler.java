package com.forgeMQ.forgeMQ.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.forgeMQ.forgeMQ.Entity.ConsumerMessageAttempt;
import com.forgeMQ.forgeMQ.Entity.ProcessingStatus;
import com.forgeMQ.forgeMQ.Repository.ConsumerMessageAttemptRepository;

@Component 
public class RetryScheduler {
    @Autowired 
    ConsumerMessageAttemptRepository consumerMessageAttemptRepository;

   // @Scheduled (fixedDelay = 1000)
    public void processRetryScheduler(){
        LocalDateTime now=LocalDateTime.now();

        List<ConsumerMessageAttempt> attempts= consumerMessageAttemptRepository.findByStatusAndNextRetryAtLessThanEqual(ProcessingStatus.RETRYING,now);
        for( ConsumerMessageAttempt attempt: attempts){
           
            attempt.setStatus(ProcessingStatus.READY_FOR_RETRY);
            consumerMessageAttemptRepository.save(attempt);

        }
        
    }
}
