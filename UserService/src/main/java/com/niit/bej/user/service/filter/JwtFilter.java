package com.niit.bej.user.service.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class JwtFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletOutputStream responseOutputStream = response.getOutputStream();
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            responseOutputStream.println("Authorization Header Missing!");
            responseOutputStream.close();
            return;
        }
        String jsonWebToken = authorizationHeader.substring("Bearer ".length());
        Claims claims = Jwts.parser().setSigningKey("U3BlZWR5Qml0ZXNGb29kRGVsaXZlcnlTZXJ2aWNl").parseClaimsJwt(jsonWebToken).getBody();
        request.setAttribute("claims", claims);
        request.setAttribute("issuer", claims.getIssuer());
        request.setAttribute("subject", claims.getSubject());
        request.setAttribute("email", claims.get("email"));
        chain.doFilter(request, response);
    }
}
