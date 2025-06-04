package com.data.service;

import com.data.entity.Customer;

import java.util.List;

public interface CustomerService {
    boolean addCustomer(Customer customer);
    List<Customer> getAllCustomers();
    List<Customer> searchCustomerByName(String last_name);
}
