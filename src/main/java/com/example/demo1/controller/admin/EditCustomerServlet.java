package com.example.demo1.controller.admin;

import com.example.demo1.entity.Customer;
import com.example.demo1.model.CustomerModel;
import com.example.demo1.model.MySqlCustomerModel;
import com.example.demo1.util.DateTimeHelper;
import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class EditCustomerServlet extends HttpServlet {
    private CustomerModel customerModel;

    public EditCustomerServlet() {
        this.customerModel = new MySqlCustomerModel();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // lấy tham số rollNumber(id)
        String stringID = req.getParameter("ID");
        int ID = Integer.parseInt(stringID);
        // kiểm tra trong database xem có tồn tại không.
        Customer customer = customerModel.findById(ID);
        // nếu không trả về trang 404
        if (customer == null) {
            req.setAttribute("message", "Customer not found!");
            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        } else {
            // nếu có trả về trang detail
            req.setAttribute("customer", customer);
            req.setAttribute("action", 2);
            req.getRequestDispatcher("/admin/customers/form.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        // xử lý validate và save.
        String stringID = req.getParameter("ID");
        int ID = Integer.parseInt(stringID);
        Customer existingStudent = customerModel.findById(ID);
        if(existingStudent == null){
            req.setAttribute("message", "Customer not found!");
            req.getRequestDispatcher("/admin/errors/404.jsp").forward(req, resp);
        }else{
            String name = req.getParameter("name");
            String phone = req.getParameter("phone");
            String img = req.getParameter("img");
            String stringDob = req.getParameter("dob");
            LocalDateTime dob = DateTimeHelper.convertStringToLocalDateTime(stringDob);
            Customer customer = new Customer(name, phone, img, dob);
            // validate dữ liệu
            if (customerModel.update(ID, customer) != null) {
                resp.sendRedirect("/admin/students/list");
            } else {
                // nếu có trả về trang form
                req.setAttribute("customer", customer);
                req.setAttribute("action", 2);
                req.getRequestDispatcher("/admin/customer/form.jsp").forward(req, resp);
            }
        }
    }
}
