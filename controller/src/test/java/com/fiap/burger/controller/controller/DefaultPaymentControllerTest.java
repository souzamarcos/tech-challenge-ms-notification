package com.fiap.burger.controller.controller;

import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import com.fiap.burger.entity.product.Category;
import com.fiap.burger.entity.product.Product;
import com.fiap.burger.usecase.misc.exception.PaymentNotFoundException;
import com.fiap.burger.usecase.misc.exception.ProductNotFoundException;
import com.fiap.burger.usecase.usecase.DefaultPaymentUseCase;
import com.fiap.burger.usecase.usecase.DefaultProductUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class DefaultPaymentControllerTest {

    @Mock
    DefaultPaymentUseCase useCase;

    @InjectMocks
    DefaultPaymentController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindById() {
        var id = 1L;
        var expected = new Payment(1L, PaymentStatus.ABERTO);

        when(useCase.findById(id)).thenReturn(expected);

        Payment actual = controller.findById(id);

        assertEquals(expected, actual);

        verify(useCase, times(1)).findById(id);
    }

    @Test
    public void shouldThrownPaymentNotFoundExceptionWhenPaymentNotFoundById() {
        var id = 1L;

        when(useCase.findById(id)).thenReturn(null);

        assertThrows(PaymentNotFoundException.class, () -> controller.findById(id));

        verify(useCase, times(1)).findById(id);
    }

    @Test
    public void shouldFindByOrderId() {
        var orderId = 1L;
        var expected = List.of(new Payment(1L, PaymentStatus.ABERTO));

        when(useCase.findByOrderId(orderId)).thenReturn(expected);

        List<Payment> actual = controller.findByOrderId(orderId);

        assertEquals(expected, actual);

        verify(useCase, times(1)).findByOrderId(orderId);
    }

    @Test
    public void shouldThrownPaymentNotFoundExceptionWhenPaymentNotFoundByOrderId() {
        var orderId = 1L;

        when(useCase.findByOrderId(orderId)).thenReturn(null);

        assertThrows(PaymentNotFoundException.class, () -> controller.findByOrderId(orderId));

        verify(useCase, times(1)).findByOrderId(orderId);
    }

    @Test
    public void shouldInsertPayment() {
        var orderId = 1L;
        var payment = new Payment(1L, PaymentStatus.ABERTO);

        when(useCase.insert(orderId)).thenReturn(payment);

        Payment actual = controller.insert(orderId);

        assertEquals(payment, actual);

        verify(useCase, times(1)).insert(orderId);
    }

    @Test
    public void shouldUpdateStatusPayment() {
        var id = 1L;

        controller.updateStatus(id, PaymentStatus.APROVADO);

        verify(useCase, times(1)).updateStatus(id, PaymentStatus.APROVADO);
    }
}
