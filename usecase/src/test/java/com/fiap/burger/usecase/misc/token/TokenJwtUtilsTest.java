package com.fiap.burger.usecase.misc.token;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fiap.burger.usecase.misc.exception.TokenJwtException;
import com.fiap.burger.usecase.misc.secret.SecretUtils;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TokenJwtUtilsTest {
    private static final String TOKEN_SECRET = "TEST-SECRET";
    private static final String TOKEN_ISSUER = "TEST-ISSUER";

    @Test
    void readTokenOkay() {
        var expected = 1L;
        String validToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBdWRpZW5jZSIsImNsaWVudElkIjoxLCJpc3MiOiJURVNULUlTU1VFUiIsImNwZiI6IjE2NTY1ODI0NzM4IiwiaWF0IjoxNjk4NjI1OTYxLCJqdGkiOiJhNzIyYTBiNC1lY2M5LTQ2ZDQtOTRhYy00Mzg1NzI1YTAxOTcifQ.CuXTgz2VE-5ThjQRHMQtZ3iZE5zz3JV0vji5urdqrPI";

        try (MockedStatic<SecretUtils> utilities = Mockito.mockStatic(SecretUtils.class)) {
            utilities.when(SecretUtils::getTokenJwtSecret).thenReturn(new TokenJwtSecret(TOKEN_SECRET, TOKEN_ISSUER));
            DecodedJWT result = TokenJwtUtils.readToken(validToken);
            assertEquals(expected, result.getClaim("clientId").asLong());
        }
    }

    @Test
    void readTokenExpired() {
        String expiredToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBdWRpZW5jZSIsImNsaWVudElkIjoxLCJpc3MiOiJURVNULUlTU1VFUiIsImNwZiI6IjE2NTY1ODI0NzM4IiwiZXhwIjoxNjk4NjI4MjE2LCJpYXQiOjE2OTg2MjgxNTYsImp0aSI6ImRmYjM1OWFjLTI3MGItNDUxMC05YjcxLTZiYjE3YzI5MzQ1NCJ9.iVcSUx-UShDr1rSSJt2lweRDcwtM-AkRX2buCeI5e0E";

        try (MockedStatic<SecretUtils> utilities = Mockito.mockStatic(SecretUtils.class)) {
            utilities.when(SecretUtils::getTokenJwtSecret).thenReturn(new TokenJwtSecret(TOKEN_SECRET, TOKEN_ISSUER));
            assertThrows(TokenJwtException.class, () -> TokenJwtUtils.readToken(expiredToken));
        }
    }

    @Test
    void readTokenInvalidSecretAndIssuer() {
        String validToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBdWRpZW5jZSIsImNsaWVudElkIjoxLCJpc3MiOiJURVNULUlTU1VFUiIsImNwZiI6IjE2NTY1ODI0NzM4IiwiaWF0IjoxNjk4NjI1OTYxLCJqdGkiOiJhNzIyYTBiNC1lY2M5LTQ2ZDQtOTRhYy00Mzg1NzI1YTAxOTcifQ.CuXTgz2VE-5ThjQRHMQtZ3iZE5zz3JV0vji5urdqrPI";

        try (MockedStatic<SecretUtils> utilities = Mockito.mockStatic(SecretUtils.class)) {
            utilities.when(SecretUtils::getTokenJwtSecret).thenReturn(new TokenJwtSecret("INVALID-SECRET", "INVALID-ISSUER"));
            assertThrows(TokenJwtException.class, () -> TokenJwtUtils.readToken(validToken));
        }
    }
}
