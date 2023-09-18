package com.niit.bej.restaurant.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Document
public class Restaurant {
    @Id
    private int restaurantId;
    private String restaurantName;
    private String imageUrl;
    private String location;
    private double rating;
    @Getter
    private List<FoodItems> items;

    public Restaurant() {
        this.items = new ArrayList<>();
    }

    public void setItems(List<FoodItems> items) {
        this.items = items;
    }
}
