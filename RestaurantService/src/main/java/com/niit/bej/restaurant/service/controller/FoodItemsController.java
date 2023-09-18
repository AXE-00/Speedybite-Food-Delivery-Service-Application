package com.niit.bej.restaurant.service.controller;

import com.niit.bej.restaurant.service.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cuisine")
public class FoodItemsController {
    @Autowired
    FoodItemService foodItemService;
}
