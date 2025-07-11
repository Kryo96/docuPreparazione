package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.entity.Customer;
import org.example.exception.MiddlewareException;

public interface AS400Repository {
    Customer findCustomerById(String customerId) throws MiddlewareException;

    void updateCustomer(Customer customer) throws MiddlewareException;
}
