package com.data.service;

import com.data.entity.Customer;
import com.data.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements  CustomerService {
    @Autowired
    private CustomerRepository customerRepo;
    @Override
    public boolean addCustomer(Customer customer) {
        return customerRepo.addCustomer(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.getAllCustomers();
    }

    @Override
    public List<Customer> searchCustomerByName(String last_name) {
        return customerRepo.searchCustomerByName(last_name);
    }
}
