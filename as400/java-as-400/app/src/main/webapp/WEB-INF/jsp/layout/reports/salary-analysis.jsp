<!-- /WEB-INF/jsp/layout/reports/salary-analysis.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="report-header">
    <h2>💰 Salary Analysis Report</h2>
    <p>Comprehensive salary analysis and compensation statistics by department</p>
</div>

<c:choose>
    <c:when test="${not empty salaryStatistics}">
        <!-- Overall Statistics -->
        <div class="overall-stats">
            <h3>📊 Organization Overview</h3>
            <div class="stats-grid">
                <div class="stat-card highlight">
                    <div class="stat-icon">💰</div>
                    <div class="stat-content">
                        <h4>Total Payroll</h4>
                        <div class="stat-value">
                            <c:set var="totalPayroll" value="0" />
                            <c:forEach items="${salaryStatistics}" var="stat">
                                <c:if test="${stat.TOTAL_SALARY != null}">
                                    <c:set var="totalPayroll" value="${totalPayroll + stat.TOTAL_SALARY}" />
                                </c:if>
                            </c:forEach>
                            <fmt:formatNumber value="${totalPayroll}" type="currency" currencySymbol="$" />
                        </div>
                    </div>
                </div>
                
                <div class="stat-card">
                    <div class="stat-icon">📈</div>
                    <div class="stat-content">
                        <h4>Average Salary</h4>
                        <div class="stat-value">
                            <c:set var="avgSalary" value="0" />
                            <c:set var="deptCount" value="0" />
                            <c:forEach items="${salaryStatistics}" var="stat">
                                <c:if test="${stat.AVG_SALARY != null}">
                                    <c:set var="avgSalary" value="${avgSalary + stat.AVG_SALARY}" />
                                    <c:set var="deptCount" value="${deptCount + 1}" />
                                </c:if>
                            </c:forEach>
                            <c:if test="${deptCount > 0}">
                                <fmt:formatNumber value="${avgSalary / deptCount}" type="currency" currencySymbol="$" />
                            </c:if>
                        </div>
                    </div>
                </div>
                
                <div class="stat-card">
                    <div class="stat-icon">🏆</div>
                    <div class="stat-content">
                        <h4>Highest Salary</h4>
                        <div class="stat-value">
                            <c:set var="maxSalary" value="0" />
                            <c:forEach items="${salaryStatistics}" var="stat">
                                <c:if test="${stat.MAX_SALARY != null && stat.MAX_SALARY > maxSalary}">
                                    <c:set var="maxSalary" value="${stat.MAX_SALARY}" />
                                </c:if>
                            </c:forEach>
                            <fmt:formatNumber value="${maxSalary}" type="currency" currencySymbol="$" />
                        </div>
                    </div>
                </div>
                
                <div class="stat-card">
                    <div class="stat-icon">🏢</div>
                    <div class="stat-content">
                        <h4>Departments</h4>
                        <div class="stat-value">${salaryStatistics.size()}</div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Department Salary Breakdown -->
        <div class="salary-breakdown">
            <h3>🏢 Department Salary Breakdown</h3>
            <div class="table-responsive">
                <table class="salary-table">
                    <thead>
                        <tr>
                            <th>Department</th>
                            <th>Employees</th>
                            <th>Total Payroll</th>
                            <th>Average Salary</th>
                            <th>Min Salary</th>
                            <th>Max Salary</th>
                            <th>Salary Range</th>
                            <th>% of Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${salaryStatistics}" var="stat">
                            <tr>
                                <td>
                                    <div class="dept-info">
                                        <strong class="dept-code">${stat.DEPTNO}</strong>
                                        <span class="dept-name">${stat.DEPTNAME}</span>
                                    </div>
                                </td>
                                <td class="text-center">
                                    <span class="employee-count">${stat.EMPLOYEE_COUNT}</span>
                                </td>
                                <td class="currency highlight">
                                    <strong>
                                        <fmt:formatNumber value="${stat.TOTAL_SALARY}" type="currency" currencySymbol="$" />
                                    </strong>
                                </td>
                                <td class="currency">
                                    <fmt:formatNumber value="${stat.AVG_SALARY}" type="currency" currencySymbol="$" />
                                </td>
                                <td class="currency">
                                    <fmt:formatNumber value="${stat.MIN_SALARY}" type="currency" currencySymbol="$" />
                                </td>
                                <td class="currency">
                                    <fmt:formatNumber value="${stat.MAX_SALARY}" type="currency" currencySymbol="$" />
                                </td>
                                <td class="salary-range">
                                    <div class="range-bar">
                                        <div class="range-fill" style="width: ${(stat.MAX_SALARY - stat.MIN_SALARY) / stat.MAX_SALARY * 100}%"></div>
                                    </div>
                                    <small>
                                        <fmt:formatNumber value="${stat.MAX_SALARY - stat.MIN_SALARY}" type="currency" currencySymbol="$" />
                                    </small>
                                </td>
                                <td class="text-center">
                                    <span class="percentage">
                                        <fmt:formatNumber value="${(stat.TOTAL_SALARY / totalPayroll) * 100}" 
                                                        type="number" maxFractionDigits="1" />%
                                    </span>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Visual Charts -->
        <div class="charts-section">
            <h3>📈 Visual Analysis</h3>
            <div class="charts-grid">
                <!-- Salary Distribution Chart -->
                <div class="chart-card">
                    <h4>💰 Salary Distribution by Department</h4>
                    <div class="chart-container">
                        <c:forEach items="${salaryStatistics}" var="stat">
                            <div class="chart-bar">
                                <div class="bar-label">${stat.DEPTNO}</div>
                                <div class="bar-container">
                                    <div class="bar-fill" style="width: ${(stat.TOTAL_SALARY / totalPayroll) * 100}%">
                                        <span class="bar-value">
                                            <fmt:formatNumber value="${stat.TOTAL_SALARY}" type="currency" currencySymbol="$" />
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <!-- Average Salary Comparison -->
                <div class="chart-card">
                    <h4>📊 Average Salary Comparison</h4>
                    <div class="comparison-chart">
                        <c:set var="maxAvgSalary" value="0" />
                        <c:forEach items="${salaryStatistics}" var="stat">
                            <c:if test="${stat.AVG_SALARY > maxAvgSalary}">
                                <c:set var="maxAvgSalary" value="${stat.AVG_SALARY}" />
                            </c:if>
                        </c:forEach>
                        
                        <c:forEach items="${salaryStatistics}" var="stat">
                            <div class="comparison-item">
                                <div class="comparison-label">
                                    <strong>${stat.DEPTNO}</strong>
                                    <span class="comparison-value">
                                        <fmt:formatNumber value="${stat.AVG_SALARY}" type="currency" currencySymbol="$" />
                                    </span>
                                </div>
                                <div class="comparison-bar">
                                    <div class="comparison-fill" style="width: ${(stat.AVG_SALARY / maxAvgSalary) * 100}%"></div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <!-- Key Insights -->
        <div class="insights-section">
            <h3>💡 Key Insights</h3>
            <div class="insights-grid">
                <c:set var="highestPayrollDept" value="" />
                <c:set var="highestPayrollValue" value="0" />
                <c:set var="highestAvgDept" value="" />
                <c:set var="highestAvgValue" value="0" />
                
                <c:forEach items="${salaryStatistics}" var="stat">
                    <c:if test="${stat.TOTAL_SALARY > highestPayrollValue}">
                        <c:set var="highestPayrollDept" value="${stat.DEPTNO}" />
                        <c:set var="highestPayrollValue" value="${stat.TOTAL_SALARY}" />
                    </c:if>
                    <c:if test="${stat.AVG_SALARY > highestAvgValue}">
                        <c:set var="highestAvgDept" value="${stat.DEPTNO}" />
                        <c:set var="highestAvgValue" value="${stat.AVG_SALARY}" />
                    </c:if>
                </c:forEach>

                <div class="insight-card">
                    <div class="insight-icon">🏆</div>
                    <div class="insight-content">
                        <h4>Highest Payroll Department</h4>
                        <p><strong>${highestPayrollDept}</strong> has the highest total payroll with 
                           <strong><fmt:formatNumber value="${highestPayrollValue}" type="currency" currencySymbol="$" /></strong></p>
                    </div>
                </div>
                
                <div class="insight-card">
                    <div class="insight-icon">💎</div>
                    <div class="insight-content">
                        <h4>Highest Average Salary</h4>
                        <p><strong>${highestAvgDept}</strong> has the highest average salary at 
                           <strong><fmt:formatNumber value="${highestAvgValue}" type="currency" currencySymbol="$" /></strong></p>
                    </div>
                </div>
                
                <div class="insight-card">
                    <div class="insight-icon">📊</div>
                    <div class="insight-content">
                        <h4>Organization Health</h4>
                        <p>Total payroll represents <strong>100%</strong> of compensation budget across 
                           <strong>${salaryStatistics.size()}</strong> departments</p>
                    </div>
                </div>
            </div>
        </div>

    </c:when>
    <c:otherwise>
        <div class="alert alert-info">
            <h3>No salary data available</h3>
            <p>There is no salary information available to analyze.</p>
            <a href="${pageContext.request.contextPath}/reports" class="btn">📊 Back to Reports</a>
        </div>
    </c:otherwise>
