package com.fiap.burger.gateway.clientcpf.gateway;

import com.fiap.burger.gateway.misc.ClientCpfModelBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DefaultClientCpfGatewayTest {

    @Mock
    DynamoDbEnhancedClient client;

    @Mock
    DynamoDbTable table;

    DefaultClientCpfGateway gateway;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        when(client.table(any(), any())).thenReturn(table);

        gateway = new DefaultClientCpfGateway(client, "tableName");
    }


    @Test
    void shouldFindByCpf() {
        var cpf = "12345678909";
        var expectedId = 1L;
        var clientCpfModel = new ClientCpfModelBuilder().withCpf(cpf).withId(expectedId).build();

        when(table.getItem(Key.builder().partitionValue(cpf).build())).thenReturn(clientCpfModel);

        var actual = gateway.findByCpf(cpf);

        assertEquals(expectedId, actual);

        verify(table, times(1)).getItem(Key.builder().partitionValue(cpf).build());
    }

    @Test
    void shouldSaveClientCpf() {
        var cpf = "12345678909";
        var id = 1L;
        var clientCpfModel = new ClientCpfModelBuilder().withCpf(cpf).withId(id).build();

        doNothing().when(table).putItem(clientCpfModel);

        gateway.save(cpf, id);

        verify(table, times(1)).putItem(clientCpfModel);
    }
}