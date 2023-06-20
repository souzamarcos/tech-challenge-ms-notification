package com.fiap.burger.domain.misc.exception;

import java.util.Map;

public abstract class DomainException extends RuntimeException {

    protected Map<String, String> parameters = Map.of();

    public DomainException (String message) {
        super(message);
    }

    public DomainException (String message, Map<String, String> parameters) {
        super(message);
        this.parameters = parameters;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

}
