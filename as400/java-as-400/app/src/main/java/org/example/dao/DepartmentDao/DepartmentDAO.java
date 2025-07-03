package org.example.dao.DepartmentDao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Default
@ApplicationScoped
public class DepartmentDAO implements DepartmentReadOperations, DepartmentWriteOperations {

    @Inject
    private DataSource dataSource;

    @Override
    public List<String> getAllDeptNumbers() {
        String sql = "SELECT DEPTNO FROM DEPARTMENT";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<String> deptNumbers = new ArrayList<>();
            while (rs.next()) {
                deptNumbers.add(rs.getString("DEPTNO"));
            }
            return deptNumbers;
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching department numbers", e);
        }
    }

    @Override
    public List<String> getAllDeptNames() {
        return List.of();
    }

    @Override
    public List<String> getAllLocations() {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> findByDeptNo(String deptNo) {
        String sql = "SELECT * FROM DEPARTMENT WHERE DEPTNO = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, deptNo);
            return executeQuery(ps);
        } catch (SQLException e) {
            throw new RuntimeException("Error finding department by number", e);
        }
    }

    @Override
    public List<Map<String, Object>> findByDeptName(String deptName) {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> findByManager(String mgrNo) {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> findByLocation(String location) {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> findByDeptNameLike(String pattern) {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> findByLocationLike(String pattern) {
        return List.of();
    }

    @Override
    public int countAllDepartments() {
        return 0;
    }

    @Override
    public Map<String, Integer> countByLocation() {
        return Map.of();
    }

    @Override
    public Map<String, Integer> countByManager() {
        return Map.of();
    }

    @Override
    public List<String> getDistinctLocations() {
        return List.of();
    }

    @Override
    public List<String> getDistinctManagers() {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> findAllOrderByDeptNo() {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> findAllOrderByDeptName() {
        return List.of();
    }

    @Override
    public List<Map<String, Object>> findAll() {
        String sql = "SELECT * FROM DEPARTMENT";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            return executeQuery(ps);
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all departments", e);
        }
    }

    @Override
    public List<Map<String, Object>> executeCustomSelect(String query) {
        return List.of();
    }

    // Implementazione metodi di scrittura
    @Override
    public int insertDepartment(String deptNo, String deptName, String mgrNo, String admrDept, String location) {
        String sql = "INSERT INTO DEPARTMENT (DEPTNO, DEPTNAME, MGRNO, ADMRDEPT, LOCATION) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, deptNo);
            ps.setString(2, deptName);
            ps.setString(3, mgrNo);
            ps.setString(4, admrDept);
            ps.setString(5, location);

            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting department", e);
        }
    }

    @Override
    public int insertDepartment(Map<String, Object> departmentData) {
        return 0;
    }

    @Override
    public int updateDepartmentName(String deptNo, String newDeptName) {
        String sql = "UPDATE DEPARTMENT SET DEPTNAME = ? WHERE DEPTNO = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newDeptName);
            ps.setString(2, deptNo);

            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating department name", e);
        }
    }

    @Override
    public int updateDepartmentManager(String deptNo, String newMgrNo) {
        return 0;
    }

    @Override
    public int updateDepartmentLocation(String deptNo, String newLocation) {
        return 0;
    }

    @Override
    public int updateDepartment(String deptNo, Map<String, Object> updateData) {
        return 0;
    }

    @Override
    public int deleteDepartment(String deptNo) {
        String sql = "DELETE FROM DEPARTMENT WHERE DEPTNO = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, deptNo);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting department", e);
        }
    }

    @Override
    public int deleteDepartmentsByLocation(String location) {
        return 0;
    }

    @Override
    public int deleteDepartmentsByManager(String mgrNo) {
        return 0;
    }

    @Override
    public int[] batchInsertDepartments(List<Map<String, Object>> departments) {
        return new int[0];
    }

    @Override
    public int[] batchUpdateDepartments(List<Map<String, Object>> departments) {
        return new int[0];
    }

    @Override
    public int[] batchDeleteDepartments(List<String> deptNos) {
        return new int[0];
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

    // Implementazione transazioni
    @Override
    public void executeInTransaction(Runnable operation) {
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            try {
                operation.run();
                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                throw new RuntimeException("Transaction failed", e);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error managing transaction", e);
        }
    }

    @Override
    public <T> T executeInTransaction(Supplier<T> operation) {
        return null;
    }

    @Override
    public int executeCustomUpdate(String query) {
        return 0;
    }

    @Override
    public int executeCustomUpdate(String query, Object... parameters) {
        return 0;
    }
}
