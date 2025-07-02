package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class EmployeeDao {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeDao.class);
    private final String url = "jdbc:db2://localhost:50000/SAMPLE";
    private final String user = "db2inst1";
    private final String password = "test";

    public EmployeeDao() {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            logger.info("Driver JDBC DB2 caricato correttamente");
        } catch (ClassNotFoundException e) {
            logger.error("Driver JDBC non trovato", e);
            throw new RuntimeException("Driver JDBC non trovato", e);
        }
    }

    public List<Map<String, Object>> executeQuery(String sql, List<Object> parameters) throws SQLException {
        logger.info("Esecuzione query: {}", sql);
        if (parameters != null && !parameters.isEmpty()) {
            logger.debug("Con parametri: {}", parameters);
        } else {
            logger.debug("Nessun parametro passato alla query");
        }

        List<Map<String, Object>> resultList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            logger.info("Connessione al database stabilita");

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                // Imposto i parametri nel PreparedStatement
                if (parameters != null) {
                    for (int i = 0; i < parameters.size(); i++) {
                        pstmt.setObject(i + 1, parameters.get(i));
                        logger.debug("Parametro {} settato con valore {}", i + 1, parameters.get(i));
                    }
                }

                try (ResultSet rs = pstmt.executeQuery()) {
                    logger.info("Query eseguita con successo, recupero risultati");

                    ResultSetMetaData meta = rs.getMetaData();
                    int columnCount = meta.getColumnCount();

                    while (rs.next()) {
                        Map<String, Object> row = new HashMap<>();
                        for (int i = 1; i <= columnCount; i++) {
                            Object value = rs.getObject(i);
                            row.put(meta.getColumnLabel(i), value);
                        }
                        resultList.add(row);
                    }
                    logger.info("Numero di righe recuperate: {}", resultList.size());
                }
            }
        } catch (SQLException e) {
            logger.error("Errore durante l'esecuzione della query", e);
            throw e;
        }

        return resultList;
    }
}
