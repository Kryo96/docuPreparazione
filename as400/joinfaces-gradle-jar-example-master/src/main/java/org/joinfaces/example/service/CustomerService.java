package org.joinfaces.example.service;

import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class CustomerService {

    public String getCustomerMessage() {
        return "Hello from Customer Service!";
    }
}
