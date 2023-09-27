package com.niit.bej.user.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.bej.user.service.exception.FavouriteItemNotFoundException;
import com.niit.bej.user.service.exception.UserNotFoundException;
import com.niit.bej.user.service.model.Address;
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
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/userService")
public class UserController {
    private final UserService userService;

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

    @GetMapping("/get/profile")
    public ResponseEntity<?> getUserImage(HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        byte[] imageData = userService.getUserProfileImage(email);
        if (imageData == null) {
            return new ResponseEntity<>("No image found", HttpStatus.NOT_FOUND);
        }
        String encodedImage = Base64.getEncoder().encodeToString(imageData);
        Map<String, Object> response = new HashMap<>();
        response.put("imageData", encodedImage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getName")
    public ResponseEntity<?> getUserName(HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        if (email.isEmpty()) {
            return new ResponseEntity<>("Not a valid email", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(userService.getUserName(email), HttpStatus.OK);
        }
    }

    @GetMapping("/get/user/favourite")
    public ResponseEntity<?> getUserFavourite(HttpServletRequest request) throws FavouriteItemNotFoundException {
        String email = (String) request.getAttribute("email");
        return new ResponseEntity<>(userService.getListOfFavouriteById(email), HttpStatus.OK);
    }

    @DeleteMapping("/remove/favourite")
    public ResponseEntity<?> removeFavouriteFromList(HttpServletRequest request, @RequestParam int itemId) throws FavouriteItemNotFoundException {
        String email = (String) request.getAttribute("email");
        userService.removeFavoriteById(email, itemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/check/list")
    public ResponseEntity<?> checkList(HttpServletRequest request, @RequestParam int itemId) {
        String email = (String) request.getAttribute("email");
        return new ResponseEntity<>(userService.favouriteItemExists(email, itemId), HttpStatus.OK);
    }

    @PostMapping("/add/address")
    public ResponseEntity<?> addAddress(HttpServletRequest request, @RequestBody Address address) {
        String email = (String) request.getAttribute("email");
        return new ResponseEntity<>(userService.addAddress(email, address), HttpStatus.OK);
    }

    @GetMapping("/get/address")
    public ResponseEntity<?> getAddress(HttpServletRequest request) {
        String email = (String) request.getAttribute("email");
        return new ResponseEntity<>(userService.getAddress(email), HttpStatus.OK);
    }
}
