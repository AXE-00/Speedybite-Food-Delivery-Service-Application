package com.niit.bej.restaurant.service.service;

import com.niit.bej.restaurant.service.exception.FoodItemNotFoundException;
import com.niit.bej.restaurant.service.exception.RestaurantAlreadyExistsException;
import com.niit.bej.restaurant.service.exception.RestaurantNotFoundException;
import com.niit.bej.restaurant.service.model.FoodItems;
import com.niit.bej.restaurant.service.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant addRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistsException;

    List<Restaurant> getAllRestaurants();


    Restaurant getByRating(double rating);

    Restaurant updateRestaurant(Restaurant restaurant, int restaurantId) throws RestaurantNotFoundException;

    boolean deleteById(int restaurantId) throws RestaurantNotFoundException;

    List<FoodItems> getItems(int id) throws RestaurantNotFoundException;

    Restaurant updateFoodItem(int restaurantId, FoodItems newFoodItem) throws RestaurantNotFoundException, FoodItemNotFoundException;

    boolean deleteFoodItem(int restaurantId, FoodItems foodItems) throws RestaurantNotFoundException, FoodItemNotFoundException;
}
