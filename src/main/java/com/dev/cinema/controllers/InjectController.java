package com.dev.cinema.controllers;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Controller;

@Controller
public class InjectController {
    private final RoleService roleService;
    private final UserService userService;

    public InjectController(RoleService roleService,
                            UserService userService,
                            UserService userService1) {
        this.roleService = roleService;
        this.userService = userService1;
    }

    @PostConstruct
    public void init() {
        injectInitialData();
    }

    public void injectInitialData() {
        Role userRole = Role.of("USER");
        Role adminRole = Role.of("ADMIN");
        roleService.add(userRole);
        roleService.add(adminRole);

        User user = new User();
        user.setEmail("user@gmail.com");
        user.setPassword("password");
        user.setRoles(Set.of(userRole));
        userService.create(user);

        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("password");
        admin.setRoles(Set.of(adminRole));
        userService.create(admin);
    }
}
