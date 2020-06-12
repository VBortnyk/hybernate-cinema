package com.dev.cinema.service;

import com.dev.cinema.model.User;

public interface UserService {

    User create(User user);

    User get(Long userId);

    User getByEmail(String email);

    User findById(Long userId);
}
