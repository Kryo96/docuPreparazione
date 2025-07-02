package org.example.dao;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import javax.sql.DataSource;

import java.sql.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
public class ProductDao {
    private static final Logger logger = LoggerFactory.getLogger(ProductDao.class);

    @Inject
    private DataSource dataSource;

    public List<Map<String, Object>> getAllProducts() throws SQLException {
        String sql = "SELECT PID, NAME, PRICE FROM PRODUCT FETCH FIRST 10 ROWS ONLY;";
        return executeQuery(sql, Collections.emptyList());
    }

    private List<Map<String, Object>> executeQuery(String sql, List<Object> parameters) throws SQLException {
        logger.info("Esecuzione query: {}", sql);

        List<Map<String, Object>> resultList = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < parameters.size(); i++) {
                pstmt.setObject(i + 1, parameters.get(i));
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();

                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.put(meta.getColumnLabel(i), rs.getObject(i));
                    }
                    resultList.add(row);
                }
            }
        }
        return resultList;
    }
}
