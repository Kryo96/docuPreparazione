package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;
import java.util.*;

public class EmployeeDao {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeDao.class);

    private DataSource dataSource;

    public EmployeeDao() {
        try {
            InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:jboss/datasources/MyOrderDB"); // JNDI
            logger.info("DataSource ottenuto correttamente tramite JNDI");
        } catch (NamingException e) {
            logger.error("Impossibile ottenere il DataSource tramite JNDI", e);
            throw new RuntimeException("DataSource non trovato", e);
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

        try (Connection conn = dataSource.getConnection()) {
            logger.info("Connessione al database ottenuta dal DataSource");

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
                            row.put(meta.getColumnLabel(i), rs.getObject(i));
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
