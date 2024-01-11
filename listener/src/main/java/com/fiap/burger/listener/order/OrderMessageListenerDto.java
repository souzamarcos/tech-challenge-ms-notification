package com.fiap.burger.listener.order;

public class OrderMessageListenerDto {
    private Long id;

    public OrderMessageListenerDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
