package com.niit.bej.user.service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Document
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {
    @Id
    private String email;
    private String password;
    private String name;
    private long phoneNumber;
    private String role = "user";
    private Address address;
    private String city;
    private byte[] profileImage;
    private String imageName;
    private List<FavouriteCart> favouriteCartList;
}
