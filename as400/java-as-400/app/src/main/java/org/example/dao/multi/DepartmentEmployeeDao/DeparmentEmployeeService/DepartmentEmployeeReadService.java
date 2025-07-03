package org.example.dao.multi.DepartmentEmployeeDao.DeparmentEmployeeService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.dao.multi.DepartmentEmployeeDao.DepartmentEmployeeReadOperations;
import org.example.dao.multi.DepartmentEmployeeDao.DepartmentEmployeeDAO;
import org.example.qualifier.ReadOnly;
import java.util.List;
import java.util.Map;

@ApplicationScoped
@ReadOnly
public class DepartmentEmployeeReadService implements DepartmentEmployeeReadOperations {

    @Inject
    private DepartmentEmployeeDAO dao;

    // Delega tutti i metodi di lettura al DAO
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
}