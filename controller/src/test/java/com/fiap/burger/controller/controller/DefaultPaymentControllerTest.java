package com.fiap.burger.controller.controller;

import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import com.fiap.burger.usecase.misc.exception.PaymentNotFoundException;
import com.fiap.burger.usecase.usecase.DefaultPaymentUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DefaultPaymentControllerTest {

    @Mock
    DefaultPaymentUseCase useCase;

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
    void shouldUpdateStatusPayment() {
        var id = 1L;

        controller.updateStatus(id, PaymentStatus.APROVADO);

        verify(useCase, times(1)).updateStatus(id, PaymentStatus.APROVADO);
    }
}
