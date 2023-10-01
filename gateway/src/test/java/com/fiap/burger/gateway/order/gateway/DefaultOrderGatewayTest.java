package com.fiap.burger.gateway.order.gateway;

import com.fiap.burger.entity.order.OrderStatus;
import com.fiap.burger.gateway.misc.OrderBuilder;
import com.fiap.burger.gateway.misc.OrderJPABuilder;
import com.fiap.burger.gateway.order.dao.OrderDAO;
import com.fiap.burger.gateway.order.model.OrderJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DefaultOrderGatewayTest {

    @Mock
    OrderDAO orderDAO;

    @InjectMocks
    DefaultOrderGateway gateway;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindById() {
        var id = 1L;
        var orderJPA = new OrderJPABuilder().withId(1L).build();
        var expected = orderJPA.toEntityWithItems();

        when(orderDAO.findById(id)).thenReturn(Optional.of(orderJPA));

        var actual = gateway.findById(id);

        assertEquals(expected, actual);

        verify(orderDAO, times(1)).findById(id);
    }

    @Test
    public void shouldFindAll() {
        var ordersJPA = List.of(new OrderJPABuilder().withId(1L).build());
        var expected = ordersJPA.stream().map(OrderJPA::toEntity).collect(Collectors.toList());

        when(orderDAO.findAllByDeletedAtNull()).thenReturn(ordersJPA);

        var actual = gateway.findAll();

        assertEquals(expected, actual);

        verify(orderDAO, times(1)).findAllByDeletedAtNull();
    }

    @Test
    public void shouldFindAllInProgress() {
        var ordersJPA = List.of(new OrderJPABuilder().withId(1L).build());
        var expected = ordersJPA.stream().map(OrderJPA::toEntity).collect(Collectors.toList());

        when(orderDAO.findAllInProgress(Set.of(OrderStatus.RECEBIDO, OrderStatus.EM_PREPARACAO, OrderStatus.PRONTO))).thenReturn(ordersJPA);

        var actual = gateway.findAllInProgress();

        assertEquals(expected, actual);

        verify(orderDAO, times(1)).findAllInProgress(Set.of(OrderStatus.RECEBIDO, OrderStatus.EM_PREPARACAO, OrderStatus.PRONTO));
    }

    @Test
    public void shouldSaveClient() {
        var orderJPA = new OrderJPABuilder().withId(1L).build();
        var order = new OrderBuilder().withId(null).build();
        var expected = new OrderBuilder().withId(1L).build();

        when(orderDAO.save(any())).thenReturn(orderJPA);

        var actual = gateway.save(order);

        assertEquals(expected.getId(), actual.getId());

        verify(orderDAO, times(1)).save(any());
    }

    @Test
    public void shouldUpdateStatus() {
        gateway.updateStatus(1L, OrderStatus.RECEBIDO, LocalDateTime.now());
        verify(orderDAO, times(1)).updateStatus(eq(1L), eq(OrderStatus.RECEBIDO), any());
    }
}
