package com.fiap.burger.api.api;

import com.fiap.burger.api.notification.response.NotificationResponseDto;
import com.fiap.burger.controller.adapter.api.NotificationController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationApi {

    @Autowired
    private NotificationController controller;

    @Operation(summary = "Enviar notificações", description = "Enviar notificações para o customerId especificado", tags = {"notificacao"})
    @ApiResponses(value = {@ApiResponse(responseCode = "400", description = "customerId inválido")})
    @PutMapping("/{customerId}")
    public NotificationResponseDto sendNotification(@PathVariable String customerId) {
        return NotificationResponseDto.toResponseDto(controller.sendNotification(customerId));
    }
}
