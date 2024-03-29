package com.fiap.burger.controller.controller;

import com.fiap.burger.entity.common.NotificationType;
import com.fiap.burger.gateway.customer.gateway.DefaultCustomerGateway;
import com.fiap.burger.usecase.usecase.DefaultNotificationUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DefaultNotificationControllerTest {

    @Mock
    DefaultNotificationUseCase useCase;

    @Mock
    DefaultCustomerGateway customerGateway;

    @InjectMocks
    DefaultNotificationController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class sendNotifications {
        @Test
        void shouldSendNotifications() {
            var customerId = "123";
            var orderId = 123L;
            var notificationType = NotificationType.PAGAMENTO_CONFIRMADO;
            var expected = "Success";

            when(useCase.sendNotification(customerId, orderId, notificationType)).thenReturn(expected);

            String actual = controller.sendNotification(customerId, orderId, notificationType);

            assertEquals(expected, actual);

            verify(useCase, times(1)).sendNotification(customerId, orderId, notificationType);
        }
    }
}
