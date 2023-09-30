package com.fiap.burger.gateway.client.gateway;

import com.fiap.burger.gateway.client.dao.ClientDAO;
import com.fiap.burger.gateway.client.model.ClientJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class DefaultClientGatewayTest {

    @Mock
    ClientDAO clientDAO;

    @InjectMocks
    DefaultClientGateway gateway;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindById() {
        var id = 1L;
        var clientJPA = new ClientJPA();

    }

}
