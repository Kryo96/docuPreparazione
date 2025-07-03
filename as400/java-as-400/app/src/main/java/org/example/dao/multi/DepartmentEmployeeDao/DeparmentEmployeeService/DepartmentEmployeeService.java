package org.example.dao.multi.DepartmentEmployeeDao.DeparmentEmployeeService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import org.example.dao.multi.DepartmentEmployeeDao.DepartmentEmployeeDAO;
import org.example.dao.multi.DepartmentEmployeeDao.DepartmentEmployeeReadOperations;
import org.example.dao.multi.DepartmentEmployeeDao.DepartmentEmployeeWriteOperations;

import java.util.List;
import java.util.Map;

@ApplicationScoped
@Default
public class DepartmentEmployeeService implements DepartmentEmployeeReadOperations, DepartmentEmployeeWriteOperations {

    @Inject
    private DepartmentEmployeeDAO dao;

    // Delega tutti i metodi di lettura
    @Override public List<Map<String, Object>> getDepartmentSummaryReport() { return dao.getDepartmentSummaryReport(); }
    @Override public List<Map<String, Object>> getDepartmentDetailedReport(String deptNo) { return dao.getDepartmentDetailedReport(deptNo); }
    @Override public List<Map<String, Object>> getDepartmentsWithoutEmployees() { return dao.getDepartmentsWithoutEmployees(); }
    @Override public List<Map<String, Object>> getEmployeesWithDepartmentInfo() { return dao.getEmployeesWithDepartmentInfo(); }
    @Override public List<Map<String, Object>> getEmployeesByLocation(String location) { return dao.getEmployeesByLocation(location); }
    @Override public List<Map<String, Object>> getEmployeesByDepartmentName(String deptName) { return dao.getEmployeesByDepartmentName(deptName); }
    @Override public List<Map<String, Object>> findEmployeesByLocationAndJob(String location, String job) { return dao.findEmployeesByLocationAndJob(location, job); }
    @Override public List<Map<String, Object>> findHighEarnersInDepartment(String deptNo, double minSalary) { return dao.findHighEarnersInDepartment(deptNo, minSalary); }
    @Override public List<Map<String, Object>> findRecentHiresByDepartment(String deptNo, String fromDate) { return dao.findRecentHiresByDepartment(deptNo, fromDate); }
    @Override public Map<String, Object> getOrganizationOverview() { return dao.getOrganizationOverview(); }
    @Override public List<Map<String, Object>> getSalaryStatisticsByDepartment() { return dao.getSalaryStatisticsByDepartment(); }
    @Override public List<Map<String, Object>> getEmployeeDistributionByLocation() { return dao.getEmployeeDistributionByLocation(); }
    @Override public List<Map<String, Object>> getGenderDistributionByDepartment() { return dao.getGenderDistributionByDepartment(); }
    @Override public List<Map<String, Object>> getTopEarnersByDepartment(int limit) { return dao.getTopEarnersByDepartment(limit); }
    @Override public List<Map<String, Object>> getLargestDepartments(int limit) { return dao.getLargestDepartments(limit); }
    @Override public List<Map<String, Object>> getOldestEmployeesByDepartment() { return dao.getOldestEmployeesByDepartment(); }
    @Override public List<Map<String, Object>> getNewestEmployeesByDepartment() { return dao.getNewestEmployeesByDepartment(); }
    @Override public Map<String, Object> getDashboardMetrics() { return dao.getDashboardMetrics(); }
    @Override public List<Map<String, Object>> getDepartmentPerformanceMetrics() { return dao.getDepartmentPerformanceMetrics(); }
    @Override public List<Map<String, Object>> getMonthlyHiringTrends() { return dao.getMonthlyHiringTrends(); }
    @Override public List<Map<String, Object>> getSalaryTrendsByDepartment() { return dao.getSalaryTrendsByDepartment(); }
    @Override public List<Map<String, Object>> getManagerEfficiencyReport() { return dao.getManagerEfficiencyReport(); }
    @Override public List<Map<String, Object>> getDepartmentCostAnalysis() { return dao.getDepartmentCostAnalysis(); }
    @Override public List<Map<String, Object>> getEmployeeTurnoverAnalysis() { return dao.getEmployeeTurnoverAnalysis(); }
    @Override public List<Map<String, Object>> getWorkforceDemographics() { return dao.getWorkforceDemographics(); }
    @Override public List<Map<String, Object>> executeJoinQuery(String customQuery) { return dao.executeJoinQuery(customQuery); }

    // Delega tutti i metodi di scrittura
    @Override public int createDepartmentWithInitialStaff(Map<String, Object> deptData, List<Map<String, Object>> employeesData) { return dao.createDepartmentWithInitialStaff(deptData, employeesData); }
    @Override public int transferAllEmployees(String fromDeptNo, String toDeptNo) { return dao.transferAllEmployees(fromDeptNo, toDeptNo); }
    @Override public int disbandDepartmentSafely(String deptNo, String targetDeptNo) { return dao.disbandDepartmentSafely(deptNo, targetDeptNo); }
    @Override public int mergeGepartments(String dept1, String dept2, String newDeptNo, Map<String, Object> newDeptData) { return dao.mergeGepartments(dept1, dept2, newDeptNo, newDeptData); }
    @Override public int splitDepartment(String originalDept, Map<String, Object> newDept1Data, Map<String, Object> newDept2Data, List<String> employeesForNewDept) { return dao.splitDepartment(originalDept, newDept1Data, newDept2Data, employeesForNewDept); }
    @Override public int bulkUpdateEmployeeDepartments(Map<String, String> employeeDeptMapping) { return dao.bulkUpdateEmployeeDepartments(employeeDeptMapping); }
    @Override public int bulkSalaryAdjustmentByDepartment(String deptNo, double adjustmentPercentage) { return dao.bulkSalaryAdjustmentByDepartment(deptNo, adjustmentPercentage); }
    @Override public int bulkPromoteEmployees(List<String> empNos, String newJob, double salaryIncrease) { return dao.bulkPromoteEmployees(empNos, newJob, salaryIncrease); }
    @Override public int assignNewManager(String deptNo, String newManagerEmpNo) { return dao.assignNewManager(deptNo, newManagerEmpNo); }
    @Override public int rotateManagers(Map<String, String> deptManagerMapping) { return dao.rotateManagers(deptManagerMapping); }
}
