package com.dev.cinema.dao.interfaces;

import com.dev.cinema.model.User;

public interface UserDao {

    User add(User user);

    User findByEmail(String email);
}
