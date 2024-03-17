package com.fiap.burger.usecase.usecase;

import com.fiap.burger.entity.common.NotificationType;
import com.fiap.burger.entity.customer.Customer;
import com.fiap.burger.usecase.adapter.gateway.CustomerGateway;
import com.fiap.burger.usecase.adapter.usecase.EmailUseCase;
import com.fiap.burger.usecase.adapter.usecase.NotificationUseCase;
import com.fiap.burger.usecase.misc.exception.*;
import jakarta.validation.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DefaultNotificationUseCase implements NotificationUseCase {

    private final Logger LOGGER = LoggerFactory.getLogger(DefaultNotificationUseCase.class);

    private final EmailUseCase emailUseCase;
    private final CustomerGateway customerGateway;

    public DefaultNotificationUseCase(CustomerGateway customerGateway, EmailUseCase emailUseCase) {
        this.customerGateway = customerGateway;
        this.emailUseCase = emailUseCase;
    }
    @Override
    public String sendNotification(String customerId, Long orderId, NotificationType notificationType) {
        Customer customer = customerGateway.findById(customerId);
        if (customer == null) {
            throw new InvalidAttributeException(String.format("Customer '%s' not found.", customerId), "customerToken");
        }
        sendEmail(customer, orderId, notificationType);
        sendSMS(customerId,orderId, notificationType);

        return buildMessageBy(orderId, notificationType);
    }

    private String sendEmail(Customer customer, Long orderId, NotificationType notificationType) {
        emailUseCase.sendEmail(
            customer.getEmail(),
            buildMessageBy(orderId, notificationType),
            buildMessageBy(orderId, notificationType)
        );

        return buildMessageBy(orderId, notificationType);
    }

    private String sendSMS(String customerId, Long orderId, NotificationType notificationType) {
        LOGGER.info("SMS sent successfully to customerId '" + customerId + "'");

        return buildMessageBy(orderId, notificationType);
    }

    private String buildMessageBy(Long orderId, NotificationType notificationType) {
        return switch (notificationType) {
            case PAGAMENTO_NAO_CONFIRMADO -> "Pagamento não confirmado para o pedido " + orderId;
            case PAGAMENTO_CONFIRMADO -> "Pagamento confirmado para o pedido " + orderId;
            case PEDIDO_PRONTO -> "Pedido '" + orderId + "' pronto";
        };
    }
}
