package com.fiap.burger.gateway.payment.gateway;

import com.fiap.burger.entity.payment.PaymentStatus;
import com.fiap.burger.gateway.misc.PaymentBuilder;
import com.fiap.burger.gateway.misc.PaymentJPABuilder;
import com.fiap.burger.gateway.payment.dao.PaymentDAO;
import com.fiap.burger.gateway.payment.model.PaymentJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
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
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DefaultPaymentGatewayTest {

    @Mock
    PaymentDAO paymentDAO;

    @InjectMocks
    DefaultPaymentGateway gateway;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class findById {
        @Test
        void shouldFindById() {
            var id = 1L;
            var paymentJPA = new PaymentJPABuilder().withId(1L).build();
            var expected = paymentJPA.toEntity();

            when(paymentDAO.findById(id)).thenReturn(Optional.of(paymentJPA));

            var actual = gateway.findById(id);

            assertEquals(expected, actual);

            verify(paymentDAO, times(1)).findById(id);
        }
    }

    @Nested
    class findPaymentByOrderId {
        @Test
        void shouldFindPaymentsByOrderId() {
            var id = 1L;
            var paymentsJPA = List.of(new PaymentJPABuilder().withId(1L).build());
            var expected = paymentsJPA.stream().map(PaymentJPA::toEntity).collect(Collectors.toList());

            when(paymentDAO.findAllByDeletedAtNullAndOrderId(1L)).thenReturn(paymentsJPA);

            var actual = gateway.findByOrderId(id);

            assertEquals(expected, actual);

            verify(paymentDAO, times(1)).findAllByDeletedAtNullAndOrderId(1L);
        }

        @Test
        void shouldReturnEmptyListWhenFindPaymentsByOrderIdNull() {
            var expected = Collections.emptyList();

            var actual = gateway.findByOrderId(null);

            assertEquals(expected, actual);

            verify(paymentDAO, never()).findAllByDeletedAtNullAndOrderId(any());
        }

    }

    @Nested
    class savePayment {
        @Test
        void shouldSavePayment() {
            var paymentJPA = new PaymentJPABuilder().withId(1L).build();
            var payment = new PaymentBuilder().withId(null).build();
            var expected = new PaymentBuilder().withId(1L).build();

            when(paymentDAO.save(any())).thenReturn(paymentJPA);

            var actual = gateway.save(payment);

            assertEquals(expected.getId(), actual.getId());

            verify(paymentDAO, times(1)).save(any());
        }
    }

    @Nested
    class updateStatus {
        @Test
        void shouldUpdateStatus() {
            gateway.updatePaymentStatus(1L, PaymentStatus.APROVADO, LocalDateTime.now());
            verify(paymentDAO, times(1)).updatePaymentStatus(eq(1L), eq(PaymentStatus.APROVADO), any());
        }
    }

}
