package com.data.repository;

import com.data.entity.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<Admin> findByRole(String role) {
        Session session = sessionFactory.openSession();
        Query<Admin> query = session.createQuery("from Admin where role = :role");
        query.setParameter("role", role);
        List<Admin> admins = query.getResultList();
        return admins;
    }

    @Override
    public void changeUserStatus(int id, boolean status) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Admin admin = (Admin) session.get(Admin.class, id);
        if (admin != null) {
            admin.setStatus(status);
            session.update(admin);
        }
        transaction.commit();
    }

    @Override
    public List<Admin> searchByName(String username) {
        Session session = sessionFactory.openSession();
        List<Admin> admins = null;
        try {
            Query<Admin> query = session.createQuery(
                    "from Admin where lower(username) like :username", Admin.class);
            query.setParameter("username", "%" + username.toLowerCase() + "%");
            admins = query.getResultList();
        } finally {
            session.close();
        }
        return admins;
    }



}
