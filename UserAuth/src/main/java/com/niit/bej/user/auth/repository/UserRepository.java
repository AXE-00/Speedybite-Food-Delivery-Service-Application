package com.niit.bej.user.auth.repository;

import com.niit.bej.user.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
