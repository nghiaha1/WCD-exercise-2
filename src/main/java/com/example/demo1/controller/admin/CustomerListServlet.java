package com.example.demo1.controller.admin;

import com.example.demo1.entity.Customer;
import com.example.demo1.model.CustomerModel;
import com.example.demo1.model.MySqlCustomerModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CustomerListServlet extends HttpServlet {
    private CustomerModel customerModel;

    public CustomerListServlet() {
        this.customerModel = new MySqlCustomerModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> list = customerModel.findAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/admin/customers/list.jsp").forward(req, resp);
    }
}
