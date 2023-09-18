package com.niit.bej.restaurant.service.service;

import com.niit.bej.restaurant.service.exception.FoodItemNotFoundException;
import com.niit.bej.restaurant.service.exception.RestaurantAlreadyExistsException;
import com.niit.bej.restaurant.service.exception.RestaurantNotFoundException;
import com.niit.bej.restaurant.service.model.FoodItems;
import com.niit.bej.restaurant.service.model.Restaurant;
import com.niit.bej.restaurant.service.repository.FoodItemRepository;
import com.niit.bej.restaurant.service.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantAlreadyExistsException {
        if (restaurantRepository.findById(restaurant.getRestaurantId()).isPresent()) {
            throw new RestaurantAlreadyExistsException();
        }
        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurantByLocation(String location) {
        return restaurantRepository.findByLocation(location);
    }

    @Override
    public Restaurant getByRating(double rating) {
        return restaurantRepository.findByRating(rating);
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant, int restaurantId) throws RestaurantNotFoundException {
        Optional<Restaurant> checkById = restaurantRepository.findById(restaurantId);
        if (checkById.isEmpty()) {
            throw new RestaurantNotFoundException();
        }
        Restaurant existingRestaurant = checkById.get();
        if (restaurant.getRestaurantName() != null) {
            existingRestaurant.setRestaurantName(restaurant.getRestaurantName());
        }
        if (restaurant.getLocation() != null) {
            existingRestaurant.setLocation(restaurant.getLocation());
        }
        if (restaurant.getImageUrl() != null) {
            existingRestaurant.setImageUrl(restaurant.getImageUrl());
        }
        if (restaurant.getRating() != 0) {
            existingRestaurant.setRating(restaurant.getRating());
        }
        return restaurantRepository.save(existingRestaurant);
    }

    @Override
    public boolean deleteById(int restaurantId) throws RestaurantNotFoundException {
        return false;
    }

    @Override
    public List<FoodItems> getItems(int id) throws RestaurantNotFoundException {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            return restaurant.getItems();
        } else {
            throw new RestaurantNotFoundException();
        }
    }

    @Override
    public Restaurant updateFoodItem(int restaurantId, FoodItems newFoodItem) throws RestaurantNotFoundException, FoodItemNotFoundException {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            List<FoodItems> items = restaurant.getItems();
            Optional<FoodItems> optionalFoodItem = items.stream().filter(item -> item.getItemId() == newFoodItem.getItemId()).findFirst();
            if (optionalFoodItem.isPresent()) {
                FoodItems existingFoodItem = optionalFoodItem.get();
                existingFoodItem.setItemName(newFoodItem.getItemName());
                existingFoodItem.setItemPrice(newFoodItem.getItemPrice());
                existingFoodItem.setImageUrl(newFoodItem.getImageUrl());
                existingFoodItem.setItemRating(newFoodItem.getItemRating());
                return restaurantRepository.save(restaurant);
            } else {
                throw new FoodItemNotFoundException();
            }
        } else {
            throw new RestaurantNotFoundException();
        }
    }

    @Override
    public boolean deleteFoodItem(int restaurantId, FoodItems foodItems) throws RestaurantNotFoundException, FoodItemNotFoundException {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            Optional<FoodItems> optionalFoodItem = restaurant.getItems().stream()
                    .filter(foodItem -> foodItem.getItemId() == foodItems.getItemId())
                    .findFirst();
            if (optionalFoodItem.isPresent()) {
                FoodItems foodItem = optionalFoodItem.get();
                restaurant.getItems().remove(foodItem);
                foodItemRepository.deleteById(foodItem.getItemId());
                restaurantRepository.save(restaurant);
                return true;
            } else {
                throw new FoodItemNotFoundException();
            }
        } else {
            throw new RestaurantNotFoundException();
        }
    }
}
