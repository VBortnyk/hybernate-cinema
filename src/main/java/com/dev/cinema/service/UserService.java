package com.dev.cinema.service;

import com.dev.cinema.model.User;

public interface UserService {

    User create(User user);

    User getByEmail(String email);

    User findById(Long userId);
}
