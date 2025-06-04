package com.data.repository;

import com.data.entity.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepositoryImp implements AdminRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Admin findByUsernameAndPassword(String username, String password) {
        Session session = sessionFactory.openSession();
        Query<Admin> query = session.createQuery("from Admin where username = :username and password = :password");
        query.setParameter("username", username);
        query.setParameter("password", password);
        Admin admin = query.uniqueResult();
        return admin;
    }
}
