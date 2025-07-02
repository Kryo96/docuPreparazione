package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.EmployeeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(UserServlet.class);
    private EmployeeDao employeeDao = new EmployeeDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String empnoParam = req.getParameter("empno");
        logger.info("Ricevuta richiesta GET su /users con parametro empno={}", empnoParam);

        String sql;
        List<Object> params = new ArrayList<>();

        if (empnoParam != null && !empnoParam.trim().isEmpty()) {
            logger.debug("Parametro empno fornito: {}", empnoParam);
            try {
                params.add(empnoParam);
            } catch (NumberFormatException e) {
                logger.warn("Parametro empno non valido: {}", empnoParam);
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parametro empno non valido");
                return;
            }
            sql = "SELECT EMPNO, FIRSTNME, LASTNAME FROM EMPLOYEE WHERE EMPNO = ?";
        } else {
            logger.debug("Nessun parametro empno fornito, carico prime 5 righe");
            sql = "SELECT EMPNO, FIRSTNME, LASTNAME FROM EMPLOYEE FETCH FIRST 5 ROWS ONLY";
        }

        resp.setContentType("text/html;charset=UTF-8");

//        try (PrintWriter out = resp.getWriter()) {
//            List<Map<String, Object>> results = employeeDao.executeQuery(sql, params);
//            logger.info("Query eseguita con successo, {} righe restituite", results.size());
//
//            out.println("<html><body><h2>Risultati Query</h2><table border='1'>");
//
//            if (!results.isEmpty()) {
//                out.println("<tr>");
//                for (String col : results.get(0).keySet()) {
//                    out.printf("<th>%s</th>", col);
//                }
//                out.println("</tr>");
//            }
//
//            for (Map<String, Object> row : results) {
//                out.println("<tr>");
//                for (Object value : row.values()) {
//                    out.printf("<td>%s</td>", value);
//                }
//                out.println("</tr>");
//            }
//
//            out.println("</table></body></html>");
//        } catch (SQLException e) {
//            logger.error("Errore durante l'esecuzione della query al database", e);
//            throw new ServletException("Errore nel database: " + e.getMessage(), e);
//        } catch (IOException e) {
//            logger.error("Errore nell'invio della risposta HTTP", e);
//            throw e;
//        }
    }
}
