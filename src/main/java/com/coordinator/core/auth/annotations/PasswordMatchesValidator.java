package com.coordinator.core.auth.annotations;

import com.coordinator.core.auth.models.AuthUserRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        AuthUserRequest authUserRequest = (AuthUserRequest) obj;
        return authUserRequest.getPassword().equals(authUserRequest.getMatchingPassword());
    }
}