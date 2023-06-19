package com.fiap.burger.domain.service;

import com.fiap.burger.domain.adapter.repository.product.ProductRepository;
import com.fiap.burger.domain.entities.product.Category;
import com.fiap.burger.domain.entities.product.Product;
import com.fiap.burger.domain.misc.ProductBuilder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock
    ProductRepository repository;

    @InjectMocks
    ProductService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindAllProducts() {
        List<Product> expected = Arrays.asList(new ProductBuilder().withId(1L).build(), new ProductBuilder().withId(2L).build());

        when(repository.findlAll()).thenReturn(expected);

        List<Product> actual = service.findAll();

        assertEquals(expected, actual);

        verify(repository, times(1)).findlAll();
    }

    @Test
    public void shouldFindAllProductsByCategory() {
        List<Product> expected = Collections.singletonList(new ProductBuilder().build());
        Category category = Category.BEBIDA;

        when(repository.findlAllBy(category)).thenReturn(expected);

        List<Product> actual = service.findAllBy(category);

        assertEquals(expected, actual);

        verify(repository, times(1)).findlAllBy(category);
    }
}