package com.niit.bej.restaurant.service.service;

import com.niit.bej.restaurant.service.exception.RestaurantAlreadyExistsException;
import com.niit.bej.restaurant.service.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant addRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistsException;

    List<Restaurant> getAllRestaurants();

    Restaurant getRestaurantByLocation(String location);

    Restaurant getByRating(double rating);
}
