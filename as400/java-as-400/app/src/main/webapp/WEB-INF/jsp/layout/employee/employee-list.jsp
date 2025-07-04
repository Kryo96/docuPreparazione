<!-- /WEB-INF/jsp/layout/employee/employee-list.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="page-header">
    <h2>👥 Employee List</h2>
    <c:if test="${showAddButton}">
        <a href="${pageContext.request.contextPath}/employees?action=add" class="btn">➕ Add New Employee</a>
    </c:if>
</div>

<c:choose>
    <c:when test="${not empty employees}">
        <div class="table-responsive">
            <table>
                <thead>
                    <tr>
                        <th>Employee #</th>
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
                        <tr>
                            <td><strong>${emp.EMPNO}</strong></td>
                            <td>
                                <div style="display: flex; flex-direction: column;">
                                    <span style="font-weight: 600;">${emp.FIRSTNME} ${emp.MIDINIT != null ? emp.MIDINIT : ''} ${emp.LASTNAME}</span>
                                    <span style="font-size: 0.9em; color: #666;">${emp.SEX == 'M' ? '👨' : '👩'} ${emp.SEX}</span>
                                </div>
                            </td>
                            <td>${emp.WORKDEPT}</td>
                            <td>${emp.JOB}</td>
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
                                <a href="${pageContext.request.contextPath}/employees?action=view&empNo=${emp.EMPNO}" class="action-link">👁️ View</a>
                                <a href="${pageContext.request.contextPath}/employees?action=edit&empNo=${emp.EMPNO}" class="action-link">✏️ Edit</a>
                                <a href="${pageContext.request.contextPath}/employees?action=delete&empNo=${emp.EMPNO}" class="action-link delete"
                                   onclick="return confirm('Are you sure you want to delete employee ${emp.FIRSTNME} ${emp.LASTNAME}?')">
                                   🗑️ Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Summary -->
        <div class="alert alert-info">
            📊 Total employees: <strong>${employees.size()}</strong>
        </div>

    </c:when>
    <c:otherwise>
        <div class="alert alert-info">
            <h3>No employees found</h3>
            <p>There are no employees in the system yet.</p>
            <a href="${pageContext.request.contextPath}/employees?action=add" class="btn">➕ Add First Employee</a>
        </div>
    </c:otherwise>
</c:choose>

<style>
    .page-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 30px;
        padding-bottom: 15px;
        border-bottom: 2px solid #e0e0e0;
    }

    .page-header h2 {
        color: #333;
        margin: 0;
    }

    @media (max-width: 768px) {
        .page-header {
            flex-direction: column;
            gap: 15px;
            align-items: stretch;
        }
        
        .page-header .btn {
            text-align: center;
        }
    }
</style>