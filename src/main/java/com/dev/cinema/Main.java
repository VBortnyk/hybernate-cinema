package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.interfaces.MovieService;

public class Main {
    private static final Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);

        Movie movie = new Movie();
        movie.setTitle("Matrix");
        movieService.add(movie);

        Movie movie2 = new Movie();
        movie.setTitle("Matrix-2");
        movieService.add(movie);

        movieService.getALl().forEach(System.out::println);
    }
}
