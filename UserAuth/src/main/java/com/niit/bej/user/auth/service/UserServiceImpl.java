package com.niit.bej.user.auth.service;

import com.niit.bej.user.auth.exception.InvalidCredentialsException;
import com.niit.bej.user.auth.exception.UserAlreadyExistsException;
import com.niit.bej.user.auth.exception.UserNotFoundException;
import com.niit.bej.user.auth.model.User;

public class UserServiceImpl implements UserService {
    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        return null;
    }

    @Override
    public boolean loginUser(User user) throws InvalidCredentialsException, UserNotFoundException {
        return false;
    }
}
