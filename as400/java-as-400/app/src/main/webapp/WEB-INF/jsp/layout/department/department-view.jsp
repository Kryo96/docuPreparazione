<!-- /WEB-INF/jsp/layout/department/department-view.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:choose>
    <c:when test="${not empty department}">
        <div class="page-header">
            <h2>🏢 Department Details</h2>
            <div class="btn-group">
                <a href="${pageContext.request.contextPath}/departments?action=edit&deptNo=${department.DEPTNO}" class="btn">✏️ Edit</a>
                <a href="${pageContext.request.contextPath}/departments?action=list" class="btn btn-secondary">📋 Back to List</a>
            </div>
        </div>

        <div class="department-card">
            <div class="department-header">
                <h3>${department.DEPTNAME}</h3>
                <span class="department-id">Department #${department.DEPTNO}</span>
            </div>

            <div class="department-info">
                <!-- Basic Information -->
                <div class="info-item">
                    <span class="info-label">🏢 Department Name:</span>
                    <span class="info-value">${department.DEPTNAME}</span>
                </div>

                <div class="info-item">
                    <span class="info-label">🆔 Department Number:</span>
                    <span class="info-value">${department.DEPTNO}</span>
                </div>

                <div class="info-item">
                    <span class="info-label">👤 Manager:</span>
                    <span class="info-value">
                        <c:choose>
                            <c:when test="${department.MGRNO != null}">
                                <strong>${department.MGRNO}</strong>
                            </c:when>
                            <c:otherwise>
                                <span style="color: #666; font-style: italic;">No Manager Assigned</span>
                            </c:otherwise>
                        </c:choose>
                    </span>
                </div>

                <div class="info-item">
                    <span class="info-label">🏛️ Admin Department:</span>
                    <span class="info-value">
                        <c:choose>
                            <c:when test="${department.ADMRDEPT != null}">
                                ${department.ADMRDEPT}
                            </c:when>
                            <c:otherwise>
                                <span style="color: #666; font-style: italic;">Not specified</span>
                            </c:otherwise>
                        </c:choose>
                    </span>
                </div>

                <div class="info-item">
                    <span class="info-label">📍 Location:</span>
                    <span class="info-value">
                        <c:choose>
                            <c:when test="${department.LOCATION != null}">
                                <strong style="color: #28a745;">${department.LOCATION}</strong>
                            </c:when>
                            <c:otherwise>
                                <span style="color: #666; font-style: italic;">Not specified</span>
                            </c:otherwise>
                        </c:choose>
                    </span>
                </div>

                <!-- Additional Information Section -->
                <div class="info-item full-width">
                    <span class="info-label">📊 Department Status:</span>
                    <span class="info-value">
                        <span style="background: #28a745; color: white; padding: 3px 8px; border-radius: 12px; font-size: 0.9em;">
                            ✅ Active
                        </span>
                    </span>
                </div>
            </div>

            <!-- Quick Actions -->
            <div class="quick-actions">
                <h4>📋 Quick Actions</h4>
                <div class="action-grid">
                    <a href="${pageContext.request.contextPath}/reports?type=detailed&deptNo=${department.DEPTNO}" class="action-card">
                        <div class="action-icon">📊</div>
                        <div class="action-text">
                            <strong>Department Report</strong>
                            <small>View detailed department report</small>
                        </div>
                    </a>
                    
                    <a href="${pageContext.request.contextPath}/employees?deptNo=${department.DEPTNO}" class="action-card">
                        <div class="action-icon">👥</div>
                        <div class="action-text">
                            <strong>View Employees</strong>
                            <small>See all department employees</small>
                        </div>
                    </a>
                    
                    <a href="${pageContext.request.contextPath}/reports?type=salary&deptNo=${department.DEPTNO}" class="action-card">
                        <div class="action-icon">💰</div>
                        <div class="action-text">
                            <strong>Salary Analysis</strong>
                            <small>Department salary statistics</small>
                        </div>
                    </a>
                </div>
            </div>

            <!-- Action Buttons -->
            <div class="action-buttons">
                <a href="${pageContext.request.contextPath}/departments?action=edit&deptNo=${department.DEPTNO}" class="btn">✏️ Edit Department</a>
                <a href="${pageContext.request.contextPath}/departments?action=list" class="btn btn-secondary">📋 Back to List</a>
                <form method="post" action="${pageContext.request.contextPath}/departments" style="display: inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="deptNo" value="${department.DEPTNO}">
                    <button type="submit" class="btn btn-danger" 
                            onclick="return confirm('Are you sure you want to delete department ${department.DEPTNAME}?')">
                        🗑️ Delete Department
                    </button>
                </form>
            </div>
        </div>

    </c:when>
    <c:otherwise>
        <div class="alert alert-error">
            <h3>❌ Department Not Found</h3>
            <p>The requested department could not be found in the system.</p>
            <a href="${pageContext.request.contextPath}/departments?action=list" class="btn">🏢 Back to Department List</a>
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

    .department-header {
        text-align: center;
        margin-bottom: 30px;
        padding-bottom: 20px;
        border-bottom: 2px solid #e0e0e0;
    }

    .department-header h3 {
        color: #333;
        font-size: 1.8em;
        margin-bottom: 10px;
    }

    .department-id {
        background: #28a745;
        color: white;
        padding: 5px 15px;
        border-radius: 20px;
        font-size: 0.9em;
        font-weight: 600;
    }

    .info-item.full-width {
        grid-column: 1 / -1;
    }

    .quick-actions {
        margin-top: 30px;
        padding-top: 20px;
        border-top: 2px solid #e0e0e0;
    }

    .quick-actions h4 {
        color: #333;
        margin-bottom: 20px;
        text-align: center;
    }

    .action-grid {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
        gap: 15px;
        margin-bottom: 20px;
    }

    .action-card {
        display: flex;
        align-items: center;
        padding: 15px;
        background: #f8f9fa;
        border: 2px solid #e0e0e0;
        border-radius: 10px;
        text-decoration: none;
        color: #333;
        transition: all 0.3s ease;
    }

    .action-card:hover {
        background: #28a745;
        color: white;
        transform: translateY(-2px);
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    }

    .action-icon {
        font-size: 1.5em;
        margin-right: 15px;
    }

    .action-text {
        display: flex;
        flex-direction: column;
    }

    .action-text strong {
        margin-bottom: 5px;
    }

    .action-text small {
        color: #666;
        font-size: 0.9em;
    }

    .action-card:hover .action-text small {
        color: rgba(255,255,255,0.8);
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

        .action-grid {
            grid-template-columns: 1fr;
        }

        .action-buttons {
            flex-direction: column;
        }
    }
</style>