package com.example.demo1.model;

import com.example.demo1.entity.Customer;
import com.example.demo1.util.ConnectionHelper;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class MySqlCustomerModel implements CustomerModel{
    @Override
    public Customer save(Customer customer) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "insert into customers " +
                    "(ID, name, phone, img, dob, createAt, updateAt, status) " +
                    "values " +
                    "(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, customer.getID());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getPhone());
            preparedStatement.setString(4, customer.getImg());
            preparedStatement.setString(5, customer.getDob().toString());
            preparedStatement.setString(6, customer.getCreateAt().toString());
            preparedStatement.setString(7, customer.getUpdateAt().toString());
            preparedStatement.setInt(8, customer.getStatus());
            System.out.println("Connection success!");
            preparedStatement.execute();
            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "select * from customers where status = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, 1);
            System.out.println("Connection success!");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int ID = resultSet.getInt("ID");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String img = resultSet.getString("img");
                LocalDateTime dob =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("dob").toInstant(), ZoneId.systemDefault());
                LocalDateTime createAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updateAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updateAt").toInstant(), ZoneId.systemDefault());
                int status = resultSet.getInt("status");
                Customer customer = new Customer(ID, name, phone, img, dob, createAt, updateAt, status);
                list.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Customer findById(int ID) {
        Customer customer = null;
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "select * from customers where status = ? and ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, String.valueOf(ID));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String img = resultSet.getString("img");

                LocalDateTime dob =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("dob").toInstant(), ZoneId.systemDefault());
                LocalDateTime createAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("createAt").toInstant(), ZoneId.systemDefault());
                LocalDateTime updateAt =
                        LocalDateTime.ofInstant(resultSet.getTimestamp("updateAt").toInstant(), ZoneId.systemDefault());
                int status = resultSet.getInt("status");
                customer = new Customer(ID, name, phone, img, dob, createAt, updateAt, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public Customer update(int ID, Customer updateCustomer) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "update customers " +
                    "set ID = ?, name = ?, phone = ?, img = ?, dob = ?, createAt = ?, updateAt = ?, status = ? where ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, updateCustomer.getID());
            preparedStatement.setString(2, updateCustomer.getName());
            preparedStatement.setString(3, updateCustomer.getPhone());
            preparedStatement.setString(4, updateCustomer.getImg());
            preparedStatement.setString(5, updateCustomer.getDob().toString());
            preparedStatement.setString(6, updateCustomer.getCreateAt().toString());
            preparedStatement.setString(7, updateCustomer.getUpdateAt().toString());
            preparedStatement.setInt(8, updateCustomer.getStatus());
            preparedStatement.setString(9, String.valueOf(ID));
            System.out.println("Connection success!");
            preparedStatement.execute();
            return updateCustomer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int ID) {
        try {
            Connection connection = ConnectionHelper.getConnection();
            String sqlQuery = "update customers " +
                    "set status = ? where ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, -1);
            preparedStatement.setInt(2, ID);
            System.out.println("Connection success!");
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
