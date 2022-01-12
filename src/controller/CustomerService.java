package controller;

import model.Customer;

import java.sql.SQLException;

public interface CustomerService {
    public boolean addCustomer(Customer c) throws SQLException, ClassNotFoundException;
    public boolean deleteCustomer(String c) throws SQLException, ClassNotFoundException;
    public Customer getCustomer(String id) throws SQLException, ClassNotFoundException;
}
