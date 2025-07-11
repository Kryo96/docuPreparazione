package org.example.repository;

import org.example.entity.Customer;
import org.example.exception.MiddlewareException;

public interface AS400Repository {
    Customer findCustomerById(String customerId) throws MiddlewareException;
    void updateCustomer(Customer customer) throws MiddlewareException;
}
