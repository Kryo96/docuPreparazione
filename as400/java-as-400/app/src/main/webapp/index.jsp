<%-- /webapp/index.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AS400 Employee Management System</title>

    <%-- CSS Files in proper loading order --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css">

    <%-- Preload critical resources --%>
    <link rel="preload" href="${pageContext.request.contextPath}/assets/css/common.css" as="style">
    <link rel="preload" href="${pageContext.request.contextPath}/assets/css/index.css" as="style">
</head>
<body class="homepage">
    <div class="homepage-container">
        <main class="hero-section" role="main">
            <%-- Hero Content --%>
            <header class="hero-header">
                <h1 class="hero-title">AS400 Management System</h1>
                <p class="hero-subtitle">
                    Comprehensive employee and department management solution with advanced reporting capabilities.
                    Streamline your HR operations with our powerful and intuitive platform.
                </p>
            </header>

            <%-- Main Navigation Grid --%>
            <nav class="modules-grid" role="navigation" aria-label="Main navigation">
                <%-- Employee Management Module --%>
                <a href="${pageContext.request.contextPath}/employees"
                   class="module-card employees"
                   aria-label="Employee Management - Manage employee information and records">
                    <span class="module-icon" aria-hidden="true"></span>
                    <h2 class="module-title">Employee Management</h2>
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

                <%-- Department Management Module --%>
                <a href="${pageContext.request.contextPath}/departments"
                   class="module-card departments"
                   aria-label="Department Management - Organize and manage organizational structure">
                    <span class="module-icon" aria-hidden="true"></span>
                    <h2 class="module-title">Department Management</h2>
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

                <%-- Reports & Analytics Module --%>
                <a href="${pageContext.request.contextPath}/reports"
                   class="module-card reports"
                   aria-label="Reports and Analytics - View comprehensive reports and analytics">
                    <span class="module-icon" aria-hidden="true"></span>
                    <h2 class="module-title">Reports & Analytics</h2>
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
            </nav>

            <%-- Statistics Section --%>
            <section class="stats-section" aria-label="System statistics">
                <div class="stat-item">
                    <span class="stat-number" aria-label="Unlimited employees">∞</span>
                    <span class="stat-label">Employees</span>
                </div>
                <div class="stat-item">
                    <span class="stat-number" aria-label="Unlimited departments">∞</span>
                    <span class="stat-label">Departments</span>
                </div>
                <div class="stat-item">
                    <span class="stat-number" aria-label="Unlimited locations">∞</span>
                    <span class="stat-label">Locations</span>
                </div>
                <div class="stat-item">
                    <span class="stat-number" aria-label="24/7 support available">24/7</span>
                    <span class="stat-label">Support</span>
                </div>
            </section>

            <%-- Footer Information --%>
            <footer class="footer-info">
                <p>© 2025 AS400 Management System. Built with modern technologies for optimal performance.</p>
            </footer>
        </main>
    </div>

    <%-- Optional: Add structured data for SEO --%>
    <script type="application/ld+json">
    {
        "@context": "https://schema.org",
        "@type": "WebApplication",
        "name": "AS400 Management System",
        "description": "Comprehensive employee and department management solution",
        "url": "${pageContext.request.contextPath}/",
        "applicationCategory": "BusinessApplication",
        "operatingSystem": "Any",
        "offers": {
            "@type": "Offer",
            "price": "0"
        }
    }
    </script>
</body>
</html>