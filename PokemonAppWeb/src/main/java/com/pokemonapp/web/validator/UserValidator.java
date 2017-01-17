package com.pokemonapp.web.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.pokemonapp.db.datamodel.User;
import com.pokemonapp.servicelayer.service.UserService;
import com.pokemonapp.web.viewmodel.UserViewModel;

/**
 * User Validator.
 */
@Component
public class UserValidator implements Validator {

    private static final String EMPTY_USER_NAME_MESSAGE = "User field must not be empty";
    private static final String DUPLICATE_USER_NAME_MESSAGE = "User already exists";
    private static final String WRONG_LENGTH_USER_NAME_MESSAGE =
        "The length of the user name should be between 6 and 12 characters.";
    private static final String WRONG_LENGTH_PASSWORD_MESSAGE =
        "The length of the password should be between 6 and 12 characters.";
    private static final String EMPTY_PASSWORD_MESSAGE = "Password must not be empty";
    private static final String PASSWORD_CONFIRM_NOT_MATCHED_MESSAGE = "Password and Password confirmation do not match";

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserViewModel.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        UserViewModel user = (UserViewModel) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", EMPTY_USER_NAME_MESSAGE, EMPTY_USER_NAME_MESSAGE);
        if (user.getUserName().length() < 6 || user.getUserName().length() > 32) {
            errors.rejectValue("userName", null, WRONG_LENGTH_USER_NAME_MESSAGE);
        }
        Optional<User> userOptional = userService.findByUserName(user.getUserName());
        if (userOptional.isPresent()) {
            errors.rejectValue("userName", null, DUPLICATE_USER_NAME_MESSAGE);
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", EMPTY_PASSWORD_MESSAGE);
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", null, WRONG_LENGTH_PASSWORD_MESSAGE);
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", null, PASSWORD_CONFIRM_NOT_MATCHED_MESSAGE);
        }
    }
}
