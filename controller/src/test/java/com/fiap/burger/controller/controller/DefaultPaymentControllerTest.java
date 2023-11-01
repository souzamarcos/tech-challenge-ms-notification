package com.fiap.burger.controller.controller;

import com.fiap.burger.entity.order.Order;
import com.fiap.burger.entity.order.OrderStatus;
import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import com.fiap.burger.usecase.misc.exception.PaymentNotFoundException;
import com.fiap.burger.usecase.usecase.DefaultOrderUseCase;
import com.fiap.burger.usecase.usecase.DefaultPaymentUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DefaultPaymentControllerTest {

    @Mock
    DefaultPaymentUseCase useCase;

    @Mock
    DefaultOrderUseCase orderUseCase;

    @InjectMocks
    DefaultPaymentController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindById() {
        var id = 1L;
        var expected = new Payment(1L, PaymentStatus.ABERTO);

        when(useCase.findById(id)).thenReturn(expected);

        Payment actual = controller.findById(id);

        assertEquals(expected, actual);

        verify(useCase, times(1)).findById(id);
    }

    @Test
    void shouldThrownPaymentNotFoundExceptionWhenPaymentNotFoundById() {
        var id = 1L;

        when(useCase.findById(id)).thenReturn(null);

        assertThrows(PaymentNotFoundException.class, () -> controller.findById(id));

        verify(useCase, times(1)).findById(id);
    }

    @Test
    void shouldFindByOrderId() {
        var orderId = 1L;
        var expected = List.of(new Payment(1L, PaymentStatus.ABERTO));

        when(useCase.findByOrderId(orderId)).thenReturn(expected);

        List<Payment> actual = controller.findByOrderId(orderId);

        assertEquals(expected, actual);

        verify(useCase, times(1)).findByOrderId(orderId);
    }

    @Test
    void shouldThrownPaymentNotFoundExceptionWhenPaymentNotFoundByOrderId() {
        var orderId = 1L;

        when(useCase.findByOrderId(orderId)).thenReturn(null);

        assertThrows(PaymentNotFoundException.class, () -> controller.findByOrderId(orderId));

        verify(useCase, times(1)).findByOrderId(orderId);
    }

    @Test
    void shouldInsertPayment() {
        var orderId = 1L;
        var payment = new Payment(1L, PaymentStatus.ABERTO);

        when(useCase.insert(orderId)).thenReturn(payment);

        Payment actual = controller.insert(orderId);

        assertEquals(payment, actual);

        verify(useCase, times(1)).insert(orderId);
    }

    @Test
    void shouldUpdateStatusPaymentToApproved() {
        var id = 1L;
        var order = new Order(1L, Collections.emptyList(), OrderStatus.AGUARDANDO_PAGAMENTO);
        var persistedPayment = new Payment(1L, order, PaymentStatus.APROVADO);

        when(useCase.updateStatus(id, PaymentStatus.APROVADO)).thenReturn(persistedPayment);

        controller.updateStatus(id, PaymentStatus.APROVADO);

        verify(useCase, times(1)).updateStatus(id, PaymentStatus.APROVADO);
        verify(orderUseCase, times(1)).checkout(order.getId());
        verify(orderUseCase, never()).updateStatus(order.getId(), OrderStatus.CANCELADO);
    }

    @Test
    void shouldUpdateStatusPaymentToRefused() {
        var id = 1L;
        var order = new Order(1L, Collections.emptyList(), OrderStatus.AGUARDANDO_PAGAMENTO);
        var persistedPayment = new Payment(1L, order, PaymentStatus.RECUSADO);

        when(useCase.updateStatus(id, PaymentStatus.RECUSADO)).thenReturn(persistedPayment);

        controller.updateStatus(id, PaymentStatus.RECUSADO);

        verify(useCase, times(1)).updateStatus(id, PaymentStatus.RECUSADO);
        verify(orderUseCase, never()).checkout(order.getId());
        verify(orderUseCase, times(1)).updateStatus(order.getId(), OrderStatus.CANCELADO);
    }
}
