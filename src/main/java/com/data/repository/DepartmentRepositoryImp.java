package com.data.repository;

import com.data.entity.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepositoryImp implements DepartmentRepository {
    private SessionFactory sessionFactory;

    public DepartmentRepositoryImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Department> findAll() {
        Session session = sessionFactory.openSession();

        // hql
        Query<Department> query = session.createQuery("FROM Department");
        // native sql
//        Query<Department> query = session.createSQLQuery("SELECT * FROM department");
        List<Department> departments = query.getResultList();
        return departments;
    }
}