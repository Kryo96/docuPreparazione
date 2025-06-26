package com.example.demo.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CustomerServiceImpl implements CustomerService {

    @Override
    public String CustomerSaysHello() {
        return "Hello Client";
    }
}
