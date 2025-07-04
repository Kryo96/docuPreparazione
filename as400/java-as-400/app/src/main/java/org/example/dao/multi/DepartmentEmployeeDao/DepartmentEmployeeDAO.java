package org.example.dao.multi.DepartmentEmployeeDao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.dao.multi.DepartmentEmployeeDao.DepartmentEmployeeReadOperations;
import org.example.dao.multi.DepartmentEmployeeDao.DepartmentEmployeeWriteOperations;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@ApplicationScoped
public class DepartmentEmployeeDAO implements DepartmentEmployeeReadOperations, DepartmentEmployeeWriteOperations {

    @Inject
    private DataSource dataSource;

    // === IMPLEMENTAZIONE METODI DI LETTURA ===

    @Override
    public List<Map<String, Object>> getDepartmentSummaryReport() {
        String sql =
            "SELECT " +
                "d.DEPTNO, " +
                "d.DEPTNAME, " +
                "d.LOCATION, " +
                "d.MGRNO, " +
                "COUNT(e.EMPNO) AS TOTAL_EMPLOYEES, " +
                "COALESCE(AVG(e.SALARY), 0) AS AVG_SALARY, " +
                "COALESCE(SUM(e.SALARY), 0) AS TOTAL_SALARY_COST, " +
                "COALESCE(MAX(e.SALARY), 0) AS MAX_SALARY, " +
                "COALESCE(MIN(e.SALARY), 0) AS MIN_SALARY " +
                "FROM DEPARTMENT d " +
                "LEFT JOIN EMPLOYEE e ON d.DEPTNO = e.WORKDEPT " +
                "GROUP BY d.DEPTNO, d.DEPTNAME, d.LOCATION, d.MGRNO " +
                "ORDER BY TOTAL_EMPLOYEES DESC, d.DEPTNAME";

        return executeQuery(sql);
    }

    @Override
    public List<Map<String, Object>> getDepartmentDetailedReport(String deptNo) {
        String sql =
            "SELECT " +
                "d.DEPTNO, " +
                "d.DEPTNAME, " +
                "d.LOCATION, " +
                "d.MGRNO, " +
                "e.EMPNO, " +
                "e.FIRSTNME, " +
                "e.LASTNAME, " +
                "e.JOB, " +
                "e.SALARY, " +
                "e.HIREDATE, " +
                "e.SEX, " +
                "e.EDLEVEL, " +
                "CASE " +
                "    WHEN e.EMPNO = d.MGRNO THEN 'MANAGER' " +
                "    ELSE 'EMPLOYEE' " +
                "END AS ROLE_IN_DEPT " +
                "FROM DEPARTMENT d " +
                "LEFT JOIN EMPLOYEE e ON d.DEPTNO = e.WORKDEPT " +
                "WHERE d.DEPTNO = ? " +
                "ORDER BY " +
                "CASE WHEN e.EMPNO = d.MGRNO THEN 0 ELSE 1 END, " +
                "e.SALARY DESC, " +
                "e.LASTNAME, " +
                "e.FIRSTNME";


        return executeQueryWithParams(sql, deptNo);
    }

    @Override
    public List<Map<String, Object>> getEmployeesWithDepartmentInfo() {
        String sql =
            "SELECT " +
                "e.EMPNO, " +
                "e.FIRSTNME, " +
                "e.LASTNAME, " +
                "e.JOB, " +
                "e.SALARY, " +
                "e.HIREDATE, " +
                "e.SEX, " +
                "e.EDLEVEL, " +
                "d.DEPTNO, " +
                "d.DEPTNAME, " +
                "d.LOCATION, " +
                "CASE " +
                "    WHEN e.EMPNO = d.MGRNO THEN 'YES' " +
                "    ELSE 'NO' " +
                "END AS IS_MANAGER " +
                "FROM EMPLOYEE e " +
                "INNER JOIN DEPARTMENT d ON e.WORKDEPT = d.DEPTNO " +
                "ORDER BY d.DEPTNAME, e.SALARY DESC, e.LASTNAME";

        return executeQuery(sql);
    }

