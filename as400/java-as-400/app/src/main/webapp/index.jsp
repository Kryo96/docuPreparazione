<!-- /webapp/index.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AS400 Employee Management System</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .homepage-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            text-align: center;
        }

        .hero-section {
            background: rgba(255, 255, 255, 0.95);
            padding: 60px 40px;
            border-radius: 20px;
            box-shadow: 0 20px 40px rgba(0,0,0,0.1);
            backdrop-filter: blur(10px);
        }

        .hero-title {
            font-size: 3.5em;
            font-weight: 700;
            color: #333;
            margin-bottom: 20px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
        }

        .hero-subtitle {
            font-size: 1.3em;
            color: #666;
            margin-bottom: 50px;
            max-width: 600px;
            margin-left: auto;
            margin-right: auto;
            line-height: 1.6;
        }

        .modules-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
            gap: 30px;
            margin: 50px 0;
        }

        .module-card {
            background: white;
            padding: 40px 30px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
            transition: all 0.3s ease;
            text-decoration: none;
            color: inherit;
            border: 2px solid transparent;
        }

        .module-card:hover {
            transform: translateY(-10px);
            box-shadow: 0 20px 40px rgba(0,0,0,0.15);
            border-color: #667eea;
        }

        .module-icon {
            font-size: 4em;
            margin-bottom: 20px;
            display: block;
        }

        .module-card.employees {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }

        .module-card.departments {
            background: linear-gradient(135deg, #28a745 0%, #20c997 100%);
            color: white;
        }

        .module-card.reports {
            background: linear-gradient(135deg, #fd7e14 0%, #e83e8c 100%);
            color: white;
        }

        .module-title {
            font-size: 1.8em;
            font-weight: 600;
            margin-bottom: 15px;
        }

        .module-description {
            font-size: 1.1em;
            opacity: 0.9;
            line-height: 1.5;
            margin-bottom: 20px;
        }

        .module-features {
            list-style: none;
            padding: 0;
            margin: 20px 0;
        }

        .module-features li {
            padding: 5px 0;
            opacity: 0.8;
        }

        .module-features li:before {
            content: "✓ ";
            font-weight: bold;
            margin-right: 5px;
        }

        .cta-button {
            background: rgba(255,255,255,0.2);
            color: white;
            padding: 12px 25px;
            border: 2px solid rgba(255,255,255,0.3);
            border-radius: 25px;
            text-decoration: none;
            font-weight: 600;
            display: inline-block;
            transition: all 0.3s ease;
            margin-top: 15px;
        }

        .cta-button:hover {
            background: rgba(255,255,255,0.3);
            border-color: rgba(255,255,255,0.5);
            transform: translateY(-2px);
        }

        .stats-section {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
            gap: 20px;
            margin: 40px 0;
            padding: 30px;
            background: rgba(255,255,255,0.1);
            border-radius: 15px;
            backdrop-filter: blur(10px);
        }

        .stat-item {
            text-align: center;
            color: white;
        }

        .stat-number {
            font-size: 2.5em;
            font-weight: bold;
            display: block;
        }

        .stat-label {
            font-size: 0.9em;
            opacity: 0.8;
            text-transform: uppercase;
            letter-spacing: 1px;
        }

        .footer-info {
            margin-top: 40px;
            padding-top: 30px;
            border-top: 1px solid rgba(255,255,255,0.2);
            color: rgba(255,255,255,0.8);
            font-size: 0.9em;
        }

        @media (max-width: 768px) {
            .hero-title {
                font-size: 2.5em;
            }

            .hero-subtitle {
                font-size: 1.1em;
            }

            .modules-grid {
                grid-template-columns: 1fr;
                gap: 20px;
            }

            .module-card {
                padding: 30px 20px;
            }

            .stats-section {
                grid-template-columns: repeat(2, 1fr);
            }
        }

        @media (max-width: 480px) {
            .hero-section {
                padding: 40px 20px;
            }

            .stats-section {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
    <div class="homepage-container">
        <div class="hero-section">
            <h1 class="hero-title">AS400 Management System</h1>
            <p class="hero-subtitle">
                Comprehensive employee and department management solution with advanced reporting capabilities.
                Streamline your HR operations with our powerful and intuitive platform.
            </p>

            <div class="modules-grid">
                <!-- Employee Management Module -->
                <a href="${pageContext.request.contextPath}/employees" class="module-card employees">
                    <span class="module-icon"></span>
                    <h3 class="module-title">Employee Management</h3>
                    <p class="module-description">
                        Complete employee lifecycle management with detailed profiles and comprehensive tracking.
                    </p>
                    <ul class="module-features">
                        <li>Add, edit, and view employees</li>
                        <li>Salary and compensation tracking</li>
                        <li>Department assignments</li>
                        <li>Contact information management</li>
                    </ul>
                    <span class="cta-button">Manage Employees →</span>
                </a>

                <!-- Department Management Module -->
                <a href="${pageContext.request.contextPath}/departments" class="module-card departments">
                    <span class="module-icon"></span>
                    <h3 class="module-title">Department Management</h3>
                    <p class="module-description">
                        Organize and manage organizational structure with department hierarchy and administration.
                    </p>
                    <ul class="module-features">
                        <li>Create and manage departments</li>
                        <li>Assign managers and locations</li>
                        <li>Track department metrics</li>
                        <li>Organizational hierarchy</li>
                    </ul>
                    <span class="cta-button">Manage Departments →</span>
                </a>

                <!-- Reports & Analytics Module -->
                <a href="${pageContext.request.contextPath}/reports" class="module-card reports">
                    <span class="module-icon"></span>
                    <h3 class="module-title">Reports & Analytics</h3>
                    <p class="module-description">
                        Powerful reporting and analytics tools for data-driven decision making and insights.
                    </p>
                    <ul class="module-features">
                        <li>Real-time dashboards</li>
                        <li>Salary analysis and trends</li>
                        <li>Geographic distribution</li>
                        <li>Advanced analytics</li>
                    </ul>
                    <span class="cta-button">View Reports →</span>
                </a>
            </div>

            <div class="stats-section">
                <div class="stat-item">
                    <span class="stat-number">∞</span>
                    <span class="stat-label">Employees</span>
                </div>
                <div class="stat-item">
                    <span class="stat-number">∞</span>
                    <span class="stat-label">Departments</span>
                </div>
                <div class="stat-item">
                    <span class="stat-number">∞</span>
                    <span class="stat-label">Locations</span>
                </div>
                <div class="stat-item">
                    <span class="stat-number">24/7</span>
                    <span class="stat-label">Support</span>
                </div>
            </div>

            <div class="footer-info">
                <p>© 2025 AS400 Management System. Built with modern technologies for optimal performance.</p>
            </div>
        </div>
    </div>
</body>
</html>