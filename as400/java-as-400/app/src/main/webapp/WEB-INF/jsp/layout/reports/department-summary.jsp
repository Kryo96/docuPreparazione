<!-- /WEB-INF/jsp/department-summary.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<h2>📋 Department Summary Report</h2>

<c:choose>
    <c:when test="${empty departments}">
        <div class="alert alert-info">
            <strong>No departments found.</strong> Please check your data source.
        </div>
    </c:when>
    <c:otherwise>
        <div class="table-responsive">
            <table>
                <thead>
                    <tr>
                        <th>Dept No</th>
                        <th>Department Name</th>
                        <th>Location</th>
                        <th>Manager</th>
                        <th>Employees</th>
                        <th>Avg Salary</th>
                        <th>Total Cost</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dept" items="${departments}">
                        <tr>
                            <td><strong>${dept.DEPTNO}</strong></td>
                            <td>${dept.DEPTNAME}</td>
                            <td>${dept.LOCATION}</td>
                            <td>${dept.MGRNO}</td>
                            <td class="number">${dept.TOTAL_EMPLOYEES}</td>
                            <td class="currency">
                                <fmt:formatNumber value="${dept.AVG_SALARY}" type="currency" currencySymbol="$"/>
                            </td>
                            <td class="currency">
                                <fmt:formatNumber value="${dept.TOTAL_SALARY_COST}" type="currency" currencySymbol="$"/>
                            </td>
                            <td>
                                <a href="?type=detailed&deptNo=${dept.DEPTNO}" class="action-link">View Details</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="alert alert-info">
            <strong>Total Departments:</strong> ${departments.size()}
        </div>
    </c:otherwise>
</c:choose>