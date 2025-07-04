package org.example;

import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.EmployeeDao.EmployeeReadOperations;
import org.example.dao.EmployeeDao.EmployeeService.EmployeeService;
import org.example.qualifier.ReadOnly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "EmployeeServlet", urlPatterns = {"/employees"})
public class EmployeeServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServlet.class);

    @Inject
    @ReadOnly
    private EmployeeReadOperations readOnlyEmpl;

    @Inject
    @Default
    private EmployeeService fullEmplService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String empNo = req.getParameter("empNo");

        try {
            switch (action == null ? "list" : action) {
                case "list":
                    prepareListData(req);
                    req.setAttribute("content", "employee-list.jsp");
                    break;

                case "view":
                    prepareViewData(req, empNo);
                    req.setAttribute("content", "employee-view.jsp");
                    break;

                case "add":
                    prepareAddForm(req);
                    req.setAttribute("content", "employee-form.jsp");
                    break;

                case "edit":
                    prepareEditForm(req, empNo);
                    req.setAttribute("content", "employee-form.jsp");
                    break;

                default:
                    prepareListData(req);
                    req.setAttribute("content", "employee-list.jsp");
            }
            req.setAttribute("moduleType", "employee");
            req.setAttribute("layoutPath", "/WEB-INF/jsp/layout/employee-layout.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/layout/main.jsp").forward(req, resp);

        } catch (Exception e) {
            logger.error("Error in employee servlet GET", e);
            req.setAttribute("errorMessage", "Error loading employee data: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/layout/common/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            switch (action == null ? "create" : action) {
                case "create":
                    handleCreate(req, resp);
                    break;

                case "update":
                    handleUpdate(req, resp);
                    break;

                case "delete":
                    handleDelete(req, resp);
                    break;

                default:
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }

        } catch (Exception e) {
            logger.error("Error in employee servlet POST", e);
            req.setAttribute("errorMessage", "Error processing request: " + e.getMessage());
            req.setAttribute("formData", extractFormData(req));
            req.setAttribute("content", "employee-form.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/layout/employee-layout.jsp").forward(req, resp);
        }
    }

    // === METODI DI PREPARAZIONE DATI ===

    private void prepareListData(HttpServletRequest req) {
        List<Map<String, Object>> employees = readOnlyEmpl.findAll();
        req.setAttribute("employees", employees);
        req.setAttribute("pageTitle", "Employee Management");
        req.setAttribute("currentAction", "list");
        req.setAttribute("showAddButton", true);
    }

    private void prepareViewData(HttpServletRequest req, String empNo) {
        if (empNo != null && !empNo.trim().isEmpty()) {
            List<Map<String, Object>> empData = readOnlyEmpl.findByEmpNo(empNo);
            if (!empData.isEmpty()) {
                req.setAttribute("employee", empData.get(0));
                req.setAttribute("pageTitle", "Employee Details - " + empNo);
            } else {
                req.setAttribute("errorMessage", "Employee not found: " + empNo);
            }
        } else {
            req.setAttribute("errorMessage", "Employee number is required");
        }
        req.setAttribute("currentAction", "view");
        req.setAttribute("empNo", empNo);
    }

    private void prepareAddForm(HttpServletRequest req) {
        req.setAttribute("pageTitle", "Add New Employee");
        req.setAttribute("currentAction", "add");
        req.setAttribute("formAction", "create");
        req.setAttribute("submitButtonText", "Create Employee");
        req.setAttribute("cancelUrl", "/employees");

        // Dati per popolare dropdown (dipartimenti, ecc.)
        prepareDepartmentOptions(req);
    }

    private void prepareEditForm(HttpServletRequest req, String empNo) {
        if (empNo != null && !empNo.trim().isEmpty()) {
            List<Map<String, Object>> empData = readOnlyEmpl.findByEmpNo(empNo);
            if (!empData.isEmpty()) {
                req.setAttribute("employee", empData.get(0));
                req.setAttribute("pageTitle", "Edit Employee - " + empNo);
                req.setAttribute("currentAction", "edit");
                req.setAttribute("formAction", "update");
                req.setAttribute("submitButtonText", "Update Employee");
                req.setAttribute("cancelUrl", "/employees?action=view&empNo=" + empNo);

                // Dati per dropdown
                prepareDepartmentOptions(req);
            } else {
                req.setAttribute("errorMessage", "Employee not found: " + empNo);
            }
        } else {
            req.setAttribute("errorMessage", "Employee number is required");
        }
    }

    private void prepareDepartmentOptions(HttpServletRequest req) {
        // Assumo che esista un metodo per ottenere i dipartimenti
        // Se non esiste, puoi commentare questa parte
        try {
            // List<Map<String, Object>> departments = departmentService.findAll();
            // req.setAttribute("departments", departments);
        } catch (Exception e) {
            logger.warn("Could not load departments for dropdown", e);
        }
    }

    // === METODI DI GESTIONE OPERAZIONI ===

    private void handleCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String empNo = req.getParameter("empNo");
        String firstName = req.getParameter("firstName");
        String midInit = req.getParameter("midInit");
        String lastName = req.getParameter("lastName");
        String workDept = req.getParameter("workDept");
        String phoneNo = req.getParameter("phoneNo");
        String hireDate = req.getParameter("hireDate");
        String job = req.getParameter("job");
        String edLevel = req.getParameter("edLevel");
        String sex = req.getParameter("sex");
        String birthDate = req.getParameter("birthDate");
        String salary = req.getParameter("salary");
        String bonus = req.getParameter("bonus");
        String comm = req.getParameter("comm");

        // Validazione
        if (isInvalidInput(empNo, firstName, lastName)) {
            req.setAttribute("errorMessage", "Required fields: Employee Number, First Name, and Last Name");
            req.setAttribute("formData", extractFormData(req));
            prepareAddForm(req);
            req.setAttribute("content", "employee-form.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/layout/employee-layout.jsp").forward(req, resp);
            return;
        }

        int result = fullEmplService.insertEmployee(empNo, firstName, midInit, lastName,
                workDept, phoneNo, hireDate, job, edLevel, sex, birthDate, salary, bonus, comm);

        if (result > 0) {
            req.getSession().setAttribute("successMessage", "Employee created successfully!");
            resp.sendRedirect("/employees?action=view&empNo=" + empNo);
        } else {
            req.setAttribute("errorMessage", "Failed to create employee. Please check if employee number already exists.");
            req.setAttribute("formData", extractFormData(req));
            prepareAddForm(req);
            req.setAttribute("content", "employee-form.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/layout/employee-layout.jsp").forward(req, resp);
        }
    }

    private void handleUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String empNo = req.getParameter("empNo");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        if (isInvalidInput(empNo, firstName, lastName)) {
            req.setAttribute("errorMessage", "Required fields: Employee Number, First Name, and Last Name");
            req.setAttribute("formData", extractFormData(req));
            prepareEditForm(req, empNo);
            req.setAttribute("content", "employee-form.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/layout/employee-layout.jsp").forward(req, resp);
            return;
        }

        // Per semplicità, aggiorniamo solo il nome (puoi estendere per altri campi)
        int result = fullEmplService.updateFirstName(empNo, firstName);

        if (result > 0) {
            req.getSession().setAttribute("successMessage", "Employee updated successfully!");
            resp.sendRedirect("/employees?action=view&empNo=" + empNo);
        } else {
            req.setAttribute("errorMessage", "Failed to update employee. Employee may not exist.");
            req.setAttribute("formData", extractFormData(req));
            prepareEditForm(req, empNo);
            req.setAttribute("content", "employee-form.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/layout/employee-layout.jsp").forward(req, resp);
        }
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String empNo = req.getParameter("empNo");

        if (empNo == null || empNo.trim().isEmpty()) {
            req.getSession().setAttribute("errorMessage", "Employee number is required for deletion");
            resp.sendRedirect("/employees");
            return;
        }

        int result = fullEmplService.deleteEmployee(empNo);

        if (result > 0) {
            req.getSession().setAttribute("successMessage", "Employee deleted successfully!");
        } else {
            req.getSession().setAttribute("errorMessage", "Failed to delete employee. It may not exist or have related records.");
        }

        resp.sendRedirect("/employees");
    }

    // === METODI HELPER ===

    private boolean isInvalidInput(String empNo, String firstName, String lastName) {
        return empNo == null || empNo.trim().isEmpty() ||
                firstName == null || firstName.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty();
    }

    private Map<String, String[]> extractFormData(HttpServletRequest req) {
        return req.getParameterMap();
    }

    /**
     * Metodo helper per gestire valori null
     */
    private String getValueOrEmpty(Map<String, Object> map, String key) {
        Object value = map.get(key);
        return value != null ? value.toString() : "";
    }

    /**
     * Metodo helper per formattare valori monetari
     */
    private String formatCurrency(Object value) {
        if (value == null) return "";
        try {
            double amount = Double.parseDouble(value.toString());
            return String.format("$%.2f", amount);
        } catch (NumberFormatException e) {
            return value.toString();
        }
    }
}