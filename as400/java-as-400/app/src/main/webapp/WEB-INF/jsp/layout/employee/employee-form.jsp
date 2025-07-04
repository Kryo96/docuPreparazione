<!-- /WEB-INF/jsp/layout/employee/employee-form.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<div class="page-header">
    <h2>
        <c:choose>
            <c:when test="${currentAction == 'add'}">➕ Add New Employee</c:when>
            <c:otherwise>✏️ Edit Employee</c:otherwise>
        </c:choose>
    </h2>
    <div class="btn-group">
        <a href="${cancelUrl}" class="btn btn-secondary">❌ Cancel</a>
    </div>
</div>

<form method="post" action="${pageContext.request.contextPath}/employees" class="employee-form fade-in-up">
    <input type="hidden" name="action" value="${formAction}">

    <c:if test="${currentAction == 'edit'}">
        <input type="hidden" name="empNo" value="${employee.EMPNO}">
    </c:if>

    <!-- Personal Information Section -->
    <div class="form-section">
        <h3>👤 Personal Information</h3>

        <div class="form-row">
            <div class="form-group">
                <label for="empNo">Employee Number *</label>
                <input type="text"
                       id="empNo"
                       name="empNo"
                       value="${employee.EMPNO != null ? employee.EMPNO : (formData.empNo != null ? formData.empNo[0] : '')}"
                       ${currentAction == 'edit' ? 'readonly' : ''}
                       required>
            </div>

            <div class="form-group">
                <label for="sex">Gender</label>
                <select id="sex" name="sex">
                    <option value="">Select Gender</option>
                    <option value="M" ${(employee.SEX == 'M' || (formData.sex != null && formData.sex[0] == 'M')) ? 'selected' : ''}>👨 Male</option>
                    <option value="F" ${(employee.SEX == 'F' || (formData.sex != null && formData.sex[0] == 'F')) ? 'selected' : ''}>👩 Female</option>
                </select>
            </div>
        </div>

        <div class="form-row-3">
            <div class="form-group">
                <label for="firstName">First Name *</label>
                <input type="text"
                       id="firstName"
                       name="firstName"
                       value="${employee.FIRSTNME != null ? employee.FIRSTNME : (formData.firstName != null ? formData.firstName[0] : '')}"
                       required>
            </div>

            <div class="form-group">
                <label for="midInit">Middle Initial</label>
                <input type="text"
                       id="midInit"
                       name="midInit"
                       value="${employee.MIDINIT != null ? employee.MIDINIT : (formData.midInit != null ? formData.midInit[0] : '')}"
                       maxlength="1">
            </div>

            <div class="form-group">
                <label for="lastName">Last Name *</label>
                <input type="text"
                       id="lastName"
                       name="lastName"
                       value="${employee.LASTNAME != null ? employee.LASTNAME : (formData.lastName != null ? formData.lastName[0] : '')}"
                       required>
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="birthDate">Birth Date</label>
                <input type="date"
                       id="birthDate"
                       name="birthDate"
                       value="${employee.BIRTHDATE != null ? employee.BIRTHDATE : (formData.birthDate != null ? formData.birthDate[0] : '')}">
            </div>

            <div class="form-group">
                <label for="phoneNo">Phone Number</label>
                <input type="tel"
                       id="phoneNo"
                       name="phoneNo"
                       value="${employee.PHONENO != null ? employee.PHONENO : (formData.phoneNo != null ? formData.phoneNo[0] : '')}"
                       placeholder="e.g., +1-555-123-4567">
            </div>
        </div>
    </div>

    <!-- Work Information Section -->
    <div class="form-section">
        <h3>💼 Work Information</h3>

        <div class="form-row">
            <div class="form-group">
                <label for="workDept">Department</label>
                <input type="text"
                       id="workDept"
                       name="workDept"
                       value="${employee.WORKDEPT != null ? employee.WORKDEPT : (formData.workDept != null ? formData.workDept[0] : '')}"
                       placeholder="e.g., A00, B01, C01">
            </div>

            <div class="form-group">
                <label for="job">Job Title</label>
                <input type="text"
                       id="job"
                       name="job"
                       value="${employee.JOB != null ? employee.JOB : (formData.job != null ? formData.job[0] : '')}"
                       placeholder="e.g., Manager, Developer, Analyst">
            </div>
        </div>

        <div class="form-row">
            <div class="form-group">
                <label for="hireDate">Hire Date</label>
                <input type="date"
                       id="hireDate"
                       name="hireDate"
                       value="${employee.HIREDATE != null ? employee.HIREDATE : (formData.hireDate != null ? formData.hireDate[0] : '')}">
            </div>

            <div class="form-group">
                <label for="edLevel">Education Level</label>
                <select id="edLevel" name="edLevel">
                    <option value="">Select Education Level</option>
                    <option value="16" ${(employee.EDLEVEL == '16' || (formData.edLevel != null && formData.edLevel[0] == '16')) ? 'selected' : ''}>🎓 High School</option>
                    <option value="17" ${(employee.EDLEVEL == '17' || (formData.edLevel != null && formData.edLevel[0] == '17')) ? 'selected' : ''}>📚 Some College</option>
                    <option value="18" ${(employee.EDLEVEL == '18' || (formData.edLevel != null && formData.edLevel[0] == '18')) ? 'selected' : ''}>🎓 Bachelor's Degree</option>
                    <option value="19" ${(employee.EDLEVEL == '19' || (formData.edLevel != null && formData.edLevel[0] == '19')) ? 'selected' : ''}>🎓 Master's Degree</option>
                    <option value="20" ${(employee.EDLEVEL == '20' || (formData.edLevel != null && formData.edLevel[0] == '20')) ? 'selected' : ''}>🎓 PhD/Doctorate</option>
                </select>
            </div>
        </div>
    </div>

    <!-- Compensation Section -->
    <div class="form-section">
        <h3>💰 Compensation</h3>

        <div class="form-row-3">
            <div class="form-group">
                <label for="salary">Salary ($)</label>
                <input type="number"
                       id="salary"
                       name="salary"
                       value="${employee.SALARY != null ? employee.SALARY : (formData.salary != null ? formData.salary[0] : '')}"
                       min="0"
                       step="0.01"
                       placeholder="e.g., 50000.00">
            </div>

            <div class="form-group">
                <label for="bonus">Bonus ($)</label>
                <input type="number"
                       id="bonus"
                       name="bonus"
                       value="${employee.BONUS != null ? employee.BONUS : (formData.bonus != null ? formData.bonus[0] : '')}"
                       min="0"
                       step="0.01"
                       placeholder="e.g., 5000.00">
            </div>

            <div class="form-group">
                <label for="comm">Commission ($)</label>
                <input type="number"
                       id="comm"
                       name="comm"
                       value="${employee.COMM != null ? employee.COMM : (formData.comm != null ? formData.comm[0] : '')}"
                       min="0"
                       step="0.01"
                       placeholder="e.g., 2000.00">
            </div>
        </div>
    </div>

    <!-- Form Actions -->
    <div class="form-actions">
        <button type="submit" class="btn btn-primary">
            <c:choose>
                <c:when test="${currentAction == 'add'}">➕ Create Employee</c:when>
                <c:otherwise>💾 Update Employee</c:otherwise>
            </c:choose>
        </button>
        <a href="${cancelUrl}" class="btn btn-secondary">❌ Cancel</a>
    </div>
