package org.example;

import org.example.config.DataSourceConfig;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test per verificare le connessioni database
 */
public class AppTest {

    private DataSourceConfig dataSourceConfig;

    @BeforeEach
    public void setUp() {
        dataSourceConfig = new DataSourceConfig();
    }

    @Test
    @Tag("integration")
    @DisplayName("Test Connessione DB2 LUW")
    @EnabledIfSystemProperty(named = "run.db2.tests", matches = "true")
    public void testDB2Connection() {
        // Setup ambiente DB2
        System.setProperty("app.env", "development");
        System.setProperty("db2.host", System.getProperty("db2.host", "localhost"));
        System.setProperty("db2.port", System.getProperty("db2.port", "50000"));
        System.setProperty("db2.database", System.getProperty("db2.database", "SAMPLE"));
        System.setProperty("db2.user", System.getProperty("db2.user", "db2inst1"));
        System.setProperty("db2.password", System.getProperty("db2.password", "test"));

        DataSource dataSource = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Ottieni DataSource
            dataSource = dataSourceConfig.getDataSource();
            assertNotNull(dataSource, "DataSource DB2 non deve essere null");

            // Test connessione
            connection = dataSource.getConnection();
            assertNotNull(connection, "Connection DB2 non deve essere null");
            assertFalse(connection.isClosed(), "La connessione deve essere aperta");

            // Verifica metadata database
            DatabaseMetaData metaData = connection.getMetaData();
            String dbProduct = metaData.getDatabaseProductName();
            String dbVersion = metaData.getDatabaseProductVersion();

            System.out.println("=== TEST DB2 CONNECTION ===");
            System.out.println("Database: " + dbProduct);
            System.out.println("Versione: " + dbVersion);
            System.out.println("URL: " + metaData.getURL());
            System.out.println("Driver: " + metaData.getDriverName() + " " + metaData.getDriverVersion());

            // Verifica che sia DB2
            assertTrue(dbProduct.toLowerCase().contains("db2"),
                    "Il database deve essere DB2, trovato: " + dbProduct);

            // Test query semplice
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT CURRENT_TIMESTAMP FROM SYSIBM.SYSDUMMY1");

            assertTrue(resultSet.next(), "La query deve restituire un risultato");
            assertNotNull(resultSet.getTimestamp(1), "Il timestamp non deve essere null");

            System.out.println("Timestamp DB2: " + resultSet.getTimestamp(1));
            System.out.println("✅ Test DB2 completato con successo");

        } catch (SQLException e) {
            fail("Errore durante il test DB2: " + e.getMessage());
        } finally {
            // Cleanup risorse
            if (resultSet != null) {
                try { resultSet.close(); } catch (SQLException e) { /* ignore */ }
            }
            if (statement != null) {
                try { statement.close(); } catch (SQLException e) { /* ignore */ }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) { /* ignore */ }
            }
        }
    }

    @Test
    @DisplayName("Test Connessione AS400")
    @Tag("integration")
    @EnabledIfSystemProperty(named = "run.as400.tests", matches = "true")
    public void testAS400Connection() {
        // Verifica che le proprietà AS400 siano configurate
        String as400Host = System.getProperty("as400.host");
        String as400User = System.getProperty("as400.user");
        String as400Password = System.getProperty("as400.password");

        Assumptions.assumeTrue(as400Host != null && !as400Host.isEmpty(),
                "as400.host deve essere configurato");
        Assumptions.assumeTrue(as400User != null && !as400User.isEmpty(),
                "as400.user deve essere configurato");
        Assumptions.assumeTrue(as400Password != null && !as400Password.isEmpty(),
                "as400.password deve essere configurato");

        // Setup ambiente AS400
        System.setProperty("app.env", "production");

        DataSource dataSource = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Ottieni DataSource
            dataSource = dataSourceConfig.getDataSource();
            assertNotNull(dataSource, "DataSource AS400 non deve essere null");

            // Test connessione
            connection = dataSource.getConnection();
            assertNotNull(connection, "Connection AS400 non deve essere null");
            assertFalse(connection.isClosed(), "La connessione deve essere aperta");

            // Verifica metadata database
            DatabaseMetaData metaData = connection.getMetaData();
            String dbProduct = metaData.getDatabaseProductName();
            String dbVersion = metaData.getDatabaseProductVersion();

            System.out.println("=== TEST AS400 CONNECTION ===");
            System.out.println("Database: " + dbProduct);
            System.out.println("Versione: " + dbVersion);
            System.out.println("URL: " + metaData.getURL());
            System.out.println("Driver: " + metaData.getDriverName() + " " + metaData.getDriverVersion());
            System.out.println("Utente: " + as400User);
            System.out.println("Host: " + as400Host);

            // Verifica che sia AS400/DB2 for i
            assertTrue(dbProduct.toLowerCase().contains("as") ||
                            dbProduct.toLowerCase().contains("db2"),
                    "Il database deve essere AS400/DB2, trovato: " + dbProduct);

            // Test query semplice
            statement = connection.createStatement();

            // Query compatibile AS400
            String query = "VALUES CURRENT_DATE";
            resultSet = statement.executeQuery(query);

            assertTrue(resultSet.next(), "La query deve restituire un risultato");
            assertNotNull(resultSet.getDate(1), "La data non deve essere null");

            System.out.println("Data AS400: " + resultSet.getDate(1));

            // Test librerie configurate
            String libraries = System.getProperty("as400.libraries", "N/A");
            System.out.println("Librerie configurate: " + libraries);

            System.out.println("✅ Test AS400 completato con successo");

        } catch (SQLException e) {
            fail("Errore durante il test AS400: " + e.getMessage() +
                    "\nVerifica che l'AS400 sia raggiungibile e le credenziali siano corrette");
        } finally {
            // Cleanup risorse
            if (resultSet != null) {
                try { resultSet.close(); } catch (SQLException e) { /* ignore */ }
            }
            if (statement != null) {
                try { statement.close(); } catch (SQLException e) { /* ignore */ }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) { /* ignore */ }
            }
        }
    }

    @Test
    @DisplayName("Test configurazione ambiente")
    @Tag("integration")
    public void testEnvironmentConfiguration() {
        // Test default environment
        String defaultEnv = System.getProperty("app.env", "development");
        assertNotNull(defaultEnv, "L'ambiente di default deve essere configurato");

        System.out.println("Ambiente corrente: " + defaultEnv);

        // Test che DataSourceConfig possa essere istanziato
        assertNotNull(dataSourceConfig, "DataSourceConfig deve essere istanziabile");
    }
}