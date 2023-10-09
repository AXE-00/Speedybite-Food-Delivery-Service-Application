package com.niit.bej.user.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "speedybite_users")
public class User {
    @Id
    @Column(name = "email", length = 50, unique = true, nullable = false)
    private String email;
    @Column(name = "password", length = 30, unique = true, nullable = false)
    private String password;
    @Column(name = "name", length = 30)
    private String name;
    @Column(name = "phoneNumber")
    private long phoneNumber;
    @Column(name = "role", length = 300)
    private String role;
    @Column(name = "imageName", length = 300)
    private String imageName;

}
