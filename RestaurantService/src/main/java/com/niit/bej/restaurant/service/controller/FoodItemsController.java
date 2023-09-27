package com.niit.bej.restaurant.service.controller;

import com.niit.bej.restaurant.service.exception.RestaurantNotFoundException;
import com.niit.bej.restaurant.service.model.FoodItems;
import com.niit.bej.restaurant.service.service.FoodItemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/cuisine")
public class FoodItemsController {
    @Autowired
    FoodItemService foodItemService;

    @PostMapping("/addNewCuisine/{restaurantId}")
    public ResponseEntity<?> addCuisine(HttpServletRequest httpServletRequest, @RequestBody FoodItems foodItems, @PathVariable int restaurantId) throws RestaurantNotFoundException {
        if (httpServletRequest.getAttribute("email").equals("ashutosh.k.work@gmail.com")) {
            return new ResponseEntity<>(foodItemService.addItems(foodItems, restaurantId), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("You are not authorized to add new items", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/getAllItems")
    public ResponseEntity<?> getAllItems() {
        return new ResponseEntity<>(foodItemService.getAllItems(), HttpStatus.OK);
    }

    @GetMapping("/getByName/{itemName}")
    public ResponseEntity<?> getByName(@PathVariable String itemName) {
        return new ResponseEntity<>(foodItemService.getByName(itemName), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(HttpServletRequest httpServletRequest, @RequestBody FoodItems foodItems, @PathVariable int id) {
        if (httpServletRequest.getAttribute("email").equals("ashutosh.k.work@gmail.com")) {
            return new ResponseEntity<>(foodItemService.updateFoodItem(foodItems, id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("You are not authorized to update", HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(HttpServletRequest httpServletRequest, @PathVariable int id) {
        if (httpServletRequest.getAttribute("email").equals("ashutosh.k.work@gmail.com")) {
            return new ResponseEntity<>(foodItemService.deleteById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("You are not authorized to delete", HttpStatus.UNAUTHORIZED);
        }
    }
}
