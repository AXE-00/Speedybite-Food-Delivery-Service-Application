package com.niit.bej.restaurant.service.service;

import com.niit.bej.restaurant.service.exception.RestaurantNotFoundException;
import com.niit.bej.restaurant.service.model.FoodItems;
import com.niit.bej.restaurant.service.repository.FoodItemRepository;
import com.niit.bej.restaurant.service.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemServiceImpl implements FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public FoodItems addItems(FoodItems items, int restaurantId) throws RestaurantNotFoundException {
        if (restaurantRepository.findById(restaurantId).isPresent()) {
            return foodItemRepository.save(items);
        } else {
            throw new RestaurantNotFoundException();
        }
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
