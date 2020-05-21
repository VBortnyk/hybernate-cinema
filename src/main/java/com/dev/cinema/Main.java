package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.interfaces.CinemaHallService;
import com.dev.cinema.service.interfaces.MovieService;
import com.dev.cinema.service.interfaces.MovieSessionService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    private static final Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        Movie matrix = new Movie();
        matrix.setTitle("Matrix");
        movieService.add(matrix);

        Movie matrix2 = new Movie();
        matrix2.setTitle("Matrix-2");
        movieService.add(matrix2);

        CinemaHallService cinemaHallService
                = (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall cinemaHall1 = new CinemaHall();
        cinemaHall1.setCapacity(100);
        cinemaHallService.add(cinemaHall1);

        CinemaHall cinemaHall2 = new CinemaHall();
        cinemaHall2.setCapacity(150);
        cinemaHallService.add(cinemaHall2);

        MovieSession movieSession1 = new MovieSession();
        movieSession1.setMovie(matrix);
        movieSession1.setShowTime(LocalDateTime
                .of(LocalDate.of(2020, 05, 23), LocalTime.of(10, 30)));
        movieSession1.setCinemaHall(cinemaHall1);
        MovieSessionService movieSessionService
                = (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession1);

        MovieSession movieSession2 = new MovieSession();
        movieSession2.setMovie(matrix2);
        movieSession2.setShowTime(LocalDateTime
                .of(LocalDate.of(2020, 05, 23), LocalTime.of(12, 30)));
        movieSession2.setCinemaHall(cinemaHall2);
        movieSessionService.add(movieSession2);

        MovieSession movieSession3 = new MovieSession();
        movieSession3.setMovie(matrix);
        movieSession3.setShowTime(LocalDateTime
                .of(LocalDate.of(2020, 05, 21), LocalTime.of(10, 30)));
        movieSession3.setCinemaHall(cinemaHall1);
        movieSessionService.add(movieSession3);

        movieService.getALl().forEach(System.out::println);
        cinemaHallService.getAll().forEach((System.out::println));
        movieSessionService
                .findAvailableSessions(1L, LocalDate.of(2020, 05, 24))
                .forEach(System.out::println);
    }
}
