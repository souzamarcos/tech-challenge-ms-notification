package com.fiap.burger.application.bdd;

import com.fiap.burger.api.notification.request.NotificationRequestDto;
import com.fiap.burger.api.notification.response.NotificationResponseDto;
import com.fiap.burger.entity.common.NotificationType;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class StepDefinition extends CucumberIntegrationTest {

    private Response response;

    private String getEndpoint() { return "http://localhost:" + port + "/notifications";}

    @Quando("solicitar o envio de notificações para um usuário")
    public NotificationResponseDto submeterUmNovoPedido() {
        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(new NotificationRequestDto("1", 123L, NotificationType.PAGAMENTO_CONFIRMADO))
            .when().put(getEndpoint());

        return response.then().extract().as(NotificationResponseDto.class);
    }
    @Entao("as notificações devem ser enviadas")
    public void pedidoRegistradoComSucesso() {
        response.then()
            .statusCode(HttpStatus.OK.value())
            .body(matchesJsonSchemaInClasspath("schemas/NotificationResponseSchema.json"));
    }
}
