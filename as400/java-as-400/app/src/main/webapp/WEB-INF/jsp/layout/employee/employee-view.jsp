<!-- /WEB-INF/jsp/layout/employee/employee-view.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:choose>
    <c:when test="${not empty employee}">
        <div class="page-header">
            <h2>👤 Employee Details</h2>
            <div class="btn-group">
                <a href="${pageContext.request.contextPath}/employees?action=edit&empNo=${employee.EMPNO}"
                   class="btn btn-primary">✏️ Edit</a>
                <a href="${pageContext.request.contextPath}/employees?action=list"
                   class="btn btn-secondary">📋 Back to List</a>
            </div>
        </div>

        <div class="employee-card fade-in-up">
            <div class="employee-header">
                <h3>${employee.FIRSTNME} ${employee.MIDINIT != null ? employee.MIDINIT : ''} ${employee.LASTNAME}</h3>
                <span class="employee-id">Employee #${employee.EMPNO}</span>
            </div>

            <div class="employee-info">
                <!-- Personal Information -->
                <div class="info-item">
                    <span class="info-label">👤 Full Name:</span>
                    <span class="info-value">${employee.FIRSTNME} ${employee.MIDINIT != null ? employee.MIDINIT : ''} ${employee.LASTNAME}</span>
                </div>

                <div class="info-item">
                    <span class="info-label">🆔 Employee ID:</span>
                    <span class="info-value">${employee.EMPNO}</span>
                </div>

                <div class="info-item">
                    <span class="info-label">👨‍👩‍👧‍👦 Gender:</span>
                    <span class="info-value">${employee.SEX == 'M' ? '👨 Male' : '👩 Female'}</span>
                </div>

                <div class="info-item">
                    <span class="info-label">🎂 Birth Date:</span>
                    <span class="info-value">
                        <c:choose>
                            <c:when test="${employee.BIRTHDATE != null}">
                                <fmt:formatDate value="${employee.BIRTHDATE}" pattern="dd/MM/yyyy" />
                            </c:when>
                            <c:otherwise>Not specified</c:otherwise>
                        </c:choose>
                    </span>
                </div>

                <!-- Work Information -->
                <div class="info-item">
                    <span class="info-label">🏢 Department:</span>
                    <span class="info-value">
                        <c:choose>
                            <c:when test="${not empty employee.WORKDEPT}">
                                <span class="department-badge">${employee.WORKDEPT}</span>
                            </c:when>
                            <c:otherwise>Not assigned</c:otherwise>
                        </c:choose>
                    </span>
                </div>

                <div class="info-item">
                    <span class="info-label">💼 Job Title:</span>
                    <span class="info-value">
                        <c:choose>
                            <c:when test="${not empty employee.JOB}">
                                <span class="job-title">${employee.JOB}</span>
                            </c:when>
                            <c:otherwise>Not specified</c:otherwise>
                        </c:choose>
                    </span>
                </div>

                <div class="info-item">
                    <span class="info-label">📅 Hire Date:</span>
                    <span class="info-value">
                        <c:choose>
                            <c:when test="${employee.HIREDATE != null}">
                                <fmt:formatDate value="${employee.HIREDATE}" pattern="dd/MM/yyyy" />
                            </c:when>
                            <c:otherwise>Not specified</c:otherwise>
                        </c:choose>
                    </span>
                </div>

                <div class="info-item">
                    <span class="info-label">🎓 Education Level:</span>
                    <span class="info-value">${employee.EDLEVEL != null ? employee.EDLEVEL : 'Not specified'}</span>
                </div>

                <!-- Contact Information -->
                <div class="info-item">
                    <span class="info-label">📞 Phone:</span>
                    <span class="info-value">${employee.PHONENO != null ? employee.PHONENO : 'Not provided'}</span>
                </div>

                <!-- Compensation Information -->
                <div class="info-item">
                    <span class="info-label">💰 Salary:</span>
                    <span class="info-value">
                        <c:choose>
                            <c:when test="${employee.SALARY != null}">
                                <strong class="currency">
                                    <fmt:formatNumber value="${employee.SALARY}" type="currency" currencySymbol="$" />
                                </strong>
                            </c:when>
                            <c:otherwise>Not specified</c:otherwise>
                        </c:choose>
                    </span>
                </div>

                <div class="info-item">
                    <span class="info-label">🎁 Bonus:</span>
                    <span class="info-value">
                        <c:choose>
                            <c:when test="${employee.BONUS != null}">
                                <span class="currency">
                                    <fmt:formatNumber value="${employee.BONUS}" type="currency" currencySymbol="$" />
                                </span>
                            </c:when>
                            <c:otherwise>Not applicable</c:otherwise>
                        </c:choose>
                    </span>
                </div>

                <div class="info-item">
                    <span class="info-label">💼 Commission:</span>
                    <span class="info-value">
                        <c:choose>
                            <c:when test="${employee.COMM != null}">
                                <span class="currency">
                                    <fmt:formatNumber value="${employee.COMM}" type="currency" currencySymbol="$" />
                                </span>
                            </c:when>
                            <c:otherwise>Not applicable</c:otherwise>
                        </c:choose>
                    </span>
                </div>
            </div>

            <!-- Action Buttons -->
            <div class="action-buttons">
                <a href="${pageContext.request.contextPath}/employees?action=edit&empNo=${employee.EMPNO}"
                   class="btn btn-primary">✏️ Edit Employee</a>
                <a href="${pageContext.request.contextPath}/employees?action=list"
                   class="btn btn-secondary">📋 Back to List</a>
                <form method="post" action="${pageContext.request.contextPath}/employees" style="display: inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="empNo" value="${employee.EMPNO}">
                    <button type="submit" class="btn btn-danger"
                            onclick="return confirm('Are you sure you want to delete employee ${employee.FIRSTNME} ${employee.LASTNAME}?')">
                        🗑️ Delete Employee
                    </button>
                </form>
            </div>
        </div>

    </c:when>
    <c:otherwise>
        <div class="alert alert-error">
            <h3>❌ Employee Not Found</h3>
            <p>The requested employee could not be found in the system.</p>
            <a href="${pageContext.request.contextPath}/employees?action=list" class="btn btn-primary">
                📋 Back to Employee List
            </a>
        </div>
    </c:otherwise>
</c:choose>