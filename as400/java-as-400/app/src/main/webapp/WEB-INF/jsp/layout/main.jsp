<!-- /WEB-INF/jsp/layout/main.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} - AS400 Management System</title>

    <!-- ===== CSS LOADING LOGIC ===== -->
    <!-- Always load common CSS first -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">

    <!-- Load module-specific CSS based on moduleType -->
    <c:choose>
        <c:when test="${moduleType == 'employee'}">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/employee.css">
        </c:when>
        <c:when test="${moduleType == 'department'}">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/department.css">
        </c:when>
        <c:when test="${moduleType == 'reports'}">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/reports.css">
        </c:when>
    </c:choose>
</head>
<body class="module-${moduleType}">
    <!-- Include the specific module layout -->
    <jsp:include page="${layoutPath}" />
</body>
</html>