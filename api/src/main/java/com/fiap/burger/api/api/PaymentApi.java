package com.fiap.burger.api.api;

import com.fiap.burger.api.dto.payment.request.PaymentInsertRequestDto;
import com.fiap.burger.api.dto.payment.response.PaymentResponseDto;
import com.fiap.burger.controller.adapter.api.PaymentController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Efetuar pagamento", description = "Efetuar pagamento de pedido", tags = {"pagamento"})
    @ApiResponses(value = {@ApiResponse(responseCode = "400", description = "Request inválida")})
    @PostMapping()
    public PaymentResponseDto insert(@RequestBody PaymentInsertRequestDto orderIdDto) {
        // TODO retornar items do pedido inserido
        return PaymentResponseDto.toResponseDto(paymentController.insert(null));
    }

    @Operation(summary = "Enviar Webhook", description = "Enviar Webhook de pagamento", tags = {"pagamento"})
    @ApiResponses(value = {@ApiResponse(responseCode = "400", description = "Request inválida")})
    @PostMapping("/webhook")
    public PaymentResponseDto insert() {
        // TODO retornar items do pedido inserido
        return null;
    }
}
