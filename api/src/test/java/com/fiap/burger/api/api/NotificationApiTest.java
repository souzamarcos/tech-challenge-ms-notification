package com.fiap.burger.api.api;

import com.fiap.burger.api.notification.response.NotificationResponseDto;
import com.fiap.burger.controller.adapter.api.NotificationController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationApiTest {

    @InjectMocks
    NotificationApi api;

    @Mock
    NotificationController controller;

    @Test
    void shouldSendNotification() {
        var customerId = "123";
        var expected = new NotificationResponseDto("Success");

        when(controller.sendNotification(customerId)).thenReturn("Success");

        NotificationResponseDto actual = api.sendNotification(customerId);

        assertEquals(expected, actual);

        verify(controller, times(1)).sendNotification(customerId);
    }
}
