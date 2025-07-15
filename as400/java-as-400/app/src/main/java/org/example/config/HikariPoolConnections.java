package org.example.config;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import org.example.config.model.AS400ConnectionDescriptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HikariPoolConnections {

    private static final Logger log = LoggerFactory.getLogger(HikariPoolConnections.class);

    private AS400ConnectionDescriptor AS400Details;
    private String salesforceNextConnector;
    private HikariDataSource dataSource;

    public HikariPoolConnections(HikariDataSource dataSource, AS400ConnectionDescriptor AS400Details) {
        this.dataSource = dataSource;
        this.AS400Details = AS400Details;
    }

    public AS400ConnectionDescriptor getDetails() {
        return this.AS400Details;
    }

    public HikariDataSource getDataSource() {
        return this.dataSource;
    }

    public void logStatus() {
        HikariPoolMXBean pool = this.dataSource.getHikariPoolMXBean();
        System.out.println("Pool Status:");
        System.out.println("- Active: " + pool.getActiveConnections());
        System.out.println("- Idle: " + pool.getIdleConnections());
        System.out.println("- Total: " + pool.getTotalConnections());
        System.out.println("- Threads waiting: " + pool.getThreadsAwaitingConnection());
    }

}
