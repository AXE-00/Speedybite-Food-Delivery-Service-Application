package com.niit.bej.user.auth.service.security;

import com.niit.bej.user.auth.model.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String, String> generateToken(User user);
}
