package com.example.c_on.auth.application;

import com.example.c_on.auth.domain.AuthToken;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class AuthTokenCreator implements TokenCreator{

    private final TokenProvider tokenProvider;

    public AuthTokenCreator(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public AuthToken createAuthToken(String customerName) {
        String accessToken = tokenProvider.createAccessToken(customerName);
        String refreshToken = tokenProvider.createRefreshToken(customerName);
        return new AuthToken(accessToken, refreshToken);
    }

    @Override
    public AuthToken renewAuthToken(String outRefreshToken) {
        return null;
    }

    @Override
    public String extractPayload(String accessToken) {
        return tokenProvider.getPayload(accessToken);
    }
}
