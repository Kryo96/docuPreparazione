package org.jboss.as.quickstarts.numberguess;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class EmployeeDao {

    private static final String JDBC_URL = "jdbc:db2://localhost:50000/SAMPLE";
    private static final String JDBC_USER = "db2inst1";
    private static final String JDBC_PASSWORD = "test";

    static {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT EMPNO, FIRSTNME, LASTNAME, WORKDEPT, SALARY FROM EMPLOYEE";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmpNo(rs.getInt("EMPNO"));
                emp.setFirstName(rs.getString("FIRSTNME"));
                emp.setLastName(rs.getString("LASTNAME"));
                emp.setWorkDept(rs.getString("WORKDEPT"));
                emp.setSalary(rs.getDouble("SALARY"));
                employees.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }
}
