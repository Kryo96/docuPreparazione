<!-- /WEB-INF/jsp/layout/main.jsp -->
%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} - Department Employee Reports</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f5f5f5;
            line-height: 1.6;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        .header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 20px 0;
            margin-bottom: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }

        .header h1 {
            text-align: center;
            font-size: 2.5em;
            margin-bottom: 10px;
        }

        .navigation {
            background: white;
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 30px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        .nav-links {
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
            gap: 15px;
        }

        .nav-link {
            padding: 10px 20px;
            text-decoration: none;
            color: #333;
            border: 2px solid #e0e0e0;
            border-radius: 25px;
            transition: all 0.3s ease;
            font-weight: 500;
        }

        .nav-link:hover, .nav-link.active {
            background: #667eea;
            color: white;
            border-color: #667eea;
            transform: translateY(-2px);
        }

        .content {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            margin-bottom: 30px;
        }

        .metrics-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }

        .metric-card {
            background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
            color: white;
            padding: 25px;
            border-radius: 15px;
            text-align: center;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            transform: translateY(0);
            transition: transform 0.3s ease;
        }

        .metric-card:hover {
            transform: translateY(-5px);
        }

        .metric-card h3 {
            font-size: 1.2em;
            margin-bottom: 10px;
            opacity: 0.9;
        }

        .metric-card .value {
            font-size: 2.5em;
            font-weight: bold;
            margin: 10px 0;
        }

        .table-responsive {
            overflow-x: auto;
            margin: 20px 0;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            background: white;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        th {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 15px;
            text-align: left;
            font-weight: 600;
            text-transform: uppercase;
            font-size: 0.9em;
            letter-spacing: 0.5px;
        }

        td {
            padding: 12px 15px;
            border-bottom: 1px solid #f0f0f0;
        }

        tr:hover {
            background-color: #f8f9ff;
        }

        .currency {
            text-align: right;
            font-weight: 600;
            color: #2e7d32;
        }

        .number {
            text-align: right;
            font-weight: 600;
        }

        .action-link {
            color: #667eea;
            text-decoration: none;
            font-weight: 500;
            padding: 5px 10px;
            border-radius: 4px;
            transition: all 0.3s ease;
        }

        .action-link:hover {
            background: #667eea;
            color: white;
        }

        .alert {
            padding: 15px;
            margin: 20px 0;
            border-radius: 8px;
            font-weight: 500;
        }

        .alert-error {
            background: #ffebee;
            color: #c62828;
            border-left: 4px solid #c62828;
        }

        .alert-info {
            background: #e3f2fd;
            color: #1565c0;
            border-left: 4px solid #1565c0;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: 600;
            color: #333;
        }

        .form-group select, .form-group input {
            width: 100%;
            padding: 10px;
            border: 2px solid #e0e0e0;
            border-radius: 6px;
            font-size: 16px;
            transition: border-color 0.3s ease;
        }

        .form-group select:focus, .form-group input:focus {
            outline: none;
            border-color: #667eea;
        }

        .btn {
            background: #667eea;
            color: white;
            padding: 12px 25px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 16px;
            font-weight: 500;
            transition: all 0.3s ease;
        }

        .btn:hover {
            background: #5a67d8;
            transform: translateY(-2px);
        }

        @media (max-width: 768px) {
            .container {
                padding: 10px;
            }

            .nav-links {
                flex-direction: column;
                align-items: center;
            }

            .metrics-grid {
                grid-template-columns: 1fr;
            }

            .header h1 {
                font-size: 2em;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Department Employee Reports</h1>
            <p style="text-align: center; opacity: 0.9; font-size: 1.1em;">${pageTitle}</p>
        </div>

        <div class="navigation">
            <div class="nav-links">
                <a href="?type=dashboard" class="nav-link ${currentReport == 'dashboard' ? 'active' : ''}">📊 Dashboard</a>
                <a href="?type=summary" class="nav-link ${currentReport == 'summary' ? 'active' : ''}">📋 Department Summary</a>
                <a href="?type=employees" class="nav-link ${currentReport == 'employees' ? 'active' : ''}">👥 Employee List</a>
                <a href="?type=salary" class="nav-link ${currentReport == 'salary' ? 'active' : ''}">💰 Salary Analysis</a>
                <a href="?type=location" class="nav-link ${currentReport == 'location' ? 'active' : ''}">📍 Location Report</a>
                <a href="?type=analytics" class="nav-link ${currentReport == 'analytics' ? 'active' : ''}">📈 Analytics</a>
            </div>
        </div>

        <div class="content">
            <jsp:include page="${content}" />
        </div>
    </div>
</body>
</html>