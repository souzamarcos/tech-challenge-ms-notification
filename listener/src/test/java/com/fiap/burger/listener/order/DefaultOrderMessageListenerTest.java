package com.fiap.burger.listener.order;

import com.fiap.burger.controller.adapter.api.PaymentController;
import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import com.fiap.burger.usecase.misc.exception.OrderMessageListenerException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultOrderMessageListenerTest {
    @InjectMocks
    DefaultOrderMessageListener listener;

    @Mock
    PaymentController controller;

    @Test
    void shouldInsertPaymentWhenListening() {
        when(controller.insert(1L)).thenReturn(new Payment(1L, 1L, PaymentStatus.ABERTO));

        listener.orderQueueListener("{\"id\":1}");

        verify(controller, times(1)).insert(1L);
    }

    @Test
    void shouldThrownOrderMessageListenerExceptionWhenExceptionInInserting() {
        var expected = new OrderMessageListenerException("Mock Error");
        when(controller.insert(1L)).thenThrow(new RuntimeException("Mock Error"));

        OrderMessageListenerException actual = assertThrows(OrderMessageListenerException.class, () -> listener.orderQueueListener("{\"id\":1}"));

        assertEquals(expected.getMessage(), actual.getMessage());

        verify(controller, times(1)).insert(1L);
    }
}
