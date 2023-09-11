package com.niit.bej.user.service.service;

import com.niit.bej.user.service.exception.FavouriteItemNotFoundException;
import com.niit.bej.user.service.exception.UserNotFoundException;
import com.niit.bej.user.service.model.Address;
import com.niit.bej.user.service.model.FavouriteCart;
import com.niit.bej.user.service.model.User;
import com.niit.bej.user.service.model.UserDto;
import com.niit.bej.user.service.proxy.UserProxy;
import com.niit.bej.user.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserProxy userProxy;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProxy userProxy) {
        this.userRepository = userRepository;
        this.userProxy = userProxy;
    }

    @Override
    public User addUser(User user) {
        UserDto userDto = new UserDto();

        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setName(user.getName());
        userDto.setRole(user.getRole());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setImageName(user.getImageName());
        System.out.println(userDto);
        userProxy.registerUser(userDto);
        return userRepository.save(user);
    }

    @Override
    public User addFavouritesInList(String email, FavouriteCart favouriteCart) {
        User user = userRepository.findById(email).get();

        boolean alreadyExists = false;
        if (user.getFavouriteCartList() != null) {
            for (FavouriteCart list : user.getFavouriteCartList()) {
                if (list.getItemName().equals(favouriteCart.getItemName())) {
                    alreadyExists = true;
                    break;
                }
            }
        }
        if (!alreadyExists) {
            if (user.getFavouriteCartList() == null) {
                user.setFavouriteCartList(new ArrayList<>());
            }
            user.getFavouriteCartList().add(favouriteCart);
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(String email, User updatedUser) throws UserNotFoundException {
        if (userRepository.findById(email).isEmpty()) {
            throw new UserNotFoundException("User not found in database!");
        }
        User existingUser = userRepository.findById(email).get();
        UserDto userDto = new UserDto();

        if (updatedUser.getProfileImage() != null) {
            existingUser.setProfileImage(updatedUser.getProfileImage());
        }
        if (updatedUser.getProfileImage() != null) {
            existingUser.setName(updatedUser.getName());
        }
        if (updatedUser.getPhoneNumber() != 0) {
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        }
        if (updatedUser.getImageName() != null) {
            existingUser.setImageName(updatedUser.getImageName());
            userDto.setImageName(updatedUser.getImageName());
        }
        userProxy.updateUser(userDto, email);
        return userRepository.save(existingUser);
    }

    @Override
    public List<FavouriteCart> getListOfFavouriteById(String email) throws FavouriteItemNotFoundException {
        if (userRepository.findById(email).isEmpty()) {
            throw new FavouriteItemNotFoundException("No favourite item found in database!");
        }
        User user = userRepository.findById(email).get();
        System.out.println(user.getFavouriteCartList());
        return user.getFavouriteCartList();
    }

    @Override
    public void removeFavoriteById(String email, int itemId) throws FavouriteItemNotFoundException {
        if (userRepository.findById(email).isEmpty()) {
            throw new FavouriteItemNotFoundException("No favourite item found in database!");
        }
        User user = userRepository.findById(email).get();
        List<FavouriteCart> favouriteCartList = user.getFavouriteCartList();
        for (FavouriteCart list : favouriteCartList) {
            if (list.getItemId() == itemId) {
                favouriteCartList.remove(list);
                System.out.println("Removed successfully");
                userRepository.save(user);
                break;
            }
        }
    }

    @Override
    public boolean favouriteItemExists(String email, int itemId) {
        List<FavouriteCart> favouriteCartList = userRepository.findById(email).get().getFavouriteCartList();
        for (FavouriteCart list : favouriteCartList) {
            if (list.getItemId() == itemId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public byte[] getUserProfileImage(String email) {
        User user = userRepository.findById(email).get();
        if (user.getEmail() != null) {
            return user.getProfileImage();
        } else {
            return null;
        }
    }

    @Override
    public boolean addAddress(String email, Address address) {
        return false;
    }

    @Override
    public User getUserName(String email) {
        User user = userRepository.findById(email).get();
        if (user.getEmail().equals(email)) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public Address getAddress(String email) {
        return null;
    }
}
