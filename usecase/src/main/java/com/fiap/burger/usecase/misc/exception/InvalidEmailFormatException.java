package com.fiap.burger.usecase.misc.exception;

public class InvalidEmailFormatException extends DomainException {

    public InvalidEmailFormatException() {
        super("Invalid email format.");
    }

}
