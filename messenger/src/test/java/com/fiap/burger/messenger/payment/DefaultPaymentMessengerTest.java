package com.fiap.burger.messenger.payment;

import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.entity.payment.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application.properties")
class DefaultPaymentMessengerTest {

    @Mock
    QueueMessagingTemplate queueMessagingTemplate;

    @InjectMocks
    DefaultPaymentMessenger messenger;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSendMessage() {
        Payment payment = new Payment(1L, 1L, PaymentStatus.APROVADO);
        org.springframework.test.util.ReflectionTestUtils.setField(messenger, "queueName", "payment-queue");

        messenger.sendMessage(payment);
        verify(queueMessagingTemplate, times(1)).send(eq("payment-queue"), any());
    }

}
