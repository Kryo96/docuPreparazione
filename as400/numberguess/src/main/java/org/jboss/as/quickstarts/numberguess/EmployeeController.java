package org.jboss.as.quickstarts.numberguess;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class EmployeeController {

    @Inject
    private EmployeeDao employeeDao;

    private List<Employee> employees;

    @PostConstruct
    public void init() {
        employees = employeeDao.findAll();
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
