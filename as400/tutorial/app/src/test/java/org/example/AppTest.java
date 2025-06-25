package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import com.google.common.collect.ImmutableList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AppTest {
    @Test
    public void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull("app should have a greeting", classUnderTest.getGreeting());
    }

    @Test
    public void guavaShouldWork() {
        ImmutableList<String> list = ImmutableList.of("A", "B", "C");
        assertEquals(3, list.size());
    }

    @Test
    public void db2ConnectionTest() {
        String url = "jdbc:db2://localhost:50000/SAMPLE";
        String username = "db2inst1";
        String password = "test";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            assertNotNull("La connessione non dovrebbe essere null", conn);
            assertTrue("La connessione al database non è valida", conn.isValid(2));

            // Test basico: esegue una query dummy, ad esempio sul catalogo
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT CURRENT DATE FROM SYSIBM.SYSDUMMY1");

            assertTrue("La query non ha restituito righe", rs.next());
            System.out.println("Connessione a DB2 riuscita, data corrente: " + rs.getDate(1));

        } catch (Exception e) {
            fail("Errore nella connessione a DB2: " + e.getMessage());
        }
    }

    @Test
    public void selectFromEmployeeShouldReturnResults() {
        String url = "jdbc:db2://localhost:50000/SAMPLE";
        String username = "db2inst1";
        String password = "test";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EMPNO, FIRSTNME, LASTNAME FROM EMPLOYEE");

            assertTrue("La query su EMPLOYEE non ha restituito righe", rs.next());
            String empNo = rs.getString("EMPNO");
            String firstName = rs.getString("FIRSTNME");
            String lastName = rs.getString("LASTNAME");

            assertNotNull("EMPNO non dovrebbe essere null", empNo);
            assertNotNull("FIRSTNME non dovrebbe essere null", firstName);
            assertNotNull("LASTNAME non dovrebbe essere null", lastName);

            System.out.println("Dipendente: " + empNo + " - " + firstName + " " + lastName);

        } catch (Exception e) {
            fail("Errore nella SELECT da EMPLOYEE: " + e.getMessage());
        }
    }

    @Test
    public void createAndQueryTemporaryTable() {
        String url = "jdbc:db2://localhost:50000/SAMPLE";
        String username = "db2inst1";
        String password = "test";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement stmt = conn.createStatement();

            // Pulisci se già esiste
            try {
                stmt.executeUpdate("DROP TABLE TEST_USERS");
            } catch (Exception ignored) {
            }

            // Crea tabella e inserisci dati
            stmt.executeUpdate("CREATE TABLE TEST_USERS (ID INT, NAME VARCHAR(100))");
            stmt.executeUpdate("INSERT INTO TEST_USERS (ID, NAME) VALUES (1, 'Alice'), (2, 'Bob')");

            ResultSet rs = stmt.executeQuery("SELECT ID, NAME FROM TEST_USERS ORDER BY ID");

            int count = 0;
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                assertNotNull(name);
                count++;
                System.out.println("User " + id + ": " + name);
            }

            assertEquals("Numero di utenti inatteso", 2, count);

        } catch (Exception e) {
            fail("Errore nel test con tabella temporanea: " + e.getMessage());
        }
    }
}