package com.fiap.burger.gateway.client.gateway;

import com.fiap.burger.gateway.client.dao.ClientDAO;
import com.fiap.burger.gateway.misc.ClientBuilder;
import com.fiap.burger.gateway.misc.ClientJPABuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DefaultClientGatewayTest {

    @Mock
    ClientDAO clientDAO;

    @InjectMocks
    DefaultClientGateway gateway;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindById() {
        var id = 1L;
        var clientJPA = new ClientJPABuilder().withId(1L).build();
        var expected = clientJPA.toEntity();

        when(clientDAO.findById(id)).thenReturn(Optional.of(clientJPA));

        var actual = gateway.findById(id);

        assertEquals(expected, actual);

        verify(clientDAO, times(1)).findById(id);
    }

    @Test
    void shouldFindByCpf() {
        var cpf = "12345678909";
        var clientJPA = new ClientJPABuilder().withId(1L).build();
        var expected = clientJPA.toEntity();

        when(clientDAO.findByCpf(cpf)).thenReturn(Optional.of(clientJPA));

        var actual = gateway.findByCpf(cpf);

        assertEquals(expected, actual);

        verify(clientDAO, times(1)).findByCpf(cpf);
    }

    @Test
    void shouldSaveClient() {
        var clientJPA = new ClientJPABuilder().withId(1L).build();
        var client = new ClientBuilder().withId(null).build();
        var expected = new ClientBuilder().withId(1L).build();

        when(clientDAO.save(any())).thenReturn(clientJPA);

        var actual = gateway.save(client);

        assertEquals(expected.getId(), actual.getId());

        verify(clientDAO, times(1)).save(any());
    }
}
