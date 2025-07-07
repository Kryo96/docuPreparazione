<!-- /WEB-INF/jsp/error.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error - Department Employee Reports</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: var (--primary-color);
            margin: 0;
            padding: 20px;
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .error-container {
            background: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
            max-width: 600px;
            text-align: center;
        }

        .error-icon {
            font-size: 4em;
            margin-bottom: 20px;
        }

        .error-title {
            color: #d32f2f;
            font-size: 2em;
            margin-bottom: 20px;
        }

        .error-message {
            color: #666;
            font-size: 1.1em;
            margin-bottom: 30px;
            line-height: 1.6;
        }

        .error-actions {
            display: flex;
            gap: 15px;
            justify-content: center;
            flex-wrap: wrap;
        }

        .btn {
            background: #667eea;
            color: white;
            padding: 12px 25px;
            border: none;
            border-radius: 6px;
            text-decoration: none;
            font-size: 16px;
            font-weight: 500;
            transition: all 0.3s ease;
            display: inline-block;
        }

        .btn:hover {
            background: #5a67d8;
            transform: translateY(-2px);
        }

        .btn-secondary {
            background: #6c757d;
        }

        .btn-secondary:hover {
            background: #5a6268;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <div class="error-icon"></div>
        <h1 class="error-title">Oops! Something went wrong</h1>

        <div class="error-message">
            <c:choose>
                <c:when test="${not empty errorMessage}">
                    <p><strong>Error Details:</strong></p>
                    <p>${errorMessage}</p>
                </c:when>
                <c:otherwise>
                    <p>We encountered an unexpected error while processing your request.</p>
                    <p>Please try again or contact the system administrator if the problem persists.</p>
                </c:otherwise>
            </c:choose>
        </div>

        <div class="error-actions">
            <a href="${pageContext.request.contextPath}/reports" class="btn">Go to Dashboard</a>
            <a href="javascript:history.back()" class="btn btn-secondary">← Go Back</a>
            <a href="${pageContext.request.contextPath}/reports?type=summary" class="btn btn-secondary">Department Summary</a>
        </div>
    </div>
</body>
</html>