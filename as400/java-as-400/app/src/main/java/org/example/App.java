package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {

    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        // Stampa il saluto
        System.out.println(new App().getGreeting());

        // Dettagli della connessione al database
        String url = "jdbc:db2://localhost:50000/SAMPLE";  // URL del database SAMPLE
        String user = "db2inst1";  // Nome utente DB2
        String password = "test";  // Password utente DB2

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Carica il driver JDBC per DB2
            Class.forName("com.ibm.db2.jcc.DB2Driver");

            // Crea una connessione al database
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connessione al database riuscita.");

            // Esegui una query di test per verificare che la connessione funzioni
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT 1 FROM SYSIBM.SYSDUMMY1");

            // Verifica che la query restituisca un risultato valido
            if (resultSet.next()) {
                System.out.println("La connessione è riuscita! ResultSet: " + resultSet.getInt(1));
            }

            // Esegui una query sulla tabella EMPLOYEE per ottenere informazioni sui dipendenti
            System.out.println("\n=== Esegui la query sulla tabella EMPLOYEE ===");
            resultSet = statement.executeQuery("SELECT EMPNO, FIRSTNME, LASTNAME FROM EMPLOYEE FETCH FIRST 5 ROWS ONLY");

            // Stampa i risultati della query EMPLOYEE
            System.out.println("EMPNO | FIRSTNME | LASTNAME");
            while (resultSet.next()) {
                int empno = resultSet.getInt("EMPNO");
                String firstName = resultSet.getString("FIRSTNME");
                String lastName = resultSet.getString("LASTNAME");
                System.out.println(empno + " | " + firstName + " | " + lastName);
            }

            // Esegui una seconda query per contare il numero di dipendenti
            System.out.println("\n=== Esegui la query per contare i dipendenti ===");
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM EMPLOYEE");
            if (resultSet.next()) {
                int employeeCount = resultSet.getInt(1);
                System.out.println("Numero totale di dipendenti: " + employeeCount);
            }

        } catch (SQLException e) {
            System.err.println("Errore durante la connessione al database: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC non trovato: " + e.getMessage());
        } finally {
            // Chiudi la connessione e le risorse se sono state aperte
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
