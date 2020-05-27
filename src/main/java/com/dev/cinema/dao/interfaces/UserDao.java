package com.dev.cinema.dao.interfaces;

import com.dev.cinema.model.User;
import java.util.Optional;

public interface UserDao {

    User create(User user);

    Optional<User> findByEmail(String email);
}
