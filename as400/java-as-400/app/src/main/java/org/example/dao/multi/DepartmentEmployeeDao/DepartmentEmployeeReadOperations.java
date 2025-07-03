package org.example.dao.multi.DepartmentEmployeeDao;

import java.util.List;
import java.util.Map;

public interface DepartmentEmployeeReadOperations {
    // === REPORT E VISTE INTEGRATE ===

    // Dipartimenti con informazioni sui dipendenti
    List<Map<String, Object>> getDepartmentSummaryReport();
    List<Map<String, Object>> getDepartmentDetailedReport(String deptNo);
    List<Map<String, Object>> getDepartmentsWithoutEmployees();

    // Dipendenti con informazioni dipartimento
    List<Map<String, Object>> getEmployeesWithDepartmentInfo();
    List<Map<String, Object>> getEmployeesByLocation(String location);
    List<Map<String, Object>> getEmployeesByDepartmentName(String deptName);

    // Ricerche incrociate
    List<Map<String, Object>> findEmployeesByLocationAndJob(String location, String job);
    List<Map<String, Object>> findHighEarnersInDepartment(String deptNo, double minSalary);
    List<Map<String, Object>> findRecentHiresByDepartment(String deptNo, String fromDate);

    // === ANALYTICS E STATISTICHE ===

    // Statistiche generali
    Map<String, Object> getOrganizationOverview();
    List<Map<String, Object>> getSalaryStatisticsByDepartment();
    List<Map<String, Object>> getEmployeeDistributionByLocation();
    List<Map<String, Object>> getGenderDistributionByDepartment();

    // Top performers e rankings
    List<Map<String, Object>> getTopEarnersByDepartment(int limit);
    List<Map<String, Object>> getLargestDepartments(int limit);
    List<Map<String, Object>> getOldestEmployeesByDepartment();
    List<Map<String, Object>> getNewestEmployeesByDepartment();

    // === DASHBOARD DATA ===

    // Per dashboard principali
    Map<String, Object> getDashboardMetrics();
    List<Map<String, Object>> getDepartmentPerformanceMetrics();
    List<Map<String, Object>> getMonthlyHiringTrends();
    List<Map<String, Object>> getSalaryTrendsByDepartment();

    // === MANAGEMENT REPORTS ===

    // Report per management
    List<Map<String, Object>> getManagerEfficiencyReport();
    List<Map<String, Object>> getDepartmentCostAnalysis();
    List<Map<String, Object>> getEmployeeTurnoverAnalysis();
    List<Map<String, Object>> getWorkforceDemographics();

    // Query personalizzate per report specifici
    List<Map<String, Object>> executeJoinQuery(String customQuery);
}
