package com.fiap.burger.api.api;

import com.fiap.burger.api.dto.payment.response.PaymentResponseDto;
import com.fiap.burger.controller.adapter.api.PaymentController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@Tag(name = "pagamento", description = "API responsável pelo controle de pagamento.")
public class PaymentApi {

    @Autowired
    private PaymentController paymentController;

    @Operation(summary = "Consultar pagamento", description = "Consultar pagamento", tags = {"pagamento"})
    @ApiResponses(value = {@ApiResponse(responseCode = "400", description = "Request inválida")})
    @GetMapping("/{id}")
    public PaymentResponseDto findById(@PathVariable Long id) {
        return PaymentResponseDto.toResponseDto(paymentController.findById(id));
    }
}
