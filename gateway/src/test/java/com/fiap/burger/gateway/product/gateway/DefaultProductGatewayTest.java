package com.fiap.burger.gateway.product.gateway;

import com.fiap.burger.entity.product.Category;
import com.fiap.burger.gateway.misc.ProductBuilder;
import com.fiap.burger.gateway.misc.ProductJPABuilder;
import com.fiap.burger.gateway.product.dao.ProductDAO;
import com.fiap.burger.gateway.product.model.ProductJPA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DefaultProductGatewayTest {

    @Mock
    ProductDAO productDAO;

    @InjectMocks
    DefaultProductGateway gateway;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void shouldFindById() {
        var id = 1L;
        var productJPA = new ProductJPABuilder().withId(1L).build();
        var expected = productJPA.toEntity();

        when(productDAO.findById(id)).thenReturn(Optional.of(productJPA));

        var actual = gateway.findById(id);

        assertEquals(expected, actual);

        verify(productDAO, times(1)).findById(id);
    }

    @Test
    public void shouldFindAllProducts() {
        var productsJPA = Arrays.asList(new ProductJPABuilder().withId(1L).build(), new ProductJPABuilder().withId(2L).build());
        var expected = productsJPA.stream().map(ProductJPA::toEntity).toList();

        when(productDAO.findAllByDeletedAtNull()).thenReturn(productsJPA);

        var actual = gateway.findAll();

        assertIterableEquals(expected, actual);

        verify(productDAO, times(1)).findAllByDeletedAtNull();
    }

    @Test
    public void shouldFindAllProductsByCategory() {
        var category = Category.LANCHE;
        var productsJPA = Collections.singletonList(new ProductJPABuilder().withId(2L).build());
        var expected = productsJPA.stream().map(ProductJPA::toEntity).toList();

        when(productDAO.findAllByCategoryAndDeletedAtNull(category)).thenReturn(productsJPA);

        var actual = gateway.findAllBy(category);

        assertIterableEquals(expected, actual);

        verify(productDAO, times(1)).findAllByCategoryAndDeletedAtNull(category);
    }

    @Test
    public void shouldSaveProduct() {
        var productJPA = new ProductJPABuilder().withId(1L).build();
        var product = new ProductBuilder().withId(null).build();
        var expected = new ProductBuilder().withId(1L).build();

        when(productDAO.save(any())).thenReturn(productJPA);

        var actual = gateway.save(product);

        assertEquals(expected.getId(), actual.getId());

        verify(productDAO, times(1)).save(any());
    }

    @Test
    public void shouldDeleteProduct() {
        var id = 1L;

        gateway.deleteBy(id);

        verify(productDAO, times(1)).deleteById(1L);
    }
}