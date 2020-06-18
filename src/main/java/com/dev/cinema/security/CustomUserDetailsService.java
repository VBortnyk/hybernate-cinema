package com.dev.cinema.security;

import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getByEmail(email);
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder;
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        userBuilder = org.springframework.security.core.userdetails
                .User.withUsername(email);
        userBuilder.password(user.getPassword());
        String[] roles = user.getRoles().stream()
                .map(role -> role.getRoleName().name())
                .toArray(String[]::new);
        return userBuilder.build();
    }
}
