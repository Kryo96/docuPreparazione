package org.example;

import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dao.DepartmentDao.DepartmentReadOperations;
import org.example.dao.DepartmentDao.DepartmentService.DepartmentService;
import org.example.qualifier.ReadOnly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DepartmentServlet", urlPatterns = {"/departments"})
public class DepartmentServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentServlet.class);

    @Inject
    @ReadOnly
    private DepartmentReadOperations readOnlyDept;

    @Inject
    @Default
    private DepartmentService fullDeptService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String deptNo = req.getParameter("deptNo");

        try {
            switch (action == null ? "list" : action) {
                case "list":
                    prepareListData(req);
                    req.setAttribute("content", "department-list.jsp");
                    break;

                case "view":
                    prepareViewData(req, deptNo);
                    req.setAttribute("content", "department-view.jsp");
                    break;

                case "add":
                    prepareAddForm(req);
                    req.setAttribute("content", "department-form.jsp");
                    break;

                case "edit":
                    prepareEditForm(req, deptNo);
                    req.setAttribute("content", "department-form.jsp");
                    break;

                default:
                    prepareListData(req);
                    req.setAttribute("content", "department-list.jsp");
            }

            // Forward al layout principale
            req.getRequestDispatcher("/WEB-INF/jsp/layout/department-layout.jsp").forward(req, resp);

        } catch (Exception e) {
            logger.error("Error in department servlet GET", e);
            req.setAttribute("errorMessage", "Error loading department data: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
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
            logger.error("Error in department servlet POST", e);
            req.setAttribute("errorMessage", "Error processing request: " + e.getMessage());
            req.setAttribute("formData", extractFormData(req));
            req.setAttribute("content", "department-form.jsp");
            req.getRequestDispatcher("/WEB-INF/jsp/layout/department-layout.jsp").forward(req, resp);
        }
    }

    // === METODI DI PREPARAZIONE DATI ===

    private void prepareListData(HttpServletRequest req) {
        List<Map<String, Object>> departments = readOnlyDept.findAll();
        req.setAttribute("departments", departments);
        req.setAttribute("pageTitle", "Department Management");
        req.setAttribute("currentAction", "list");
        req.setAttribute("showAddButton", true);
    }

    private void prepareViewData(HttpServletRequest req, String deptNo) {
        if (deptNo != null && !deptNo.trim().isEmpty()) {
            List<Map<String, Object>> deptData = readOnlyDept.findByDeptNo(deptNo);
            if (!deptData.isEmpty()) {
                req.setAttribute("department", deptData.get(0));
                req.setAttribute("pageTitle", "Department Details - " + deptNo);
            } else {
                req.setAttribute("errorMessage", "Department not found: " + deptNo);
            }
        } else {
            req.setAttribute("errorMessage", "Department number is required");
        }
        req.setAttribute("currentAction", "view");
        req.setAttribute("deptNo", deptNo);
    }

    private void prepareAddForm(HttpServletRequest req) {
        req.setAttribute("pageTitle", "Add New Department");
        req.setAttribute("currentAction", "add");
        req.setAttribute("formAction", "create");
        req.setAttribute("submitButtonText", "Create Department");
        req.setAttribute("cancelUrl", "/departments");

        // Dati per popolare dropdown (se necessario)
        List<Map<String, Object>> allDepartments = readOnlyDept.findAll();
        req.setAttribute("allDepartments", allDepartments);
    }

    private void prepareEditForm(HttpServletRequest req, String deptNo) {
        if (deptNo != null && !deptNo.trim().isEmpty()) {
            List<Map<String, Object>> deptData = readOnlyDept.findByDeptNo(deptNo);
            if (!deptData.isEmpty()) {
                req.setAttribute("department", deptData.get(0));
                req.setAttribute("pageTitle", "Edit Department - " + deptNo);
                req.setAttribute("currentAction", "edit");
                req.setAttribute("formAction", "update");
                req.setAttribute("submitButtonText", "Update Department");
                req.setAttribute("cancelUrl", "/departments?action=view&deptNo=" + deptNo);

                // Dati per dropdown
                List<Map<String, Object>> allDepartments = readOnlyDept.findAll();
                req.setAttribute("allDepartments", allDepartments);
            } else {
                req.setAttribute("errorMessage", "Department not found: " + deptNo);
            }
        } else {
            req.setAttribute("errorMessage", "Department number is required");
        }
    }

    // === METODI DI GESTIONE OPERAZIONI ===

    private void handleCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String deptNo = req.getParameter("deptNo");
        String deptName = req.getParameter("deptName");
        String mgrNo = req.getParameter("mgrNo");
        String admrDept = req.getParameter("admrDept");
        String location = req.getParameter("location");

        // Validazione
        if (isInvalidInput(deptNo, deptName, location)) {
            req.setAttribute("errorMessage", "Required fields: Department Number, Name, and Location");
            req.setAttribute("formData", extractFormData(req));
            prepareAddForm(req);
            return;
        }

        int result = fullDeptService.insertDepartment(deptNo, deptName, mgrNo, admrDept, location);

        if (result > 0) {
            req.getSession().setAttribute("successMessage", "Department created successfully!");
            resp.sendRedirect("/departments?action=view&deptNo=" + deptNo);
        } else {
            req.setAttribute("errorMessage", "Failed to create department. Please check if department number already exists.");
            req.setAttribute("formData", extractFormData(req));
            prepareAddForm(req);
        }
    }

    private void handleUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String deptNo = req.getParameter("deptNo");
        String deptName = req.getParameter("deptName");
        String location = req.getParameter("location");

        if (isInvalidInput(deptNo, deptName, location)) {
            req.setAttribute("errorMessage", "Required fields: Department Number, Name, and Location");
            req.setAttribute("formData", extractFormData(req));
            prepareEditForm(req, deptNo);
            return;
        }

        // Per semplicità, aggiorniamo solo il nome (puoi estendere per altri campi)
        int result = fullDeptService.updateDepartmentName(deptNo, deptName);

        // Se hai altri metodi di update, puoi chiamarli qui
        if (location != null && !location.trim().isEmpty()) {
            fullDeptService.updateDepartmentLocation(deptNo, location);
        }

        if (result > 0) {
            req.getSession().setAttribute("successMessage", "Department updated successfully!");
            resp.sendRedirect("/departments?action=view&deptNo=" + deptNo);
        } else {
            req.setAttribute("errorMessage", "Failed to update department. Department may not exist.");
            req.setAttribute("formData", extractFormData(req));
            prepareEditForm(req, deptNo);
        }
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String deptNo = req.getParameter("deptNo");

        if (deptNo == null || deptNo.trim().isEmpty()) {
            req.getSession().setAttribute("errorMessage", "Department number is required for deletion");
            resp.sendRedirect("/departments");
            return;
        }

        int result = fullDeptService.deleteDepartment(deptNo);

        if (result > 0) {
            req.getSession().setAttribute("successMessage", "Department deleted successfully!");
        } else {
            req.getSession().setAttribute("errorMessage", "Failed to delete department. It may not exist or have related records.");
        }

        resp.sendRedirect("/departments");
    }

    // === METODI HELPER ===

    private boolean isInvalidInput(String deptNo, String deptName, String location) {
        return deptNo == null || deptNo.trim().isEmpty() ||
                deptName == null || deptName.trim().isEmpty() ||
                location == null || location.trim().isEmpty();
    }

    private Map<String, String[]> extractFormData(HttpServletRequest req) {
        return req.getParameterMap();
    }
}