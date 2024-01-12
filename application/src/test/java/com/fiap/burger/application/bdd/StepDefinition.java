package com.fiap.burger.application.bdd;

import com.fiap.burger.application.utils.PaymentHelper;
import com.fiap.burger.entity.payment.PaymentStatus;
import com.fiap.burger.listener.order.OrderMessageListener;
import com.fiap.burger.listener.order.OrderMessageListenerDto;
import com.google.gson.Gson;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class StepDefinition extends CucumberIntegrationTest {
    @Autowired
    OrderMessageListener orderMessageListener;
    private Integer paymentId;
    private Long orderId;
    private PaymentStatus paymentStatus;

    private Response response;

    private String getEndpoint() { return "http://localhost:" + port + "/payments"; }

    public void submeterUmNovoPagamento() {
        orderId = ThreadLocalRandom.current().nextLong();
        OrderMessageListenerDto orderDto = PaymentHelper.createOrderMessageListenerDto(orderId);
        orderMessageListener.orderQueueListener(new Gson().toJson(orderDto));
    }
    @Dado("que um pagamento já foi registrado")
    public void pagamentoJaFoiRegistrado() {
        submeterUmNovoPagamento();

        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get(getEndpoint() + "/byOrderId/{orderId}", orderId);

        paymentId = response.then().extract().response().body().path("[0].id");
    }
    @Quando("requisitar a busca de um pagamento por id")
    public void requisitarBuscaDeUmPagamentoPorId() {
        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get(getEndpoint() + "/{id}", paymentId);
    }
    @Entao("pagamento é exibido com sucesso")
    public void pagamentoExibidoComSucesso() {
        response.then()
            .statusCode(HttpStatus.OK.value())
            .body(matchesJsonSchemaInClasspath("./schemas/PaymentResponse.json"));
    }
    @Quando("requisitar a busca de um pagamento por order id")
    public void requisitarBuscaDePagamentoPorOrderId() {
        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get(getEndpoint() + "/byOrderId/{orderId}", orderId);
    }
    @Entao("pagamentos são exibidos com sucesso")
    public void pagamentosExibidosComSucesso() {
        response.then()
            .statusCode(HttpStatus.OK.value())
            .body(matchesJsonSchemaInClasspath("./schemas/ListPaymentResponse.json"));
    }
    @Quando("receber weebhook com novo status do pagamento")
    public void receberWeebhookComNovoStatusDoPagamento() {
        paymentStatus = PaymentStatus.APROVADO;
        var paymentResquest = PaymentHelper.createWebhookRequest(paymentId.longValue(), PaymentStatus.APROVADO);

        given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(paymentResquest)
            .when().put(getEndpoint() + "/webhook");

        response = given()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get(getEndpoint() + "/{id}", paymentId);
    }
    @Entao("pagamento é exibido com sucesso com novo status")
    public void pagamentoExibidoComSucessoComNovoStatus() {
        response.then()
            .statusCode(HttpStatus.OK.value())
            .body(matchesJsonSchemaInClasspath("./schemas/PaymentResponse.json"))
            .body("id", equalTo(paymentId))
            .body("status", equalTo(paymentStatus.name()));
    }
}
