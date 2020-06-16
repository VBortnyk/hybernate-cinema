package com.dev.cinema.model.dto;

import com.dev.cinema.annotations.EmailValidation;

public class UserResponseDto {
    @EmailValidation
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
