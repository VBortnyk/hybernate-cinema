package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.User;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User create(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Failed to add user", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> get(Long userId) {
        try {
            Session session = sessionFactory.openSession();
            Query<User> query = session.createQuery("FROM User WHERE id = :userId", User.class);
            query.setParameter("userId", userId);
            return Optional.of(query.getSingleResult());
        } catch (Exception e) {
            throw new DataProcessingException("Failed to get user with id: " + userId, e);
        }
    }

    @Override
    public Optional<User> getByEmail(String email) {
        try {
            Session session = sessionFactory.openSession();
            String selectQuery = "FROM User WHERE email = :email";
            Query<User> query = session.createQuery(selectQuery, User.class);
            query.setParameter("email", email);
            return Optional.of((User) query.getSingleResult());
        } catch (Exception e) {
            throw new DataProcessingException("Failed to find user by email: " + email, e);
        }
    }

    @Override
    public Optional<User> findById(Long userId) {
        try {
            Session session = sessionFactory.openSession();
            String selectQuery = "FROM User WHERE id = :userId";
            Query<User> query = session.createQuery(selectQuery, User.class);
            query.setParameter("id", userId);
            return Optional.of((User) query.getSingleResult());
        } catch (Exception e) {
            throw new DataProcessingException("Failed to find user by email: " + userId, e);
        }
    }
}
