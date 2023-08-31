package com.fiap.burger.api.api;

import com.fiap.burger.controller.adapter.api.PaymentController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@Tag(name = "pagamento", description = "API responsável pelo controle de pagamento.")
public class PaymentApi {

    @Autowired
    private PaymentController paymentController;


}
