package org.example.dao.multi.DepartmentEmployeeDao;

import java.util.List;
import java.util.Map;

public interface DepartmentEmployeeWriteOperations {
    // === OPERAZIONI TRANSAZIONALI COMPLESSE ===

    // Gestione dipartimenti con dipendenti
    int createDepartmentWithInitialStaff(Map<String, Object> deptData, List<Map<String, Object>> employeesData);
    int transferAllEmployees(String fromDeptNo, String toDeptNo);
    int disbandDepartmentSafely(String deptNo, String targetDeptNo);

    // Riorganizzazioni
    int mergeGepartments(String dept1, String dept2, String newDeptNo, Map<String, Object> newDeptData);
    int splitDepartment(String originalDept, Map<String, Object> newDept1Data, Map<String, Object> newDept2Data, List<String> employeesForNewDept);

    // Bulk operations
    int bulkUpdateEmployeeDepartments(Map<String, String> employeeDeptMapping);
    int bulkSalaryAdjustmentByDepartment(String deptNo, double adjustmentPercentage);
    int bulkPromoteEmployees(List<String> empNos, String newJob, double salaryIncrease);

    // Gestione gerarchie
    int assignNewManager(String deptNo, String newManagerEmpNo);
    int rotateManagers(Map<String, String> deptManagerMapping);
}
