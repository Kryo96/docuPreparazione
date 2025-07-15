package org.example.config.pools;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HikariPoolConnections <T extends HikariConfig> {

    private static final Logger log = LoggerFactory.getLogger(HikariPoolConnections.class);

    private final T details;
    private final HikariDataSource dataSource;

    public HikariPoolConnections(HikariDataSource dataSource, T details) {
        this.dataSource = dataSource;
        this.details = details;
    }

    public T getDetails() {
        return this.details;
    }

    public HikariDataSource getDataSource() {
        return this.dataSource;
    }

    public void logStatus() {
        HikariPoolMXBean pool = this.dataSource.getHikariPoolMXBean();
        if (pool == null) {
            log.warn("HikariPoolMXBean is unavailable. The pool might be shut down.");
            return;
        }

        log.info("Pool Status:");
        log.info("- Active: {}", pool.getActiveConnections());
        log.info("- Idle: {}", pool.getIdleConnections());
        log.info("- Total: {}", pool.getTotalConnections());
        log.info("- Threads waiting: {}", pool.getThreadsAwaitingConnection());
    }


}
