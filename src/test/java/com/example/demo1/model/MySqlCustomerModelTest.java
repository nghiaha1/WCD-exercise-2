package com.example.demo1.model;

import com.example.demo1.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MySqlCustomerModelTest {

    CustomerModel model;

    @BeforeEach
    void setUp() {
        model = new MySqlCustomerModel();
    }

    @Test
    void save() {
        model.save(new Customer("tester 6", "0123123132", "123456",LocalDateTime.of(2010, 1, 1, 1, 1)));
    }

    @Test
    void findAll() {
        List <Customer> list = model.findAll();
        for (Customer st :
                list) {
            System.out.println(st.toString());
        }
    }

    @Test
    void findById() {
        Customer customer = model.findById(1);
        assertEquals("tester 005", customer.getName());
    }

    @Test
    void update() {
        Customer customer = model.findById(1);
        customer.setName("tester 5");
        model.update(1, customer);
    }

    @Test
    void delete() {
        model.delete(1);
        Customer customer = model.findById(1);
        assertEquals(null, customer);
    }
}