package com.example.demo1.demo;

import com.example.demo1.entity.Customer;
import com.example.demo1.model.CustomerModel;
import com.example.demo1.model.InMemoryCustomerModel;
import com.example.demo1.model.MySqlCustomerModel;
import com.sun.xml.internal.bind.v2.model.core.ID;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class DemoConsole {
    private static CustomerModel customerModel;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Please choose type of model: ");
            System.out.println("--------------------------");
            System.out.println("1. In memory model.");
            System.out.println("2. My SQL model.");
            System.out.println("--------------------------");
            System.out.println("Please enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    customerModel = new InMemoryCustomerModel();
                    break;
                case 2:
                    customerModel = new MySqlCustomerModel();
                    break;
            }
            generateMenu();
        }
    }

    private static void generateMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("-------------Student Manager--------------");
            System.out.println("1. Create new student");
            System.out.println("2. Show all student");
            System.out.println("3. Update student");
            System.out.println("4. Delete student");
            System.out.println("5. Exit");
            System.out.println("------------------------------------------");
            System.out.println("Please enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addNewCustomer();
                    break;
                case 2:
                    showAllStudent();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    delCustomer();
                    break;
                case 5:
                    System.out.println("Cya!");
                    break;
            }
            if (choice == 5) {
                break;
            }
        }
    }

    private static void addNewCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter ID:");
        int ID = scanner.nextInt();
        System.out.println("Please enter full name");
        String name = scanner.nextLine();
        Customer customer = new Customer(name, name + "@test.com", "10123456789", LocalDateTime.of(2000, 10, 10, 10, 10));
        if (customerModel.save(customer) != null) {
            System.out.println("Create student success!");
        } else {
            System.err.println("Save student fails, please try again later!");
        }
    }

    private static void showAllStudent() {
        List<Customer> list = customerModel.findAll();
        for (Customer customer :
                list) {
            System.out.println(customer.toString());
        }
    }

    private static void updateCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter rollNumber to update: ");
        int ID = scanner.nextInt();
        Customer existingStudent = customerModel.findById(ID);
        if (existingStudent == null) {
            System.err.println("404, Customer not found!");
        } else {
            System.out.println("Please enter full name");
            String name = scanner.nextLine();
            existingStudent.setName(name);
            if (customerModel.update(ID, existingStudent) != null) {
                System.out.println("Action success!");
            } else {
                System.err.println("Action fails, please try again!");
            }
        }
    }

    private static void delCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter ID to update: ");
        int ID = scanner.nextInt();
        Customer existingCustomer = customerModel.findById(ID);
        if (existingCustomer == null) {
            System.err.println("404, Student not found!");
        } else {
            if (customerModel.delete(ID)) {
                System.out.println("Action success!");
            } else {
                System.err.println("Action fails, please try again!");
            }
        }
    }
}
