package com.dev.cinema.service.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {

    @Autowired
    private MovieSessionDao movieSessionDao;

    @Override
    public MovieSession create(MovieSession movieSession) {
        return movieSessionDao.create(movieSession);
    }

    @Override
    public MovieSession getById(Long sessionId) {
        return movieSessionDao.findById(sessionId);
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }
}
