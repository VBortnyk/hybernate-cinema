package com.dev.cinema;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.User;
import com.dev.cinema.secutity.AuthenticationService;
import com.dev.cinema.service.interfaces.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Main {
    private static final Injector injector = Injector.getInstance("com.dev.cinema");
    private static Logger logger = LogManager.getLogger(Main.class);
    private static UserService userService = (UserService) injector.getInstance(UserService.class);
    private static AuthenticationService authenticationService
            = (AuthenticationService) injector.getInstance(AuthenticationService.class);

    public static void main(String[] args) {
        User user = new User("mail@gmail.com", "password");
        authenticationService.register(user.getEmail(), user.getPassword());

        login("wrong@gmail.com", "password");
        login("mail@gmail.com", "password");

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
