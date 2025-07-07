<!-- /WEB-INF/jsp/layout/reports/employee-list.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="report-header">
    <h2>Employee List Report</h2>
    <p>Complete employee listing with department and compensation information</p>
</div>

<c:choose>
    <c:when test="${not empty employees}">
        <!-- Summary Statistics -->
        <div class="summary-stats">
            <div class="stat-card">
                <h3>Total Employees</h3>
                <div class="stat-value">${employees.size()}</div>
            </div>
            <div class="stat-card">
                <h3>Total Payroll</h3>
                <div class="stat-value">
                    <c:set var="totalPayroll" value="0" />
                    <c:forEach items="${employees}" var="emp">
                        <c:if test="${emp.SALARY != null}">
                            <c:set var="totalPayroll" value="${totalPayroll + emp.SALARY}" />
                        </c:if>
                    </c:forEach>
                    <fmt:formatNumber value="${totalPayroll}" type="currency" currencySymbol="$" />
                </div>
            </div>
            <div class="stat-card">
                <h3>Average Salary</h3>
                <div class="stat-value">
                    <c:set var="avgSalary" value="0" />
                    <c:set var="salaryCount" value="0" />
                    <c:forEach items="${employees}" var="emp">
                        <c:if test="${emp.SALARY != null}">
                            <c:set var="avgSalary" value="${avgSalary + emp.SALARY}" />
                            <c:set var="salaryCount" value="${salaryCount + 1}" />
                        </c:if>
                    </c:forEach>
                    <c:if test="${salaryCount > 0}">
                        <fmt:formatNumber value="${avgSalary / salaryCount}" type="currency" currencySymbol="$" />
                    </c:if>
                </div>
            </div>
        </div>

        <!-- Filter and Search -->
        <div class="filters-section">
            <h3>Filter & Search</h3>
            <div class="filter-row">
                <div class="filter-group">
                    <label for="deptFilter">Department:</label>
                    <select id="deptFilter" onchange="filterEmployees()">
                        <option value="">All Departments</option>
                        <c:set var="seenDepts" value="${[]}" />
                        <c:forEach items="${employees}" var="emp">
                            <c:if test="${emp.DEPTNAME != null && !fn:contains(seenDepts, emp.DEPTNAME)}">
                                <c:set var="seenDepts" value="${seenDepts}${emp.DEPTNAME}|" />
                                <option value="${emp.DEPTNAME}">${emp.DEPTNAME}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div class="filter-group">
                    <label for="jobFilter">Job Title:</label>
                    <select id="jobFilter" onchange="filterEmployees()">
                        <option value="">All Jobs</option>
                        <c:set var="jobs" value="${[]}" />
                        <c:forEach items="${employees}" var="emp">
                            <c:if test="${emp.JOB != null && !fn:contains(jobs, emp.JOB)}">
                                <c:set var="jobs" value="${jobs}${emp.JOB}" />
                                <option value="${emp.JOB}">${emp.JOB}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div class="filter-group">
                    <label for="searchInput">Search:</label>
                    <input type="text" id="searchInput" placeholder="Search by name..." onkeyup="filterEmployees()">
                </div>
            </div>
        </div>

        <!-- Employee Table -->
        <div class="table-responsive">
            <table id="employeeTable">
                <thead>
                    <tr>
                        <th>Employee</th>
                        <th>Department</th>
                        <th>Job Title</th>
                        <th>Hire Date</th>
                        <th>Salary</th>
                        <th>Bonus</th>
                        <th>Total Comp</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${employees}" var="emp">
                        <tr class="employee-row" 
                            data-dept="${emp.DEPTNAME}"
                            data-job="${emp.JOB}" 
                            data-name="${emp.FIRSTNME} ${emp.LASTNAME}">
                            <td class="employee-info">
                                <div class="employee-card">
                                    <div class="employee-name">
                                        <strong>${emp.FIRSTNME} ${emp.MIDINIT != null ? emp.MIDINIT : ''} ${emp.LASTNAME}</strong>
                                        <span class="employee-id">#${emp.EMPNO}</span>
                                    </div>
                                    <div class="employee-details">
                                        <span class="gender-badge">${emp.SEX == 'M' ? 'F' : 'M'}</span>
                                        <span class="phone">${emp.PHONENO}</span>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <c:if test="${emp.WORKDEPT != null}">
                                    <span class="dept-badge">${emp.WORKDEPT}</span>
                                </c:if>
                            </td>
                            <td class="job-title">${emp.JOB}</td>
                            <td>
                                <c:if test="${emp.HIREDATE != null}">
                                    <fmt:formatDate value="${emp.HIREDATE}" pattern="dd/MM/yyyy" />
                                </c:if>
                            </td>
                            <td class="currency">
                                <c:if test="${emp.SALARY != null}">
                                    <fmt:formatNumber value="${emp.SALARY}" type="currency" currencySymbol="$" />
                                </c:if>
                            </td>
                            <td class="currency">
                                <c:if test="${emp.BONUS != null}">
                                    <fmt:formatNumber value="${emp.BONUS}" type="currency" currencySymbol="$" />
                                </c:if>
                            </td>
                            <td class="currency total-comp">
                                <c:set var="totalComp" value="0" />
                                <c:if test="${emp.SALARY != null}">
                                    <c:set var="totalComp" value="${totalComp + emp.SALARY}" />
                                </c:if>
                                <c:if test="${emp.BONUS != null}">
                                    <c:set var="totalComp" value="${totalComp + emp.BONUS}" />
                                </c:if>
                                <c:if test="${emp.COMM != null}">
                                    <c:set var="totalComp" value="${totalComp + emp.COMM}" />
                                </c:if>
                                <strong>
                                    <fmt:formatNumber value="${totalComp}" type="currency" currencySymbol="$" />
                                </strong>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/employees?action=view&empNo=${emp.EMPNO}" class="action-link">View</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Mobile Cards -->
        <div class="employee-cards mobile-only">
            <c:forEach items="${employees}" var="emp">
                <div class="employee-card-mobile" 
                     data-dept="${emp.WORKDEPT}" 
                     data-job="${emp.JOB}" 
                     data-name="${emp.FIRSTNME} ${emp.LASTNAME}">
                    <div class="card-header">
                        <h3>${emp.FIRSTNME} ${emp.LASTNAME}</h3>
                        <span class="employee-id">#${emp.EMPNO}</span>
                    </div>
                    <div class="card-content">
                        <div class="card-row">
                            <span class="label">Department:</span>
                            <span class="value">
                                <c:if test="${emp.WORKDEPT != null}">
                                    <span class="dept-badge">${emp.WORKDEPT}</span>
                                </c:if>
                            </span>
                        </div>
                        <div class="card-row">
                            <span class="label">Job:</span>
                            <span class="value">${emp.JOB}</span>
                        </div>
                        <div class="card-row">
                            <span class="label">Salary:</span>
                            <span class="value">
                                <c:if test="${emp.SALARY != null}">
                                    <fmt:formatNumber value="${emp.SALARY}" type="currency" currencySymbol="$" />
                                </c:if>
                            </span>
                        </div>
                        <div class="card-row">
                            <span class="label">Hire Date:</span>
                            <span class="value">
                                <c:if test="${emp.HIREDATE != null}">
                                    <fmt:formatDate value="${emp.HIREDATE}" pattern="dd/MM/yyyy" />
                                </c:if>
                            </span>
                        </div>
                    </div>
                    <div class="card-actions">
                        <a href="${pageContext.request.contextPath}/employees?action=view&empNo=${emp.EMPNO}" class="btn btn-sm">View</a>
                    </div>
                </div>
            </c:forEach>
        </div>

    </c:when>
    <c:otherwise>
        <div class="alert alert-info">
            <h3>No employees found</h3>
            <p>There are no employees in the system to display.</p>
            <a href="${pageContext.request.contextPath}/employees?action=add" class="btn">Add First Employee</a>
        </div>
    </c:otherwise>