    @Override
    public Map<String, Object> getOrganizationOverview() {
        String sql =
            "SELECT " +
                "COUNT(DISTINCT d.DEPTNO) AS TOTAL_DEPARTMENTS, " +
                "COUNT(DISTINCT e.EMPNO) AS TOTAL_EMPLOYEES, " +
                "COUNT(DISTINCT d.LOCATION) AS TOTAL_LOCATIONS, " +
                "COALESCE(AVG(e.SALARY), 0) AS COMPANY_AVG_SALARY, " +
                "COALESCE(SUM(e.SALARY), 0) AS TOTAL_PAYROLL, " +
                "COUNT(DISTINCT e.JOB) AS UNIQUE_JOB_TITLES, " +
                "COUNT(CASE WHEN e.SEX = 'M' THEN 1 END) AS MALE_EMPLOYEES, " +
                "COUNT(CASE WHEN e.SEX = 'F' THEN 1 END) AS FEMALE_EMPLOYEES, " +
                "COALESCE(AVG(emp_per_dept.emp_count), 0) AS AVG_EMPLOYEES_PER_DEPT " +
                "FROM DEPARTMENT d " +
                "LEFT JOIN EMPLOYEE e ON d.DEPTNO = e.WORKDEPT " +
                "LEFT JOIN ( " +
                "    SELECT WORKDEPT, COUNT(*) AS emp_count " +
                "    FROM EMPLOYEE " +
                "    GROUP BY WORKDEPT " +
                ") emp_per_dept ON d.DEPTNO = emp_per_dept.WORKDEPT";

        List<Map<String, Object>> results = executeQuery(sql);
        return results.isEmpty() ? new HashMap<>() : results.get(0);
    }

    @Override
    public List<Map<String, Object>> getSalaryStatisticsByDepartment() {
        String sql =
            "SELECT " +
                "d.DEPTNO, " +
                "d.DEPTNAME, " +
                "COUNT(e.EMPNO) AS EMPLOYEE_COUNT, " +
                "COALESCE(AVG(e.SALARY), 0) AS AVG_SALARY, " +
                "COALESCE(STDDEV(e.SALARY), 0) AS SALARY_STDDEV, " +
                "COALESCE(MAX(e.SALARY), 0) AS MAX_SALARY, " +
                "COALESCE(MIN(e.SALARY), 0) AS MIN_SALARY, " +
                "COALESCE(SUM(e.SALARY), 0) AS TOTAL_SALARY, " +
                "COALESCE(PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY e.SALARY), 0) AS MEDIAN_SALARY " +
                "FROM DEPARTMENT d " +
                "LEFT JOIN EMPLOYEE e ON d.DEPTNO = e.WORKDEPT " +
                "GROUP BY d.DEPTNO, d.DEPTNAME " +
                "HAVING COUNT(e.EMPNO) > 0 " +
                "ORDER BY AVG_SALARY DESC";

        return executeQuery(sql);
    }

    @Override
    public List<Map<String, Object>> getTopEarnersByDepartment(int limit) {
        String sql =
            "SELECT " +
                "d.DEPTNO, " +
                "d.DEPTNAME, " +
                "e.EMPNO, " +
                "e.FIRSTNME, " +
                "e.LASTNAME, " +
                "e.JOB, " +
                "e.SALARY, " +
                "RANK() OVER (PARTITION BY d.DEPTNO ORDER BY e.SALARY DESC) AS SALARY_RANK_IN_DEPT, " +
                "RANK() OVER (ORDER BY e.SALARY DESC) AS COMPANY_SALARY_RANK " +
                "FROM DEPARTMENT d " +
                "INNER JOIN EMPLOYEE e ON d.DEPTNO = e.WORKDEPT " +
                "QUALIFY SALARY_RANK_IN_DEPT <= ? " +
                "ORDER BY d.DEPTNO, e.SALARY DESC";


        return executeQueryWithParams(sql, limit);
    }

