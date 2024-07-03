package com.example.c_on.auth.application;

import com.example.c_on.auth.domain.AuthToken;
import com.example.c_on.auth.dto.response.AccessAndRefreshTokenResponse;
import java.util.Objects;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@AllArgsConstructor
public class AuthService {

    private final TokenCreator tokenCreator;

    public String extractCustomerId(String accessToken) {
        return tokenCreator.extractPayload(accessToken);
    }

    public AccessAndRefreshTokenResponse generateAccessAndRefreshToken(String customerName, String cno) {
        AuthToken authToken = tokenCreator.createAuthToken(customerName);
        String authority = "common";
        if (Objects.equals("c0", cno)) {
            authority = "admin";
        }
        return new AccessAndRefreshTokenResponse(authToken.getAccessToken(), authToken.getRefreshToken(), authority);
    }
}
