package com.dev.cinema.service.impl;

import com.dev.cinema.dao.interfaces.MovieDao;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.interfaces.MovieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

    @Override
    public Movie create(Movie movie) {
        return movieDao.create(movie);
    }

    @Override
    public List<Movie> getALl() {
        return movieDao.getALl();
    }
}
