package org.example;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.multi.DepartmentEmployeeDao.DepartmentEmployeeReadOperations;
import org.example.qualifier.ReadOnly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DepartmentEmployeeReportServlet", urlPatterns = {"/reports"})
public class DepartmentEmployeeReportServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentEmployeeReportServlet.class);

    @Inject
    @ReadOnly
    private DepartmentEmployeeReadOperations reportService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reportType = req.getParameter("type");
        String deptNo = req.getParameter("deptNo");
        String location = req.getParameter("location");
        
        logger.info("DepartmentEmployeeReportServlet.doGet() - reportType: {}, deptNo: {}, location: {}", reportType, deptNo, location);
        logger.debug("Request URI: {}, Query String: {}", req.getRequestURI(), req.getQueryString());

        try {
            // Preparazione dati in base al tipo di report
            switch (reportType == null ? "dashboard" : reportType) {
                case "dashboard":
                    prepareDashboardData(req);
                    req.setAttribute("content", "dashboard.jsp");
                    break;

                case "summary":
                    prepareSummaryData(req);
                    req.setAttribute("content", "department-summary.jsp");
                    break;

                case "detailed":
                    prepareDetailedData(req, deptNo);
                    req.setAttribute("content", "department-detailed.jsp");
                    break;

                case "employees":
                    prepareEmployeeData(req);
                    req.setAttribute("content", "employee-list.jsp");
                    break;

                case "location":
                    prepareLocationData(req, location);
                    req.setAttribute("content", "location-report.jsp");
                    break;

                case "salary":
                    prepareSalaryData(req);
                    req.setAttribute("content", "salary-analysis.jsp");
                    break;

                case "analytics":
                    prepareAnalyticsData(req);
                    req.setAttribute("content", "analytics.jsp");
                    break;

                default:
                    prepareDashboardData(req);
                    req.setAttribute("content", "dashboard.jsp");
            }
            // Forward al layout principale
            req.setAttribute("moduleType", "reports");
            req.setAttribute("layoutPath", "/WEB-INF/jsp/layout/reports-layout.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/layout/main.jsp").forward(req, resp);

        } catch (Exception e) {
            logger.error("Error preparing report data", e);
            req.setAttribute("errorMessage", "Error generating report: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/layout/common/error.jsp").forward(req, resp);
        }
    }

    private void prepareDashboardData(HttpServletRequest req) {
        logger.info("Preparing dashboard data");
        try {
            Map<String, Object> metrics = reportService.getDashboardMetrics();
            logger.debug("Dashboard metrics retrieved: {}", metrics.keySet());
            req.setAttribute("dashboardMetrics", metrics);

            List<Map<String, Object>> topDepartments = reportService.getLargestDepartments(5);
            logger.debug("Top departments retrieved: {} items", topDepartments.size());
            req.setAttribute("topDepartments", topDepartments);

            List<Map<String, Object>> locationDist = reportService.getEmployeeDistributionByLocation();
            logger.debug("Location distribution retrieved: {} items", locationDist.size());
            req.setAttribute("locationDistribution", locationDist);

            req.setAttribute("pageTitle", "Organization Dashboard");
            req.setAttribute("currentReport", "dashboard");
            
            logger.info("Dashboard data prepared successfully");
        } catch (Exception e) {
            logger.error("Error preparing dashboard data", e);
            throw e;
        }
    }

    private void prepareSummaryData(HttpServletRequest req) {
        List<Map<String, Object>> departments = reportService.getDepartmentSummaryReport();
        req.setAttribute("departments", departments);
        req.setAttribute("pageTitle", "Department Summary Report");
        req.setAttribute("currentReport", "summary");
    }

    private void prepareDetailedData(HttpServletRequest req, String deptNo) {
        logger.info("Preparing detailed data for department: {}", deptNo);
        
        if (deptNo != null && !deptNo.trim().isEmpty()) {
            try {
                List<Map<String, Object>> details = reportService.getDepartmentDetailedReport(deptNo);
                logger.debug("Department details retrieved: {} records for deptNo: {}", details.size(), deptNo);
                
                req.setAttribute("departmentDetails", details);
                req.setAttribute("selectedDeptNo", deptNo);

                if (!details.isEmpty()) {
                    Map<String, Object> deptInfo = details.get(0);
                    req.setAttribute("departmentInfo", deptInfo);
                    logger.info("Department info loaded for: {}", deptNo);
                } else {
                    logger.warn("No department details found for: {}", deptNo);
                }
            } catch (Exception e) {
                logger.error("Error loading department details for: {}", deptNo, e);
                throw e;
            }
        } else {
            logger.warn("prepareDetailedData called with null or empty deptNo");
        }

        req.setAttribute("pageTitle", "Department Detailed Report");
        req.setAttribute("currentReport", "detailed");
    }

    private void prepareEmployeeData(HttpServletRequest req) {

        logger.info("Preparing employee data");
        try {
            List<Map<String, Object>> employees = reportService.getEmployeesWithDepartmentInfo();
            logger.debug("Employee data retrieved: {} items", employees.size());
            req.setAttribute("employees", employees);
            req.setAttribute("pageTitle", "Employee List with Department Info");
            req.setAttribute("currentReport", "employees");
        }catch(RuntimeException e) {
            logger.error("Error preparing employee data", e);
            throw new RuntimeException("Error while preparing employee data", e);
        }
    }

    private void prepareLocationData(HttpServletRequest req, String location) {
        if (location != null && !location.trim().isEmpty()) {
            List<Map<String, Object>> employees = reportService.getEmployeesByLocation(location);
            req.setAttribute("employees", employees);
            req.setAttribute("selectedLocation", location);
        }

        List<Map<String, Object>> locations = reportService.getEmployeeDistributionByLocation();
        req.setAttribute("allLocations", locations);

        req.setAttribute("pageTitle", "Location Report");
        req.setAttribute("currentReport", "location");
    }

    private void prepareSalaryData(HttpServletRequest req) {
        List<Map<String, Object>> salaryStats = reportService.getSalaryStatisticsByDepartment();
        req.setAttribute("salaryStatistics", salaryStats);
        req.setAttribute("pageTitle", "Salary Analysis by Department");
        req.setAttribute("currentReport", "salary");
    }

    private void prepareAnalyticsData(HttpServletRequest req) {
        List<Map<String, Object>> genderDist = reportService.getGenderDistributionByDepartment();
        req.setAttribute("genderDistribution", genderDist);

        List<Map<String, Object>> locationDist = reportService.getEmployeeDistributionByLocation();
        req.setAttribute("locationDistribution", locationDist);

        List<Map<String, Object>> topEarners = reportService.getTopEarnersByDepartment(3);
        req.setAttribute("topEarners", topEarners);

        req.setAttribute("pageTitle", "Advanced Analytics");
        req.setAttribute("currentReport", "analytics");
    }
}