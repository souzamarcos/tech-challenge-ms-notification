package com.fiap.burger.gateway.clientcpf.gateway;

import com.fiap.burger.gateway.clientcpf.model.ClientCpfModel;
import com.fiap.burger.usecase.adapter.gateway.ClientCpfGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Repository
public class DefaultClientCpfGateway implements ClientCpfGateway {

    @Value("${dynamodb.tablename}")
    private String tableName;
    private final DynamoDbClient dynamoDbClient =
        DynamoDbClient.builder()
            .region(Region.US_EAST_1)
            .credentialsProvider(ProfileCredentialsProvider.create())
            .build();
    private final DynamoDbEnhancedClient enhancedClient =
        DynamoDbEnhancedClient.builder()
            .dynamoDbClient(dynamoDbClient)
            .build();

    @Override
    public void save(String cpf, Long clientId) {
        getTable().putItem(new ClientCpfModel(cpf, clientId));
    }

    @Override
    public Long findByCpf(String cpf) {
        return getTable().getItem(Key.builder().partitionValue(cpf).build()).getId();
    }

    private DynamoDbTable<ClientCpfModel> getTable() {
        return enhancedClient.table(tableName, TableSchema.fromBean(ClientCpfModel.class));
    }
}
