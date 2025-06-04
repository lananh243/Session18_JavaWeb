package com.data.repository;

import com.data.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryImp implements EmployeeRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> findAll() {
        Session session = sessionFactory.openSession();
        Query<Employee> query = session.createQuery("from Employee");
        List<Employee> employees = query.getResultList();
        return employees;
    }
}
