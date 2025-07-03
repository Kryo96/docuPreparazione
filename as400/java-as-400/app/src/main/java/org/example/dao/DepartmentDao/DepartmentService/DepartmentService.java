package org.example.dao.DepartmentDao.DepartmentService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import org.example.dao.DepartmentDao.DepartmentDAO;
import org.example.dao.DepartmentDao.DepartmentReadOperations;
import org.example.dao.DepartmentDao.DepartmentWriteOperations;
import java.util.Map;
import java.util.List;
import java.util.function.Supplier;

@Default
@ApplicationScoped
public class DepartmentService implements DepartmentReadOperations, DepartmentWriteOperations {
    @Inject
    private DepartmentDAO dao;

    // Delega tutti i metodi di lettura
    @Override
    public List<String> getAllDeptNumbers() {
        return dao.getAllDeptNumbers();
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
        return dao.findByDeptNo(deptNo);
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
        return List.of();
    }

    @Override
    public List<Map<String, Object>> executeCustomSelect(String query) {
        return List.of();
    }

    // Delega tutti i metodi di scrittura
    @Override
    public int insertDepartment(String deptNo, String deptName, String mgrNo, String admrDept, String location) {
        return dao.insertDepartment(deptNo, deptName, mgrNo, admrDept, location);
    }

    @Override
    public int insertDepartment(Map<String, Object> departmentData) {
        return 0;
    }

    @Override
    public int updateDepartmentName(String deptNo, String newDeptName) {
        return dao.updateDepartmentName(deptNo, newDeptName);
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
        return dao.deleteDepartment(deptNo);
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

    @Override
    public void executeInTransaction(Runnable operation) {

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
