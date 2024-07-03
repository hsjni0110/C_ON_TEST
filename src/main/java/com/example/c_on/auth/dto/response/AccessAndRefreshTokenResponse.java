package com.example.c_on.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AccessAndRefreshTokenResponse {

    private String accessToken;
    private String refreshToken;
    private String authority;
}
