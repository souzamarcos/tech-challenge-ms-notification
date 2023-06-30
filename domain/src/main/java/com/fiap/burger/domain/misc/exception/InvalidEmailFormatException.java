package com.fiap.burger.domain.misc.exception;

public class InvalidEmailFormatException extends DomainException {

    public InvalidEmailFormatException() {
        super("Invalid email format.");
    }

}
