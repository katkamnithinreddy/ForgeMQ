package com.forgeMQ.forgeMQ.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.forgeMQ.forgeMQ.Entity.ConsumerMessageAttempt;
import com.forgeMQ.forgeMQ.Entity.ProcessingStatus;

public interface ConsumerMessageAttemptRepository extends JpaRepository<ConsumerMessageAttempt,Long> {
    Optional<ConsumerMessageAttempt> findByConsumerIdAndMessageId(Long consumerId, Long messageId);

    List<ConsumerMessageAttempt> findByStatusAndNextRetryAtLessThanEqual(ProcessingStatus retrying, LocalDateTime now);
}
