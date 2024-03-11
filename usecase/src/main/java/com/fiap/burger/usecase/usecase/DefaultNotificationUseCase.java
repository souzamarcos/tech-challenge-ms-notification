package com.fiap.burger.usecase.usecase;

import com.fiap.burger.entity.customer.Customer;
import com.fiap.burger.usecase.adapter.gateway.CustomerGateway;
import com.fiap.burger.usecase.adapter.usecase.NotificationUseCase;
import com.fiap.burger.usecase.misc.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DefaultNotificationUseCase implements NotificationUseCase {

    private final Logger LOGGER = LoggerFactory.getLogger(DefaultNotificationUseCase.class);

    private final CustomerGateway customerGateway;

    public DefaultNotificationUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }
    @Override
    public String sendNotification(String customerId) {
        sendEmail(customerId);
        sendSMS(customerId);

        return "Notifications sent successfully to customerId '" + customerId + "'";
    }

    private String sendEmail(String customerId) {
        Customer customer = customerGateway.findById(customerId);
        if (customer == null) {
            throw new InvalidAttributeException(String.format("Customer '%s' not found.", customerId), "customerToken");
        }
        LOGGER.info("E-mail sent successfully to customerId '" + customerId + "'");

        return "E-mail sent successfully to customerId '" + customerId + "'";
    }

    private String sendSMS(String customerId) {
        Customer customer = customerGateway.findById(customerId);
        if (customer == null) {
            throw new InvalidAttributeException(String.format("Customer '%s' not found.", customerId), "customerToken");
        }
        LOGGER.info("SMS sent successfully to customerId '" + customerId + "'");

        return "SMS sent successfully to customerId '" + customerId + "'";
    }
}
