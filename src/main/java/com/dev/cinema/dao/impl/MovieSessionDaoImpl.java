package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.interfaces.MovieSessionDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery(
                    "FROM MovieSession WHERE  showTime > :start  AND showTime < :end");
            query.setParameter("start", date.atStartOfDay());
            query.setParameter("end", date.atStartOfDay().plusDays(1).minusSeconds(1));
            return query.getResultList();
        } catch (Exception ex) {
            throw new DataProcessingException("Failed to get available sessions", ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Failed to add movie session to the DB", ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
