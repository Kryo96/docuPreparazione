<!-- /WEB-INF/jsp/layout/department/department-list.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="page-header">
    <h2>Department List</h2>
    <c:if test="${showAddButton}">
        <a href="${pageContext.request.contextPath}/departments?action=add" class="btn">Add New Department</a>
    </c:if>
</div>

<c:choose>
    <c:when test="${not empty departments}">
        <div class="table-responsive">
            <table>
                <thead>
                    <tr>
                        <th>Department #</th>
                        <th>Department Name</th>
                        <th>Manager</th>
                        <th>Admin Dept</th>
                        <th>Location</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${departments}" var="dept">
                        <tr>
                            <td><strong>${dept.DEPTNO}</strong></td>
                            <td>
                                <div style="display: flex; flex-direction: column;">
                                    <span style="font-weight: 600;">${dept.DEPTNAME}</span>
                                </div>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${dept.MGRNO != null}">
                                        ${dept.MGRNO}
                                    </c:when>
                                    <c:otherwise>
                                        <span style="color: #666; font-style: italic;">No Manager</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${dept.ADMRDEPT != null}">
                                        ${dept.ADMRDEPT}
                                    </c:when>
                                    <c:otherwise>
                                        <span style="color: #666; font-style: italic;">-</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${dept.LOCATION != null}">
                                        ${dept.LOCATION}
                                    </c:when>
                                    <c:otherwise>
                                        <span style="color: #666; font-style: italic;">Not specified</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/departments?action=view&deptNo=${dept.DEPTNO}" class="action-link">View</a>
                                <a href="${pageContext.request.contextPath}/departments?action=edit&deptNo=${dept.DEPTNO}" class="action-link">Edit</a>
                                <a href="${pageContext.request.contextPath}/departments?action=delete&deptNo=${dept.DEPTNO}" class="action-link delete"
                                   onclick="return confirm('Are you sure you want to delete department ${dept.DEPTNAME}?')">
                                   Delete
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Summary -->
        <div class="alert alert-info">
            Total departments: <strong>${departments.size()}</strong>
        </div>

    </c:when>
    <c:otherwise>
        <div class="alert alert-info">
            <h3>No departments found</h3>
            <p>There are no departments in the system yet.</p>
            <a href="${pageContext.request.contextPath}/departments?action=add" class="btn">Add First Department</a>
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