package com.niit.bej.restaurant.service.repository;

import com.niit.bej.restaurant.service.model.FoodItems;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends MongoRepository<FoodItems, Integer> {
    FoodItems findByItemName(String itemName);
}
