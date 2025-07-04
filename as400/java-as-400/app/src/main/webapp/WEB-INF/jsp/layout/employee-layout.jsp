<!-- /WEB-INF/jsp/layout/employee-layout.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<body>
    <div class="container">
        <div class="header">
            <h1>👥 Employee Management</h1>
            <p style="text-align: center; opacity: 0.9; font-size: 1.1em;">${pageTitle}</p>
        </div>

        <!-- Breadcrumb -->
        <div class="breadcrumb">
            <a href="${pageContext.request.contextPath}/reports">🏠 Home</a> /
            <a href="${pageContext.request.contextPath}/employees">👥 Employees</a>
            <c:if test="${currentAction == 'view'}">
                / <span>View Employee</span>
            </c:if>
            <c:if test="${currentAction == 'add'}">
                / <span>Add Employee</span>
            </c:if>
            <c:if test="${currentAction == 'edit'}">
                / <span>Edit Employee</span>
            </c:if>
        </div>

        <!-- Navigation -->
        <div class="navigation">
            <div class="nav-links">
                <a href="${pageContext.request.contextPath}/employees?action=list" class="nav-link ${currentAction == 'list' ? 'active' : ''}">📋 All Employees</a>
                <a href="${pageContext.request.contextPath}/employees?action=add" class="nav-link ${currentAction == 'add' ? 'active' : ''}">➕ Add Employee</a>
                <a href="${pageContext.request.contextPath}/reports" class="nav-link">📊 Reports</a>
            </div>
        </div>

        <!-- Messages -->
        <c:if test="${not empty sessionScope.successMessage}">
            <div class="alert alert-success">
                ✅ ${sessionScope.successMessage}
            </div>
            <c:remove var="successMessage" scope="session" />
        </c:if>

        <c:if test="${not empty sessionScope.errorMessage}">
            <div class="alert alert-error">
                ❌ ${sessionScope.errorMessage}
            </div>
            <c:remove var="errorMessage" scope="session" />
        </c:if>

        <c:if test="${not empty errorMessage}">
            <div class="alert alert-error">
                ❌ ${errorMessage}
            </div>
        </c:if>

        <!-- Main Content -->
        <div class="content">
            <jsp:include page="/WEB-INF/jsp/layout/employee/${content}" />
        </div>
    </div>
</body>
</html>