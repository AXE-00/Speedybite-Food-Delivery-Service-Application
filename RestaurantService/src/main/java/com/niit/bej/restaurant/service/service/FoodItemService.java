package com.niit.bej.restaurant.service.service;

import com.niit.bej.restaurant.service.exception.RestaurantNotFoundException;
import com.niit.bej.restaurant.service.model.FoodItems;

public interface FoodItemService {
    FoodItems addItems(FoodItems items, int restaurantId) throws RestaurantNotFoundException;
}
