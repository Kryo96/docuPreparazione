package com.example.demo.controller;

import com.example.demo.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.inject.Inject;

@RestController
class CustomerServiceController{
    @Inject
    private CustomerService customerService;

    @GetMapping("/customer")
    public String hello() {
        return customerService.CustomerSaysHello();
    }
}