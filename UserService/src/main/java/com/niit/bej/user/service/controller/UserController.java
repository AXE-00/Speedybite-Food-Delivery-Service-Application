package com.niit.bej.user.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.bej.user.service.exception.UserNotFoundException;
import com.niit.bej.user.service.model.User;
import com.niit.bej.user.service.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/userService")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register/user")
    public ResponseEntity<?> addUser(@RequestParam("file") MultipartFile file, @RequestParam("userData") String user) throws IOException {
        User newUser = new ObjectMapper().readValue(user, User.class);
        newUser.setProfileImage(file.getBytes());
        String filename = file.getOriginalFilename();

        String newFileName = FilenameUtils.getBaseName(filename) + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(filename);
        newUser.setImageName(newFileName);
        return new ResponseEntity<>(userService.addUser(newUser), HttpStatus.CREATED);
    }

    @PutMapping("/update/user")
    public ResponseEntity<?> updateCurrentUser(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file, @RequestParam("userInfo") String user) throws IOException, UserNotFoundException {
        String email = (String) request.getAttribute("email");
        User newUser = new ObjectMapper().readValue(user, User.class);
        if (file != null && !file.isEmpty()) {
            newUser.setProfileImage(file.getBytes());
            String filename = file.getOriginalFilename();
            String newFileName = FilenameUtils.getBaseName(filename) + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(filename);
            System.out.println(newFileName);
            newUser.setImageName(newFileName);
        }
        return new ResponseEntity<>(userService.updateUser(email, newUser), HttpStatus.OK);
    }

}