</c:choose>

<style>
    .report-header {
        text-align: center;
        margin-bottom: 40px;
    }

    .overall-stats {
        margin-bottom: 40px;
    }

    .overall-stats h3 {
        color: #333;
        margin-bottom: 20px;
        text-align: center;
    }

    .stats-grid {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
        gap: 20px;
        margin-bottom: 30px;
    }

    .stat-card {
        background: white;
        border: 2px solid #e0e0e0;
        border-radius: 15px;
        padding: 25px;
        text-align: center;
        box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        transition: transform 0.3s ease;
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 15px;
    }

    .stat-card:hover {
        transform: translateY(-5px);
    }

    .stat-card.highlight {
        background: linear-gradient(135deg, #28a745 0%, #20c997 100%);
        color: white;
        border-color: #28a745;
    }

    .stat-icon {
        font-size: 2.5em;
        opacity: 0.8;
    }

    .stat-content h4 {
        margin: 0 0 10px 0;
        font-size: 1em;
        opacity: 0.9;
    }

    .stat-value {
        font-size: 1.8em;
        font-weight: bold;
        margin: 0;
    }

    .salary-breakdown {
        margin-bottom: 40px;
    }

    .salary-breakdown h3 {
        color: #333;
        margin-bottom: 20px;
    }

    .salary-table {
        font-size: 0.9em;
    }

    .dept-info {
        display: flex;
        flex-direction: column;
        gap: 5px;
    }

    .dept-code {
        background: #667eea;
        color: white;
        padding: 3px 8px;
        border-radius: 4px;
        font-size: 0.9em;
        align-self: flex-start;
    }

    .dept-name {
        font-weight: 600;
        color: #333;
        font-size: 0.9em;
    }

    .employee-count {
        background: #ffc107;
        color: #333;
        padding: 4px 8px;
        border-radius: 12px;
        font-weight: 600;
    }

    .salary-range {
        min-width: 120px;
    }

    .range-bar {
        background: #e0e0e0;
        height: 6px;
        border-radius: 3px;
        margin-bottom: 5px;
        overflow: hidden;
    }

    .range-fill {
        background: linear-gradient(90deg, #28a745, #20c997);
        height: 100%;
        border-radius: 3px;
    }

    .percentage {
        background: #17a2b8;
        color: white;
        padding: 4px 8px;
        border-radius: 12px;
        font-weight: 600;
    }

    .charts-section {
        margin-bottom: 40px;
    }

    .charts-section h3 {
        color: #333;
        margin-bottom: 20px;
    }

    .charts-grid {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 30px;
    }

    .chart-card {
        background: white;
        border: 1px solid #e0e0e0;
        border-radius: 10px;
        padding: 25px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }

    .chart-card h4 {
        margin-bottom: 20px;
        color: #333;
        text-align: center;
    }

    .chart-bar {
        margin-bottom: 15px;
    }

    .bar-label {
        font-weight: 600;
        margin-bottom: 5px;
        color: #333;
    }

    .bar-container {
        background: #f8f9fa;
        border-radius: 6px;
        overflow: hidden;
        position: relative;
    }

    .bar-fill {
        background: linear-gradient(90deg, #667eea, #764ba2);
        height: 30px;
        border-radius: 6px;
        display: flex;
        align-items: center;
        justify-content: flex-end;
        padding: 0 10px;
        color: white;
        font-weight: 600;
        font-size: 0.9em;
    }

    .comparison-item {
        margin-bottom: 15px;
    }

    .comparison-label {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 5px;
    }

    .comparison-value {
        font-weight: 600;
        color: #28a745;
    }

    .comparison-bar {
        background: #e0e0e0;
        height: 8px;
        border-radius: 4px;
        overflow: hidden;
    }

    .comparison-fill {
        background: linear-gradient(90deg, #28a745, #20c997);
        height: 100%;
        border-radius: 4px;
    }

    .insights-section {
        margin-bottom: 40px;
    }

    .insights-section h3 {
        color: #333;
        margin-bottom: 20px;
    }

    .insights-grid {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
        gap: 20px;
    }

    .insight-card {
        background: white;
        border: 1px solid #e0e0e0;
        border-radius: 10px;
        padding: 20px;
        display: flex;
        align-items: center;
        gap: 15px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }

    .insight-icon {
        font-size: 2em;
        opacity: 0.8;
    }

    .insight-content h4 {
        margin: 0 0 10px 0;
        color: #333;
    }

    .insight-content p {
        margin: 0;
        color: #666;
        line-height: 1.4;
    }

    .highlight {
        background: #e8f5e8 !important;
        border-color: #28a745 !important;
    }

    .text-center {
        text-align: center;
    }

    @media (max-width: 768px) {
        .stats-grid {
            grid-template-columns: 1fr;
        }
        
        .charts-grid {
            grid-template-columns: 1fr;
        }
        
        .stat-card {
            flex-direction: column;
            text-align: center;
        }
    }
</style>