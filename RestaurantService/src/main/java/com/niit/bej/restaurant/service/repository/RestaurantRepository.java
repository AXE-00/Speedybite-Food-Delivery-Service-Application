package com.niit.bej.restaurant.service.repository;

import com.niit.bej.restaurant.service.model.Restaurant;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository {
    Restaurant findByLocation(String location);

    Restaurant findByRating(double rating);
}
