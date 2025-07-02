package org.example;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/data")
public class DataServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DataServlet.class);

    @Inject
    private EmployeeDao employeeDao;

    @Inject
    private ProductDao productDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            out.println("<html><body>");

            // Employees
            out.println("<h2>Employees</h2>");
            String empno = req.getParameter("empno");
            List<Map<String, Object>> employees;
            if (empno != null && !empno.trim().isEmpty()) {
                employees = employeeDao.getEmployeesByEmpno(empno);
            } else {
                employees = employeeDao.getFirstFiveEmployees();
            }
            printTable(out, employees);

            // Products
            out.println("<h2>Products</h2>");
            List<Map<String, Object>> products = productDao.getAllProducts();
            printTable(out, products);

            out.println("</body></html>");

        } catch (SQLException e) {
            logger.error("Errore durante l'accesso al database", e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore interno");
        }
    }

    private void printTable(PrintWriter out, List<Map<String, Object>> data) {
        if (data == null || data.isEmpty()) {
            out.println("<p>Nessun dato trovato.</p>");
            return;
        }

        out.println("<table border='1'><tr>");
        // Header
        data.get(0).keySet().forEach(col -> out.printf("<th>%s</th>", col));
        out.println("</tr>");

        // Rows
        for (Map<String, Object> row : data) {
            out.println("<tr>");
            row.values().forEach(val -> out.printf("<td>%s</td>", val));
            out.println("</tr>");
        }
        out.println("</table>");
    }
}
