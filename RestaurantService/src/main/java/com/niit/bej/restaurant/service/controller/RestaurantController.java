package com.niit.bej.restaurant.service.controller;

import com.niit.bej.restaurant.service.exception.FoodItemNotFoundException;
import com.niit.bej.restaurant.service.exception.RestaurantAlreadyExistsException;
import com.niit.bej.restaurant.service.exception.RestaurantNotFoundException;
import com.niit.bej.restaurant.service.model.FoodItems;
import com.niit.bej.restaurant.service.model.Restaurant;
import com.niit.bej.restaurant.service.repository.RestaurantRepository;
import com.niit.bej.restaurant.service.service.RestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {
    RestaurantService restaurantService;
    RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, RestaurantRepository restaurantRepository) {
        this.restaurantService = restaurantService;
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping
    public ResponseEntity<?> addNewRestaurant(HttpServletRequest httpServletRequest, @RequestBody Restaurant restaurant) throws RestaurantAlreadyExistsException {
        if (httpServletRequest.getAttribute("email").equals("ashutosh.k.work@gmail.com")) {
            return new ResponseEntity<>(restaurantService.addRestaurant(restaurant), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("You are not authorized to add new restaurant", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/addItem/{id}")
    public ResponseEntity<?> addFoodItemsToSameRestaurant(HttpServletRequest httpServletRequest, @PathVariable int id, @RequestBody List<FoodItems> items) throws RestaurantNotFoundException {
        if (httpServletRequest.getAttribute("email").equals("ashutosh.k.work@gmail.com")) {
            Restaurant restaurant = restaurantRepository.findById(id).get();
            restaurant.getItems().addAll(items);
            Restaurant savedRestaurant = restaurantRepository.save(restaurant);
            return ResponseEntity.ok(savedRestaurant);
        } else {
            return new ResponseEntity<>("Restaurant not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getItems/{id}")
    public ResponseEntity<?> getAllFoodItems(@PathVariable int id) throws RestaurantNotFoundException {
        return new ResponseEntity<>(restaurantService.getItems(id), HttpStatus.OK);
    }

    @GetMapping("/getRestaurant")
    public ResponseEntity<?> getAllRestaurants() {
        return new ResponseEntity<>(restaurantService.getAllRestaurants(), HttpStatus.OK);
    }

    @GetMapping("/getRating/{location}")
    public ResponseEntity<?> getRestaurantByLocation(@PathVariable String location) {
        return new ResponseEntity<>(restaurantService.getRestaurantByLocation(location), HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRestaurant(HttpServletRequest httpServletRequest, @RequestBody Restaurant restaurant, @PathVariable int id) throws RestaurantNotFoundException {
        if (httpServletRequest.getAttribute("email").equals("ashutosh.k.work@gmail.com")) {
            return new ResponseEntity<>(restaurantService.updateRestaurant(restaurant, id), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("You are not authorized to update information", HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/updateItem/{restaurantId}")
    public ResponseEntity<?> updateFoodItems(HttpServletRequest httpServletRequest, @PathVariable int restaurantId, @RequestBody FoodItems foodItems) throws RestaurantNotFoundException, FoodItemNotFoundException {
        if (httpServletRequest.getAttribute("email") != null && httpServletRequest.getAttribute("email").equals("ashutosh.k.work@gmail.com")) {
            return new ResponseEntity<>(restaurantService.updateFoodItem(restaurantId, foodItems), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Not authorized to update food items", HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRestaurant(HttpServletRequest httpServletRequest, @PathVariable int id) throws RestaurantNotFoundException {
        if (httpServletRequest.getAttribute("email").equals("ashutosh.k.work@gmail.com")) {
            return new ResponseEntity<>(restaurantService.deleteById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("You are not authorized to delete", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/deleteItem/{id}")
    public ResponseEntity<?> deleteFoodItem(HttpServletRequest httpServletRequest, @PathVariable int id, @RequestBody FoodItems foodItems) throws RestaurantNotFoundException, FoodItemNotFoundException {
        if (httpServletRequest.getAttribute("email") != null && httpServletRequest.getAttribute("email").equals("ashutosh.k.work@gmail.com")) {
            return new ResponseEntity<>(restaurantService.deleteFoodItem(id, foodItems), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("You are not authorized to delete food item", HttpStatus.UNAUTHORIZED);
        }
    }
}
