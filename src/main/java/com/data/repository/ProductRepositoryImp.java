package com.data.repository;

import com.data.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImp implements ProductRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean addProduct(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
        return true;
    }

    @Override
    public List<Product> findAllProduct(int page, int size) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Product", Product.class)
                    .setFirstResult(page * size)
                    .setMaxResults(size)
                    .list();
        }
    }

    @Override
    public long countProducts() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT COUNT(p.id) FROM Product p", Long.class).getSingleResult();
        }
    }

    @Override
    public List<Product> filterProductByPrice(double minPrice, double maxPrice, int page, int size) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query<Product> query = session.createQuery("FROM Product WHERE price >= :minPrice AND price <= :maxPrice ORDER BY id DESC", Product.class);
            query.setParameter("minPrice", minPrice);
            query.setParameter("maxPrice", maxPrice);

            // Phân trang
            query.setFirstResult(page * size);
            query.setMaxResults(size);

            List<Product> products = query.getResultList();
            transaction.commit();
            return products;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public long countFilteredProducts(double minPrice, double maxPrice) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        long count = 0;
        try {
            transaction = session.beginTransaction();

            String hql = "select count(p) from Product p where p.price >= :minPrice and p.price <= :maxPrice";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("minPrice", minPrice);
            query.setParameter("maxPrice", maxPrice);

            count = query.uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return count;
    }


}
