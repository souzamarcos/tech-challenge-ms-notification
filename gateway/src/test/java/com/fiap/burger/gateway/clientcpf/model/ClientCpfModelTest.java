package com.fiap.burger.gateway.clientcpf.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientCpfModelTest {

    @Test
    void shouldUseSettersCorrectly() {
        var newCpf = "123456";
        var newId = 123L;

        var actual = new ClientCpfModel("oldCpf", 1L);
        actual.setCpf(newCpf);
        actual.setId(newId);

        assertEquals(actual.getCpf(), newCpf);
        assertEquals(actual.getId(), newId);
    }

    @Test
    void shouldCreateInstanceCorrectly() {
        var cpf = "123456";
        var id = 1L;

        var actual = new ClientCpfModel(cpf, id);

        assertEquals(actual.getCpf(), cpf);
        assertEquals(actual.getId(), id);
    }

    @Test
    void shouldMatchToString() {
        var expected = "Customer [cpf=123456, id=1]";
        var clientCpf = new ClientCpfModel("123456", 1L);

        var actual = clientCpf.toString();

        assertEquals(expected, actual);
    }
}