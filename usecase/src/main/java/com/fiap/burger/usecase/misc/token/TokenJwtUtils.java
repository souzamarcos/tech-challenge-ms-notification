package com.fiap.burger.usecase.misc.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fiap.burger.usecase.misc.exception.TokenJwtException;
import com.fiap.burger.usecase.misc.secret.SecretUtils;
import com.fiap.burger.usecase.misc.secret.TokenJwtSecret;


public class TokenJwtUtils {
    private TokenJwtUtils() {

    }

    public static DecodedJWT readToken(String token) {
        try {
            JWTVerifier verifier = buildJwtVerifier();
            return verifier.verify(token);
        } catch (TokenExpiredException tokenExpiredException) {
            throw new TokenJwtException("Token JWT expired at " + tokenExpiredException.getExpiredOn());
        } catch (Exception e) {
            throw new TokenJwtException("Token JWT Error: " + e.getMessage());
        }
    }

    private static JWTVerifier buildJwtVerifier() {
        TokenJwtSecret jwtSecret = SecretUtils.getTotalJwtSecret();
        return JWT.require(buildAlgorithm(jwtSecret.getSecret()))
            .withIssuer(jwtSecret.getIssuer())
            .build();
    }

    private static Algorithm buildAlgorithm(String secret) {
        return Algorithm.HMAC256(secret);
    }
}
