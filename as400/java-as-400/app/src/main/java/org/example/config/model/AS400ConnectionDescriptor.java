package org.example.config.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configurazione per la connessione AS400
 */
@Data
public class AS400ConnectionDescriptor extends HikariConfig{

    private static final Logger log = LoggerFactory.getLogger(AS400ConnectionDescriptor.class);
    @JsonProperty("driver")
    private String driver;

    @JsonProperty("url")
    private String url;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("fieldMapper")
    private String fieldMapper;

    @JsonProperty("minConnections")
    private int minConnections = 5;

    @JsonProperty("maxConnections")
    private int maxConnections = 20;

    @JsonProperty("connectionTimeout")
    private long connectionTimeout = 30000;

    @JsonProperty("idleTimeout")
    private long idleTimeout = 600000;

    @JsonProperty("maxLifetime")
    private long maxLifetime = 1800000;

    @JsonProperty("autoCommit")
    private boolean autoCommit = true;

    @JsonProperty("properties")
    private String properties;

    @JsonIgnore
    private HikariDataSource dataSource;

    /**
     * Inizializza il connection pool
     */
    public HikariDataSource createDataSource() {

        if (dataSource != null && !dataSource.isClosed()) {
            log.info("Present AS400 connection pool");
            return dataSource;
        }

        log.info("Initializing AS400 connection pool");
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        // Pool settings
        config.setMinimumIdle(minConnections);
        config.setMaximumPoolSize(maxConnections);
        config.setConnectionTimeout(connectionTimeout);
        config.setIdleTimeout(idleTimeout);
        config.setMaxLifetime(maxLifetime);
        config.setAutoCommit(autoCommit);

        // AS400 specific settings
        config.addDataSourceProperty("prompt", "false");
        config.addDataSourceProperty("naming", "sql");
        config.addDataSourceProperty("errors", "full");
        config.addDataSourceProperty("date format", "iso");
        config.addDataSourceProperty("time format", "iso");

        // Proprietà aggiuntive se specificate
        if (properties != null && !properties.isEmpty()) {
            String[] props = properties.split(";");
            for (String prop : props) {
                String[] kv = prop.split("=");
                if (kv.length == 2) {
                    config.addDataSourceProperty(kv[0].trim(), kv[1].trim());
                }
            }
        }

        config.setPoolName("AS400-Pool");
        dataSource = new HikariDataSource(config);
        log.info("AS400 connection pool initialized successfully");
        return dataSource;
    }
}
