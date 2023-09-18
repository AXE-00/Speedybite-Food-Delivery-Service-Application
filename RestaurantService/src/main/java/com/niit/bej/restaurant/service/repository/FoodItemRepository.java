package com.niit.bej.restaurant.service.repository;

import com.niit.bej.restaurant.service.model.FoodItems;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository {
    public FoodItems findByItemName(String itemName);
}
