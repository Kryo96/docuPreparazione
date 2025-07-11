package org.example.repository.impl;

import org.example.entity.Customer;
import org.example.repository.AS400Repository;
import org.example.exception.MiddlewareException;

import jakarta.enterprise.context.ApplicationScoped;

import javax.sql.DataSource;
import jakarta.transaction.Transactional;
import jakarta.annotation.Resource;
import java.sql.*;
import java.util.logging.Logger;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class AS400RepositoryImpl implements AS400Repository {

    private static final Logger LOGGER = Logger.getLogger(AS400RepositoryImpl.class.getName());

    @Resource(lookup = "java:app/jdbc/AS400DS")
    private DataSource dataSource;

    @Override
    public Customer findCustomerById(String customerId) throws MiddlewareException {
        String sql = "SELECT CUSTID, CUSTNAME, CUSTADDR, CUSTCITY FROM LIBRARY.CUSTOMER WHERE CUSTID = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customerId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCustomer(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.severe("Errore durante recupero cliente: " + e.getMessage());
            throw new MiddlewareException("Errore recupero dati AS400", e);
        }
        return null;
    }


    @Override
    public void updateCustomer(Customer customer) throws MiddlewareException {
        String sql = "UPDATE LIBRARY.CUSTOMER SET CUSTNAME = ?, CUSTADDR = ?, CUSTCITY = ? WHERE CUSTID = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getCity());
            stmt.setString(4, customer.getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new MiddlewareException("Cliente non trovato: " + customer.getId());
            }

        } catch (SQLException e) {
            LOGGER.severe("Errore durante aggiornamento cliente: " + e.getMessage());
            throw new MiddlewareException("Errore aggiornamento dati AS400", e);
        }
    }

    private Customer mapResultSetToCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getString("CUSTID"));
        customer.setName(rs.getString("CUSTNAME"));
        customer.setAddress(rs.getString("CUSTADDR"));
        customer.setCity(rs.getString("CUSTCITY"));
        return customer;
    }
}
