package com.fiap.burger.usecase.usecase;

import com.fiap.burger.entity.order.OrderStatus;
import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import com.fiap.burger.usecase.adapter.gateway.PaymentGateway;
import com.fiap.burger.usecase.adapter.usecase.OrderUseCase;
import com.fiap.burger.usecase.misc.OrderBuilder;
import com.fiap.burger.usecase.misc.PaymentBuilder;
import com.fiap.burger.usecase.misc.exception.InvalidAttributeException;
import com.fiap.burger.usecase.misc.exception.OrderCannotBePaidException;
import com.fiap.burger.usecase.misc.exception.PaymentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DefaultPaymentUseCaseTest {

    @Mock
    PaymentGateway gateway;

    @Mock
    OrderUseCase orderUseCase;

    @InjectMocks
    DefaultPaymentUseCase useCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindById() {
        var id = 1L;
        var expected = new PaymentBuilder().withId(id).build();

        when(gateway.findById(id)).thenReturn(expected);

        var actual = useCase.findById(id);

        assertEquals(expected, actual);

        verify(gateway, times(1)).findById(id);
    }

    @Test
    void shouldFindByOrderId() {
        var orderId = 1L;
        var expected = List.of(new PaymentBuilder().build());

        when(gateway.findByOrderId(orderId)).thenReturn(expected);

        var actual = useCase.findByOrderId(orderId);

        assertEquals(expected, actual);

        verify(gateway, times(1)).findByOrderId(orderId);
    }

    @Test
    void shouldInsertPayment() {
        var orderId = 1L;
        var order = new OrderBuilder().build();
        var expected = new Payment(order, PaymentStatus.ABERTO);

        when(orderUseCase.findById(orderId)).thenReturn(order);
        when(orderUseCase.canBePaid(order.getStatus())).thenReturn(true);
        when(gateway.save(expected)).thenReturn(expected);

        Payment actual = useCase.insert(orderId);

        assertEquals(expected, actual);

        verify(gateway, times(1)).save(expected);
    }

    @Test
    void shouldThrowOrderCannotBePaidExceptionWhenPaymentOrderCantBePaid() {
        var orderId = 1L;
        var order = new OrderBuilder().build();
        var expected = new Payment(order, PaymentStatus.ABERTO);

        when(orderUseCase.findById(orderId)).thenReturn(order);
        when(orderUseCase.canBePaid(order.getStatus())).thenReturn(false);

        assertThrows(OrderCannotBePaidException.class, () -> useCase.insert(orderId));

        verify(gateway, times(0)).save(expected);
    }

    @Test
    void shouldUpdateStatusPaymentToApproved() {
        var id = 1L;
        var payment = new PaymentBuilder().build();

        when(gateway.findById(id)).thenReturn(payment);

        useCase.updateStatus(id, PaymentStatus.APROVADO);

        verify(gateway, times(1)).updatePaymentStatus(eq(id), eq(PaymentStatus.APROVADO), any());
        verify(orderUseCase, times(1)).checkout(payment.getOrder().getId());
        verify(orderUseCase, times(0)).updateStatus(any(), any());
    }

    @Test
    void shouldUpdateStatusPaymentToRefused() {
        var id = 1L;
        var payment = new PaymentBuilder().build();

        when(gateway.findById(id)).thenReturn(payment);

        useCase.updateStatus(id, PaymentStatus.RECUSADO);

        verify(gateway, times(1)).updatePaymentStatus(eq(id), eq(PaymentStatus.RECUSADO), any());
        verify(orderUseCase, times(0)).checkout(any());
        verify(orderUseCase, times(1)).updateStatus(payment.getOrder().getId(), OrderStatus.CANCELADO);
    }

    @Test
    void shouldThrownPaymentNotFoundExceptionWhenPaymentNotFoundToUpdateStatus() {
        var id = 1L;

        when(gateway.findById(id)).thenReturn(null);

        assertThrows(PaymentNotFoundException.class, () -> useCase.updateStatus(id, PaymentStatus.APROVADO));

        verify(gateway, times(0)).updatePaymentStatus(any(), any(), any());
        verify(orderUseCase, times(0)).checkout(any());
        verify(orderUseCase, times(0)).updateStatus(any(), any());
    }

    @Test
    void shouldThrownInvalidAttributeExceptionWhenPaymentStatusDoNotAllowUpdate() {
        var id = 1L;
        var payment = new PaymentBuilder().withStatus(PaymentStatus.APROVADO).build();

        when(gateway.findById(id)).thenReturn(payment);

        assertThrows(InvalidAttributeException.class, () -> useCase.updateStatus(id, PaymentStatus.RECUSADO));

        verify(gateway, times(0)).updatePaymentStatus(any(), any(), any());
        verify(orderUseCase, times(0)).checkout(any());
        verify(orderUseCase, times(0)).updateStatus(any(), any());
    }

    @Test
    void shouldThrownInvalidAttributeExceptionWhenNewPaymentStatusIsInvalid() {
        var id = 1L;
        var payment = new PaymentBuilder().withStatus(PaymentStatus.ABERTO).build();

        when(gateway.findById(id)).thenReturn(payment);

        assertThrows(InvalidAttributeException.class, () -> useCase.updateStatus(id, PaymentStatus.ABERTO));

        verify(gateway, times(0)).updatePaymentStatus(any(), any(), any());
        verify(orderUseCase, times(0)).checkout(any());
        verify(orderUseCase, times(0)).updateStatus(any(), any());
    }
}
