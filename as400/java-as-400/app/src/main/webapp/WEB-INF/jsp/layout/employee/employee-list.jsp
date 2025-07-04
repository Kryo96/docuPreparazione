<!-- /WEB-INF/jsp/layout/employee/employee-list.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="page-header">
    <h2>Employee List</h2>
    <c:if test="${showAddButton}">
        <div class="btn-group">
            <a href="${pageContext.request.contextPath}/employees?action=add" class="btn btn-primary">
                Add New Employee
            </a>
        </div>
    </c:if>
</div>

<c:choose>
    <c:when test="${not empty employees}">
        <!-- Employee Statistics -->
        <div class="employee-stats">
            <div class="stat-card-employee">
                <h3>Total Employees</h3>
                <div class="value">${employees.size()}</div>
            </div>
            <div class="stat-card-employee">
                <h3>Active Records</h3>
                <div class="value">${employees.size()}</div>
            </div>
        </div>

        <!-- Employee Table -->
        <div class="table-responsive">
            <table class="employee-table">
                <thead>
                    <tr>
                        <th>Employee</th>
                        <th>Name</th>
                        <th>Department</th>
                        <th>Job Title</th>
                        <th>Phone</th>
                        <th>Hire Date</th>
                        <th>Salary</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${employees}" var="emp">
                        <tr class="employee-row">
                            <td>
                                <span class="employee-badge">${emp.EMPNO}</span>
                            </td>
                            <td>
                                <div class="employee-list-item">
                                    <div class="employee-name">
                                        <strong>${emp.FIRSTNME} ${emp.MIDINIT != null ? emp.MIDINIT : ''} ${emp.LASTNAME}</strong>
                                    </div>
                                    <div class="employee-details">
                                        <span class="gender-badge"></span>
                                        <span>${emp.SEX}</span>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <c:if test="${not empty emp.WORKDEPT}">
                                    <span class="department-badge">${emp.WORKDEPT}</span>
                                </c:if>
                            </td>
                            <td>
                                <span class="job-title">${emp.JOB}</span>
                            </td>
                            <td>${emp.PHONENO}</td>
                            <td>
                                <fmt:formatDate value="${emp.HIREDATE}" pattern="dd/MM/yyyy" />
                            </td>
                            <td class="currency">
                                <c:choose>
                                    <c:when test="${emp.SALARY != null}">
                                        <fmt:formatNumber value="${emp.SALARY}" type="currency" currencySymbol="$" />
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/employees?action=view&empNo=${emp.EMPNO}"
                                   class="action-link">View</a>
                                <a href="${pageContext.request.contextPath}/employees?action=edit&empNo=${emp.EMPNO}"
                                   class="action-link">Edit</a>
                                <a href="${pageContext.request.contextPath}/employees?action=delete&empNo=${emp.EMPNO}"
                                   class="action-link delete"
                                   onclick="return confirm('Are you sure you want to delete employee ${emp.FIRSTNME} ${emp.LASTNAME}?')">
                                   Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Summary Alert -->
        <div class="alert alert-info">
            Total employees: <strong>${employees.size()}</strong>
        </div>

    </c:when>
    <c:otherwise>
        <div class="alert alert-info">
            <h3>No employees found</h3>
            <p>There are no employees in the system yet.</p>
            <a href="${pageContext.request.contextPath}/employees?action=add" class="btn btn-primary">
                Add First Employee
            </a>
        </div>
    </c:otherwise>
</c:choose>