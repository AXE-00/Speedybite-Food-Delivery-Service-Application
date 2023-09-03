package com.niit.bej.user.auth.service.security;

import com.niit.bej.user.auth.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator {
    public static final int SECONDS = 60;
    public static final int MINUTES = 60;
    public static final int DURATION = 10;
    public static final int MILLISECOND = 1000;

    @Override
    public Map<String, String> generateToken(User user) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        String token = Jwts.builder()
                .setIssuedAt(new Date())
                .setIssuer("user-auth")
                .setExpiration(new Date(System.currentTimeMillis() + MILLISECOND * SECONDS * MINUTES * DURATION))
                .setClaims(claims)
                .setSubject(user.getEmail())
                .signWith(SignatureAlgorithm.HS256, "password")
                .compact();

        return Map.of("token", token, "message", user.getEmail() + " logged in successfully!");
    }
}
