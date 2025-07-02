package org.example;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import javax.sql.DataSource;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
public class EmployeeDao {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeDao.class);

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

    public List<Map<String, Object>> getEmployeeWithDepartment(String empNo) throws SQLException {
        String sql = "SELECT E.EMPNOEMPNO, E.FIRSTNME, E.LASTNAME, D.DEPTNAME, D.LOCATION " +
                "FROM EMPLOYEE E " +
                "LEFT JOIN DEPARTMENT D ON E.WORKDEPT = D.DEPTNO " +
                "WHERE E.EMPNOEMPNO = ?";
        return executeQuery(sql, Collections.singletonList(empNo));
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

    public List<Map<String, Object>> getEmployeesWithSalaryAbove(double minSalary) throws SQLException {
        String sql = "SELECT EMPNOEMPNO, FIRSTNME, LASTNAME, SALARY FROM EMPLOYEE WHERE SALARY > ?";
        return executeQuery(sql, Collections.singletonList(minSalary));
    }

    public int insertEmployee(Map<String, Object> empData) throws SQLException {
        String sql = "INSERT INTO EMPLOYEE (EMPNOEMPNO, FIRSTNME, MIDINIT, LASTNAME, WORKDEPT, PHONENO, HIREDATE, JOB, EDLEVEL, SEX, BIRTHDATE, SALARY, BONUS, COMM) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, (String) empData.get("EMPNOEMPNO"));
            pstmt.setString(2, (String) empData.get("FIRSTNME"));
            pstmt.setString(3, (String) empData.get("MIDINIT"));
            pstmt.setString(4, (String) empData.get("LASTNAME"));
            pstmt.setString(5, (String) empData.get("WORKDEPT"));
            pstmt.setString(6, (String) empData.get("PHONENO"));
            pstmt.setDate(7, (Date) empData.get("HIREDATE"));
            pstmt.setString(8, (String) empData.get("JOB"));
            pstmt.setShort(9, (Short) empData.get("EDLEVEL"));
            pstmt.setString(10, (String) empData.get("SEX"));
            pstmt.setDate(11, (Date) empData.get("BIRTHDATE"));
            pstmt.setDouble(12, (Double) empData.get("SALARY"));
            pstmt.setDouble(13, (Double) empData.get("BONUS"));
            pstmt.setDouble(14, (Double) empData.get("COMM"));

            return pstmt.executeUpdate();
        }
    }

    public int updateSalaryAndBonus(String empNo, double salary, double bonus) throws SQLException {
        String sql = "UPDATE EMPLOYEE SET SALARY = ?, BONUS = ? WHERE EMPNOEMPNO = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, salary);
            pstmt.setDouble(2, bonus);
            pstmt.setString(3, empNo);

            return pstmt.executeUpdate();
        }
    }

    public int deleteEmployee(String empNo) throws SQLException {
        String sql = "DELETE FROM EMPLOYEE WHERE EMPNOEMPNO = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, empNo);
            return pstmt.executeUpdate();
        }
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
