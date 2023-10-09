package com.niit.bej.user.auth.service;

import com.niit.bej.user.auth.exception.UserAlreadyExistsException;
import com.niit.bej.user.auth.exception.UserNotFoundException;
import com.niit.bej.user.auth.model.User;

public interface UserService {
    User registerUser(User user) throws UserAlreadyExistsException;

    User loginUser(User user) throws UserNotFoundException;

    User updateUser(String email, String password, String imageName, long phoneNumber) throws UserNotFoundException;

}
