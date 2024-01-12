package com.fiap.burger.usecase.misc.exception;

public class OrderMessageListenerException extends DomainException {
    public OrderMessageListenerException(String message) {
        super("An exception was thrown during the execution of the SQS listener of Order Queue and Message will be still available in Queue. Error: " + message);
    }
}
