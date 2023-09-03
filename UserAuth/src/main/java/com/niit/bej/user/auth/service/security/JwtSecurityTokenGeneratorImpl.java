package com.niit.bej.user.auth.service.security;

import com.niit.bej.user.auth.model.User;

import java.util.Map;

public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator {
    public static final int SECONDS = 60;
    public static final int MINUTES = 60;
    public static final int DURATION = 10;
    public static final int MILLISECOND = 1000;

    @Override
    public Map<String, String> generateToken(User user) {
        return null;
    }
}
