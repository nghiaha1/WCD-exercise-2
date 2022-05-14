package com.example.demo1.controller.admin;

import com.example.demo1.entity.Customer;
import com.example.demo1.model.CustomerModel;
import com.example.demo1.model.MySqlCustomerModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DetailCustomerServlet extends HttpServlet {

    private CustomerModel customerModel;

    public DetailCustomerServlet() { this.customerModel = new MySqlCustomerModel(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringID = req.getParameter("ID");
        int ID = Integer.parseInt(stringID);
        Customer customer = customerModel.findById(ID);

        if(customer == null) {
            req.setAttribute("message", "Customer not found!");
            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        }else {
            // nếu có trả về trang detail
            req.setAttribute("customer", customer);
            req.getRequestDispatcher("/admin/customers/tables/detail.jsp").forward(req, resp);
        }
    }
}
