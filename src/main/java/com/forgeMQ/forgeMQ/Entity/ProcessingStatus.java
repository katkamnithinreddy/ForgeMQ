package com.forgeMQ.forgeMQ.Entity;

public enum ProcessingStatus {
    DELIVERED,
    PROCESSING,
    ACKED,
    FAILED,
    RETRYING,
    READY_FOR_RETRY
}
