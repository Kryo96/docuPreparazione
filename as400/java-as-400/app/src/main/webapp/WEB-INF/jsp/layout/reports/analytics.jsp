<!-- /WEB-INF/jsp/layout/reports/analytics.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="report-header">
    <h2>Advanced Analytics Dashboard</h2>
    <p>Deep insights into organizational demographics, performance, and trends</p>
</div>

<div class="analytics-container">
    <!-- Gender Distribution Analysis -->
    <c:if test="${not empty genderDistribution}">
        <div class="analytics-section">
            <h3>Gender Distribution by Department</h3>
            <div class="gender-analysis">
                <div class="gender-overview">
                    <c:set var="totalMale" value="0" />
                    <c:set var="totalFemale" value="0" />
                    <c:forEach items="${genderDistribution}" var="dept">
                        <c:set var="totalMale" value="${totalMale + (dept.MALE_COUNT != null ? dept.MALE_COUNT : 0)}" />
                        <c:set var="totalFemale" value="${totalFemale + (dept.FEMALE_COUNT != null ? dept.FEMALE_COUNT : 0)}" />
                    </c:forEach>
                    <c:set var="totalEmployees" value="${totalMale + totalFemale}" />
                    
                    <div class="overview-stats">
                        <div class="gender-stat male">
                            <div class="stat-icon"></div>
                            <div class="stat-info">
                                <h4>Male Employees</h4>
                                <div class="stat-value">${totalMale}</div>
                                <div class="stat-percent">
                                    <fmt:formatNumber value="${(totalMale / totalEmployees) * 100}" type="number" maxFractionDigits="1" />%
                                </div>
                            </div>
                        </div>
                        
                        <div class="gender-stat female">
                            <div class="stat-icon"></div>
                            <div class="stat-info">
                                <h4>Female Employees</h4>
                                <div class="stat-value">${totalFemale}</div>
                                <div class="stat-percent">
                                    <fmt:formatNumber value="${(totalFemale / totalEmployees) * 100}" type="number" maxFractionDigits="1" />%
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="gender-breakdown">
                    <h4>Department Breakdown</h4>
                    <div class="table-responsive">
                        <table class="analytics-table">
                            <thead>
                                <tr>
                                    <th>Department</th>
                                    <th>Male</th>
                                    <th>Female</th>
                                    <th>Total</th>
                                    <th>Gender Ratio</th>
                                    <th>Diversity Index</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${genderDistribution}" var="dept">
                                    <c:set var="maleCount" value="${dept.MALE_COUNT != null ? dept.MALE_COUNT : 0}" />
                                    <c:set var="femaleCount" value="${dept.FEMALE_COUNT != null ? dept.FEMALE_COUNT : 0}" />
                                    <c:set var="deptTotal" value="${maleCount + femaleCount}" />
                                    <tr>
                                        <td>
                                            <div class="dept-info">
                                                <strong>${dept.DEPTNO}</strong>
                                                <span class="dept-name">${dept.DEPTNAME}</span>
                                            </div>
                                        </td>
                                        <td class="text-center">
                                            <span class="gender-count male">${maleCount}</span>
                                        </td>
                                        <td class="text-center">
                                            <span class="gender-count female">${femaleCount}</span>
                                        </td>
                                        <td class="text-center">
                                            <strong>${deptTotal}</strong>
                                        </td>
                                        <td>
                                            <div class="ratio-bar">
                                                <div class="ratio-male" style="width: ${(maleCount / deptTotal) * 100}%"></div>
                                                <div class="ratio-female" style="width: ${(femaleCount / deptTotal) * 100}%"></div>
                                            </div>
                                            <small>
                                                <fmt:formatNumber value="${(maleCount / deptTotal) * 100}" type="number" maxFractionDigits="0" />% / 
                                                <fmt:formatNumber value="${(femaleCount / deptTotal) * 100}" type="number" maxFractionDigits="0" />%
                                            </small>
                                        </td>
                                        <td class="text-center">
                                            <c:set var="diversity" value="${1 - Math.abs((maleCount - femaleCount) / deptTotal)}" />
                                            <div class="diversity-indicator ${diversity > 0.8 ? 'high' : diversity > 0.5 ? 'medium' : 'low'}">
                                                <fmt:formatNumber value="${diversity * 100}" type="number" maxFractionDigits="0" />%
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </c:if>

    <!-- Location Distribution Analysis -->
    <c:if test="${not empty locationDistribution}">
        <div class="analytics-section">
            <h3>Geographic Distribution</h3>
            <div class="location-analysis">
                <div class="location-stats">
                    <c:forEach items="${locationDistribution}" var="location">
                        <div class="location-card">
                            <div class="location-header">
                                <h4>${location.LOCATION}</h4>
                                <span class="location-count">${location.EMPLOYEE_COUNT} employees</span>
                            </div>
                            <div class="location-details">
                                <div class="location-percentage">
                                    <fmt:formatNumber value="${(location.EMPLOYEE_COUNT / totalEmployees) * 100}" 
                                                    type="number" maxFractionDigits="1" />%
                                </div>
                                <div class="location-bar">
                                    <div class="location-fill" style="width: ${(location.EMPLOYEE_COUNT / totalEmployees) * 100}%"></div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </c:if>

    <!-- Top Performers Analysis -->
    <c:if test="${not empty topEarners}">
        <div class="analytics-section">
            <h3>Top Performers by Department</h3>
            <div class="top-performers">
                <c:forEach items="${topEarners}" var="dept">
                    <div class="department-performers">
                        <div class="dept-header">
                            <h4>${dept.DEPTNO} - ${dept.DEPTNAME}</h4>
                            <span class="dept-location">${dept.LOCATION}</span>
                        </div>
                        
                        <c:if test="${not empty dept.topEmployees}">
                            <div class="performers-grid">
                                <c:forEach items="${dept.topEmployees}" var="emp" varStatus="status">
                                    <div class="performer-card rank-${status.count}">
                                        <div class="rank-badge">#${status.count}</div>
                                        <div class="performer-info">
                                            <h5>${emp.FIRSTNME} ${emp.LASTNAME}</h5>
                                            <div class="performer-details">
                                                <span class="job-title">${emp.JOB}</span>
                                                <span class="emp-id">#${emp.EMPNO}</span>
                                            </div>
                                            <div class="salary-info">
                                                <strong>
                                                    <fmt:formatNumber value="${emp.SALARY}" type="currency" currencySymbol="$" />
                                                </strong>
                                                <c:if test="${emp.BONUS != null && emp.BONUS > 0}">
                                                    <span class="bonus">+ <fmt:formatNumber value="${emp.BONUS}" type="currency" currencySymbol="$" /> bonus</span>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:if>

    <!-- Key Insights and Recommendations -->
    <div class="analytics-section">
        <h3>Key Insights & Recommendations</h3>
        <div class="insights-grid">
            <div class="insight-card diversity">
                <div class="insight-icon"></div>
                <div class="insight-content">
                    <h4>Gender Diversity</h4>
                    <p>Organization maintains a 
                       <strong><fmt:formatNumber value="${(totalMale / totalEmployees) * 100}" type="number" maxFractionDigits="0" />% / 
                               <fmt:formatNumber value="${(totalFemale / totalEmployees) * 100}" type="number" maxFractionDigits="0" />%</strong> 
                       male-to-female ratio.</p>
                    <div class="recommendation">
                        <small>Consider diversity initiatives for better balance</small>
                    </div>
                </div>
            </div>

            <div class="insight-card geography">
                <div class="insight-icon"></div>
                <div class="insight-content">
                    <h4>Geographic Spread</h4>
                    <p>Workforce distributed across <strong>${locationDistribution.size()}</strong> locations.</p>
                    <div class="recommendation">
                        <small>Evaluate remote work opportunities</small>
                    </div>
                </div>
            </div>

            <div class="insight-card performance">
                <div class="insight-icon"></div>
                <div class="insight-content">
                    <h4>Performance Distribution</h4>
                    <p>Top performers identified across all departments.</p>
                    <div class="recommendation">
                        <small>Consider leadership development programs</small>
                    </div>
                </div>
            </div>

            <div class="insight-card growth">
                <div class="insight-icon"></div>
                <div class="insight-content">
                    <h4>Organizational Growth</h4>
                    <p>Balanced structure across departments and locations.</p>
                    <div class="recommendation">
                        <small>Focus on retention strategies for top talent</small>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Advanced Metrics -->
    <div class="analytics-section">
        <h3>🔬 Advanced Metrics</h3>
        <div class="advanced-metrics">
            <div class="metric-card">
                <h4>Diversity Score</h4>
                <div class="metric-value">
                    <c:set var="overallDiversity" value="${1 - Math.abs((totalMale - totalFemale) / totalEmployees)}" />
                    <div class="score-circle ${overallDiversity > 0.8 ? 'high' : overallDiversity > 0.5 ? 'medium' : 'low'}">
                        <fmt:formatNumber value="${overallDiversity * 100}" type="number" maxFractionDigits="0" />%
                    </div>
                </div>
                <p class="metric-description">Organization-wide gender diversity index</p>
            </div>

            <div class="metric-card">
                <h4>Geographic Diversity</h4>
                <div class="metric-value">
                    <div class="score-circle medium">
                        ${locationDistribution.size()}
                    </div>
                </div>
                <p class="metric-description">Number of office locations</p>
            </div>

            <div class="metric-card">
                <h4>Compensation Spread</h4>
                <div class="metric-value">
                    <c:set var="maxSalary" value="0" />
                    <c:set var="minSalary" value="999999" />
                    <c:forEach items="${topEarners}" var="dept">
                        <c:forEach items="${dept.topEmployees}" var="emp">
                            <c:if test="${emp.SALARY > maxSalary}">
                                <c:set var="maxSalary" value="${emp.SALARY}" />
                            </c:if>
                            <c:if test="${emp.SALARY < minSalary}">
                                <c:set var="minSalary" value="${emp.SALARY}" />
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                    <div class="score-circle high">
                        <fmt:formatNumber value="${((maxSalary - minSalary) / maxSalary) * 100}" type="number" maxFractionDigits="0" />%
                    </div>
                </div>
                <p class="metric-description">Salary range variation</p>
            </div>

            <div class="metric-card">
                <h4>Department Balance</h4>
                <div class="metric-value">
                    <div class="score-circle high">
                        ${genderDistribution.size()}
                    </div>
                </div>
                <p class="metric-description">Active departments</p>
            </div>
        </div>
    </div>