    @Override
    public Map<String, Object> getDashboardMetrics() {
        String sql =
            "WITH dept_stats AS ( " +
                "    SELECT " +
                "        d.DEPTNO, " +
                "        COUNT(e.EMPNO) AS emp_count, " +
                "        AVG(e.SALARY) AS avg_sal " +
                "    FROM DEPARTMENT d " +
                "    LEFT JOIN EMPLOYEE e ON d.DEPTNO = e.WORKDEPT " +
                "    GROUP BY d.DEPTNO " +
                ") " +
                "SELECT " +
                "    COUNT(DISTINCT d.DEPTNO) AS total_departments, " +
                "    COUNT(DISTINCT e.EMPNO) AS total_employees, " +
                "    AVG(e.SALARY) AS avg_company_salary, " +
                "    MAX(ds.emp_count) AS largest_dept_size, " +
                "    MIN(CASE WHEN ds.emp_count > 0 THEN ds.emp_count END) AS smallest_dept_size, " +
                "    COUNT(DISTINCT d.LOCATION) AS total_locations, " +
                "    SUM(e.SALARY + COALESCE(e.BONUS, 0) + COALESCE(e.COMM, 0)) AS total_compensation " +
                "FROM DEPARTMENT d " +
                "LEFT JOIN EMPLOYEE e ON d.DEPTNO = e.WORKDEPT " +
                "LEFT JOIN dept_stats ds ON d.DEPTNO = ds.DEPTNO";


        List<Map<String, Object>> results = executeQuery(sql);
        return results.isEmpty() ? new HashMap<>() : results.get(0);
    }

    @Override
    public List<Map<String, Object>> getEmployeesByLocation(String location) {
        String sql =
            "SELECT " +
                "e.EMPNO, " +
                "e.FIRSTNME, " +
                "e.LASTNAME, " +
                "e.JOB, " +
                "e.SALARY, " +
                "d.DEPTNO, " +
                "d.DEPTNAME, " +
                "d.LOCATION " +
                "FROM EMPLOYEE e " +
                "INNER JOIN DEPARTMENT d ON e.WORKDEPT = d.DEPTNO " +
                "WHERE d.LOCATION = ? " +
                "ORDER BY d.DEPTNAME, e.SALARY DESC";

        return executeQueryWithParams(sql, location);
    }

        // === IMPLEMENTAZIONE METODI DI SCRITTURA ===

    @Override
    public int createDepartmentWithInitialStaff(Map<String, Object> deptData, List<Map<String, Object>> employeesData) {
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            try {
                // 1. Crea il dipartimento
                String deptSql = "INSERT INTO DEPARTMENT (DEPTNO, DEPTNAME, MGRNO, ADMRDEPT, LOCATION) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement deptPs = conn.prepareStatement(deptSql)) {
                    deptPs.setString(1, (String) deptData.get("DEPTNO"));
                    deptPs.setString(2, (String) deptData.get("DEPTNAME"));
                    deptPs.setString(3, (String) deptData.get("MGRNO"));
                    deptPs.setString(4, (String) deptData.get("ADMRDEPT"));
                    deptPs.setString(5, (String) deptData.get("LOCATION"));
                    deptPs.executeUpdate();
                }

                // 2. Aggiungi i dipendenti
                String empSql =
                        "INSERT INTO EMPLOYEE (EMPNO, FIRSTNME, MIDINIT, LASTNAME, WORKDEPT, PHONENO," +
                            "HIREDATE, JOB, EDLEVEL, SEX, BIRTHDATE, SALARY, BONUS, COMM)" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement empPs = conn.prepareStatement(empSql)) {
                    for (Map<String, Object> emp : employeesData) {
                        empPs.setString(1, (String) emp.get("EMPNO"));
                        empPs.setString(2, (String) emp.get("FIRSTNME"));
                        empPs.setString(3, (String) emp.get("MIDINIT"));
                        empPs.setString(4, (String) emp.get("LASTNAME"));
                        empPs.setString(5, (String) deptData.get("DEPTNO")); // Assegna al nuovo dipartimento
                        empPs.setString(6, (String) emp.get("PHONENO"));
                        empPs.setString(7, (String) emp.get("HIREDATE"));
                        empPs.setString(8, (String) emp.get("JOB"));
                        empPs.setString(9, (String) emp.get("EDLEVEL"));
                        empPs.setString(10, (String) emp.get("SEX"));
                        empPs.setString(11, (String) emp.get("BIRTHDATE"));
                        empPs.setString(12, (String) emp.get("SALARY"));
                        empPs.setString(13, (String) emp.get("BONUS"));
                        empPs.setString(14, (String) emp.get("COMM"));
                        empPs.addBatch();
                    }
                    empPs.executeBatch();
                }

