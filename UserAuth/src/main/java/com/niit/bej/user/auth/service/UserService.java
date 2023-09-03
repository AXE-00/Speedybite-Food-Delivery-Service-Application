package com.niit.bej.user.auth.service;

import com.niit.bej.user.auth.exception.InvalidCredentialsException;
import com.niit.bej.user.auth.exception.UserAlreadyExistsException;
import com.niit.bej.user.auth.exception.UserNotFoundException;
import com.niit.bej.user.auth.model.User;

public interface UserService {
    User registerUser(User user) throws UserAlreadyExistsException;

    boolean loginUser(User user) throws InvalidCredentialsException, UserNotFoundException;
}
