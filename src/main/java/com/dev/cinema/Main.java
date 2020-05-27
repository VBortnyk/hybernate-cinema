package com.dev.cinema;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.secutity.AuthenticationService;
import com.dev.cinema.service.interfaces.CinemaHallService;
import com.dev.cinema.service.interfaces.MovieService;
import com.dev.cinema.service.interfaces.MovieSessionService;
import com.dev.cinema.service.interfaces.ShoppingCartService;
import com.dev.cinema.service.interfaces.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);
    private static final Injector injector = Injector.getInstance("com.dev.cinema");
    private static UserService userService = (UserService) injector.getInstance(UserService.class);
    private static AuthenticationService authenticationService
            = (AuthenticationService) injector.getInstance(AuthenticationService.class);
    private static MovieSessionService movieSessionService
            = (MovieSessionService) injector.getInstance(MovieSessionService.class);
    private static ShoppingCartService shoppingCartService
            = (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
    private static MovieService movieService
            = (MovieService) injector.getInstance(MovieService.class);
    private static CinemaHallService cinemaHallService
            = (CinemaHallService) injector.getInstance(CinemaHallService.class);

    public static void main(String[] args) {
        Movie matrix = new Movie();
        matrix.setTitle("Matrix");
        movieService.add(matrix);

        CinemaHall cinemaHall1 = new CinemaHall();
        cinemaHall1.setCapacity(100);
        cinemaHallService.add(cinemaHall1);

        MovieSession testSession = new MovieSession();
        testSession.setShowTime(LocalDateTime
                .of(LocalDate.of(2020, 05, 27), LocalTime.of(10, 30)));
        testSession.setCinemaHall(cinemaHall1);
        movieSessionService.add(testSession);

        User testUser = authenticationService.register("mail@gmail.com", "password");

        ShoppingCart shoppingCart = shoppingCartService.getByUser(testUser);
        shoppingCartService.addSession(testSession, testUser);

    }

    public static void login(String email, String password) {
        try {
            authenticationService.login(email, password);
        } catch (DataProcessingException e) {
            logger.warn("Nonexistent email input");
        } catch (AuthenticationException e) {
            logger.warn("Wrong password input");
        }
        logger.warn("User with email: " + email + " logged in successfully");
    }
}
