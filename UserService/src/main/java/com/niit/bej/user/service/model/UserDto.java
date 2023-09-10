package com.niit.bej.user.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String email;
    private String password;
    private String name;
    private String role;
    private long phoneNumber;
    private String imageName;
}
