package com.fiap.burger.entity.client;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {

    @Test
    public void shouldCreateInstanceWithOnlyIdConstructor() {
        var id = 1L;

        Client actual = new Client(id);

        assertEquals(id, actual.getId());
    }

    @Test
    public void shouldCreateInstanceWithSimpleConstructor() {
        var cpf = "12345678909";
        var email = "email@email.com";
        var name = "Cliente Exemplo";


        Client actual = new Client(cpf, email, name);

        assertEquals(cpf, actual.getCpf());
        assertEquals(email, actual.getEmail());
        assertEquals(name, actual.getName());
    }

    @Test
    public void shouldCreateInstanceWithSimpleConstructorWithId() {
        var id = 1L;
        var cpf = "12345678909";
        var email = "email@email.com";
        var name = "Cliente Exemplo";


        Client actual = new Client(id, cpf, email, name);

        assertEquals(id, actual.getId());
        assertEquals(cpf, actual.getCpf());
        assertEquals(email, actual.getEmail());
        assertEquals(name, actual.getName());
    }

    @Test
    public void shouldCreateInstanceWithFullConstructor() {
        var id = 1L;
        var cpf = "12345678909";
        var email = "email@email.com";
        var name = "Cliente Exemplo";
        var createdAt = LocalDateTime.now();
        var modifiedAt = LocalDateTime.now();

        Client actual = new Client(id, cpf, email, name, createdAt, modifiedAt, null);

        assertEquals(id, actual.getId());
        assertEquals(cpf, actual.getCpf());
        assertEquals(email, actual.getEmail());
        assertEquals(name, actual.getName());
        assertEquals(createdAt, actual.getCreatedAt());
        assertEquals(modifiedAt, actual.getModifiedAt());
        assertEquals(null, actual.getDeletedAt());
    }

}