</c:choose>

<style>
    .report-header {
        text-align: center;
        margin-bottom: 40px;
    }

    .filters-section {
        background: #f8f9fa;
        padding: 20px;
        border-radius: 10px;
        margin-bottom: 30px;
    }

    .filters-section h3 {
        margin-bottom: 15px;
        color: #333;
    }

    .filter-row {
        display: grid;
        grid-template-columns: 1fr 1fr 1fr;
        gap: 15px;
    }

    .filter-group label {
        display: block;
        margin-bottom: 5px;
        font-weight: 600;
    }

    .employee-info {
        min-width: 200px;
    }

    .employee-card {
        display: flex;
        flex-direction: column;
        gap: 5px;
    }

    .employee-name {
        display: flex;
        align-items: center;
        gap: 10px;
    }

    .employee-id {
        background: #6c757d;
        color: white;
        padding: 2px 6px;
        border-radius: 10px;
        font-size: 0.8em;
    }

    .employee-details {
        display: flex;
        align-items: center;
        gap: 10px;
        font-size: 0.9em;
        color: #666;
    }

    .gender-badge {
        font-size: 1.2em;
    }

    .dept-badge {
        background: #667eea;
        color: white;
        padding: 3px 8px;
        border-radius: 12px;
        font-size: 0.9em;
        font-weight: 600;
    }

    .job-title {
        font-weight: 600;
        color: #333;
    }

    .total-comp {
        background: #e8f5e8;
        font-weight: 700;
    }

    .mobile-only {
        display: none;
    }

    .employee-cards {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
        gap: 20px;
    }

    .employee-card-mobile {
        background: white;
        border: 1px solid #e0e0e0;
        border-radius: 10px;
        padding: 20px;
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }

    @media (max-width: 768px) {
        .table-responsive {
            display: none;
        }
        
        .mobile-only {
            display: block;
        }
        
        .filter-row {
            grid-template-columns: 1fr;
        }
    }
</style>

<script>
    function filterEmployees() {
        const deptFilter = document.getElementById('deptFilter').value;
        const jobFilter = document.getElementById('jobFilter').value;
        const searchInput = document.getElementById('searchInput').value.toLowerCase();
        
        const rows = document.querySelectorAll('.employee-row, .employee-card-mobile');
        
        rows.forEach(row => {
            const dept = row.getAttribute('data-dept') || '';
            const job = row.getAttribute('data-job') || '';
            const name = row.getAttribute('data-name') || '';
            
            const matchesDept = !deptFilter || dept === deptFilter;
            const matchesJob = !jobFilter || job === jobFilter;
            const matchesSearch = !searchInput || name.toLowerCase().includes(searchInput);
            
            if (matchesDept && matchesJob && matchesSearch) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        });
    }
</script>