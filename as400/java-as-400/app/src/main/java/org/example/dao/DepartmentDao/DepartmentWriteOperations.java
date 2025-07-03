package org.example.dao.DepartmentDao;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;


public interface DepartmentWriteOperations {
    // INSERT
    int insertDepartment(String deptNo, String deptName, String mgrNo, String admrDept, String location);
    int insertDepartment(Map<String, Object> departmentData);

    // UPDATE
    int updateDepartmentName(String deptNo, String newDeptName);
    int updateDepartmentManager(String deptNo, String newMgrNo);
    int updateDepartmentLocation(String deptNo, String newLocation);
    int updateDepartment(String deptNo, Map<String, Object> updateData);

    // DELETE
    int deleteDepartment(String deptNo);
    int deleteDepartmentsByLocation(String location);
    int deleteDepartmentsByManager(String mgrNo);

    // BATCH operations
    int[] batchInsertDepartments(List<Map<String, Object>> departments);
    int[] batchUpdateDepartments(List<Map<String, Object>> departments);
    int[] batchDeleteDepartments(List<String> deptNos);

    // Transactional operations
    void executeInTransaction(Runnable operation);
    <T> T executeInTransaction(Supplier<T> operation);

    // Custom operations
    int executeCustomUpdate(String query);
    int executeCustomUpdate(String query, Object... parameters);
}
