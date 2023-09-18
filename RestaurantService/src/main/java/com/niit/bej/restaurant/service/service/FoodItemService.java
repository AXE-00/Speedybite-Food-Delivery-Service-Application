package com.niit.bej.restaurant.service.service;

import com.niit.bej.restaurant.service.exception.RestaurantNotFoundException;
import com.niit.bej.restaurant.service.model.FoodItems;

import java.util.List;

public interface FoodItemService {
    FoodItems addItems(FoodItems items, int restaurantId) throws RestaurantNotFoundException;

    List<FoodItems> getAllItems();

    FoodItems getById(int itemId);

    FoodItems getByName(int itemName);

}