</div>

<style>
    .report-header {
        text-align: center;
        margin-bottom: 40px;
    }

    .analytics-container {
        max-width: 1200px;
        margin: 0 auto;
    }

    .analytics-section {
        background: #f8f9fa;
        padding: 30px;
        border-radius: 15px;
        margin-bottom: 30px;
        border: 1px solid #e0e0e0;
    }

    .analytics-section h3 {
        color: #333;
        margin-bottom: 25px;
        text-align: center;
        font-size: 1.5em;
    }

    /* Gender Distribution Styles */
    .gender-overview {
        margin-bottom: 30px;
    }

    .overview-stats {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 30px;
        margin-bottom: 30px;
    }

    .gender-stat {
        background: white;
        padding: 25px;
        border-radius: 15px;
        display: flex;
        align-items: center;
        gap: 20px;
        box-shadow: 0 4px 6px rgba(0,0,0,0.1);
    }

    .gender-stat.male {
        border-left: 5px solid #3498db;
    }

    .gender-stat.female {
        border-left: 5px solid #e74c3c;
    }

    .stat-icon {
        font-size: 3em;
        opacity: 0.8;
    }

    .stat-info h4 {
        margin: 0 0 10px 0;
        color: #333;
        font-size: 1.1em;
    }

    .stat-value {
        font-size: 2.5em;
        font-weight: bold;
        color: #333;
        margin: 0;
    }

    .stat-percent {
        font-size: 1.2em;
        font-weight: 600;
        color: #666;
    }

    .analytics-table {
        font-size: 0.9em;
    }

    .dept-info {
        display: flex;
        flex-direction: column;
        gap: 5px;
    }

    .dept-name {
        font-size: 0.9em;
        color: #666;
    }

    .gender-count {
        padding: 4px 8px;
        border-radius: 12px;
        font-weight: 600;
        color: white;
    }

    .gender-count.male {
        background: #3498db;
    }

    .gender-count.female {
        background: #e74c3c;
    }

    .ratio-bar {
        height: 8px;
        background: #e0e0e0;
        border-radius: 4px;
        display: flex;
        overflow: hidden;
        margin-bottom: 5px;
    }

    .ratio-male {
        background: #3498db;
    }

    .ratio-female {
        background: #e74c3c;
    }

    .diversity-indicator {
        padding: 4px 8px;
        border-radius: 12px;
        font-weight: 600;
        color: white;
    }

    .diversity-indicator.high {
        background: #27ae60;
    }

    .diversity-indicator.medium {
        background: #f39c12;
    }

    .diversity-indicator.low {
        background: #e74c3c;
    }

    /* Location Distribution Styles */
    .location-stats {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
        gap: 20px;
    }

    .location-card {
        background: white;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }

    .location-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 15px;
    }

    .location-header h4 {
        margin: 0;
        color: #333;
    }

    .location-count {
        background: #667eea;
        color: white;
        padding: 4px 8px;
        border-radius: 12px;
        font-size: 0.9em;
        font-weight: 600;
    }

    .location-percentage {
        font-size: 1.5em;
        font-weight: bold;
        color: #333;
        margin-bottom: 10px;
    }

    .location-bar {
        background: #e0e0e0;
        height: 8px;
        border-radius: 4px;
        overflow: hidden;
    }

    .location-fill {
        background: linear-gradient(90deg, #667eea, #764ba2);
        height: 100%;
        border-radius: 4px;
    }

    /* Top Performers Styles */
    .department-performers {
        margin-bottom: 30px;
    }

    .dept-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 20px;
        padding-bottom: 10px;
        border-bottom: 2px solid #e0e0e0;
    }

    .dept-header h4 {
        margin: 0;
        color: #333;
    }

    .dept-location {
        background: #17a2b8;
        color: white;
        padding: 4px 8px;
        border-radius: 12px;
        font-size: 0.9em;
        font-weight: 600;
    }

    .performers-grid {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
        gap: 15px;
    }

    .performer-card {
        background: white;
        padding: 15px;
        border-radius: 10px;
        border: 2px solid #e0e0e0;
        position: relative;
        transition: transform 0.3s ease;
    }

    .performer-card:hover {
        transform: translateY(-2px);
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    }

    .performer-card.rank-1 {
        border-color: #ffd700;
        background: linear-gradient(135deg, #fff9c4, #ffffff);
    }

    .performer-card.rank-2 {
        border-color: #c0c0c0;
        background: linear-gradient(135deg, #f5f5f5, #ffffff);
    }

    .performer-card.rank-3 {
        border-color: #cd7f32;
        background: linear-gradient(135deg, #f4e4bc, #ffffff);
    }

    .rank-badge {
        position: absolute;
        top: -10px;
        right: -10px;
        background: #667eea;
        color: white;
        width: 30px;
        height: 30px;
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        font-weight: bold;
        font-size: 0.9em;
    }

    .performer-info h5 {
        margin: 0 0 10px 0;
        color: #333;
        font-size: 1.1em;
    }

    .performer-details {
        display: flex;
        flex-direction: column;
        gap: 5px;
        margin-bottom: 10px;
    }

    .job-title {
        font-weight: 600;
        color: #666;
        font-size: 0.9em;
    }

    .emp-id {
        font-size: 0.8em;
        color: #999;
    }

    .salary-info {
        display: flex;
        flex-direction: column;
        gap: 5px;
    }

    .bonus {
        font-size: 0.9em;
        color: #28a745;
        font-weight: 600;
    }

    /* Insights Styles */
    .insights-grid {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
        gap: 20px;
    }

    .insight-card {
        background: white;
        padding: 20px;
        border-radius: 10px;
        display: flex;
        align-items: flex-start;
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
        margin: 0 0 10px 0;
        color: #666;
        line-height: 1.4;
    }

    .recommendation {
        background: #e8f5e8;
        padding: 8px 10px;
        border-radius: 6px;
        border-left: 3px solid #28a745;
    }

    .recommendation small {
        color: #155724;
        font-weight: 600;
    }

    /* Advanced Metrics Styles */
    .advanced-metrics {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
        gap: 20px;
    }

    .metric-card {
        background: white;
        padding: 25px;
        border-radius: 15px;
        text-align: center;
        box-shadow: 0 4px 6px rgba(0,0,0,0.1);
    }

    .metric-card h4 {
        margin: 0 0 20px 0;
        color: #333;
        font-size: 1.1em;
    }

    .score-circle {
        width: 80px;
        height: 80px;
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        margin: 0 auto 15px auto;
        font-size: 1.5em;
        font-weight: bold;
        color: white;
    }

    .score-circle.high {
        background: linear-gradient(135deg, #27ae60, #2ecc71);
    }

    .score-circle.medium {
        background: linear-gradient(135deg, #f39c12, #e67e22);
    }

    .score-circle.low {
        background: linear-gradient(135deg, #e74c3c, #c0392b);
    }

    .metric-description {
        color: #666;
        font-size: 0.9em;
        margin: 0;
    }

    .text-center {
        text-align: center;
    }

    @media (max-width: 768px) {
        .overview-stats {
            grid-template-columns: 1fr;
        }
        
        .location-stats {
            grid-template-columns: 1fr;
        }
        
        .performers-grid {
            grid-template-columns: 1fr;
        }
        
        .insights-grid {
            grid-template-columns: 1fr;
        }
        
        .advanced-metrics {
            grid-template-columns: repeat(2, 1fr);
        }
    }
</style>