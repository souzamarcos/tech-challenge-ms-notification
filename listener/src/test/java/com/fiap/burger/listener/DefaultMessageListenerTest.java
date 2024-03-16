package com.fiap.burger.listener;

import com.fiap.burger.controller.adapter.api.NotificationController;
import com.fiap.burger.entity.common.NotificationType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultMessageListenerTest {
    @InjectMocks
    DefaultMessageListener listener;

    @Mock
    NotificationController controller;

    @Test
    void shouldSendNotifications() {
        var customerId = "123";
        var orderId = 123L;
        var notificationType = NotificationType.PAGAMENTO_CONFIRMADO;
        when(controller.sendNotification(customerId, orderId, notificationType)).thenReturn(customerId);

        listener.notificationsQueueListener("{\"customerId\":123,\"orderId\": 123,\"notificationType\":\"PAGAMENTO_CONFIRMADO\"}");

        verify(controller, times(1)).sendNotification(customerId, orderId, notificationType);
    }
}
