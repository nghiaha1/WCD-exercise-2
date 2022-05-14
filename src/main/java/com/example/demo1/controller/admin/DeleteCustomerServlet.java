package com.example.demo1.controller.admin;

import com.example.demo1.entity.Customer;
import com.example.demo1.model.CustomerModel;
import com.example.demo1.model.MySqlCustomerModel;
import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCustomerServlet extends HttpServlet {
    private CustomerModel customerModel;

    public DeleteCustomerServlet() {
        this.customerModel = new MySqlCustomerModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // lấy tham số rollNumber(id)
        String stringID = req.getParameter("ID");
        int ID = Integer.parseInt(stringID);
        // kiểm tra trong database xem có tồn tại không.
        Customer student = customerModel.findById(ID);
        // nếu không trả về trang 404
        if (student == null) {
            req.setAttribute("message", "Customer not found!");
            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        } else {
            boolean result = customerModel.delete(ID); // xoá mềm.
            if (result) {
                resp.sendRedirect("/admin/customers/list");
            } else {
                req.setAttribute("message", "Action fails!");
                req.getRequestDispatcher("/admin/errors/500.jsp").forward(req, resp);
            }
        }
    }
}