                conn.commit();
                return 1 + employeesData.size(); // Dipartimento + dipendenti creati

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating department with staff", e);
        }
    }

    @Override
    public int transferAllEmployees(String fromDeptNo, String toDeptNo) {
        String sql = "UPDATE EMPLOYEE SET WORKDEPT = ? WHERE WORKDEPT = ?";
        return executeUpdate(sql, toDeptNo, fromDeptNo);
    }

    @Override
    public int disbandDepartmentSafely(String deptNo, String targetDeptNo) {
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            try {
                // 1. Sposta tutti i dipendenti
                String moveEmp = "UPDATE EMPLOYEE SET WORKDEPT = ? WHERE WORKDEPT = ?";
                int movedEmployees;
                try (PreparedStatement ps1 = conn.prepareStatement(moveEmp)) {
                    ps1.setString(1, targetDeptNo);
                    ps1.setString(2, deptNo);
                    movedEmployees = ps1.executeUpdate();
                }

                // 2. Elimina il dipartimento
                String deleteDept = "DELETE FROM DEPARTMENT WHERE DEPTNO = ?";
                try (PreparedStatement ps2 = conn.prepareStatement(deleteDept)) {
                    ps2.setString(1, deptNo);
                    ps2.executeUpdate();
                }

                conn.commit();
                return movedEmployees;

            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error disbanding department", e);
        }
    }

    // === METODI HELPER ===

    private List<Map<String, Object>> executeQuery(String sql) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return executeQuery(ps);
        } catch (SQLException e) {
            throw new RuntimeException("Error executing query", e);
        }
    }

    private List<Map<String, Object>> executeQueryWithParams(String sql, Object... params) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            return executeQuery(ps);
        } catch (SQLException e) {
            throw new RuntimeException("Error executing parameterized query", e);
        }
    }

    private int executeUpdate(String sql, Object... params) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error executing update", e);
        }
    }

    private List<Map<String, Object>> executeQuery(PreparedStatement ps) throws SQLException {
        try (ResultSet rs = ps.executeQuery()) {
            List<Map<String, Object>> results = new ArrayList<>();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(i);
                    row.put(columnName, value);
                }
                results.add(row);
            }
            return results;
        }
    }

    // Implementazione stub per gli altri metodi...
    @Override public List<Map<String, Object>> getDepartmentsWithoutEmployees() { return executeQuery("SELECT d.* FROM DEPARTMENT d LEFT JOIN EMPLOYEE e ON d.DEPTNO = e.WORKDEPT WHERE e.EMPNO IS NULL"); }
    @Override public List<Map<String, Object>> getEmployeesByDepartmentName(String deptName) { return executeQueryWithParams("SELECT e.*, d.* FROM EMPLOYEE e JOIN DEPARTMENT d ON e.WORKDEPT = d.DEPTNO WHERE d.DEPTNAME LIKE ?", "%" + deptName + "%"); }
    @Override public List<Map<String, Object>> findEmployeesByLocationAndJob(String location, String job) { return executeQueryWithParams("SELECT e.*, d.* FROM EMPLOYEE e JOIN DEPARTMENT d ON e.WORKDEPT = d.DEPTNO WHERE d.LOCATION = ? AND e.JOB = ?", location, job); }
    @Override public List<Map<String, Object>> findHighEarnersInDepartment(String deptNo, double minSalary) { return executeQueryWithParams("SELECT e.*, d.* FROM EMPLOYEE e JOIN DEPARTMENT d ON e.WORKDEPT = d.DEPTNO WHERE d.DEPTNO = ? AND e.SALARY >= ?", deptNo, minSalary); }
    @Override public List<Map<String, Object>> findRecentHiresByDepartment(String deptNo, String fromDate) { return executeQueryWithParams("SELECT e.*, d.* FROM EMPLOYEE e JOIN DEPARTMENT d ON e.WORKDEPT = d.DEPTNO WHERE d.DEPTNO = ? AND e.HIREDATE >= ?", deptNo, fromDate); }
    @Override public List<Map<String, Object>> getEmployeeDistributionByLocation() { return executeQuery("SELECT d.LOCATION, COUNT(e.EMPNO) as EMP_COUNT FROM DEPARTMENT d LEFT JOIN EMPLOYEE e ON d.DEPTNO = e.WORKDEPT GROUP BY d.LOCATION"); }
    @Override public List<Map<String, Object>> getGenderDistributionByDepartment() { return executeQuery("SELECT d.DEPTNO, d.DEPTNAME, COUNT(CASE WHEN e.SEX='M' THEN 1 END) as MALE_COUNT, COUNT(CASE WHEN e.SEX='F' THEN 1 END) as FEMALE_COUNT FROM DEPARTMENT d LEFT JOIN EMPLOYEE e ON d.DEPTNO = e.WORKDEPT GROUP BY d.DEPTNO, d.DEPTNAME"); }

    @Override
    public List<Map<String, Object>> getLargestDepartments(int limit) {
        String sql =
            "SELECT d.DEPTNO, " +
                "d.DEPTNAME, " +
                "d.ADMRDEPT, " +
                "d.LOCATION, " +
                "COUNT(e.EMPNO) as EMPLOYEE_COUNT " +
                "FROM DEPARTMENT d " +
                "LEFT JOIN EMPLOYEE e ON d.DEPTNO = e.WORKDEPT " +
                "GROUP BY d.DEPTNO, d.DEPTNAME, d.ADMRDEPT, d.LOCATION " +
                "ORDER BY COUNT(e.EMPNO) DESC " +
                "FETCH FIRST ? ROWS ONLY";

        return executeQueryWithParams(sql, limit);
    }

    @Override public List<Map<String, Object>> getOldestEmployeesByDepartment() { return executeQuery("SELECT d.DEPTNO, d.DEPTNAME, e.EMPNO, e.FIRSTNME, e.LASTNAME, e.BIRTHDATE FROM DEPARTMENT d JOIN EMPLOYEE e ON d.DEPTNO = e.WORKDEPT WHERE e.BIRTHDATE = (SELECT MIN(e2.BIRTHDATE) FROM EMPLOYEE e2 WHERE e2.WORKDEPT = d.DEPTNO)"); }
    @Override public List<Map<String, Object>> getNewestEmployeesByDepartment() { return executeQuery("SELECT d.DEPTNO, d.DEPTNAME, e.EMPNO, e.FIRSTNME, e.LASTNAME, e.HIREDATE FROM DEPARTMENT d JOIN EMPLOYEE e ON d.DEPTNO = e.WORKDEPT WHERE e.HIREDATE = (SELECT MAX(e2.HIREDATE) FROM EMPLOYEE e2 WHERE e2.WORKDEPT = d.DEPTNO)"); }
    @Override public List<Map<String, Object>> getDepartmentPerformanceMetrics() { return executeQuery("SELECT d.DEPTNO, d.DEPTNAME, COUNT(e.EMPNO) as TOTAL_EMP, AVG(e.SALARY) as AVG_SAL, SUM(e.SALARY) as TOTAL_COST FROM DEPARTMENT d LEFT JOIN EMPLOYEE e ON d.DEPTNO = e.WORKDEPT GROUP BY d.DEPTNO, d.DEPTNAME"); }
    @Override public List<Map<String, Object>> getMonthlyHiringTrends() { return executeQuery("SELECT EXTRACT(YEAR FROM e.HIREDATE) as HIRE_YEAR, EXTRACT(MONTH FROM e.HIREDATE) as HIRE_MONTH, COUNT(*) as HIRES FROM EMPLOYEE e GROUP BY HIRE_YEAR, HIRE_MONTH ORDER BY HIRE_YEAR, HIRE_MONTH"); }
    @Override public List<Map<String, Object>> getSalaryTrendsByDepartment() { return executeQuery("SELECT d.DEPTNO, d.DEPTNAME, AVG(e.SALARY) as AVG_SALARY, STDDEV(e.SALARY) as SALARY_VARIANCE FROM DEPARTMENT d JOIN EMPLOYEE e ON d.DEPTNO = e.WORKDEPT GROUP BY d.DEPTNO, d.DEPTNAME"); }
    @Override public List<Map<String, Object>> getManagerEfficiencyReport() { return executeQuery("SELECT d.DEPTNO, d.DEPTNAME, d.MGRNO, COUNT(e.EMPNO) as TEAM_SIZE, AVG(e.SALARY) as TEAM_AVG_SALARY FROM DEPARTMENT d LEFT JOIN EMPLOYEE e ON d.DEPTNO = e.WORKDEPT GROUP BY d.DEPTNO, d.DEPTNAME, d.MGRNO"); }
    @Override public List<Map<String, Object>> getDepartmentCostAnalysis() { return executeQuery("SELECT d.DEPTNO, d.DEPTNAME, SUM(e.SALARY + COALESCE(e.BONUS,0) + COALESCE(e.COMM,0)) as TOTAL_COST FROM DEPARTMENT d LEFT JOIN EMPLOYEE e ON d.DEPTNO = e.WORKDEPT GROUP BY d.DEPTNO, d.DEPTNAME"); }
    @Override public List<Map<String, Object>> getEmployeeTurnoverAnalysis() { return executeQuery("SELECT d.DEPTNO, d.DEPTNAME, COUNT(e.EMPNO) as CURRENT_COUNT FROM DEPARTMENT d LEFT JOIN EMPLOYEE e ON d.DEPTNO = e.WORKDEPT GROUP BY d.DEPTNO, d.DEPTNAME"); }
    @Override public List<Map<String, Object>> getWorkforceDemographics() { return executeQuery("SELECT d.LOCATION, AVG(e.EDLEVEL) as AVG_EDUCATION, COUNT(CASE WHEN e.SEX='M' THEN 1 END) as MALE, COUNT(CASE WHEN e.SEX='F' THEN 1 END) as FEMALE FROM DEPARTMENT d LEFT JOIN EMPLOYEE e ON d.DEPTNO = e.WORKDEPT GROUP BY d.LOCATION"); }
    @Override public List<Map<String, Object>> executeJoinQuery(String customQuery) { return executeQuery(customQuery); }
    @Override public int mergeGepartments(String dept1, String dept2, String newDeptNo, Map<String, Object> newDeptData) { return 0; }
    @Override public int splitDepartment(String originalDept, Map<String, Object> newDept1Data, Map<String, Object> newDept2Data, List<String> employeesForNewDept) { return 0; }
    @Override public int bulkUpdateEmployeeDepartments(Map<String, String> employeeDeptMapping) { return 0; }
    @Override public int bulkSalaryAdjustmentByDepartment(String deptNo, double adjustmentPercentage) { return executeUpdate("UPDATE EMPLOYEE SET SALARY = SALARY * (1 + ? / 100) WHERE WORKDEPT = ?", adjustmentPercentage, deptNo); }
    @Override public int bulkPromoteEmployees(List<String> empNos, String newJob, double salaryIncrease) { return 0; }
    @Override public int assignNewManager(String deptNo, String newManagerEmpNo) { return executeUpdate("UPDATE DEPARTMENT SET MGRNO = ? WHERE DEPTNO = ?", newManagerEmpNo, deptNo); }
    @Override public int rotateManagers(Map<String, String> deptManagerMapping) { return 0; }
}
