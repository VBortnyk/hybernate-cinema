package com.dev.cinema.model.dto;

import com.dev.cinema.annotations.Email;

public class UserResponseDto {
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
