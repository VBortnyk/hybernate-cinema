package com.dev.cinema.annotations;

import com.dev.cinema.model.dto.UserRegistrationDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatcher implements ConstraintValidator<MatchPasswords, UserRegistrationDto> {
    @Override
    public boolean isValid(UserRegistrationDto dto, ConstraintValidatorContext context) {
        return dto.getPassword().equals(dto.getRepeatPassword());
    }
}
