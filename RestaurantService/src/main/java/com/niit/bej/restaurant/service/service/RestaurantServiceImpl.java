package com.niit.bej.restaurant.service.service;

import com.niit.bej.restaurant.service.exception.FoodItemNotFoundException;
import com.niit.bej.restaurant.service.exception.RestaurantAlreadyExistsException;
import com.niit.bej.restaurant.service.exception.RestaurantNotFoundException;
import com.niit.bej.restaurant.service.model.FoodItems;
import com.niit.bej.restaurant.service.model.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    @Override
    public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistsException {
        return null;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return null;
    }

    @Override
    public Restaurant getRestaurantByLocation(String location) {
        return null;
    }

    @Override
    public Restaurant getByRating(double rating) {
        return null;
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant, int restaurantId) throws RestaurantNotFoundException {
        return null;
    }

    @Override
    public boolean deleteById(int restaurantId) throws RestaurantNotFoundException {
        return false;
    }

    @Override
    public List<FoodItems> getItems(int id) throws RestaurantNotFoundException {
        return null;
    }

    @Override
    public Restaurant updateFoodItem(int restaurantId, FoodItems newFoodItem) throws RestaurantNotFoundException, FoodItemNotFoundException {
        return null;
    }

    @Override
    public boolean deleteFoodItem(int restaurantId, FoodItems foodItem) throws RestaurantNotFoundException, FoodItemNotFoundException {
        return false;
    }
}
