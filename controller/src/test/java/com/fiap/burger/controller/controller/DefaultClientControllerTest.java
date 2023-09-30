package com.fiap.burger.controller.controller;

import com.fiap.burger.entity.client.Client;
import com.fiap.burger.usecase.misc.exception.ClientCpfAlreadyExistsException;
import com.fiap.burger.usecase.misc.exception.ClientNotFoundException;
import com.fiap.burger.usecase.usecase.DefaultClientUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DefaultClientControllerTest {

    @Mock
    DefaultClientUseCase useCase;

    @InjectMocks
    DefaultClientController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindById() {
        var id = 1L;
        var expected = new Client(id);

        when(useCase.findById(id)).thenReturn(expected);

        Client actual = controller.findById(id);

        assertEquals(expected, actual);

        verify(useCase, times(1)).findById(id);
    }

    @Test
    public void shouldThrownClientNotFoundExceptionWhenClientNotFoundById() {
        var id = 1L;

        when(useCase.findById(id)).thenReturn(null);

        assertThrows(ClientNotFoundException.class, () -> controller.findById(id));

        verify(useCase, times(1)).findById(id);
    }

    @Test
    public void shouldFindByCpf() {
        var cpf = "12345678909";
        var expected = new Client(1L);

        when(useCase.findByCpf(cpf)).thenReturn(expected);

        Client actual = controller.findByCpf(cpf);

        assertEquals(expected, actual);

        verify(useCase, times(1)).findByCpf(cpf);
    }

    @Test
    public void shouldThrownClientNotFoundExceptionWhenClientNotFoundByCpf() {
        var cpf = "12345678901";

        when(useCase.findByCpf(cpf)).thenReturn(null);

        assertThrows(ClientNotFoundException.class, () -> controller.findByCpf(cpf));

        verify(useCase, times(1)).findByCpf(cpf);
    }

    @Test
    public void shouldInsertClient() {
        var client = new Client(1L);

        when(useCase.insert(client)).thenReturn(client);

        Client actual = controller.insert(client);

        assertEquals(client, actual);

        verify(useCase, times(1)).insert(client);
    }
}
