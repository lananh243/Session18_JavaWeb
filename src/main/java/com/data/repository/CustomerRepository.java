package com.data.repository;

import com.data.entity.Customer;

import java.util.List;

public interface CustomerRepository {
    boolean addCustomer(Customer customer);
    List<Customer> getAllCustomers();
    List<Customer> searchCustomerByName(String last_name);
}
