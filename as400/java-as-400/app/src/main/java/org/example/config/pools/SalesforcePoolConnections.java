package org.example.config.pools;

import org.example.config.model.SalesforceConnectionDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

public class SalesforcePoolConnections {

    private static final Logger log = LoggerFactory.getLogger(SalesforcePoolConnections.class);

    private final SalesforceConnectionDescriptor descriptor;
    private final AtomicInteger activeRequests = new AtomicInteger(0);
    private final AtomicInteger totalRequests = new AtomicInteger(0);

    private Instant lastRequestTime;


    public SalesforcePoolConnections (SalesforceConnectionDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    public void recordRequestStart() {
        activeRequests.incrementAndGet();
        totalRequests.incrementAndGet();
        lastRequestTime = Instant.now();
    }

    public void recordRequestEnd() {
        activeRequests.decrementAndGet();
    }

    public void logStatus() {
        log.info("Salesforce Connection Status:");
        log.info("- Active Requests: {}", activeRequests.get());
        log.info("- Total Requests: {}", totalRequests.get());
        log.info("- Last Request Time: {}", lastRequestTime);
    }

    public SalesforceConnectionDescriptor getDescriptor() {
        return descriptor;
    }
}
