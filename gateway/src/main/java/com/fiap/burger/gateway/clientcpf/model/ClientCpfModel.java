package com.fiap.burger.gateway.clientcpf.model;


import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class ClientCpfModel {

    private String cpf;
    private Long id;

    public ClientCpfModel(){}

    public ClientCpfModel(String cpf, Long id) {
        this.cpf = cpf;
        this.id = id;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("cpf")
    public String getCpf() { return this.cpf; }

    public Long getId() { return this.id; }

    public void setCpf(String cpf) { this.cpf = cpf;  }

    public void setId(Long id) { this.id = id; }

    @Override
    public String toString() {
        return "Customer [cpf=" + cpf + ", id=" + id + "]";
    }
}
