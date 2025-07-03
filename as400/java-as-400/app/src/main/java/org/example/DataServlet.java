package org.example;

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
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/data")
public class DataServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DataServlet.class);

    @Inject
    @ReadOnly
    private DepartmentReadOperations readOnlyDept;

    @Inject
    private DepartmentService fullDeptService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            out.println("<html><body>");
            out.println("<h2>Department Data</h2>");

            // Chiamata al servizio read-only
            List<Map<String, Object>> departments = readOnlyDept.findAll();

            if (departments != null && !departments.isEmpty()) {
                printTable(out, departments);
            } else {
                out.println("<p>No departments found.</p>");
            }

            out.println("</body></html>");

        } catch (Exception e) {
            logger.error("Errore durante l'accesso ai dati dei dipartimenti", e);
            handleError(resp, "Errore interno del server");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Esempio di operazione di scrittura
        String deptNo = req.getParameter("deptNo");
        String deptName = req.getParameter("deptName");
        String mgrNo = req.getParameter("mgrNo");
        String admrDept = req.getParameter("admrDept");
        String location = req.getParameter("location");

        try {
            // Usa il servizio completo per operazioni di scrittura
            int result = fullDeptService.insertDepartment(deptNo, deptName, mgrNo, admrDept, location);

            if (result > 0) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().println("Department created successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Failed to create department");
            }

        } catch (Exception e) {
            logger.error("Errore durante la creazione del dipartimento", e);
            handleError(resp, "Errore durante la creazione");
        }
    }

    /**
     * Metodo per stampare una tabella HTML con i dati dei dipartimenti
     */
    private void printTable(PrintWriter out, List<Map<String, Object>> departments) {
        if (departments == null || departments.isEmpty()) {
            out.println("<p>No data available</p>");
            return;
        }

        out.println("<table border='1' cellpadding='5' cellspacing='0'>");

        // Header della tabella
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>DEPTNO</th>");
        out.println("<th>DEPTNAME</th>");
        out.println("<th>MGRNO</th>");
        out.println("<th>ADMRDEPT</th>");
        out.println("<th>LOCATION</th>");
        out.println("</tr>");
        out.println("</thead>");

        // Corpo della tabella
        out.println("<tbody>");
        for (Map<String, Object> dept : departments) {
            out.println("<tr>");
            out.println("<td>" + getValueOrEmpty(dept, "DEPTNO") + "</td>");
            out.println("<td>" + getValueOrEmpty(dept, "DEPTNAME") + "</td>");
            out.println("<td>" + getValueOrEmpty(dept, "MGRNO") + "</td>");
            out.println("<td>" + getValueOrEmpty(dept, "ADMRDEPT") + "</td>");
            out.println("<td>" + getValueOrEmpty(dept, "LOCATION") + "</td>");
            out.println("</tr>");
        }
        out.println("</tbody>");
        out.println("</table>");

        // Mostra il conteggio
        out.println("<p>Total departments: " + departments.size() + "</p>");
    }

    /**
     * Metodo helper per gestire valori null
     */
    private String getValueOrEmpty(Map<String, Object> map, String key) {
        Object value = map.get(key);
        return value != null ? value.toString() : "";
    }

    /**
     * Metodo per gestire gli errori in modo centralizzato
     */
    private void handleError(HttpServletResponse resp, String message) throws IOException {
        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            out.println("<html><body>");
            out.println("<h2>Error</h2>");
            out.println("<p>" + message + "</p>");
            out.println("<a href='/data'>Try again</a>");
            out.println("</body></html>");
        }
    }

    /**
     * Metodo per gestire richieste PUT (aggiornamento)
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deptNo = req.getParameter("deptNo");
        String newDeptName = req.getParameter("deptName");

        if (deptNo == null || newDeptName == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Missing required parameters");
            return;
        }

        try {
            int result = fullDeptService.updateDepartmentName(deptNo, newDeptName);

            if (result > 0) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println("Department updated successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().println("Department not found");
            }

        } catch (Exception e) {
            logger.error("Errore durante l'aggiornamento del dipartimento", e);
            handleError(resp, "Errore durante l'aggiornamento");
        }
    }

    /**
     * Metodo per gestire richieste DELETE
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deptNo = req.getParameter("deptNo");

        if (deptNo == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Missing deptNo parameter");
            return;
        }

        try {
            int result = fullDeptService.deleteDepartment(deptNo);

            if (result > 0) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println("Department deleted successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().println("Department not found");
            }

        } catch (Exception e) {
            logger.error("Errore durante la cancellazione del dipartimento", e);
            handleError(resp, "Errore durante la cancellazione");
        }
    }
}