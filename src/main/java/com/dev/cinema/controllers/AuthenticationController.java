package com.dev.cinema.controllers;

import com.dev.cinema.mapper.UserMapper;
import com.dev.cinema.model.dto.UserRegistrationDto;
import com.dev.cinema.secutity.AuthenticationService;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;
    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService,
                                    UserMapper userMapper, UserService userService) {
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@RequestBody UserRegistrationDto userRegistrationDto) {
        authenticationService.register(userRegistrationDto.getEmail(),
                userRegistrationDto.getPassword());
    }
}
