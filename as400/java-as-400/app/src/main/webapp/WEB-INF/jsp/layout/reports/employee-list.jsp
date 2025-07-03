<!-- /WEB-INF/jsp/employee-list.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<h2>👥 Employee List with Department Information</h2>

<c:choose>
    <c:when test="${empty employees}">
        <div class="alert alert-info">
            <strong>No employees found.</strong> Please check your data source.
        </div>
    </c:when>
    <c:otherwise>
        <!-- Search/Filter Section -->
        <div style="background: #f8f9fa; padding: 20px; border-radius: 8px; margin-bottom: 20px;">
            <h4>🔍 Filter Employees</h4>
            <form method="get" action="" style="display: flex; gap: 15px; align-items: end; flex-wrap: wrap;">
                <input type="hidden" name="type" value="employees">

                <div class="form-group" style="margin-bottom: 0; min-width: 200px;">
                    <label for="searchName">Search by Name:</label>
                    <input type="text" id="searchName" name="searchName"
                           placeholder="First or Last name..." value="${param.searchName}">
                </div>

                <div class="form-group" style="margin-bottom: 0; min-width: 150px;">
                    <label for="filterDept">Filter by Department:</label>
                    <select id="filterDept" name="filterDept">
                        <option value="">All Departments</option>
                        <c:forEach var="emp" items="${employees}">
                            <option value="${emp.DEPTNO}" ${param.filterDept == emp.DEPTNO ? 'selected' : ''}>
                                ${emp.DEPTNAME}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" class="btn">🔍 Filter</button>
            </form>
        </div>

        <div class="table-responsive">
            <table>
                <thead>
                    <tr>
                        <th>Employee No</th>
                        <th>Name</th>
                        <th>Job</th>
                        <th>Department</th>
                        <th>Location</th>
                        <th>Salary</th>
                        <th>Hire Date</th>
                        <th>Manager</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="emp" items="${employees}" varStatus="status">
                        <tr ${status.index % 2 == 0 ? '' : 'style="background-color: #fafafa;"'}>
                            <td><strong>${emp.EMPNO}</strong></td>
                            <td>
                                <div style="display: flex; align-items: center; gap: 8px;">
                                    <span style="background: ${emp.SEX == 'M' ? '#e3f2fd' : '#fce4ec'};
                                                 color: ${emp.SEX == 'M' ? '#1565c0' : '#ad1457'};
                                                 padding: 2px 6px; border-radius: 10px; font-size: 0.7em;">
                                        ${emp.SEX == 'M' ? '👨' : '👩'}
                                    </span>
                                    <strong>${emp.FIRSTNME} ${emp.LASTNAME}</strong>
                                </div>
                            </td>
                            <td>${emp.JOB}</td>
                            <td>
                                <a href="?type=detailed&deptNo=${emp.DEPTNO}" class="action-link">
                                    ${emp.DEPTNAME}
                                </a>
                            </td>
                            <td>
                                <a href="?type=location&location=${emp.LOCATION}" class="action-link">
                                    📍 ${emp.LOCATION}
                                </a>
                            </td>
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
                            <td>
                                <a href="/employee?empNo=${emp.EMPNO}" class="action-link" target="_blank">
                                    View Profile
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Summary Stats -->
        <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
                    gap: 15px; margin-top: 30px;">
            <div style="background: #e8f5e8; padding: 15px; border-radius: 8px; text-align: center;">
                <h4 style="color: #2e7d32; margin-bottom: 5px;">Total Employees</h4>
                <div style="font-size: 1.8em; font-weight: bold; color: #1b5e20;">${employees.size()}</div>
            </div>

            <div style="background: #fff3e0; padding: 15px; border-radius: 8px; text-align: center;">
                <h4 style="color: #ef6c00; margin-bottom: 5px;">Departments</h4>
                <div style="font-size: 1.8em; font-weight: bold; color: #e65100;">
                    <c:set var="uniqueDepts" value="${0}"/>
                    <c:forEach var="emp" items="${employees}">
                        <c:set var="uniqueDepts" value="${uniqueDepts + 1}"/>
                    </c:forEach>
                    ${uniqueDepts > 0 ? uniqueDepts : 'N/A'}
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>