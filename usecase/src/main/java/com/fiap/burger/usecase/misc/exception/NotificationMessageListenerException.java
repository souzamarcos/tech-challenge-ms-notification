package com.fiap.burger.usecase.misc.exception;

public class NotificationMessageListenerException extends DomainException {
    public NotificationMessageListenerException(String message) {
        super("An exception was thrown during the execution of the SQS listener of Notification Queue and Message will be still available in Queue. Error: " + message);
    }
}
