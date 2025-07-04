<!-- /WEB-INF/jsp/layout/reports/location-report.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="report-header">
    <h2>Location Report</h2>
    <p>Geographic distribution and location-based employee analysis</p>
</div>

<div class="location-container">
    <!-- Location Selection -->
    <div class="location-selector">
        <h3>Select Location</h3>
        <div class="location-buttons">
            <a href="${pageContext.request.contextPath}/reports?type=location" class="location-btn ${empty selectedLocation ? 'active' : ''}">
                All Locations
            </a>
            <c:forEach items="${allLocations}" var="location">
                <a href="${pageContext.request.contextPath}/reports?type=location&location=${location.LOCATION}"
                   class="location-btn ${selectedLocation == location.LOCATION ? 'active' : ''}">
                    ${location.LOCATION}
                    <span class="location-count">(${location.EMPLOYEE_COUNT})</span>
                </a>
            </c:forEach>
        </div>
    </div>

    <!-- All Locations Overview -->
    <c:if test="${empty selectedLocation}">
        <div class="all-locations-overview">
            <h3>All Locations Overview</h3>
            
            <!-- Location Statistics -->
            <div class="location-stats">
                <div class="stat-card">
                    <div class="stat-icon"></div>
                    <div class="stat-content">
                        <h4>Total Locations</h4>
                        <div class="stat-value">${allLocations.size()}</div>
                    </div>
                </div>
                
                <div class="stat-card">
                    <div class="stat-icon"></div>
                    <div class="stat-content">
                        <h4>Total Employees</h4>
                        <div class="stat-value">
                            <c:set var="totalEmp" value="0" />
                            <c:forEach items="${allLocations}" var="loc">
                                <c:set var="totalEmp" value="${totalEmp + loc.EMPLOYEE_COUNT}" />
                            </c:forEach>
                            ${totalEmp}
                        </div>
                    </div>
                </div>
                
                <div class="stat-card">
                    <div class="stat-icon"></div>
                    <div class="stat-content">
                        <h4>Average per Location</h4>
                        <div class="stat-value">
                            <fmt:formatNumber value="${totalEmp / allLocations.size()}" type="number" maxFractionDigits="0" />
                        </div>
                    </div>
                </div>
            </div>

            <!-- Location Distribution Chart -->
            <div class="distribution-chart">
                <h4>Employee Distribution by Location</h4>
                <div class="chart-container">
                    <c:set var="maxEmployees" value="0" />
                    <c:forEach items="${allLocations}" var="location">
                        <c:if test="${location.EMPLOYEE_COUNT > maxEmployees}">
                            <c:set var="maxEmployees" value="${location.EMPLOYEE_COUNT}" />
                        </c:if>
                    </c:forEach>
                    
                    <c:forEach items="${allLocations}" var="location">
                        <div class="chart-bar">
                            <div class="bar-info">
                                <span class="bar-label">${location.LOCATION}</span>
                                <span class="bar-value">${location.EMPLOYEE_COUNT} employees</span>
                            </div>
                            <div class="bar-container">
                                <div class="bar-fill" style="width: ${(location.EMPLOYEE_COUNT / maxEmployees) * 100}%">
                                    <span class="bar-percentage">
                                        <fmt:formatNumber value="${(location.EMPLOYEE_COUNT / totalEmp) * 100}" 
                                                        type="number" maxFractionDigits="1" />%
                                    </span>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <!-- Location Comparison Table -->
            <div class="location-table">
                <h4>Location Comparison</h4>
                <div class="table-responsive">
                    <table>
                        <thead>
                            <tr>
                                <th>Location</th>
                                <th>Employees</th>
                                <th>% of Total</th>
                                <th>Departments</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${allLocations}" var="location">
                                <tr>
                                    <td>
                                        <div class="location-info">
                                            <strong>${location.LOCATION}</strong>
                                        </div>
                                    </td>
                                    <td class="text-center">
                                        <span class="employee-badge">${location.EMPLOYEE_COUNT}</span>
                                    </td>
                                    <td class="text-center">
                                        <span class="percentage-badge">
                                            <fmt:formatNumber value="${(location.EMPLOYEE_COUNT / totalEmp) * 100}" 
                                                            type="number" maxFractionDigits="1" />%
                                        </span>
                                    </td>
                                    <td class="text-center">
                                        <c:set var="deptCount" value="0" />
                                        <c:forEach items="${allLocations}" var="loc">
                                            <c:if test="${loc.LOCATION == location.LOCATION}">
                                                <c:set var="deptCount" value="${loc.DEPARTMENT_COUNT != null ? loc.DEPARTMENT_COUNT : 1}" />
                                            </c:if>
                                        </c:forEach>
                                        <span class="dept-count">${deptCount}</span>
                                    </td>
                                    <td class="text-center">
                                        <span class="status-badge ${location.EMPLOYEE_COUNT > 0 ? 'active' : 'inactive'}">
                                            ${location.EMPLOYEE_COUNT > 0 ? 'Active' : 'Inactive'}
                                        </span>
                                    </td>
                                    <td class="text-center">
                                        <a href="${pageContext.request.contextPath}/reports?type=location&location=${location.LOCATION}" class="action-link">
                                            👁️ View Details
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </c:if>

    <!-- Specific Location Details -->
    <c:if test="${not empty selectedLocation}">
        <div class="location-details">
            <h3>${selectedLocation} - Detailed Report</h3>
            
            <!-- Location Summary -->
            <div class="location-summary">
                <div class="summary-card">
                    <h4>Location Summary</h4>
                    <div class="summary-stats">
                        <div class="summary-item">
                            <span class="summary-label">Total Employees:</span>
                            <span class="summary-value">${employees.size()}</span>
                        </div>
                        <div class="summary-item">
                            <span class="summary-label">🏢 Departments:</span>
                            <span class="summary-value">
                                <c:set var="uniqueDepts" value="${[]}" />
                                <c:forEach items="${employees}" var="emp">
                                    <c:if test="${emp.WORKDEPT != null && !uniqueDepts.contains(emp.WORKDEPT)}">
                                        <c:set var="uniqueDepts" value="${uniqueDepts.add(emp.WORKDEPT)}" />
                                    </c:if>
                                </c:forEach>
                                ${uniqueDepts.size()}
                            </span>
                        </div>
                        <div class="summary-item">
                            <span class="summary-label">Total Payroll:</span>
                            <span class="summary-value">
                                <c:set var="totalPayroll" value="0" />
                                <c:forEach items="${employees}" var="emp">
                                    <c:if test="${emp.SALARY != null}">
                                        <c:set var="totalPayroll" value="${totalPayroll + emp.SALARY}" />
                                    </c:if>
                                </c:forEach>
                                <fmt:formatNumber value="${totalPayroll}" type="currency" currencySymbol="$" />
                            </span>
                        </div>
                        <div class="summary-item">
                            <span class="summary-label">Average Salary:</span>
                            <span class="summary-value">
                                <c:set var="salaryCount" value="0" />
                                <c:forEach items="${employees}" var="emp">
                                    <c:if test="${emp.SALARY != null}">
                                        <c:set var="salaryCount" value="${salaryCount + 1}" />
                                    </c:if>
                                </c:forEach>
                                <c:if test="${salaryCount > 0}">
                                    <fmt:formatNumber value="${totalPayroll / salaryCount}" type="currency" currencySymbol="$" />
                                </c:if>
                            </span>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Department Breakdown -->
            <div class="department-breakdown">
                <h4>🏢 Department Breakdown</h4>
                <div class="dept-cards">
                    <c:set var="deptGroups" value="${{}}" />
                    <c:forEach items="${employees}" var="emp">
                        <c:if test="${emp.WORKDEPT != null}">
                            <!-- Group employees by department -->
                            <c:set var="deptKey" value="${emp.WORKDEPT}" />
                            <c:if test="${empty deptGroups[deptKey]}">
                                <c:set var="deptGroups" value="${deptGroups.put(deptKey, [])}" />
                            </c:if>
                            <c:set var="deptGroups" value="${deptGroups[deptKey].add(emp)}" />
                        </c:if>
                    </c:forEach>
                    
                    <!-- Display department cards -->
                    <c:set var="processedDepts" value="${[]}" />
                    <c:forEach items="${employees}" var="emp">
                        <c:if test="${emp.WORKDEPT != null && !processedDepts.contains(emp.WORKDEPT)}">
                            <c:set var="processedDepts" value="${processedDepts.add(emp.WORKDEPT)}" />
                            <div class="dept-card">
                                <div class="dept-header">
                                    <h5>${emp.WORKDEPT}</h5>
                                    <span class="dept-count">
                                        <c:set var="deptEmpCount" value="0" />
                                        <c:forEach items="${employees}" var="deptEmp">
                                            <c:if test="${deptEmp.WORKDEPT == emp.WORKDEPT}">
                                                <c:set var="deptEmpCount" value="${deptEmpCount + 1}" />
                                            </c:if>
                                        </c:forEach>
                                        ${deptEmpCount} employees
                                    </span>
                                </div>
                                <div class="dept-stats">
                                    <c:set var="deptPayroll" value="0" />
                                    <c:set var="deptSalaryCount" value="0" />
                                    <c:forEach items="${employees}" var="deptEmp">
                                        <c:if test="${deptEmp.WORKDEPT == emp.WORKDEPT && deptEmp.SALARY != null}">
                                            <c:set var="deptPayroll" value="${deptPayroll + deptEmp.SALARY}" />
                                            <c:set var="deptSalaryCount" value="${deptSalaryCount + 1}" />
                                        </c:if>
                                    </c:forEach>
                                    <div class="dept-stat">
                                        <span class="stat-label">Total:</span>
                                        <span class="stat-value">
                                            <fmt:formatNumber value="${deptPayroll}" type="currency" currencySymbol="$" />
                                        </span>
                                    </div>
                                    <div class="dept-stat">
                                        <span class="stat-label">Average:</span>
                                        <span class="stat-value">
                                            <c:if test="${deptSalaryCount > 0}">
                                                <fmt:formatNumber value="${deptPayroll / deptSalaryCount}" type="currency" currencySymbol="$" />
                                            </c:if>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>

            <!-- Employee List -->
            <c:if test="${not empty employees}">
                <div class="employees-section">
                    <h4>Employees in ${selectedLocation}</h4>
                    <div class="table-responsive">
                        <table class="employees-table">
                            <thead>
                                <tr>
                                    <th>Employee</th>
                                    <th>Department</th>
                                    <th>Job Title</th>
                                    <th>Hire Date</th>
                                    <th>Salary</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${employees}" var="emp">
                                    <tr>
                                        <td>
                                            <div class="employee-info">
                                                <strong>${emp.FIRSTNME} ${emp.LASTNAME}</strong>
                                                <span class="emp-id">#${emp.EMPNO}</span>
                                            </div>
                                        </td>
                                        <td>
                                            <c:if test="${emp.WORKDEPT != null}">
                                                <span class="dept-badge">${emp.WORKDEPT}</span>
                                            </c:if>
                                        </td>
                                        <td class="job-title">${emp.JOB}</td>
                                        <td>
                                            <c:if test="${emp.HIREDATE != null}">
                                                <fmt:formatDate value="${emp.HIREDATE}" pattern="dd/MM/yyyy" />
                                            </c:if>
                                        </td>
                                        <td class="currency">
                                            <c:if test="${emp.SALARY != null}">
                                                <fmt:formatNumber value="${emp.SALARY}" type="currency" currencySymbol="$" />
                                            </c:if>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/employees?action=view&empNo=${emp.EMPNO}" class="action-link">
                                                View
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
        </div>
    </c:if>
