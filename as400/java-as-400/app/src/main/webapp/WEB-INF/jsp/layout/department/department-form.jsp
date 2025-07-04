<!-- /WEB-INF/jsp/layout/department/department-form.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="page-header">
    <h2>
        <c:choose>
            <c:when test="${currentAction == 'add'}">Add New Department</c:when>
            <c:otherwise>Edit Department</c:otherwise>
        </c:choose>
    </h2>
    <a href="${pageContext.request.contextPath}/${cancelUrl}" class="btn btn-secondary">Cancel</a>
</div>

<form method="post" action="${pageContext.request.contextPath}/departments" class="department-form">
    <input type="hidden" name="action" value="${formAction}">
    
    <c:if test="${currentAction == 'edit'}">
        <input type="hidden" name="deptNo" value="${department.DEPTNO}">
    </c:if>

    <!-- Basic Information Section -->
    <div class="form-section">
        <h3>Basic Information</h3>
        
        <div class="form-row">
            <div class="form-group">
                <label for="deptNo">Department Number *</label>
                <input type="text" 
                       id="deptNo" 
                       name="deptNo" 
                       value="${department.DEPTNO != null ? department.DEPTNO : (formData.deptNo != null ? formData.deptNo[0] : '')}"
                       ${currentAction == 'edit' ? 'readonly' : ''} 
                       required
                       maxlength="3"
                       placeholder="e.g., A00, B01, C01">
            </div>
            
            <div class="form-group">
                <label for="deptName">Department Name *</label>
                <input type="text" 
                       id="deptName" 
                       name="deptName" 
                       value="${department.DEPTNAME != null ? department.DEPTNAME : (formData.deptName != null ? formData.deptName[0] : '')}"
                       required
                       maxlength="36"
                       placeholder="e.g., Human Resources, IT Development">
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="location">Location *</label>
                <input type="text" 
                       id="location" 
                       name="location" 
                       value="${department.LOCATION != null ? department.LOCATION : (formData.location != null ? formData.location[0] : '')}"
                       required
                       maxlength="16"
                       placeholder="e.g., New York, London, Milan">
            </div>
            
            <div class="form-group">
                <label for="mgrNo">Manager Employee Number</label>
                <input type="text" 
                       id="mgrNo" 
                       name="mgrNo" 
                       value="${department.MGRNO != null ? department.MGRNO : (formData.mgrNo != null ? formData.mgrNo[0] : '')}"
                       maxlength="6"
                       placeholder="e.g., 000010, 000020">
            </div>
        </div>

        <div class="form-group">
            <label for="admrDept">Administering Department</label>
            <select id="admrDept" name="admrDept">
                <option value="">Select Administering Department</option>
                <c:forEach items="${allDepartments}" var="dept">
                    <option value="${dept.DEPTNO}" 
                            ${(department.ADMRDEPT == dept.DEPTNO || (formData.admrDept != null && formData.admrDept[0] == dept.DEPTNO)) ? 'selected' : ''}>
                        ${dept.DEPTNO} - ${dept.DEPTNAME}
                    </option>
                </c:forEach>
            </select>
            <small class="form-help">Select the department that administers this department (optional)</small>
        </div>
    </div>

    <!-- Additional Information Section -->
    <div class="form-section">
        <h3>Additional Information</h3>
        
        <div class="info-box">
            <h4>Department Guidelines</h4>
            <ul>
                <li><strong>Department Number:</strong> Must be unique, 3 characters (e.g., A00, B01)</li>
                <li><strong>Department Name:</strong> Descriptive name up to 36 characters</li>
                <li><strong>Location:</strong> Physical location where department operates</li>
                <li><strong>Manager:</strong> Employee number of the department manager (optional)</li>
                <li><strong>Admin Department:</strong> Department that oversees this department (optional)</li>
            </ul>
        </div>
    </div>

    <!-- Form Actions -->
    <div class="form-actions">
        <button type="submit" class="btn">
            <c:choose>
                <c:when test="${currentAction == 'add'}">Create Department</c:when>
                <c:otherwise>Update Department</c:otherwise>
            </c:choose>
        </button>
        <a href="${pageContext.request.contextPath}/${cancelUrl}" class="btn btn-secondary">Cancel</a>
    </div>
</form>

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

    .department-form {
        max-width: 700px;
        margin: 0 auto;
    }

    .form-section {
        background: #f8f9fa;
        padding: 25px;
        border-radius: 10px;
        margin-bottom: 30px;
        border: 1px solid #e0e0e0;
    }

    .form-section h3 {
        color: #333;
        margin-bottom: 20px;
        padding-bottom: 10px;
        border-bottom: 2px solid #28a745;
        font-size: 1.3em;
    }

    .form-help {
        color: #666;
        font-size: 0.9em;
        margin-top: 5px;
        display: block;
    }

    .info-box {
        background: white;
        padding: 20px;
        border-radius: 8px;
        border: 1px solid #28a745;
        margin-top: 10px;
    }

    .info-box h4 {
        color: #28a745;
        margin-bottom: 15px;
    }

    .info-box ul {
        margin-left: 20px;
        color: #333;
    }

    .info-box li {
        margin-bottom: 8px;
        line-height: 1.4;
    }

    .form-actions {
        display: flex;
        gap: 15px;
        justify-content: center;
        margin-top: 30px;
        padding-top: 20px;
        border-top: 2px solid #e0e0e0;
    }

    input[readonly] {
        background-color: #e9ecef;
        cursor: not-allowed;
    }

    .form-group input:invalid {
        border-color: #dc3545;
    }

    .form-group input:valid {
        border-color: #28a745;
    }

    .form-group select:focus {
        border-color: #28a745;
    }

    @media (max-width: 768px) {
        .page-header {
            flex-direction: column;
            gap: 15px;
            align-items: stretch;
        }
        
        .form-actions {
            flex-direction: column;
        }
    }
</style>

<script>
    // Auto-uppercase department number
    document.getElementById('deptNo').addEventListener('input', function(e) {
        e.target.value = e.target.value.toUpperCase();
    });

    // Auto-uppercase manager number
    document.getElementById('mgrNo').addEventListener('input', function(e) {
        e.target.value = e.target.value.toUpperCase();
    });

    // Auto-format department name (title case)
    document.getElementById('deptName').addEventListener('blur', function(e) {
        let value = e.target.value.trim();
        if (value) {
            e.target.value = value.charAt(0).toUpperCase() + value.slice(1).toLowerCase();
        }
    });

    // Auto-format location (title case)
    document.getElementById('location').addEventListener('blur', function(e) {
        let value = e.target.value.trim();
        if (value) {
            e.target.value = value.split(' ').map(word => 
                word.charAt(0).toUpperCase() + word.slice(1).toLowerCase()
            ).join(' ');
        }
    });

    // Form validation
    document.querySelector('.department-form').addEventListener('submit', function(e) {
        const deptNo = document.getElementById('deptNo').value.trim();
        const deptName = document.getElementById('deptName').value.trim();
        const location = document.getElementById('location').value.trim();

        if (!deptNo || !deptName || !location) {
            e.preventDefault();
            alert('Please fill in all required fields (Department Number, Name, and Location).');
            return false;
        }

        // Validate department number format
        if (!/^[A-Z][0-9]{2}$/.test(deptNo)) {
            e.preventDefault();
            alert('Department number must be in format: One letter followed by two digits (e.g., A00, B01).');
            return false;
        }

        return true;
    });
</script>