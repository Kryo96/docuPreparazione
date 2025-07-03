<!-- /WEB-INF/jsp/dashboard.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<h2>📊 Organization Dashboard</h2>

<div class="metrics-grid">
    <div class="metric-card" style="background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);">
        <h3>Total Departments</h3>
        <div class="value">${dashboardMetrics.TOTAL_DEPARTMENTS}</div>
    </div>

    <div class="metric-card" style="background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);">
        <h3>Total Employees</h3>
        <div class="value">${dashboardMetrics.TOTAL_EMPLOYEES}</div>
    </div>

    <div class="metric-card" style="background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);">
        <h3>Avg Company Salary</h3>
        <div class="value">
            <fmt:formatNumber value="${dashboardMetrics.AVG_COMPANY_SALARY}" type="currency" currencySymbol="$" maxFractionDigits="0"/>
        </div>
    </div>

    <div class="metric-card" style="background: linear-gradient(135deg, #43e97b 0%, #38f9d7 100%);">
        <h3>Total Locations</h3>
        <div class="value">${dashboardMetrics.TOTAL_LOCATIONS}</div>
    </div>
</div>

<h3>🏢 Largest Departments</h3>
<div class="table-responsive">
    <table>
        <thead>
            <tr>
                <th>Department</th>
                <th>Location</th>
                <th>Employee Count</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="dept" items="${topDepartments}">
                <tr>
                    <td><strong>${dept.DEPTNAME}</strong></td>
                    <td>${dept.LOCATION}</td>
                    <td class="number">${dept.EMP_COUNT}</td>
                    <td>
                        <a href="?type=detailed&deptNo=${dept.DEPTNO}" class="action-link">View Details</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<h3>📍 Employee Distribution by Location</h3>
<div class="table-responsive">
    <table>
        <thead>
            <tr>
                <th>Location</th>
                <th>Employee Count</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="location" items="${locationDistribution}">
                <tr>
                    <td><strong>${location.LOCATION}</strong></td>
                    <td class="number">${location.EMP_COUNT}</td>
                    <td>
                        <a href="?type=location&location=${location.LOCATION}" class="action-link">View Details</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>