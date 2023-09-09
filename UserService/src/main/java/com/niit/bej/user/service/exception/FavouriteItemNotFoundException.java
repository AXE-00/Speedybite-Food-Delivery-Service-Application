package com.niit.bej.user.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Favourite item not found for this id", code = HttpStatus.NOT_FOUND)
public class FavouriteItemNotFoundException extends Exception {
    public FavouriteItemNotFoundException(String message) {
        super(message);
    }
}
