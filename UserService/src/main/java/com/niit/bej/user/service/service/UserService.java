package com.niit.bej.user.service.service;

import com.niit.bej.user.service.exception.FavouriteItemNotFoundException;
import com.niit.bej.user.service.exception.UserNotFoundException;
import com.niit.bej.user.service.model.FavouriteCart;
import com.niit.bej.user.service.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    User addFavouritesInList(String email, FavouriteCart favouriteCart);

    User updateUser(String email, User updatedUser) throws UserNotFoundException;

    List<FavouriteCart> getListOfFavouriteById(String email) throws FavouriteItemNotFoundException;

}
