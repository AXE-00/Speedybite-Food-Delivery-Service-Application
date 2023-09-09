package com.niit.bej.user.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FavouriteCart {
    @Id
    private int itemId;
    private String itemName;
    private float itemPrice;
    private float itemRating;
    private String imageUrl;
}
