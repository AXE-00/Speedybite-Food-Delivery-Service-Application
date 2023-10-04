package com.niit.bej.user.auth.controller;

import com.niit.bej.user.auth.exception.InvalidCredentialsException;
import com.niit.bej.user.auth.exception.UserAlreadyExistsException;
import com.niit.bej.user.auth.exception.UserNotFoundException;
import com.niit.bej.user.auth.model.User;
import com.niit.bej.user.auth.model.UserDto;
import com.niit.bej.user.auth.repository.UserRepository;
import com.niit.bej.user.auth.service.UserService;
import com.niit.bej.user.auth.service.security.SecurityTokenGenerator;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth")
public class UserController {
    private final UserService userService;

    private final SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody UserDto userDto) throws UserAlreadyExistsException {
        User user = new User(userDto.getEmail(), userDto.getPassword(), userDto.getName(), userDto.getPhoneNumber(), userDto.getRole(), userDto.getImageName());
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            User retrievedUser = this.userService.loginUser(user);
            if (retrievedUser != null) {
                Map<String, String> generatedToken = this.securityTokenGenerator.generateToken(user);
                return new ResponseEntity<>(generatedToken, HttpStatus.OK);
            } else {
                throw new InvalidCredentialsException("Email or Password incorrect!");
            }
        } catch (UserNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        } catch (InvalidCredentialsException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/updateUser/{email}")
    public ResponseEntity<?> updateUser(@PathVariable String email, @RequestBody UpdateRequest request) {

        try {
            User updatedUser = userService.updateUser(email, request.getPassword(), request.getImageName(), request.getPhoneNumber());
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException exception) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Getter
    static class UpdateRequest {
        private String password;
        private String phoneNumber;
        private String imageName;
    }
}
