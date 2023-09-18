package com.niit.bej.restaurant.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class FoodItems {
    @Id
    private int itemId;
    private String itemName;
    private int itemPrice;
    private String imageUrl;
    private double itemRating;
    private String description;
}
