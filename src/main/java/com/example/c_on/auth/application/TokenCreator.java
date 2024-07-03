package com.example.c_on.auth.application;

import com.example.c_on.auth.domain.AuthToken;
import org.springframework.stereotype.Component;

@Component
public interface TokenCreator {

    AuthToken createAuthToken(final String customerName);

    AuthToken renewAuthToken(final String outRefreshToken);

    String extractPayload(final String accessToken);
}
