package com.niit.bej.restaurant.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Restaurant with this Id does not exist in database")
public class RestaurantNotFoundException extends Exception {
}
