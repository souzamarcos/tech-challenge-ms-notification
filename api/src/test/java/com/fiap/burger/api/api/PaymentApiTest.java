package com.fiap.burger.api.api;

import com.fiap.burger.api.dto.payment.request.PaymentWebhookRequestDto;
import com.fiap.burger.api.dto.payment.response.PaymentResponseDto;
import com.fiap.burger.controller.adapter.api.PaymentController;
import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentApiTest {
    @InjectMocks
    PaymentApi api;

    @Mock
    PaymentController controller;

    @Test
    void shouldFindById() {
        var id = 1L;
        var payment = new Payment(1L, 1L, PaymentStatus.ABERTO, "QR-CODE", "external-id", null, null, null);
        var expected = new PaymentResponseDto(id, 1L, PaymentStatus.ABERTO, "QR-CODE", "external-id");

        when(controller.findById(id)).thenReturn(payment);

        PaymentResponseDto actual = api.findById(id);

        assertEquals(expected, actual);

        verify(controller, times(1)).findById(id);
    }

    @Test
    void shouldFindByOrderId() {
        var orderId = 1L;
        var payment = List.of(new Payment(1L, 1L, PaymentStatus.ABERTO, "QR-CODE", "external-id", null, null, null));
        var expected = List.of(new PaymentResponseDto(1L, orderId, PaymentStatus.ABERTO, "QR-CODE", "external-id"));

        when(controller.findByOrderId(orderId)).thenReturn(payment);

        List<PaymentResponseDto> actual = api.findByOrderId(orderId);

        assertEquals(expected, actual);

        verify(controller, times(1)).findByOrderId(orderId);
    }
    @Test
    void shouldPaymentResponse() {
        var orderId = 1L;
        var request = new PaymentWebhookRequestDto(1L, PaymentStatus.APROVADO);

        api.paymentResponse(request);

        verify(controller, times(1)).updateStatus(orderId, PaymentStatus.APROVADO);
    }

}
