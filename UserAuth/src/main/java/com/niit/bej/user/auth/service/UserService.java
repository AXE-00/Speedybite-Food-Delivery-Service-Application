package com.niit.bej.user.auth.service;

import com.niit.bej.user.auth.exception.UserAlreadyExistsException;
import com.niit.bej.user.auth.model.User;

public interface UserService {
    User registerUser(User user) throws UserAlreadyExistsException;
}
