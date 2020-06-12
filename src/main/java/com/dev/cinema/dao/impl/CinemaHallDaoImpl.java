package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.CinemaHallDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.CinemaHall;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl implements CinemaHallDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public CinemaHall create(CinemaHall cinemaHall) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(cinemaHall);
            transaction.commit();
            return cinemaHall;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Failed to add cinemaHall", ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public CinemaHall getById(Long hallId) {
        try {
            Session session = sessionFactory.openSession();
            Query<CinemaHall> query = session.createQuery(
                    "FROM CinemaHall WHERE id = :hallId", CinemaHall.class);
            query.setParameter("id", hallId);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Failed to get cinema-hall by id: " + hallId, e);
        }
    }

    @Override
    public List<CinemaHall> getALl() {
        String getAllQuery = "FROM CinemaHall";
        try (Session session = sessionFactory.openSession()) {
            Query<CinemaHall> query = session.createQuery(getAllQuery, CinemaHall.class);
            return query.getResultList();
        } catch (Exception ex) {
            throw new DataProcessingException("Failed to get all cinema halls", ex);
        }
    }
}
