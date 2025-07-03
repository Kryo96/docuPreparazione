<!-- /WEB-INF/jsp/department-detailed.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<h2>🔍 Department Detailed Report</h2>

<c:choose>
    <c:when test="${empty selectedDeptNo}">
        <div class="alert alert-info">
            <strong>Please select a department</strong> from the <a href="?type=summary">summary report</a>.
        </div>
    </c:when>
    <c:when test="${empty departmentDetails}">
        <div class="alert alert-error">
            <strong>No data found</strong> for department ${selectedDeptNo}.
        </div>
    </c:when>
    <c:otherwise>
        <!-- Department Info Card -->
        <div style="background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; padding: 20px; border-radius: 10px; margin-bottom: 30px;">
            <h3>${departmentInfo.DEPTNAME} (${departmentInfo.DEPTNO})</h3>
            <p><strong>Location:</strong> ${departmentInfo.LOCATION}</p>
            <p><strong>Manager:</strong> ${departmentInfo.MGRNO}</p>
        </div>

        <h3>👥 Department Employees</h3>
        <div class="table-responsive">
            <table>
                <thead>
                    <tr>
                        <th>Employee No</th>
                        <th>Name</th>
                        <th>Job</th>
                        <th>Salary</th>
                        <th>Hire Date</th>
                        <th>Role</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="emp" items="${departmentDetails}">
                        <c:if test="${not empty emp.EMPNO}">
                            <tr ${emp.ROLE_IN_DEPT == 'MANAGER' ? 'style="background-color: #fff3cd;"' : ''}>
                                <td><strong>${emp.EMPNO}</strong></td>
                                <td>${emp.FIRSTNME} ${emp.LASTNAME}</td>
                                <td>${emp.JOB}</td>
                                <td class="currency">
                                    <fmt:formatNumber value="${emp.SALARY}" type="currency" currencySymbol="$"/>
                                </td>
                                <td>${emp.HIREDATE}</td>
                                <td>
                                    <c:if test="${emp.ROLE_IN_DEPT == 'MANAGER'}">
                                        <span style="background: #ffd700; color: #333; padding: 3px 8px; border-radius: 12px; font-size: 0.8em; font-weight: bold;">👑 MANAGER</span>
                                    </c:if>
                                    <c:if test="${emp.ROLE_IN_DEPT == 'EMPLOYEE'}">
                                        <span style="background: #e3f2fd; color: #1565c0; padding: 3px 8px; border-radius: 12px; font-size: 0.8em;">👤 EMPLOYEE</span>
                                    </c:if>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div style="display: flex; gap: 15px; margin-top: 20px;">
            <a href="?type=summary" class="btn">← Back to Summary</a>
            <a href="?type=salary" class="btn" style="background: #28a745;">💰 View Salary Analysis</a>
        </div>
    </c:otherwise>
</c:choose>
