package com.fiap.burger.usecase.usecase;

import com.fiap.burger.entity.common.NotificationType;
import com.fiap.burger.entity.customer.Customer;
import com.fiap.burger.usecase.adapter.gateway.CustomerGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DefaultNotificationUseCaseTest {

    @Mock
    CustomerGateway customerGateway;

    @InjectMocks
    DefaultNotificationUseCase useCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class sendNotifications {
        @Test
        void shouldSendNotifications_PAGAMENTO_NAO_CONFIRMADO() {
            var customerId = "123";
            var orderId = 123L;
            var notificationType = NotificationType.PAGAMENTO_NAO_CONFIRMADO;
            var expected = "Pagamento não confirmado para o pedido " + orderId;

            when(customerGateway.findById(customerId)).thenReturn(new Customer(
                customerId,
                "12345678909",
                "email@email.com",
                "Name"
            ));


            String actual = useCase.sendNotification(customerId, orderId, notificationType);

            assertEquals(expected, actual);
        }
    }
}