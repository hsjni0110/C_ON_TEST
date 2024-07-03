package com.example.c_on.auth.application;

import com.example.c_on.auth.exception.UnAuthorizationException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider implements TokenProvider{

    private final SecretKey key;
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityinMilliseconds;

    public JwtTokenProvider(@Value("${security.jwt.token.secret-key}") final String key,
                            @Value("${security.jwt.token.access.expire-length}") final long accessTokenValidityInMilliseconds,
                            @Value("${security.jwt.token.refresh.expire-length}") long refreshTokenValidityinMilliseconds) {
        this.key = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
        this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds;
        this.refreshTokenValidityinMilliseconds = refreshTokenValidityinMilliseconds;
    }

    @Override
    public String createAccessToken(String payload) {
        return createToken(payload, accessTokenValidityInMilliseconds);
    }

    private String createToken(String payload, long validateTime) {
        Date now = new Date();
        Date validaty = new Date(now.getTime() + validateTime);

        return Jwts.builder()
                .setSubject(payload)
                .setIssuedAt(now)
                .setExpiration(validaty)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String createRefreshToken(String payload) {
        return createToken(payload, refreshTokenValidityinMilliseconds);
    }

    @Override
    public String getPayload(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            throw new UnAuthorizationException("Expired JWT Token");
        }
    }

    @Override
    public void validateToken(String token) {
        return;
    }
}
