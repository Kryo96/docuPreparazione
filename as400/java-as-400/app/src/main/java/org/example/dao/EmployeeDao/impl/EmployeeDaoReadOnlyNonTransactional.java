package org.example.dao.EmployeeDao.impl;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.example.dao.EmployeeDao.EmployeeDao;
import org.example.qualifier.DaoProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@RequestScoped
@DaoProfile(role = DaoProfile.Role.READONLY, mode = DaoProfile.Mode.NON_TRANSACTIONAL)
public class EmployeeDaoReadOnlyNonTransactional extends EmployeeDao {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeDaoReadOnlyNonTransactional.class);

    @Inject
    private DataSource dataSource; // JNDI

    public List<Map<String, Object>> getEmployeesByEmpno(String empno) throws SQLException {
        String sql = "SELECT EMPNO, FIRSTNME, LASTNAME FROM EMPLOYEE WHERE EMPNO = ?";
        return executeQuery(sql, Collections.singletonList(empno));
    }

    public List<Map<String, Object>> getFirstFiveEmployees() throws SQLException {
        String sql = "SELECT EMPNO, FIRSTNME, LASTNAME FROM EMPLOYEE FETCH FIRST 5 ROWS ONLY";
        return executeQuery(sql, Collections.emptyList());
    }

    public List<Map<String, Object>> getEmployeesWithSalaryAbove(double minSalary) throws SQLException {
        String sql = "SELECT EMPNOEMPNO, FIRSTNME, LASTNAME, SALARY FROM EMPLOYEE WHERE SALARY > ?";
        return executeQuery(sql, Collections.singletonList(minSalary));
    }


    public List<Map<String, Object>> executeQuery(String sql, List<Object> parameters) throws SQLException {
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