</div>

<style>
    .report-header {
        text-align: center;
        margin-bottom: 40px;
    }

    .location-container {
        max-width: 1200px;
        margin: 0 auto;
    }

    .location-selector {
        background: #f8f9fa;
        padding: 25px;
        border-radius: 10px;
        margin-bottom: 30px;
        border: 1px solid #e0e0e0;
    }

    .location-selector h3 {
        margin-bottom: 20px;
        color: #333;
        text-align: center;
    }

    .location-buttons {
        display: flex;
        flex-wrap: wrap;
        gap: 10px;
        justify-content: center;
    }

    .location-btn {
        padding: 10px 20px;
        border: 2px solid #e0e0e0;
        border-radius: 25px;
        text-decoration: none;
        color: #333;
        background: white;
        transition: all 0.3s ease;
        display: flex;
        align-items: center;
        gap: 8px;
    }

    .location-btn:hover, .location-btn.active {
        background: #667eea;
        color: white;
        border-color: #667eea;
        transform: translateY(-2px);
    }

    .location-count {
        background: rgba(0,0,0,0.1);
        padding: 2px 6px;
        border-radius: 10px;
        font-size: 0.9em;
    }

    .location-btn.active .location-count {
        background: rgba(255,255,255,0.2);
    }

    .all-locations-overview {
        background: #f8f9fa;
        padding: 30px;
        border-radius: 15px;
        margin-bottom: 30px;
        border: 1px solid #e0e0e0;
    }

    .all-locations-overview h3 {
        color: #333;
        margin-bottom: 25px;
        text-align: center;
    }

    .location-stats {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
        gap: 20px;
        margin-bottom: 30px;
    }

    .stat-card {
        background: white;
        padding: 20px;
        border-radius: 10px;
        display: flex;
        align-items: center;
        gap: 15px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }

    .stat-icon {
        font-size: 2.5em;
        opacity: 0.8;
    }

    .stat-content h4 {
        margin: 0 0 10px 0;
        color: #333;
        font-size: 1em;
    }

    .stat-value {
        font-size: 2em;
        font-weight: bold;
        color: #333;
    }

    .distribution-chart {
        margin-bottom: 30px;
    }

    .distribution-chart h4 {
        color: #333;
        margin-bottom: 20px;
        text-align: center;
    }

    .chart-container {
        background: white;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }

    .chart-bar {
        margin-bottom: 20px;
    }

    .bar-info {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 8px;
    }

    .bar-label {
        font-weight: 600;
        color: #333;
    }

    .bar-value {
        font-weight: 600;
        color: #666;
    }

    .bar-container {
        background: #e0e0e0;
        height: 25px;
        border-radius: 12px;
        overflow: hidden;
        position: relative;
    }

    .bar-fill {
        background: linear-gradient(90deg, #667eea, #764ba2);
        height: 100%;
        border-radius: 12px;
        display: flex;
        align-items: center;
        justify-content: flex-end;
        padding-right: 10px;
        color: white;
        font-weight: 600;
        font-size: 0.9em;
        min-width: 80px;
    }

    .location-table h4 {
        color: #333;
        margin-bottom: 20px;
    }

    .employee-badge {
        background: #28a745;
        color: white;
        padding: 4px 8px;
        border-radius: 12px;
        font-weight: 600;
    }

    .percentage-badge {
        background: #17a2b8;
        color: white;
        padding: 4px 8px;
        border-radius: 12px;
        font-weight: 600;
    }

    .dept-count {
        background: #6c757d;
        color: white;
        padding: 4px 8px;
        border-radius: 12px;
        font-weight: 600;
    }

    .status-badge {
        padding: 4px 8px;
        border-radius: 12px;
        font-weight: 600;
    }

    .status-badge.active {
        background: #d4edda;
        color: #155724;
    }

    .status-badge.inactive {
        background: #f8d7da;
        color: #721c24;
    }

    .location-details {
        background: #f8f9fa;
        padding: 30px;
        border-radius: 15px;
        margin-bottom: 30px;
        border: 1px solid #e0e0e0;
    }

    .location-details h3 {
        color: #333;
        margin-bottom: 25px;
        text-align: center;
    }

    .location-summary {
        margin-bottom: 30px;
    }

    .summary-card {
        background: white;
        padding: 25px;
        border-radius: 10px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }

    .summary-card h4 {
        color: #333;
        margin-bottom: 20px;
        text-align: center;
    }

    .summary-stats {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
        gap: 15px;
    }

    .summary-item {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 10px;
        background: #f8f9fa;
        border-radius: 6px;
    }

    .summary-label {
        font-weight: 600;
        color: #666;
    }

    .summary-value {
        font-weight: 700;
        color: #333;
    }

    .department-breakdown {
        margin-bottom: 30px;
    }

    .department-breakdown h4 {
        color: #333;
        margin-bottom: 20px;
    }

    .dept-cards {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
        gap: 20px;
    }

    .dept-card {
        background: white;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }

    .dept-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 15px;
        padding-bottom: 15px;
        border-bottom: 1px solid #e0e0e0;
    }

    .dept-header h5 {
        margin: 0;
        color: #333;
    }

    .dept-stats {
        display: flex;
        flex-direction: column;
        gap: 10px;
    }

    .dept-stat {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .employees-section {
        margin-bottom: 30px;
    }

    .employees-section h4 {
        color: #333;
        margin-bottom: 20px;
    }

    .employees-table {
        font-size: 0.9em;
    }

    .employee-info {
        display: flex;
        flex-direction: column;
        gap: 5px;
    }

    .emp-id {
        font-size: 0.8em;
        color: #666;
    }

    .dept-badge {
        background: #667eea;
        color: white;
        padding: 3px 8px;
        border-radius: 12px;
        font-size: 0.9em;
        font-weight: 600;
    }

    .job-title {
        font-weight: 600;
        color: #333;
    }

    .text-center {
        text-align: center;
    }

    @media (max-width: 768px) {
        .location-buttons {
            flex-direction: column;
            align-items: center;
        }
        
        .location-stats {
            grid-template-columns: 1fr;
        }
        
        .summary-stats {
            grid-template-columns: 1fr;
        }
        
        .dept-cards {
            grid-template-columns: 1fr;
        }
    }
</style>