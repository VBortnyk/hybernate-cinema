package com.dev.cinema.dao;

import com.dev.cinema.model.Movie;
import java.util.List;

public interface MovieDao {

    Movie create(Movie movie);

    Movie getByTitle(String title);

    Movie getById(Long movieId);

    List<Movie> getALl();
}
