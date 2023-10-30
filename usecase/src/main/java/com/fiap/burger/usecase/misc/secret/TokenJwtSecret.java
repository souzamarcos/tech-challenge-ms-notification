package com.fiap.burger.usecase.misc.secret;

import com.fiap.burger.usecase.misc.exception.SecretAwsException;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.Optional;

public class TokenJwtSecret {
    @SerializedName("token_jwt_secret")
    private final String secret;
    @SerializedName("token_jwt_issuer")
    private final String issuer;

    public String getSecret() {
        return secret;
    }

    public String getIssuer() {
        return issuer;
    }

    public TokenJwtSecret(String secret, String issuer) {
        this.secret = secret;
        this.issuer = issuer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenJwtSecret that = (TokenJwtSecret) o;
        return Objects.equals(secret, that.secret) && Objects.equals(issuer, that.issuer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(secret, issuer);
    }

    public void isValid() {
        if(Optional.ofNullable(secret).orElse("").isEmpty() ||
            Optional.ofNullable(issuer).orElse("").isEmpty()) {
            throw new SecretAwsException("Secret is malformed.");
        }
    }
}
