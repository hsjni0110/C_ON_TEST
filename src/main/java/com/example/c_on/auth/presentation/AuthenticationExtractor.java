package com.example.c_on.auth.presentation;

import com.example.c_on.auth.exception.UnAuthorizationException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Objects;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationExtractor {

    private static final String BEARER_TYPE = "Bearer ";

    public static String extract(final HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Objects.isNull(authorizationHeader)) {
            throw new UnAuthorizationException("적합하지 않은 헤더입니다.");
        }

        return authorizationHeader.substring(BEARER_TYPE.length()).trim();
    }
}
