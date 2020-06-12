package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.Movie;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl implements MovieDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Movie create(Movie movie) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
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
    public Movie getByTitle(String title) {
        try {
            Session session = sessionFactory.openSession();
            Query<Movie> query = session.createQuery(
                    "FROM Movie WHERE title = :title", Movie.class);
            query.setParameter("title", title);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Failed to get movie by title: " + title, e);
        }
    }

    @Override
    public Movie getById(Long movieId) {
        try {
            Session session = sessionFactory.openSession();
            Query<Movie> query = session.createQuery("FROM Movie WHERE id = :movieId", Movie.class);
            query.setParameter("movieId", movieId);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Failed to get movie by title: " + movieId, e);
        }
    }

    @Override
    public List<Movie> getALl() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Movie> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Movie.class);
            criteriaQuery.from(Movie.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception ex) {
            throw new DataProcessingException("Failed to get all movies", ex);
        }
    }
}
