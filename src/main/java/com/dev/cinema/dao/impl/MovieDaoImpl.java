package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.interfaces.MovieDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.Movie;
import com.dev.cinema.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class MovieDaoImpl implements MovieDao {
    @Override
    public Movie add(Movie movie) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(movie);
            transaction.commit();
            movie.setId(id);
            return movie;
        } catch (Exception ex) {
            if (transaction == null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Failed to add movie to the DB", ex);
        }
    }

    @Override
    public List<Movie> getALl() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaQuery<Movie> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Movie.class);
            criteriaQuery.from(Movie.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            throw new DataProcessingException("Failed to get all movies", ex);
        }
    }
}
