package com.niit.bej.user.service.model;

import java.util.List;

public class User {
    private String email;
    private String password;
    private String name;
    private long phoneNumber;
    private String role = "user";
    private Address address;
    private String city;
    private byte[] imageName;
    private List<FavouriteCart> favouriteCartList;
}
