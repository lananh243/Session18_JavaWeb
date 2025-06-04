package com.data.repository;

import com.data.entity.Admin;
import com.data.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryImp implements CustomerRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean addCustomer(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
        return true;
    }

    @Override
    public List<Customer> getAllCustomers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<Customer> customers = session.createQuery("from Customer", Customer.class).getResultList();
        transaction.commit();
        return customers;

    }

    @Override
    public List<Customer> searchCustomerByName(String last_name) {
        Session session = sessionFactory.openSession();
        List<Customer> customers = null;
        try {
            Query<Customer> query =  session.createQuery("from Customer where lower(last_name) like :last_name", Customer.class);
            query.setParameter("last_name", "%" + last_name.toLowerCase() + "%");
            customers = query.getResultList();
        } finally {
            session.close();
        }
        return customers;

    }
}
