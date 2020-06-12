package com.dev.cinema.controllers;

import com.dev.cinema.mapper.UserMapper;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.UserRegistrationDto;
import com.dev.cinema.model.dto.UserResponseDto;
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
    private AuthenticationService authenticationService;
    private UserMapper userMapper;
    private UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService,
                                    UserMapper userMapper, UserService userService) {
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody UserRegistrationDto userRegistrationDto) {
        User user = userService.getByEmail(userRegistrationDto.getEmail());
        user = authenticationService.register(user.getEmail(), user.getPassword());
        return userMapper.convertToResponseDto(user);
    }
}
