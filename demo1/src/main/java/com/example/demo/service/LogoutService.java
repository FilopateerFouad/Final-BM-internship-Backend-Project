package com.example.demo.service;

import com.example.demo.entity.Token;
import com.example.demo.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
    private final TokenRepository tokenRepository;
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response , Authentication  authentication){
        String headerAuth = request.getHeader("Authorization");
        final String jwt;
        if (headerAuth == null || ! headerAuth.startsWith("Bearer ")) {
            return;
        }
        jwt= headerAuth.substring(7);
        Token storedToken = tokenRepository.findByToken(jwt).orElse(null);
        if(storedToken!= null){
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
        }
    }
}