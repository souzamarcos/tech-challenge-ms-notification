package com.fiap.burger.gateway.payment.gateway;

import com.fiap.burger.entity.payment.PaymentStatus;
import com.fiap.burger.gateway.misc.OrderJPABuilder;
import com.fiap.burger.gateway.misc.PaymentBuilder;
import com.fiap.burger.gateway.misc.PaymentJPABuilder;
import com.fiap.burger.gateway.order.dao.OrderDAO;
import com.fiap.burger.gateway.payment.dao.PaymentDAO;
import com.fiap.burger.gateway.payment.model.PaymentJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DefaultPaymentGatewayTest {

    @Mock
    OrderDAO orderDAO;

    @Mock
    PaymentDAO paymentDAO;

    @InjectMocks
    DefaultPaymentGateway gateway;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindById() {
        var id = 1L;
        var paymentJPA = new PaymentJPABuilder().withId(1L).build();
        var expected = paymentJPA.toEntity();

        when(paymentDAO.findById(id)).thenReturn(Optional.of(paymentJPA));

        var actual = gateway.findById(id);

        assertEquals(expected, actual);

        verify(paymentDAO, times(1)).findById(id);
    }

    @Test
    public void shouldFindPaymentsByOrderId() {
        var id = 1L;
        var orderJPA = new OrderJPABuilder().build();
        var paymentsJPA = List.of(new PaymentJPABuilder().withId(1L).build());
        var expected = paymentsJPA.stream().map(PaymentJPA::toEntity).collect(Collectors.toList());

        when(orderDAO.findById(id)).thenReturn(Optional.of(orderJPA));
        when(paymentDAO.findAllByDeletedAtNullAndOrderJPA(orderJPA)).thenReturn(paymentsJPA);

        var actual = gateway.findByOrderId(id);

        assertEquals(expected, actual);

        verify(paymentDAO, times(1)).findAllByDeletedAtNullAndOrderJPA(orderJPA);
    }

    @Test
    public void shouldReturnEmptyPaymentsWhenOrderNotFound() {
        var id = 1L;
        var expected = Collections.emptyList();

        when(orderDAO.findById(id)).thenReturn(Optional.empty());

        var actual = gateway.findByOrderId(id);

        assertEquals(expected, actual);

        verify(paymentDAO, times(0)).findAllByDeletedAtNullAndOrderJPA(any());
    }

    @Test
    public void shouldSavePayment() {
        var paymentJPA = new PaymentJPABuilder().withId(1L).build();
        var payment = new PaymentBuilder().withId(null).build();
        var expected = new PaymentBuilder().withId(1L).build();

        when(paymentDAO.save(any())).thenReturn(paymentJPA);

        var actual = gateway.save(payment);

        assertEquals(expected.getId(), actual.getId());

        verify(paymentDAO, times(1)).save(any());
    }

    @Test
    public void shouldUpdateStatus() {
        gateway.updatePaymentStatus(1L, PaymentStatus.APROVADO, LocalDateTime.now());
        verify(paymentDAO, times(1)).updatePaymentStatus(eq(1L), eq(PaymentStatus.APROVADO), any());
    }
}
