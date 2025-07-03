package org.example.dao.EmployeeDao.EmployeeService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.dao.EmployeeDao.EmployeeDAO;
import org.example.dao.EmployeeDao.EmployeeReadOperations;
import org.example.qualifier.ReadOnly;
import java.util.List;
import java.util.Map;

@ReadOnly
@ApplicationScoped
public class EmployeeReadService implements EmployeeReadOperations {

    @Inject
    private EmployeeDAO dao;

    @Override
    public List<Map<String, Object>> findAll() {
        return dao.findAll();
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
}
