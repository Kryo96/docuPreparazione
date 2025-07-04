<!-- /WEB-INF/jsp/layout/reports/dashboard.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="dashboard-header">
    <h2>📊 Organization Dashboard</h2>
    <p>Real-time overview of your organization's key metrics</p>
</div>

<!-- Metrics Cards -->
<c:if test="${not empty dashboardMetrics}">
    <div class="metrics-grid">
        <div class="metric-card">
            <h3>👥 Total Employees</h3>
            <div class="value">${dashboardMetrics.totalEmployees}</div>
            <small>Active employees in system</small>
        </div>
        
        <div class="metric-card">
            <h3>🏢 Total Departments</h3>
            <div class="value">${dashboardMetrics.totalDepartments}</div>
            <small>Active departments</small>
        </div>
        
        <div class="metric-card">
            <h3>💰 Average Salary</h3>
            <div class="value">
                <fmt:formatNumber value="${dashboardMetrics.avgSalary}" type="currency" currencySymbol="$" />
            </div>
            <small>Organization average</small>
        </div>
        
        <div class="metric-card">
            <h3>📍 Locations</h3>
            <div class="value">${dashboardMetrics.totalLocations}</div>
            <small>Office locations</small>
        </div>
    </div>
</c:if>

<!-- Top Departments -->
<c:if test="${not empty topDepartments}">
    <div class="dashboard-section">
        <h3>🏆 Largest Departments</h3>
        <div class="table-responsive">
            <table>
                <thead>
                    <tr>
                        <th>Department</th>
                        <th>Department Name</th>
                        <th>Employee Count</th>
                        <th>Location</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${topDepartments}" var="dept">
                        <tr>
                            <td><strong>${dept.DEPTNO}</strong></td>
                            <td>${dept.DEPTNAME}</td>
                            <td class="number">
                                <span class="badge">${dept.EMPLOYEE_COUNT}</span>
                            </td>
                            <td>📍 ${dept.LOCATION}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</c:if>

<!-- Location Distribution -->
<c:if test="${not empty locationDistribution}">
    <div class="dashboard-section">
        <h3>📍 Employee Distribution by Location</h3>
        <div class="location-grid">
            <c:forEach items="${locationDistribution}" var="location">
                <div class="location-card">
                    <div class="location-header">
                        <h4>📍 ${location.LOCATION}</h4>
                        <span class="employee-count">${location.EMPLOYEE_COUNT} employees</span>
                    </div>
                    <div class="location-bar">
                        <div class="location-progress" style="width: ${(location.EMPLOYEE_COUNT / dashboardMetrics.totalEmployees) * 100}%"></div>
                    </div>
                    <small>
                        <fmt:formatNumber value="${(location.EMPLOYEE_COUNT / dashboardMetrics.totalEmployees) * 100}" 
                                        type="number" maxFractionDigits="1" />% of total workforce
                    </small>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>

<!-- Quick Actions -->
<div class="dashboard-section">
    <h3>⚡ Quick Actions</h3>
    <div class="quick-actions-grid">
        <a href="${pageContext.request.contextPath}/reports?type=summary" class="quick-action-card">
            <div class="action-icon">📋</div>
            <div class="action-content">
                <h4>Department Summary</h4>
                <p>View detailed department statistics</p>
            </div>
        </a>
        
        <a href="${pageContext.request.contextPath}/reports?type=employees" class="quick-action-card">
            <div class="action-icon">👥</div>
            <div class="action-content">
                <h4>Employee List</h4>
                <p>Browse all employees with details</p>
            </div>
        </a>
        
        <a href="${pageContext.request.contextPath}/reports?type=salary" class="quick-action-card">
            <div class="action-icon">💰</div>
            <div class="action-content">
                <h4>Salary Analysis</h4>
                <p>Analyze compensation by department</p>
            </div>
        </a>
        
        <a href="${pageContext.request.contextPath}/reports?type=analytics" class="quick-action-card">
            <div class="action-icon">📈</div>
            <div class="action-content">
                <h4>Advanced Analytics</h4>
                <p>Deep dive into organizational data</p>
            </div>
        </a>
    </div>
</div>

<style>
    .dashboard-header {
        text-align: center;
        margin-bottom: 40px;
    }

    .dashboard-header h2 {
        color: #333;
        margin-bottom: 10px;
    }

    .dashboard-header p {
        color: #666;
        font-size: 1.1em;
    }

    .dashboard-section {
        background: #f8f9fa;
        padding: 25px;
        border-radius: 10px;
        margin-bottom: 30px;
        border: 1px solid #e0e0e0;
    }

    .dashboard-section h3 {
        color: #333;
        margin-bottom: 20px;
        padding-bottom: 10px;
        border-bottom: 2px solid #667eea;
    }

    .location-grid {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
        gap: 20px;
    }

    .location-card {
        background: white;
        padding: 20px;
        border-radius: 8px;
        border: 1px solid #e0e0e0;
    }

    .location-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 15px;
    }

    .location-header h4 {
        color: #333;
        margin: 0;
    }

    .employee-count {
        background: #667eea;
        color: white;
        padding: 4px 8px;
        border-radius: 12px;
        font-size: 0.9em;
        font-weight: 600;
    }

    .location-bar {
        background: #e0e0e0;
        height: 8px;
        border-radius: 4px;
        margin-bottom: 10px;
    }

    .location-progress {
        background: linear-gradient(90deg, #667eea, #764ba2);
        height: 100%;
        border-radius: 4px;
        transition: width 0.3s ease;
    }

    .badge {
        background: #28a745;
        color: white;
        padding: 4px 8px;
        border-radius: 12px;
        font-size: 0.9em;
        font-weight: 600;
    }

    .quick-actions-grid {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
        gap: 20px;
    }

    .quick-action-card {
        display: flex;
        align-items: center;
        background: white;
        padding: 20px;
        border-radius: 10px;
        text-decoration: none;
        color: #333;
        border: 2px solid #e0e0e0;
        transition: all 0.3s ease;
    }

    .quick-action-card:hover {
        background: #667eea;
        color: white;
        transform: translateY(-2px);
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    }

    .action-icon {
        font-size: 2em;
        margin-right: 15px;
        min-width: 60px;
        text-align: center;
    }

    .action-content h4 {
        margin: 0 0 8px 0;
        font-size: 1.1em;
    }

    .action-content p {
        margin: 0;
        font-size: 0.9em;
        opacity: 0.8;
    }

    @media (max-width: 768px) {
        .location-grid {
            grid-template-columns: 1fr;
        }
        
        .quick-actions-grid {
            grid-template-columns: 1fr;
        }
    }
</style>