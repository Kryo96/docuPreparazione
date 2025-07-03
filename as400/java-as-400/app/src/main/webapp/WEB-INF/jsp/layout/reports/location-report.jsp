<!-- /WEB-INF/jsp/location-report.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<h2>📍 Location Report</h2>

<!-- Location Selector -->
<div style="background: #f8f9fa; padding: 20px; border-radius: 8px; margin-bottom: 30px;">
    <h4>🌍 Select Location</h4>
    <form method="get" action="" style="display: flex; gap: 15px; align-items: end;">
        <input type="hidden" name="type" value="location">

        <div class="form-group" style="margin-bottom: 0; min-width: 250px;">
            <label for="locationSelect">Choose Location:</label>
            <select id="locationSelect" name="location" onchange="this.form.submit()">
                <option value="">-- Select a Location --</option>
                <c:forEach var="loc" items="${allLocations}">
                    <option value="${loc.LOCATION}" ${selectedLocation == loc.LOCATION ? 'selected' : ''}>
                        📍 ${loc.LOCATION} (${loc.EMP_COUNT} employees)
                    </option>
                </c:forEach>
            </select>
        </div>

        <button type="submit" class="btn">🔍 View Report</button>
    </form>
</div>

<c:choose>
    <c:when test="${empty selectedLocation}">
        <!-- Overview of all locations -->
        <h3>🌐 All Locations Overview</h3>
        <div class="table-responsive">
            <table>
                <thead>
                    <tr>
                        <th>Location</th>
                        <th>Employee Count</th>
                        <th>Percentage</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="totalEmployees" value="0"/>
                    <c:forEach var="loc" items="${allLocations}">
                        <c:set var="totalEmployees" value="${totalEmployees + loc.EMP_COUNT}"/>
                    </c:forEach>

                    <c:forEach var="loc" items="${allLocations}">
                        <c:set var="percentage" value="${(loc.EMP_COUNT * 100) / totalEmployees}"/>
                        <tr>
                            <td>
                                <strong>📍 ${loc.LOCATION}</strong>
                            </td>
                            <td class="number">
                                <span style="background: #e3f2fd; color: #1565c0; padding: 5px 10px;
                                             border-radius: 15px; font-weight: bold;">
                                    ${loc.EMP_COUNT}
                                </span>
                            </td>
                            <td class="number">
                                <div style="display: flex; align-items: center; gap: 10px;">
                                    <div style="background: #e0e0e0; height: 20px; width: 100px;
                                                border-radius: 10px; overflow: hidden;">
                                        <div style="background: linear-gradient(90deg, #4facfe 0%, #00f2fe 100%);
                                                    height: 100%; width: ${percentage}%; transition: width 0.3s ease;"></div>
                                    </div>
                                    <span style="font-weight: bold;">
                                        <fmt:formatNumber value="${percentage}" maxFractionDigits="1"/>%
                                    </span>
                                </div>
                            </td>
                            <td>
                                <a href="?type=location&location=${loc.LOCATION}" class="action-link">
                                    View Details
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </c:when>

    <c:when test="${empty employees}">
        <div class="alert alert-error">
            <strong>No employees found</strong> for location: ${selectedLocation}
        </div>
    </c:when>

    <c:otherwise>
        <!-- Location-specific report -->
        <div style="background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                    color: white; padding: 25px; border-radius: 10px; margin-bottom: 30px;">
            <h3>📍 ${selectedLocation} Location Report</h3>
            <p style="opacity: 0.9; font-size: 1.1em;">
                <strong>${employees.size()}</strong> employees working at this location
            </p>
        </div>

        <!-- Department breakdown for this location -->
        <h4>🏢 Departments at ${selectedLocation}</h4>
        <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
                    gap: 15px; margin: 20px 0;">
            <c:set var="deptCounts" value=""/>
            <c:set var="processedDepts" value=""/>

            <c:forEach var="emp" items="${employees}">
                <c:if test="${!processedDepts.contains(emp.DEPTNO)}">
                    <c:set var="deptEmpCount" value="0"/>
                    <c:forEach var="emp2" items="${employees}">
                        <c:if test="${emp2.DEPTNO == emp.DEPTNO}">
                            <c:set var="deptEmpCount" value="${deptEmpCount + 1}"/>
                        </c:if>
                    </c:forEach>

                    <div style="background: white; padding: 15px; border-radius: 8px;
                                box-shadow: 0 2px 4px rgba(0,0,0,0.1); border-left: 4px solid #667eea;">
                        <h5 style="margin: 0 0 5px 0; color: #333;">${emp.DEPTNAME}</h5>
                        <p style="margin: 0; color: #666; font-size: 0.9em;">${emp.DEPTNO}</p>
                        <div style="margin-top: 10px;">
                            <span style="background: #e3f2fd; color: #1565c0; padding: 3px 8px;
                                         border-radius: 12px; font-size: 0.8em; font-weight: bold;">
                                ${deptEmpCount} employees
                            </span>
                        </div>
                    </div>

                    <c:set var="processedDepts" value="${processedDepts},${emp.DEPTNO}"/>
                </c:if>
            </c:forEach>
        </div>

        <h4>👥 Employees at ${selectedLocation}</h4>
        <div class="table-responsive">
            <table>
                <thead>
                    <tr>
                        <th>Employee</th>
                        <th>Department</th>
                        <th>Job Title</th>
                        <th>Salary</th>
                        <th>Hire Date</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="emp" items="${employees}">
                        <tr>
                            <td>
                                <div style="display: flex; align-items: center; gap: 8px;">
                                    <span style="background: ${emp.SEX == 'M' ? '#e3f2fd' : '#fce4ec'};
                                                 color: ${emp.SEX == 'M' ? '#1565c0' : '#ad1457'};
                                                 padding: 2px 6px; border-radius: 10px; font-size: 0.7em;">
                                        ${emp.SEX == 'M' ? '👨' : '👩'}
                                    </span>
                                    <div>
                                        <strong>${emp.FIRSTNME} ${emp.LASTNAME}</strong>
                                        <br>
                                        <small style="color: #666;">${emp.EMPNO}</small>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <a href="?type=detailed&deptNo=${emp.DEPTNO}" class="action-link">
                                    ${emp.DEPTNAME}
                                </a>
                            </td>
                            <td>${emp.JOB}</td>
                            <td class="currency">
                                <fmt:formatNumber value="${emp.SALARY}" type="currency" currencySymbol="$"/>
                            </td>
                            <td>${emp.HIREDATE}</td>
                            <td style="text-align: center;">
                                <c:if test="${emp.IS_MANAGER == 'YES'}">
                                    <span style="background: #ffd700; color: #333; padding: 3px 8px;
                                                 border-radius: 12px; font-size: 0.8em; font-weight: bold;">
                                        👑 MANAGER
                                    </span>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div style="margin-top: 30px;">
            <a href="?type=location" class="btn">← Back to All Locations</a>
        </div>
    </c:otherwise>
</c:choose>