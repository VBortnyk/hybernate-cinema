package com.dev.cinema.service;

import com.dev.cinema.model.Movie;
import java.util.List;

public interface MovieService {

    Movie create(Movie movie);

    Movie getByTitle(String title);

    Movie getById(Long movieId);

    List<Movie> getALl();

}
