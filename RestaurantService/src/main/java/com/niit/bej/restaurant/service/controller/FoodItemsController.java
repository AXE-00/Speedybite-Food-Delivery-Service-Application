package com.niit.bej.restaurant.service.controller;

import com.niit.bej.restaurant.service.exception.RestaurantNotFoundException;
import com.niit.bej.restaurant.service.model.FoodItems;
import com.niit.bej.restaurant.service.service.FoodItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cuisine")
public class FoodItemsController {
    @Autowired
    FoodItemService foodItemService;

    @PostMapping("/addNewCuisine/{restaurantId")
    public ResponseEntity<?> addCuisine(HttpServletRequest httpServletRequest, @RequestBody FoodItems foodItems, @PathVariable int restaurantId) throws RestaurantNotFoundException {
        if (httpServletRequest.getAttribute("email").equals("ashutosh.k.work@gmail.com")) {
            return new ResponseEntity<>(foodItemService.addItems(foodItems, restaurantId), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("You are not authorized to add new items", HttpStatus.UNAUTHORIZED);
        }
    }

}
