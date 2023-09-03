package com.niit.bej.user.auth.service;

import com.niit.bej.user.auth.exception.InvalidCredentialsException;
import com.niit.bej.user.auth.exception.UserAlreadyExistsException;
import com.niit.bej.user.auth.exception.UserNotFoundException;
import com.niit.bej.user.auth.model.User;
import com.niit.bej.user.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyExistsException {
        Optional<User> optionalUser = this.userRepository.findUserByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("User With email Id: " + user.getEmail() + " already exists!");
        } else {
            return this.userRepository.save(user);
        }
    }

    @Override
    public boolean loginUser(User user) throws InvalidCredentialsException, UserNotFoundException {
        Optional<User> optionalUser = this.userRepository.findUserByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            User userInDatabase = optionalUser.get();
            if (userInDatabase.getEmail().equals(user.getEmail()) && userInDatabase.getPassword().equals(user.getPassword())) {
                return true;
            } else {
                throw new InvalidCredentialsException("Email or Password incorrect");
            }
        } else {
            throw new UserNotFoundException("User with email Id: " + user.getEmail() + " does not exists!");
        }
    }
}
