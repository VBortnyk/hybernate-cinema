package com.dev.cinema.service.interfaces;

import com.dev.cinema.model.User;

public interface UserService {

    User create(User user);

    User findByEmail(String email);
}
