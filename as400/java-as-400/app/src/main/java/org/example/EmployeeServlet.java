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
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/employee")
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
        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            out.println("<html><body>");
            out.println("<h2>Employee Data</h2>");

            // Chiamata al servizio read-only
            List<Map<String, Object>> employees = readOnlyEmpl.findAll();

            if (employees != null && !employees.isEmpty()) {
                printTable(out, employees);
            } else {
                out.println("<p>No employees found.</p>");
            }

            out.println("</body></html>");

        } catch (Exception e) {
            logger.error("Errore durante l'accesso ai dati dei dipendenti", e);
            handleError(resp, "Errore interno del server");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recupera tutti i parametri necessari per un nuovo dipendente
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

        try {
            // Usa il servizio completo per operazioni di scrittura
            int result = fullEmplService.insertEmployee(empNo, firstName, midInit, lastName,
                    workDept, phoneNo, hireDate, job,
                    edLevel, sex, birthDate, salary, bonus, comm);

            if (result > 0) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().println("Employee created successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().println("Failed to create employee");
            }

        } catch (Exception e) {
            logger.error("Errore durante la creazione del dipendente", e);
            handleError(resp, "Errore durante la creazione");
        }
    }

    /**
     * Metodo per stampare una tabella HTML con i dati dei dipendenti
     */
    private void printTable(PrintWriter out, List<Map<String, Object>> employees) {
        if (employees == null || employees.isEmpty()) {
            out.println("<p>No data available</p>");
            return;
        }

        out.println("<table border='1' cellpadding='5' cellspacing='0'>");

        // Header della tabella
        out.println("<thead>");
        out.println("<tr>");
        out.println("<th>EMPNO</th>");
        out.println("<th>FIRST NAME</th>");
        out.println("<th>MI</th>");
        out.println("<th>LAST NAME</th>");
        out.println("<th>WORK DEPT</th>");
        out.println("<th>PHONE</th>");
        out.println("<th>HIRE DATE</th>");
        out.println("<th>JOB</th>");
        out.println("<th>ED LEVEL</th>");
        out.println("<th>SEX</th>");
        out.println("<th>BIRTH DATE</th>");
        out.println("<th>SALARY</th>");
        out.println("<th>BONUS</th>");
        out.println("<th>COMM</th>");
        out.println("</tr>");
        out.println("</thead>");

        // Corpo della tabella
        out.println("<tbody>");
        for (Map<String, Object> emp : employees) {
            out.println("<tr>");
            out.println("<td>" + getValueOrEmpty(emp, "EMPNO") + "</td>");
            out.println("<td>" + getValueOrEmpty(emp, "FIRSTNME") + "</td>");
            out.println("<td>" + getValueOrEmpty(emp, "MIDINIT") + "</td>");
            out.println("<td>" + getValueOrEmpty(emp, "LASTNAME") + "</td>");
            out.println("<td>" + getValueOrEmpty(emp, "WORKDEPT") + "</td>");
            out.println("<td>" + getValueOrEmpty(emp, "PHONENO") + "</td>");
            out.println("<td>" + getValueOrEmpty(emp, "HIREDATE") + "</td>");
            out.println("<td>" + getValueOrEmpty(emp, "JOB") + "</td>");
            out.println("<td>" + getValueOrEmpty(emp, "EDLEVEL") + "</td>");
            out.println("<td>" + getValueOrEmpty(emp, "SEX") + "</td>");
            out.println("<td>" + getValueOrEmpty(emp, "BIRTHDATE") + "</td>");
            out.println("<td>" + formatCurrency(emp.get("SALARY")) + "</td>");
            out.println("<td>" + formatCurrency(emp.get("BONUS")) + "</td>");
            out.println("<td>" + formatCurrency(emp.get("COMM")) + "</td>");
            out.println("</tr>");
        }
        out.println("</tbody>");
        out.println("</table>");

        // Mostra il conteggio
        out.println("<p>Total employees: " + employees.size() + "</p>");
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
            out.println("<a href='/employee'>Try again</a>");
            out.println("</body></html>");
        }
    }

    /**
     * Metodo per gestire richieste PUT (aggiornamento)
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String empNo = req.getParameter("empNo");
        String firstName = req.getParameter("firstName");

        if (empNo == null || firstName == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Missing required parameters");
            return;
        }

        try {
            int result = fullEmplService.updateFirstName(empNo, firstName);

            if (result > 0) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println("Employee updated successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().println("Employee not found");
            }

        } catch (Exception e) {
            logger.error("Errore durante l'aggiornamento del dipendente", e);
            handleError(resp, "Errore durante l'aggiornamento");
        }
    }

    /**
     * Metodo per gestire richieste DELETE
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String empNo = req.getParameter("empNo");

        if (empNo == null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Missing empNo parameter");
            return;
        }

        try {
            int result = fullEmplService.deleteEmployee(empNo);

            if (result > 0) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println("Employee deleted successfully");
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().println("Employee not found");
            }

        } catch (Exception e) {
            logger.error("Errore durante la cancellazione del dipendente", e);
            handleError(resp, "Errore durante la cancellazione");
        }
    }
}
