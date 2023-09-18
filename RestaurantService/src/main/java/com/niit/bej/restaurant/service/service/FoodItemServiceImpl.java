package com.niit.bej.restaurant.service.service;

import com.niit.bej.restaurant.service.exception.RestaurantNotFoundException;
import com.niit.bej.restaurant.service.model.FoodItems;

import java.util.List;

public class FoodItemServiceImpl implements FoodItemService {
    @Override
    public FoodItems addItems(FoodItems items, int restaurantId) throws RestaurantNotFoundException {
        return null;
    }

    @Override
    public List<FoodItems> getAllItems() {
        return null;
    }

    @Override
    public FoodItems getById(int itemId) {
        return null;
    }

    @Override
    public FoodItems getByName(int itemName) {
        return null;
    }

    @Override
    public FoodItems updateFoodItem(FoodItems items, int itemId) {
        return null;
    }

    @Override
    public boolean deleteById(int itemId) {
        return false;
    }
}
