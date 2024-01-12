package com.fiap.burger.messenger.payment;

import com.fiap.burger.entity.payment.Payment;
import com.fiap.burger.messenger.adapter.PaymentMessenger;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("test")
@Primary
@Service
public class InMemoryPaymentMessenger implements PaymentMessenger {
    public void sendMessage(Payment payment) {
        System.out.println("Payment " + payment.getId() + " was sent through messenger");
    }
}
