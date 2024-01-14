package com.fiap.burger.controller.controller;

import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import com.fiap.burger.messenger.payment.DefaultPaymentMessenger;
import com.fiap.burger.usecase.misc.exception.PaymentNotFoundException;
import com.fiap.burger.usecase.usecase.DefaultPaymentUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DefaultPaymentControllerTest {

    @Mock
    DefaultPaymentUseCase useCase;

    @Mock
    DefaultPaymentMessenger messenger;

    @InjectMocks
    DefaultPaymentController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class findById {
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
    }

    @Nested
    class findByOrderId {
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
    }

    @Nested
    class insertPayment {
        @Test
        void shouldInsertPayment() {
            var orderId = 1L;
            var payment = new Payment(1L, PaymentStatus.ABERTO);

            when(useCase.insert(orderId)).thenReturn(payment);

            Payment actual = controller.insert(orderId);

            assertEquals(payment, actual);

            verify(useCase, times(1)).insert(orderId);
            verify(messenger, times(1)).sendMessage(actual);
        }
    }

    @Nested
    class updateStatusPayment {
        @Test
        void shouldUpdateStatusPaymentToApproved() {
            var id = 1L;
            var orderId = 1L;
            var persistedPayment = new Payment(1L, orderId, PaymentStatus.APROVADO);

            when(useCase.updateStatus(id, PaymentStatus.APROVADO)).thenReturn(persistedPayment);

            controller.updateStatus(id, PaymentStatus.APROVADO);

            verify(useCase, times(1)).updateStatus(id, PaymentStatus.APROVADO);
            verify(messenger, times(1)).sendMessage(persistedPayment);
        }

        @Test
        void shouldUpdateStatusPaymentToRefused() {
            var id = 1L;
            var orderId = 1L;
            var persistedPayment = new Payment(1L, orderId, PaymentStatus.RECUSADO);

            when(useCase.updateStatus(id, PaymentStatus.RECUSADO)).thenReturn(persistedPayment);

            controller.updateStatus(id, PaymentStatus.RECUSADO);

            verify(useCase, times(1)).updateStatus(id, PaymentStatus.RECUSADO);
            verify(messenger, times(1)).sendMessage(persistedPayment);
        }
    }

}
