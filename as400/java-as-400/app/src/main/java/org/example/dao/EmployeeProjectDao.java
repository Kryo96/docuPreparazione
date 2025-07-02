package org.example.dao;

import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class EmployeeProjectDao {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeProjectDao.class);

    @Inject
    private DataSource dataSource; // JNDI

    public List<Map<String, Object>> getAllEmployeeWithProject() throws SQLException {
        String sql = "SELECT E.FIRSTNME, E.LASTNAME, E.JOB, PA.PROJNAME"+
                "FROM EMPLOYEE E"+
                "INNER JOIN PROJECT PA ON E.EMPNO = PA.RESPEMP";
        return executeQuery(sql, Collections.emptyList());
    }

    public List<Map<String, Object>> getProjectsByEmployee(String empNo) throws SQLException {
        String sql = "SELECT P.PROJNO, P.PROJNAME, P.CUSTOMER " +
                "FROM PROJ P " +
                "INNER JOIN PROJACT PA ON P.PROJNO = PA.PROJNO " +
                "INNER JOIN EMP_PROJACT EPA ON PA.ACTNO = EPA.ACTNO " +
                "WHERE EPA.EMPNOEMPNO = ?";
        return executeQuery(sql, Collections.singletonList(empNo));
    }

    public List<Map<String, Object>> getProjectActivitiesByEmployee(String empNo) throws SQLException {
        String sql = "SELECT PA.ACTNO, PA.DESCRIPTION, EPA.HOURS " +
                "FROM PROJACT PA " +
                "INNER JOIN EMPPROJACT EPA ON PA.ACTNO = EPA.ACTNO " +
                "WHERE EPA.EMPNOEMPNO = ?";
        return executeQuery(sql, Collections.singletonList(empNo));
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
