package com.dev.cinema.model.dto;

import com.dev.cinema.annotations.EmailValidation;
import com.dev.cinema.annotations.MatchPasswords;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MatchPasswords(field = "password", fieldMatch = "repeatPassword")
public class UserRegistrationDto {
    @EmailValidation
    @NotNull(message = "Email can not be null")
    private String email;
    @NotNull()
    @Size(min = 8, message = "Password should contain at least 10 symbols")
    private String password;
    @NotNull
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
