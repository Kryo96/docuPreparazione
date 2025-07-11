package org.example.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Named;
import javax.sql.DataSource;
import com.ibm.as400.access.AS400JDBCDataSource;
import com.ibm.db2.jcc.DB2SimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configurazione DataSource semplice:
 * - DB2 LUW per development
 * - AS400 per produzione
 */
@ApplicationScoped
public class DataSourceConfig {

        private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);
        private static final String ENVIRONMENT = System.getProperty("app.env", "development");

        @Produces
        @ApplicationScoped
        @Named("primaryDataSource")
        public DataSource getDataSource() {
                logger.info("Creazione DataSource per ambiente: " + ENVIRONMENT);

                switch (ENVIRONMENT.toLowerCase()) {
                        case "development":
                                return createDB2DataSource();
                        case "production":
                                return createAS400DataSource();
                        default:
                                throw new IllegalStateException("Ambiente non riconosciuto: " + ENVIRONMENT);
                }
        }

        /**
         * DataSource DB2 LUW per development locale
         */
        private DataSource createDB2DataSource() {
                logger.info("Configurazione DB2 LUW DataSource");

                DB2SimpleDataSource ds = new DB2SimpleDataSource();

                // Configurazione DB2
                ds.setServerName(System.getProperty("db2.host", "localhost"));
                ds.setPortNumber(Integer.parseInt(System.getProperty("db2.port", "50000")));
                ds.setDatabaseName(System.getProperty("db2.database", "TESTDB"));
                ds.setUser(System.getProperty("db2.user", "db2inst1"));
                ds.setPassword(System.getProperty("db2.password", "password"));
                ds.setDriverType(4); // Type 4 - Pure Java driver

                // Log configurazione
                logger.info("DB2 configurato su: " + ds.getServerName() + ":" + ds.getPortNumber());

                return ds;
        }

        /**
         * DataSource AS400 per test/produzione
         */
        private DataSource createAS400DataSource() {
                logger.info("Configurazione AS400 DataSource");

                AS400JDBCDataSource ds = new AS400JDBCDataSource();

                // Configurazione AS400
                ds.setServerName(System.getProperty("as400.host"));
                ds.setUser(System.getProperty("as400.user"));
                ds.setPassword(System.getProperty("as400.password"));

                // Librerie AS400
                String libraries = System.getProperty("as400.libraries", "PRODLIB");
                ds.setLibraries(libraries);

                // Impostazioni AS400
                ds.setNaming("sql"); // Usa naming SQL standard (LIBRARY.FILE)
                ds.setDateFormat("iso");
                ds.setTimeFormat("iso");

                // Log configurazione
                logger.info("AS400 configurato su: " + ds.getServerName() + " con librerie: " + libraries);

                return ds;
        }
}