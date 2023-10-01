package com.fiap.burger.api.api;

import com.fiap.burger.api.dto.client.request.ClientInsertRequestDto;
import com.fiap.burger.api.dto.client.response.ClientResponseDto;
import com.fiap.burger.controller.adapter.api.ClientController;
import com.fiap.burger.entity.client.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientApiTest {

    @InjectMocks
    ClientApi api;

    @Mock
    ClientController controller;

    @Test
    public void shouldFindById() {
        var id = 1L;
        var client = new Client(id, "12345678901", "email@email.com", "Nome");
        var expected = new ClientResponseDto(id, "12345678901", "email@email.com", "Nome");

        when(controller.findById(id)).thenReturn(client);

        ClientResponseDto actual = api.findById(id);

        assertEquals(expected, actual);

        verify(controller, times(1)).findById(id);
    }

    @Test
    public void shouldFindByCpf() {
        var id = 1L;
        var cpf = "12345678901";
        var client = new Client(id, cpf, "email@email.com", "Nome");
        var expected = new ClientResponseDto(id, cpf, "email@email.com", "Nome");

        when(controller.findByCpf(cpf)).thenReturn(client);

        ClientResponseDto actual = api.findByCpf(cpf);

        assertEquals(expected, actual);

        verify(controller, times(1)).findByCpf(cpf);
    }

    @Test
    public void shouldInsert() {
        var id = 1L;
        var cpf = "12345678901";
        var request = new ClientInsertRequestDto(cpf, "email@email.com", "Nome");
        var client = new Client(id, cpf, "email@email.com", "Nome");
        var expected = new ClientResponseDto(id, cpf, "email@email.com", "Nome");

        when(controller.insert(request.toEntity())).thenReturn(client);

        ClientResponseDto actual = api.insert(request);

        assertEquals(expected, actual);

        verify(controller, times(1)).insert(request.toEntity());
    }
}
