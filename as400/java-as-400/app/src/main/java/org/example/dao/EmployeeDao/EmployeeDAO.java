package org.example.dao.EmployeeDao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Default
@ApplicationScoped
public class EmployeeDAO implements EmployeeReadOperations, EmployeeWriteOperations {

    @Inject
    private DataSource dataSource;

    @Override
    public List<Map<String, Object>> findAll() {
        String sql = "SELECT * FROM EMPLOYEE";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return executeQuery(ps);
        }catch (SQLException e) {
            throw new RuntimeException("Error fetching all employees", e);
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

    @Override
    public List<Map<String, Object>> findByEmpNo(String empNo) {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> findByDeptNo(String deptNo) {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> findByLastName(String lastName) {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> findByFirstName(String firstName) {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> findByJob(String job) {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> findByLastNameLike(String pattern) {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> findBySalaryRange(double minSalary, double maxSalary) {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> findByHireDateRange(String startDate, String endDate) {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> findBySex(String sex) {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> findByEducationLevel(int edLevel) {
        return List.of();
    }

    @Override
    public int countAllEmployees() {
        return 0;
    }

    @Override
    public int countByDepartment(String deptNo) {
        return 0;
    }

    @Override
    public int countBySex(String sex) {
        return 0;
    }

    @Override
    public int countByJob(String job) {
        return 0;
    }

    @Override
    public List<String> getDistinctJobs() {
        return List.of();
    }

    @Override
    public List<String> getDistinctDepartments() {
        return List.of();
    }

    @Override
    public List<Integer> getDistinctEducationLevels() {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> findAllOrderByLastName() {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> findAllOrderBySalary() {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> findAllOrderByHireDate() {
        return List.of();
    }

    @Override
    public Map<String, Object> getSalaryStatistics() {
        return Map.of();
    }

    @Override
    public Map<String, Object> getEmployeeStatistics() {
        return Map.of();
    }

    @Override
    public List<Map<String, Object>> executeCustomSelect(String query) {
        return List.of();
    }

    @Override
    public int deleteEmployee(String empNo) {
        return 0;
    }

    @Override
    public int deleteEmployeesByDepartment(String deptNo) {
        return 0;
    }

    @Override
    public int[] batchInsertEmployees(List<Map<String, Object>> employees) {
        return new int[0];
    }

    @Override
    public int[] batchUpdateEmployees(List<Map<String, Object>> employees) {
        return new int[0];
    }

    @Override
    public int[] batchDeleteEmployees(List<String> empNos) {
        return new int[0];
    }

    @Override
    public int increaseSalaryByPercentage(String empNo, double percentage) {
        return 0;
    }

    @Override
    public int increaseSalaryByAmount(String empNo, double amount) {
        return 0;
    }

    @Override
    public int increaseDepartmentSalariesByPercentage(String deptNo, double percentage) {
        return 0;
    }

    @Override
    public int executeCustomUpdate(String query) {
        return 0;
    }

    @Override
    public int executeCustomUpdate(String query, Object... parameters) {
        return 0;
    }

    @Override
    public int insertEmployee(String empNo, String firstName, String midInit, String lastName, String workDept, String phoneNo, String hireDate, String job, String edLevel, String sex, String birthDate, String salary, String bonus, String comm) {
        return 0;
    }

    @Override
    public int insertEmployee(Map<String, Object> employeeData) {
        return 0;
    }

    @Override
    public int updateFirstName(String empNo, String firstName) {
        return 0;
    }

    @Override
    public int updateLastName(String empNo, String lastName) {
        return 0;
    }

    @Override
    public int updateDepartment(String empNo, String newDeptNo) {
        return 0;
    }

    @Override
    public int updateJob(String empNo, String newJob) {
        return 0;
    }

    @Override
    public int updateSalary(String empNo, double newSalary) {
        return 0;
    }

    @Override
    public int updatePhone(String empNo, String newPhone) {
        return 0;
    }

    @Override
    public int updateEmployee(String empNo, Map<String, Object> updateData) {
        return 0;
    }
}
