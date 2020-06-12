package com.dev.cinema.mapper;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.UserRequestDto;
import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private UserService userService;

    public UserResponseDto convertToResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }

    public User convertDtoToUser(UserRequestDto userRequestDto) {
        User user = new User();
        String email = userRequestDto.getEmail();
        Long id = userService.getByEmail(email).getId();
        user.setId(id);
        user.setEmail(email);
        return user;
    }
}
