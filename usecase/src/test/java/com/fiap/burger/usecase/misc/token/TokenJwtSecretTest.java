package com.fiap.burger.usecase.misc.token;

import com.fiap.burger.usecase.misc.exception.SecretAwsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


class TokenJwtSecretTest {

    @Test
    void shouldThrowSecretAwsExceptionWhenSecretIsNull() {
        TokenJwtSecret tokenJwtSecret = new TokenJwtSecret(null, "issuer");
        assertThrows(SecretAwsException.class, tokenJwtSecret::isValid);
    }
    @Test
    void shouldThrowSecretAwsExceptionWhenSecretIsEmpty() {
        TokenJwtSecret tokenJwtSecret = new TokenJwtSecret("", "issuer");
        assertThrows(SecretAwsException.class, tokenJwtSecret::isValid);
    }
    @Test
    void shouldThrowSecretAwsExceptionWhenIssuerIsNull() {
        TokenJwtSecret tokenJwtSecret = new TokenJwtSecret("secret", null);
        assertThrows(SecretAwsException.class, tokenJwtSecret::isValid);
    }
    @Test
    void shouldThrowSecretAwsExceptionWhenIssuerIsEmpty() {
        TokenJwtSecret tokenJwtSecret = new TokenJwtSecret("secret", "");
        assertThrows(SecretAwsException.class, tokenJwtSecret::isValid);
    }

}
