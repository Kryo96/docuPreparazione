package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.example.config.ConfigFile;
import org.example.config.model.SalesforceConnectionDescriptor;
import org.example.config.pools.HikariPoolConnections;
import org.example.config.model.AS400ConnectionDescriptor;
import org.example.config.pools.SalesforcePoolConnections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        try {

            // 1. Carica la configurazione
            ConfigFile config = loadConfig();
            AS400ConnectionDescriptor dbConfig = config.getAs400Connection();
            SalesforceConnectionDescriptor descriptor = config.getSalesforceConnection();

            HikariPoolConnections<AS400ConnectionDescriptor> pool = new HikariPoolConnections<AS400ConnectionDescriptor>(dbConfig.createDataSource(), dbConfig);
            SalesforcePoolConnections salesforcePool = new SalesforcePoolConnections(config.getSalesforceConnection());

            salesforcePool.getDescriptor().createConnectorConfig();

            // 4. Esegui test
            runDatabaseTest(pool);
            testMultipleConnections(pool);
            pool.logStatus();

            // 5. Chiudi il pool
            shutdownPool(pool);

            // 6. Riavvio per test successivo
            log.info("Riavvio del connection pool...");
            HikariDataSource newDataSource = dbConfig.createDataSource();
            HikariPoolConnections newPool = new HikariPoolConnections(newDataSource, dbConfig);

            runDatabaseTest(newPool);
            newPool.logStatus();
            shutdownPool(newPool);

        } catch (Exception e) {
            log.error("Errore nell'applicazione", e);
        }
    }

    // Carica la configurazione JSON
    private static ConfigFile loadConfig() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        File configFile = new File("/Users/lukaspetryla/docuPreparazione/as400/java-as-400/app/opt/config/salesforce-middleware.json");
        return mapper.readValue(configFile, ConfigFile.class);
    }

    // Esegui una query di test semplice
    private static void runDatabaseTest(HikariPoolConnections pool) {
        try (Connection conn = pool.getDataSource().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT NAME, CREATOR FROM SYSIBM.SYSTABLES FETCH FIRST 5 ROWS ONLY")) {

            log.info("Query eseguita con successo:");
            while (rs.next()) {
                String name = rs.getString("NAME");
                String creator = rs.getString("CREATOR");
                log.info("→ Table: " + name + ", Creator: " + creator);
            }

        } catch (Exception e) {
            log.error("Errore durante la query", e);
        }
    }

    // Simula più connessioni simultanee
    private static void testMultipleConnections(HikariPoolConnections pool) {
        log.info("Test: apertura di più connessioni...");
        try (Connection c1 = pool.getDataSource().getConnection();
             Connection c2 = pool.getDataSource().getConnection();
             Connection c3 = pool.getDataSource().getConnection()) {

            log.info("3 connessioni ottenute correttamente.");
            pool.logStatus();

        } catch (Exception e) {
            log.error("Errore nel test di più connessioni", e);
        }
    }

    // Chiude il pool
    private static void shutdownPool(HikariPoolConnections pool) {
        if (pool.getDataSource() != null && !pool.getDataSource().isClosed()) {
            log.info("Chiusura del connection pool...");
            pool.getDataSource().close();
            log.info("Pool chiuso.");
        }
    }
}
