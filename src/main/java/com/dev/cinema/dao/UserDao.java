package com.dev.cinema.dao;

import com.dev.cinema.model.User;
import java.util.Optional;

public interface UserDao {

    User create(User user);

    Optional<User> getByEmail(String email);

    Optional<User> findById(Long userId);
}
