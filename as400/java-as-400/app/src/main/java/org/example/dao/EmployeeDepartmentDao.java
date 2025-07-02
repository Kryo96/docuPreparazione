package org.example.dao;

import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class EmployeeDepartmentDao {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeDepartmentDao.class);

    @Inject
    private DataSource dataSource; // JNDI

    public List<Map<String, Object>> getEmployeeWithDepartment(String empNo) throws SQLException {
        String sql = "SELECT E.EMPNO, E.FIRSTNME, E.LASTNAME, D.DEPTNAME, D.LOCATION " +
                "FROM EMPLOYEE E " +
                "LEFT JOIN DEPARTMENT D ON E.WORKDEPT = D.DEPTNO " +
                "WHERE E.EMPNO = ?";
        return executeQuery(sql, Collections.singletonList(empNo));
    }

    public List<Map<String, Object>> getAllEmployeeWithDepartment() throws SQLException {
        String sql = "SELECT E.EMPNO, E.FIRSTNME, LASTNAME, D.DEPTNAME"+
                "FROM EMPLOYEE E"+
                "LEFT JOIN DEPARTMENT D ON D.MGRNO = E.EMPNO";
        return executeQuery(sql, Collections.emptyList());
    }


    public List<Map<String, Object>> getEmployeesWithDeptName(String deptNo) throws SQLException {
        String sql = "SELECT E.EMPNOEMPNO, E.FIRSTNME, E.LASTNAME, D.DEPTNAME " +
                "FROM EMPLOYEE E " +
                "INNER JOIN DEPARTMENT D ON E.WORKDEPT = D.DEPTNO " +
                "WHERE E.WORKDEPT = ?";
        return executeQuery(sql, Collections.singletonList(deptNo));
    }

    public List<Map<String, Object>> getEmployeesByDept(String deptNo) throws SQLException {
        String sql = "SELECT EMPNOEMPNO, FIRSTNME, LASTNAME, WORKDEPT, SALARY FROM EMPLOYEE WHERE WORKDEPT = ?";
        return executeQuery(sql, Collections.singletonList(deptNo));
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
