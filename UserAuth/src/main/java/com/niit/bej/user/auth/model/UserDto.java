package com.niit.bej.user.auth.model;

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
    private long phoneNumber;
    private String role;
    private String imageName;
}
