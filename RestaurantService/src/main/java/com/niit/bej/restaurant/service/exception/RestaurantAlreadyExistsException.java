package com.niit.bej.restaurant.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Restaurant with this Id already exists in database!")
public class RestaurantAlreadyExistsException extends Exception {
}
