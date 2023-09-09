package com.niit.bej.user.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    private String houseNumber;
    private String landmark;
    private String street;
    private String city;
    private String zipcode;

}
