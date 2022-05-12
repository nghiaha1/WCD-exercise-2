package com.example.demo1.model;

import com.example.demo1.entity.Customer;

import java.util.List;

public interface CustomerModel {
    Customer save(Customer customer);

    List<Customer> findAll();


    Customer findById(int ID);

    Customer update(int ID, Customer updateCustomer);

    boolean delete(int ID);
}
