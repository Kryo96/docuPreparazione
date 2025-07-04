<!-- /WEB-INF/jsp/layout/employee/employee-view.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:choose>
    <c:when test="${not empty employee}">
        <div class="page-header">
            <h2>👤 Employee Details</h2>
            <div class="btn-group">
                <a href="${pageContext.request.contextPath}/employees?action=edit&empNo=${employee.EMPNO}" class="btn">✏️ Edit</a>
                <a href="${pageContext.request.contextPath}/employees?action=list" class="btn btn-secondary">📋 Back to List</a>
            </div>
        </div>

        <div class="employee-card">
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
                    <span class="info-value">${employee.WORKDEPT != null ? employee.WORKDEPT : 'Not assigned'}</span>
                </div>

                <div class="info-item">
                    <span class="info-label">💼 Job Title:</span>
                    <span class="info-value">${employee.JOB != null ? employee.JOB : 'Not specified'}</span>
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
                                <strong style="color: #2e7d32;">
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
                                <fmt:formatNumber value="${employee.BONUS}" type="currency" currencySymbol="$" />
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
                                <fmt:formatNumber value="${employee.COMM}" type="currency" currencySymbol="$" />
                            </c:when>
                            <c:otherwise>Not applicable</c:otherwise>
                        </c:choose>
                    </span>
                </div>
            </div>

            <!-- Action Buttons -->
            <div class="action-buttons">
                <a href="${pageContext.request.contextPath}/employees?action=edit&empNo=${employee.EMPNO}" class="btn">✏️ Edit Employee</a>
                <a href="${pageContext.request.contextPath}/employees?action=list" class="btn btn-secondary">📋 Back to List</a>
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
            <a href="${pageContext.request.contextPath}/employees?action=list" class="btn">📋 Back to Employee List</a>
        </div>
    </c:otherwise>
</c:choose>

<style>
    .page-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 30px;
        padding-bottom: 15px;
        border-bottom: 2px solid #e0e0e0;
    }

    .page-header h2 {
        color: #333;
        margin: 0;
    }

    .employee-header {
        text-align: center;
        margin-bottom: 30px;
        padding-bottom: 20px;
        border-bottom: 2px solid #e0e0e0;
    }

    .employee-header h3 {
        color: #333;
        font-size: 1.8em;
        margin-bottom: 10px;
    }

    .employee-id {
        background: #667eea;
        color: white;
        padding: 5px 15px;
        border-radius: 20px;
        font-size: 0.9em;
        font-weight: 600;
    }

    .action-buttons {
        margin-top: 30px;
        padding-top: 20px;
        border-top: 2px solid #e0e0e0;
        display: flex;
        gap: 10px;
        justify-content: center;
        flex-wrap: wrap;
    }

    @media (max-width: 768px) {
        .page-header {
            flex-direction: column;
            gap: 15px;
            align-items: stretch;
        }
        
        .page-header .btn-group {
            display: flex;
            gap: 10px;
        }

        .action-buttons {
            flex-direction: column;
        }
    }
</style>