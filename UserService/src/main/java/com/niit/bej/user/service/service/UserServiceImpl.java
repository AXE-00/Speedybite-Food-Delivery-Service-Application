package com.niit.bej.user.service.service;

import com.niit.bej.user.service.exception.FavouriteItemNotFoundException;
import com.niit.bej.user.service.exception.UserNotFoundException;
import com.niit.bej.user.service.model.Address;
import com.niit.bej.user.service.model.FavouriteCart;
import com.niit.bej.user.service.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User addFavouritesInList(String email, FavouriteCart favouriteCart) {
        return null;
    }

    @Override
    public User updateUser(String email, User updatedUser) throws UserNotFoundException {
        return null;
    }

    @Override
    public List<FavouriteCart> getListOfFavouriteById(String email) throws FavouriteItemNotFoundException {
        return null;
    }

    @Override
    public void removeFavoriteById(String email, int itemId) throws FavouriteItemNotFoundException {

    }

    @Override
    public boolean favouriteItemExists(String email, int itemId) {
        return false;
    }

    @Override
    public byte[] getUserProfileImage(String email) {
        return new byte[0];
    }

    @Override
    public boolean addAddress(String email, Address address) {
        return false;
    }

    @Override
    public User getUserName(String email) {
        return null;
    }

    @Override
    public Address getAddress(String email) {
        return null;
    }
}
