package com.fiap.burger.usecase.usecase;

import com.fiap.burger.entity.client.Client;
import com.fiap.burger.usecase.adapter.gateway.ClientGateway;
import com.fiap.burger.usecase.misc.ClientBuilder;
import com.fiap.burger.usecase.misc.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DefaultClientUseCaseTest {

    @Mock
    ClientGateway gateway;

    @InjectMocks
    DefaultClientUseCase useCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindById() {
        var id = 1L;
        var expected = new ClientBuilder().withId(id).build();

        when(gateway.findById(id)).thenReturn(expected);

        var actual = useCase.findById(id);

        assertEquals(expected, actual);

        verify(gateway, times(1)).findById(id);
    }

    @Test
    void shouldFindByCpf() {
        var cpf = "1234567901";
        var expected = new ClientBuilder().withCpf(cpf).build();

        when(gateway.findByCpf(cpf)).thenReturn(expected);

        var actual = useCase.findByCpf(cpf);

        assertEquals(expected, actual);

        verify(gateway, times(1)).findByCpf(cpf);
    }

    @Test
    void shouldSaveClient() {
        Client client = new ClientBuilder().withId(null).build();

        when(gateway.save(client)).thenReturn(client);

        Client actual = useCase.insert(client);

        assertEquals(client, actual);

        verify(gateway, times(1)).save(client);
    }


    @Test
    void shouldThrowClientCpfAlreadyExistsExceptionWhenClientAlreadyExistsToInsert() {
        var cpf = "68203895077";
        Client client = new ClientBuilder().withId(null).withCpf(cpf).build();

        when(gateway.findByCpf(cpf)).thenReturn(client);

        assertThrows(ClientCpfAlreadyExistsException.class, () -> useCase.insert(client));

        verify(gateway, times(1)).findByCpf(cpf);
        verify(gateway, times(0)).save(client);
    }

    @Test
    void shouldThrowInvalidAttributeExceptionWhenClientIdIsNotNullToInsert() {
        Client client = new ClientBuilder().withId(1L).build();

        assertThrows(InvalidAttributeException.class, () -> useCase.insert(client));

        verify(gateway, times(0)).save(client);
    }

    @Test
    void shouldThrowNullAttributeExceptionWhenClientCpfIsNullToInsert() {
        Client client = new ClientBuilder().withId(null).withCpf(null).build();

        assertThrows(NullAttributeException.class, () -> useCase.insert(client));

        verify(gateway, times(0)).save(client);
    }

    @Test
    void shouldThrowBlankAttributeExceptionWhenClientCpfIsBlankToInsert() {
        Client client = new ClientBuilder().withId(null).withCpf("    ").build();

        assertThrows(BlankAttributeException.class, () -> useCase.insert(client));

        verify(gateway, times(0)).save(client);
    }

    @Test
    void shouldThrowInvalidCPFExceptionExceptionWhenClientCpfHasNonNumericCharactersToInsert() {
        Client client = new ClientBuilder().withId(null).withCpf("12345A2345").build();

        assertThrows(InvalidCPFException.class, () -> useCase.insert(client));

        verify(gateway, times(0)).save(client);
    }

    @Test
    void shouldThrowInvalidCPFExceptionExceptionWhenClientCpfIsInvalidToInsert() {
        Client client = new ClientBuilder().withId(null).withCpf("000000000000").build();

        assertThrows(InvalidCPFException.class, () -> useCase.insert(client));

        verify(gateway, times(0)).save(client);
    }

    @Test
    void shouldThrowNullAttributeExceptionWhenClientEmailIsNullToInsert() {
        Client client = new ClientBuilder().withId(null).withEmail(null).build();

        assertThrows(NullAttributeException.class, () -> useCase.insert(client));

        verify(gateway, times(0)).save(client);
    }

    @Test
    void shouldThrowBlankAttributeExceptionWhenClientEmailIsBlankToInsert() {
        Client client = new ClientBuilder().withId(null).withEmail("    ").build();

        assertThrows(BlankAttributeException.class, () -> useCase.insert(client));

        verify(gateway, times(0)).save(client);
    }

    @Test
    void shouldThrowInvalidEmailFormatExceptionWhenClientEmailHasInvalidFormatToInsert() {
        Client client = new ClientBuilder().withId(null).withEmail("emailemailemail").build();

        assertThrows(InvalidEmailFormatException.class, () -> useCase.insert(client));

        verify(gateway, times(0)).save(client);
    }

    @Test
    void shouldThrowNullAttributeExceptionWhenClientNameIsNullToInsert() {
        Client client = new ClientBuilder().withId(null).withName(null).build();

        assertThrows(NullAttributeException.class, () -> useCase.insert(client));

        verify(gateway, times(0)).save(client);
    }

    @Test
    void shouldThrowBlankAttributeExceptionWhenClientNameIsBlankToInsert() {
        Client client = new ClientBuilder().withId(null).withName("    ").build();

        assertThrows(BlankAttributeException.class, () -> useCase.insert(client));

        verify(gateway, times(0)).save(client);
    }
}
