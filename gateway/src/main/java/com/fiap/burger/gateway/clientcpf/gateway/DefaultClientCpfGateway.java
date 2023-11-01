package com.fiap.burger.gateway.clientcpf.gateway;

import com.fiap.burger.gateway.clientcpf.model.ClientCpfModel;
import com.fiap.burger.usecase.adapter.gateway.ClientCpfGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Repository
public class DefaultClientCpfGateway implements ClientCpfGateway {

    private DynamoDbEnhancedClient enhancedClient;
    private DynamoDbTable<ClientCpfModel> table;

    DefaultClientCpfGateway(DynamoDbEnhancedClient enhancedClient, @Value("${dynamodb.tablename}") String tableName) {
        this.enhancedClient = enhancedClient;
        this.table = enhancedClient.table(tableName, TableSchema.fromBean(ClientCpfModel.class));
    }
    @Override
    public void save(String cpf, Long clientId) {
        table.putItem(new ClientCpfModel(cpf, clientId));
    }

    @Override
    public Long findByCpf(String cpf) {
        return table.getItem(Key.builder().partitionValue(cpf).build()).getId();
    }


}
