package com.niit.bej.user.auth.service;

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
        Optional<User> optionalUser = userRepository.findUserByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("User with email ID: " + user.getEmail() + " already exists!");
        } else {
            return userRepository.save(user);
        }
    }

    @Override
    public User loginUser(User user) throws UserNotFoundException {
        if (userRepository.findById(user.getEmail()).isPresent()) {
            return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        } else throw new UserNotFoundException("User with email Id: " + user.getEmail() + " not found");
    }


    @Override
    public User updateUser(String email, String password, String imageName, String phoneNumber) throws UserNotFoundException {
        User user = userRepository.findById(email).orElseThrow(() -> new UserNotFoundException("User not found"));

        if (password != null) {
            user.setPassword(password);
        }
        if (phoneNumber != null) {
            user.setPhoneNumber(phoneNumber);
        }
        if (imageName != null) {
            user.setImageName(imageName);
        }
        return userRepository.save(user);
    }
}
