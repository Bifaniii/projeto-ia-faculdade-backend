package br.com.bifani.loginjavaprojetoia.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${JWT_SECRET}")
    private final String jwtSecret;

    @Value("${EXPIRATION_TIME}")
    private final Long expirationTime;

    public JwtUtil(String jwtSecret, Long expirationTime) {
        this.jwtSecret = jwtSecret;
        this.expirationTime = expirationTime;
    }

    public String generateToken(String email) {
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC256(jwtSecret));
    }

    public String validateTokenAndGetEmail(String token) {
        return JWT.require(Algorithm.HMAC256(jwtSecret))
                .build()
                .verify(token)
                .getSubject();
    }
}
