package com.fiap.burger.messenger.adapter;

import com.fiap.burger.entity.payment.Payment;

public interface PaymentMessenger {
    void sendMessage(Payment payment);
}