</form>

<script>
    // Auto-format phone number
    document.getElementById('phoneNo').addEventListener('input', function(e) {
        let value = e.target.value.replace(/\D/g, '');
        if (value.length >= 10) {
            value = value.substring(0, 10);
            e.target.value = value.replace(/(\d{3})(\d{3})(\d{4})/, '($1) $2-$3');
        }
    });

    // Auto-uppercase department code
    document.getElementById('workDept').addEventListener('input', function(e) {
        e.target.value = e.target.value.toUpperCase();
    });

    // Auto-uppercase employee number
    document.getElementById('empNo').addEventListener('input', function(e) {
        e.target.value = e.target.value.toUpperCase();
    });

    // Form validation enhancements
    document.addEventListener('DOMContentLoaded', function() {
        const form = document.querySelector('.employee-form');
        const inputs = form.querySelectorAll('input[required]');

        inputs.forEach(input => {
            input.addEventListener('blur', function() {
                if (this.value.trim() === '') {
                    this.classList.add('error');
                } else {
                    this.classList.remove('error');
                }
            });
        });

        // Real-time salary formatting
        const salaryInputs = ['salary', 'bonus', 'comm'];
        salaryInputs.forEach(id => {
            const input = document.getElementById(id);
            if (input) {
                input.addEventListener('blur', function() {
                    if (this.value && !isNaN(this.value)) {
                        this.value = parseFloat(this.value).toFixed(2);
                    }
                });
            }
        });
    });
</script>