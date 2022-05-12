package com.example.demo1.model;

import com.example.demo1.entity.Customer;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class InMemoryCustomerModel implements CustomerModel {

    private static List<Customer> customers;

    public InMemoryCustomerModel() {
        customers = new ArrayList<>();
        customers.add(
                new Customer(
                        10,
                        "Tester 10",
                        "tester10@test.com",
                        "103456789",
                        LocalDateTime.of(2000, 1, 1, 1, 1),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        1)
        );
        customers.add(
                new Customer(
                        11,
                        "Tester 11",
                        "tester11@test.com",
                        "113456789",
                        LocalDateTime.of(2000, 1, 1, 1, 1),
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        1)
        );
    }
    public Customer save(Customer customer) {
        customers.add(customer);
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public Customer findById(int ID) {
        Customer findCustomer = null;
        for (Customer customer :
                customers) {
            if (customer.getID() == ID) {
                findCustomer = customer;
                break;
            }
        }
        return findCustomer;
    }

    @Override
    public Customer update(int ID, Customer updateCustomer) {
        Customer existingCustomer = findById(ID);
        if(existingCustomer == null){
            return null;
        }
        existingCustomer.setName(updateCustomer.getName());
        existingCustomer.setPhone(updateCustomer.getPhone());
        existingCustomer.setImg(updateCustomer.getImg());
        existingCustomer.setDob(updateCustomer.getDob());
        existingCustomer.setUpdateAt(LocalDateTime.now());
        existingCustomer.setStatus(updateCustomer.getStatus());
        return existingCustomer;
    }

    @Override
    public boolean delete(int ID) {
        int foundIndex = -1;
        for (int i = 0; i < customers.size(); i++) {
            if(customers.get(i).getID() == ID){
                foundIndex = i;
            }
        }
        if(foundIndex != -1){
            customers.remove(foundIndex);
            return true;
        }
        return false;
    }
}
