package org.joinfaces.example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import jakarta.inject.Inject;
import org.joinfaces.example.service.CustomerService;

@WebServlet("/customer")
public class CustomerController extends HttpServlet {

    @Inject
    private CustomerService customerService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String customerMessage = customerService.getCustomerMessage();

        request.setAttribute("customerMessage", customerMessage);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/customer.xhtml");
        dispatcher.forward(request, response);
    }
}
