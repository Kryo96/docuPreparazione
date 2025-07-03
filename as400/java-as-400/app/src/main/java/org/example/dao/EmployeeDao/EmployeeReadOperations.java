package org.example.dao.EmployeeDao;

import java.util.List;
import java.util.Map;

public interface EmployeeReadOperations {
    List<Map<String, Object>> findAll();
    List<Map<String, Object>> findByEmpNo(String empNo);
    List<Map<String, Object>> findByDeptNo(String deptNo);
    List<Map<String, Object>> findByLastName(String lastName);
    List<Map<String, Object>> findByFirstName(String firstName);
    List<Map<String, Object>> findByJob(String job);
    List<Map<String, Object>> findByLastNameLike(String pattern);
    List<Map<String, Object>> findBySalaryRange(double minSalary, double maxSalary);
    List<Map<String, Object>> findByHireDateRange(String startDate, String endDate);
    List<Map<String, Object>> findBySex(String sex);
    List<Map<String, Object>> findByEducationLevel(int edLevel);

    // Aggregazioni
    int countAllEmployees();
    int countByDepartment(String deptNo);
    int countBySex(String sex);
    int countByJob(String job);

    // Distinct values
    List<String> getDistinctJobs();
    List<String> getDistinctDepartments();
    List<Integer> getDistinctEducationLevels();

    // Ordinamento
    List<Map<String, Object>> findAllOrderByLastName();
    List<Map<String, Object>> findAllOrderBySalary();
    List<Map<String, Object>> findAllOrderByHireDate();

    // Statistiche
    Map<String, Object> getSalaryStatistics();
    Map<String, Object> getEmployeeStatistics();

    // Query custom
    List<Map<String, Object>> executeCustomSelect(String query);
}