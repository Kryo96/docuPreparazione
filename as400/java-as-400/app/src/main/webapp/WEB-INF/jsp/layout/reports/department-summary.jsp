<!-- /WEB-INF/jsp/layout/reports/department-summary.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="report-header">
    <h2>Department Summary Report</h2>
    <p>Overview of all departments with key metrics and statistics</p>
</div>

<c:choose>
    <c:when test="${not empty departments}">
        <div class="summary-stats">
            <div class="stat-card">
                <h3>Total Departments</h3>
                <div class="stat-value">${departments.size()}</div>
            </div>
            <div class="stat-card">
                <h3>Total Employees</h3>
                <div class="stat-value">
                    <c:set var="totalEmployees" value="0" />
                    <c:forEach items="${departments}" var="dept">
                        <c:set var="totalEmployees" value="${totalEmployees + dept.EMPLOYEE_COUNT}" />
                    </c:forEach>
                    ${totalEmployees}
                </div>
            </div>
            <div class="stat-card">
                <h3>Average Salary</h3>
                <div class="stat-value">
                    <c:set var="totalSalary" value="0" />
                    <c:set var="deptCount" value="0" />
                    <c:forEach items="${departments}" var="dept">
                        <c:if test="${dept.AVG_SALARY != null}">
                            <c:set var="totalSalary" value="${totalSalary + dept.AVG_SALARY}" />
                            <c:set var="deptCount" value="${deptCount + 1}" />
                        </c:if>
                    </c:forEach>
                    <c:if test="${deptCount > 0}">
                        <fmt:formatNumber value="${totalSalary / deptCount}" type="currency" currencySymbol="$" />
                    </c:if>
                </div>
            </div>
        </div>

        <div class="table-responsive">
            <table class="department-table">
                <thead>
                    <tr>
                        <th>Department</th>
                        <th>Name</th>
                        <th>Manager</th>
                        <th>Location</th>
                        <th>Employees</th>
                        <th>Avg Salary</th>
                        <th>Total Payroll</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${departments}" var="dept">
                        <tr>
                            <td><strong class="dept-code">${dept.DEPTNO}</strong></td>
                            <td class="dept-name">${dept.DEPTNAME}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${dept.MGRNO != null}">
                                        <span class="manager-badge">${dept.MGRNO}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="no-manager">No Manager</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <span class="location-badge">${dept.LOCATION}</span>
                            </td>
                            <td class="text-center">
                                <span class="employee-count">${dept.EMPLOYEE_COUNT}</span>
                            </td>
                            <td class="currency">
                                <c:choose>
                                    <c:when test="${dept.AVG_SALARY != null}">
                                        <fmt:formatNumber value="${dept.AVG_SALARY}" type="currency" currencySymbol="$" />
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </td>
                            <td class="currency">
                                <c:choose>
                                    <c:when test="${dept.TOTAL_PAYROLL != null}">
                                        <strong class="payroll-total">
                                            <fmt:formatNumber value="${dept.TOTAL_PAYROLL}" type="currency" currencySymbol="$" />
                                        </strong>
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/reports?type=detailed&deptNo=${dept.DEPTNO}" class="action-link">Details</a>
                                <a href="${pageContext.request.contextPath}/departments?action=view&deptNo=${dept.DEPTNO}" class="action-link">Manage</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Department Cards for Mobile -->
        <div class="department-cards mobile-only">
            <c:forEach items="${departments}" var="dept">
                <div class="department-card">
                    <div class="card-header">
                        <h3>${dept.DEPTNO} - ${dept.DEPTNAME}</h3>
                        <span class="location-badge">${dept.LOCATION}</span>
                    </div>
                    <div class="card-content">
                        <div class="card-row">
                            <span class="label">Manager:</span>
                            <span class="value">
                                <c:choose>
                                    <c:when test="${dept.MGRNO != null}">${dept.MGRNO}</c:when>
                                    <c:otherwise>No Manager</c:otherwise>
                                </c:choose>
                            </span>
                        </div>
                        <div class="card-row">
                            <span class="label">Employees:</span>
                            <span class="value">${dept.EMPLOYEE_COUNT}</span>
                        </div>
                        <div class="card-row">
                            <span class="label">Avg Salary:</span>
                            <span class="value">
                                <c:choose>
                                    <c:when test="${dept.AVG_SALARY != null}">
                                        <fmt:formatNumber value="${dept.AVG_SALARY}" type="currency" currencySymbol="$" />
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </span>
                        </div>
                        <div class="card-row">
                            <span class="label">Total Payroll:</span>
                            <span class="value">
                                <c:choose>
                                    <c:when test="${dept.TOTAL_PAYROLL != null}">
                                        <fmt:formatNumber value="${dept.TOTAL_PAYROLL}" type="currency" currencySymbol="$" />
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </span>
                        </div>
                    </div>
                    <div class="card-actions">
                        <a href="${pageContext.request.contextPath}/reports?type=detailed&deptNo=${dept.DEPTNO}" class="btn btn-sm">Details</a>
                        <a href="${pageContext.request.contextPath}/departments?action=view&deptNo=${dept.DEPTNO}" class="btn btn-sm">Manage</a>
                    </div>
                </div>
            </c:forEach>
        </div>

    </c:when>
    <c:otherwise>
        <div class="alert alert-info">
            <h3>No departments found</h3>
            <p>There are no departments in the system to display.</p>
            <a href="${pageContext.request.contextPath}/departments?action=add" class="btn">Add First Department</a>
        </div>
    </c:otherwise>
