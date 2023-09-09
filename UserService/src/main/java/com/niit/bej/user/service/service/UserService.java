package com.niit.bej.user.service.service;

import com.niit.bej.user.service.exception.UserNotFoundException;
import com.niit.bej.user.service.model.FavouriteCart;
import com.niit.bej.user.service.model.User;

public interface UserService {
    public User addUser(User user);

    public User addFavouritesInList(String email, FavouriteCart favouriteCart);

    public User updateUser(String email, User updatedUser) throws UserNotFoundException;
}
