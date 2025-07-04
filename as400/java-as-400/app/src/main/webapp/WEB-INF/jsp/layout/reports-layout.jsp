<!-- /WEB-INF/jsp/layout/reports-layout.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>



<body>
    <div class="container">
        <div class="header">
            <h1>Department Employee Reports</h1>
            <p style="text-align: center; opacity: 0.9; font-size: 1.1em;">${pageTitle}</p>
        </div>

        <div class="navigation">
            <div class="nav-links">
                <a href="${pageContext.request.contextPath}/reports?type=dashboard" class="nav-link ${currentReport == 'dashboard' ? 'active' : ''}">Dashboard</a>
                <a href="${pageContext.request.contextPath}/reports?type=summary" class="nav-link ${currentReport == 'summary' ? 'active' : ''}">Department Summary</a>
                <a href="${pageContext.request.contextPath}/reports?type=employees" class="nav-link ${currentReport == 'employees' ? 'active' : ''}">Employee List</a>
                <a href="${pageContext.request.contextPath}/reports?type=salary" class="nav-link ${currentReport == 'salary' ? 'active' : ''}">Salary Analysis</a>
                <a href="${pageContext.request.contextPath}/reports?type=location" class="nav-link ${currentReport == 'location' ? 'active' : ''}">Location Report</a>
                <a href="${pageContext.request.contextPath}/reports?type=analytics" class="nav-link ${currentReport == 'analytics' ? 'active' : ''}">Analytics</a>
            </div>
        </div>

        <!-- Error Messages -->
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-error">
                ${errorMessage}
            </div>
        </c:if>

        <div class="content">
            <jsp:include page="/WEB-INF/jsp/layout/reports/${content}" />
        </div>
    </div>
</body>
</html>