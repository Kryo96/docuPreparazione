package org.example.dao.EmployeeDao;

import java.util.List;
import java.util.Map;

public interface EmployeeWriteOperations {
    // Insert con tutti i parametri
    int insertEmployee(String empNo, String firstName, String midInit, String lastName,
                       String workDept, String phoneNo, String hireDate, String job,
                       String edLevel, String sex, String birthDate, String salary,
                       String bonus, String comm);

    // Insert con Map
    int insertEmployee(Map<String, Object> employeeData);

    // Update methods
    int updateFirstName(String empNo, String firstName);
    int updateLastName(String empNo, String lastName);
    int updateDepartment(String empNo, String newDeptNo);
    int updateJob(String empNo, String newJob);
    int updateSalary(String empNo, double newSalary);
    int updatePhone(String empNo, String newPhone);
    int updateEmployee(String empNo, Map<String, Object> updateData);

    // Delete
    int deleteEmployee(String empNo);
    int deleteEmployeesByDepartment(String deptNo);

    // Batch operations
    int[] batchInsertEmployees(List<Map<String, Object>> employees);
    int[] batchUpdateEmployees(List<Map<String, Object>> employees);
    int[] batchDeleteEmployees(List<String> empNos);

    // Salary operations
    int increaseSalaryByPercentage(String empNo, double percentage);
    int increaseSalaryByAmount(String empNo, double amount);
    int increaseDepartmentSalariesByPercentage(String deptNo, double percentage);

    // Custom operations
    int executeCustomUpdate(String query);
    int executeCustomUpdate(String query, Object... parameters);
}