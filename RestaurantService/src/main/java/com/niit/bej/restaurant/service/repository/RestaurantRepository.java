package com.niit.bej.restaurant.service.repository;

import com.niit.bej.restaurant.service.model.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, Integer> {
    Restaurant findByLocation(String location);

    Restaurant findByRating(double rating);
}
