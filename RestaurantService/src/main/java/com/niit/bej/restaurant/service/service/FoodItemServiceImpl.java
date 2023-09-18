package com.niit.bej.restaurant.service.service;

import com.niit.bej.restaurant.service.exception.RestaurantNotFoundException;
import com.niit.bej.restaurant.service.model.FoodItems;
import com.niit.bej.restaurant.service.repository.FoodItemRepository;
import com.niit.bej.restaurant.service.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return foodItemRepository.findAll();
    }

    @Override
    public FoodItems getById(int itemId) {
        return foodItemRepository.findById(itemId).get();
    }

    @Override
    public FoodItems getByName(String itemName) {
        return foodItemRepository.findByItemName(itemName);
    }

    @Override
    public FoodItems updateFoodItem(FoodItems items, int itemId) {
        Optional<FoodItems> checkById = foodItemRepository.findById(itemId);
        if (checkById.isEmpty()) {
            return null;
        }
        FoodItems existingItem = checkById.get();
        if (items.getItemName() != null) {
            existingItem.setItemName(items.getItemName());
        }
        if (items.getImageUrl() != null) {
            existingItem.setItemRating(items.getItemRating());
        }
        if (items.getItemRating() != 0) {
            existingItem.setItemRating(items.getItemRating());
        }
        if (items.getItemPrice() != 0) {
            existingItem.setItemPrice(items.getItemPrice());
        }
        return foodItemRepository.save(existingItem);
    }

    @Override
    public boolean deleteById(int itemId) {
        if (foodItemRepository.findById(itemId).isEmpty()) {
            return false;
        } else {
            foodItemRepository.deleteById(itemId);
            return true;
        }
    }
}
