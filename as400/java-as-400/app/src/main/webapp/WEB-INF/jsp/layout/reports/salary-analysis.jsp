<!-- /WEB-INF/jsp/salary-analysis.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<h2>💰 Salary Analysis by Department</h2>

<c:choose>
    <c:when test="${empty salaryStatistics}">
        <div class="alert alert-info">
            <strong>No salary data found.</strong> Please check your data source.
        </div>
    </c:when>
    <c:otherwise>
        <!-- Salary Overview Cards -->
        <div class="metrics-grid" style="margin-bottom: 30px;">
            <c:set var="totalEmployees" value="0"/>
            <c:set var="totalSalary" value="0"/>
            <c:set var="highestAvg" value="0"/>
            <c:set var="departmentCount" value="${salaryStatistics.size()}"/>

            <c:forEach var="stat" items="${salaryStatistics}">
                <c:set var="totalEmployees" value="${totalEmployees + stat.EMPLOYEE_COUNT}"/>
                <c:set var="totalSalary" value="${totalSalary + stat.TOTAL_SALARY}"/>
                <c:if test="${stat.AVG_SALARY > highestAvg}">
                    <c:set var="highestAvg" value="${stat.AVG_SALARY}"/>
                </c:if>
            </c:forEach>

            <div class="metric-card" style="background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);">
                <h3>Total Departments</h3>
                <div class="value">${departmentCount}</div>
            </div>

            <div class="metric-card" style="background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);">
                <h3>Total Employees</h3>
                <div class="value">${totalEmployees}</div>
            </div>

            <div class="metric-card" style="background: linear-gradient(135deg, #4facfe 0%, #00f2fe 100%);">
                <h3>Total Payroll</h3>
                <div class="value">
                    <fmt:formatNumber value="${totalSalary}" type="currency" currencySymbol="$" maxFractionDigits="0"/>
                </div>
            </div>

            <div class="metric-card" style="background: linear-gradient(135deg, #43e97b 0%, #38f9d7 100%);">
                <h3>Highest Avg Salary</h3>
                <div class="value">
                    <fmt:formatNumber value="${highestAvg}" type="currency" currencySymbol="$" maxFractionDigits="0"/>
                </div>
            </div>
        </div>

        <h3>📊 Detailed Salary Statistics</h3>
        <div class="table-responsive">
            <table>
                <thead>
                    <tr>
                        <th>Department</th>
                        <th>Employee Count</th>
                        <th>Average Salary</th>
                        <th>Min Salary</th>
                        <th>Max Salary</th>
                        <th>Total Cost</th>
                        <th>Salary Range</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="stat" items="${salaryStatistics}" varStatus="status">
                        <c:set var="salaryRange" value="${stat.MAX_SALARY - stat.MIN_SALARY}"/>
                        <c:set var="avgColor" value="${stat.AVG_SALARY > 75000 ? '#2e7d32' : stat.AVG_SALARY > 50000 ? '#f57c00' : '#d32f2f'}"/>

                        <tr>
                            <td>
                                <strong>${stat.DEPTNAME}</strong>
                                <br>
                                <small style="color: #666;">${stat.DEPTNO}</small>
                            </td>
                            <td class="number">
                                <span style="background: #e3f2fd; color: #1565c0; padding: 3px 8px;
                                             border-radius: 12px; font-weight: bold;">
                                    ${stat.EMPLOYEE_COUNT} emp
                                </span>
                            </td>
                            <td class="currency" style="color: ${avgColor}; font-weight: bold;">
                                <fmt:formatNumber value="${stat.AVG_SALARY}" type="currency" currencySymbol="$"/>
                            </td>
                            <td class="currency">
                                <fmt:formatNumber value="${stat.MIN_SALARY}" type="currency" currencySymbol="$"/>
                            </td>
                            <td class="currency">
                                <fmt:formatNumber value="${stat.MAX_SALARY}" type="currency" currencySymbol="$"/>
                            </td>
                            <td class="currency" style="font-weight: bold;">
                                <fmt:formatNumber value="${stat.TOTAL_SALARY}" type="currency" currencySymbol="$"/>
                            </td>
                            <td class="currency" style="color: #666;">
                                <fmt:formatNumber value="${salaryRange}" type="currency" currencySymbol="$"/>
                            </td>
                            <td>
                                <a href="?type=detailed&deptNo=${stat.DEPTNO}" class="action-link">
                                    View Department
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Salary Distribution Chart Placeholder -->
        <h3>📈 Salary Distribution Insights</h3>
        <div style="background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
                    padding: 30px; border-radius: 10px; margin: 20px 0;">
            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); gap: 20px;">
                <div>
                    <h4 style="color: #495057; margin-bottom: 15px;">💡 Key Insights:</h4>
                    <ul style="color: #6c757d; line-height: 1.8;">
                        <li><strong>Highest paying department:</strong>
                            <c:set var="topDept" value="${salaryStatistics[0]}"/>
                            <c:forEach var="stat" items="${salaryStatistics}">
                                <c:if test="${stat.AVG_SALARY > topDept.AVG_SALARY}">
                                    <c:set var="topDept" value="${stat}"/>
                                </c:if>
                            </c:forEach>
                            ${topDept.DEPTNAME} (<fmt:formatNumber value="${topDept.AVG_SALARY}" type="currency" currencySymbol="$"/>)
                        </li>
                        <li><strong>Most employees:</strong>
                            <c:set var="biggestDept" value="${salaryStatistics[0]}"/>
                            <c:forEach var="stat" items="${salaryStatistics}">
                                <c:if test="${stat.EMPLOYEE_COUNT > biggestDept.EMPLOYEE_COUNT}">
                                    <c:set var="biggestDept" value="${stat}"/>
                                </c:if>
                            </c:forEach>
                            ${biggestDept.DEPTNAME} (${biggestDept.EMPLOYEE_COUNT} employees)
                        </li>
                        <li><strong>Total company payroll:</strong>
                            <fmt:formatNumber value="${totalSalary}" type="currency" currencySymbol="$"/>
                        </li>
                    </ul>
                </div>

                <div>
                    <h4 style="color: #495057; margin-bottom: 15px;">📊 Quick Actions:</h4>
                    <div style="display: flex; flex-direction: column; gap: 10px;">
                        <a href="?type=analytics" class="btn" style="text-align: center;">📈 View Advanced Analytics</a>
                        <a href="?type=employees" class="btn" style="background: #28a745; text-align: center;">👥 View All Employees</a>
                        <a href="?type=summary" class="btn" style="background: #17a2b8; text-align: center;">📋 Department Summary</a>
                    </div>
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>