</c:choose>

<style>
    .report-header {
        text-align: center;
        margin-bottom: 40px;
    }

    .report-header h2 {
        color: #333;
        margin-bottom: 10px;
    }

    .report-header p {
        color: #666;
        font-size: 1.1em;
    }

    .summary-stats {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
        gap: 20px;
        margin-bottom: 30px;
    }

    .stat-card {
        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
        color: white;
        padding: 20px;
        border-radius: 10px;
        text-align: center;
        box-shadow: 0 4px 6px rgba(0,0,0,0.1);
    }

    .stat-card h3 {
        margin: 0 0 10px 0;
        font-size: 1em;
        opacity: 0.9;
    }

    .stat-value {
        font-size: 2em;
        font-weight: bold;
        margin: 0;
    }

    .department-table {
        font-size: 0.9em;
    }

    .dept-code {
        background: #667eea;
        color: white;
        padding: 4px 8px;
        border-radius: 4px;
        font-size: 0.9em;
    }

    .dept-name {
        font-weight: 600;
        color: #333;
    }

    .manager-badge {
        background: #28a745;
        color: white;
        padding: 3px 8px;
        border-radius: 12px;
        font-size: 0.8em;
        font-weight: 600;
    }

    .no-manager {
        color: #666;
        font-style: italic;
        font-size: 0.9em;
    }

    .location-badge {
        background: #17a2b8;
        color: white;
        padding: 3px 8px;
        border-radius: 12px;
        font-size: 0.8em;
        font-weight: 600;
    }

    .employee-count {
        background: #ffc107;
        color: #333;
        padding: 4px 8px;
        border-radius: 12px;
        font-weight: 600;
        font-size: 0.9em;
    }

    .payroll-total {
        color: #28a745;
        font-weight: 700;
    }

    .text-center {
        text-align: center;
    }

    .mobile-only {
        display: none;
    }

    .department-cards {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
        gap: 20px;
    }

    .department-card {
        background: white;
        border: 1px solid #e0e0e0;
        border-radius: 10px;
        padding: 20px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }

    .card-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 15px;
        padding-bottom: 15px;
        border-bottom: 1px solid #e0e0e0;
    }

    .card-header h3 {
        margin: 0;
        color: #333;
        font-size: 1.1em;
    }

    .card-content {
        margin-bottom: 15px;
    }

    .card-row {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 10px;
    }

    .card-row .label {
        font-weight: 600;
        color: #666;
    }

    .card-row .value {
        font-weight: 600;
        color: #333;
    }

    .card-actions {
        display: flex;
        gap: 10px;
    }

    .btn-sm {
        padding: 8px 15px;
        font-size: 0.9em;
    }

    @media (max-width: 768px) {
        .table-responsive {
            display: none;
        }
        
        .mobile-only {
            display: block;
        }
        
        .summary-stats {
            grid-template-columns: 1fr;
        }
    }
</style>