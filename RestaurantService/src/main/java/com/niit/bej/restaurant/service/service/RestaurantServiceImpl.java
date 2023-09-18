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
        if (restaurantRepository.findById(restaurant.getRestaurantId()).isPresent() {
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
        return null;
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
        return null;
    }

    @Override
    public boolean deleteFoodItem(int restaurantId, FoodItems foodItem) throws RestaurantNotFoundException, FoodItemNotFoundException {
        return false;
    }
}
