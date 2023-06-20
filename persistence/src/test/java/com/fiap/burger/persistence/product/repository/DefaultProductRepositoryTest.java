package com.fiap.burger.persistence.product.repository;

import com.fiap.burger.domain.entities.product.Category;
import com.fiap.burger.persistence.misc.ProductBuilder;
import com.fiap.burger.persistence.misc.ProductJPABuilder;
import com.fiap.burger.persistence.product.dao.ProductDAO;
import com.fiap.burger.persistence.product.model.ProductJPA;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DefaultProductRepositoryTest {

    @Mock
    ProductDAO productDAO;

    @InjectMocks
    DefaultProductRepository repository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindAllProducts() {
        var productsJPA = Arrays.asList(new ProductJPABuilder().withId(1L).build(), new ProductJPABuilder().withId(2L).build());
        var expected = productsJPA.stream().map(ProductJPA::toEntity).toList();

        when(productDAO.findAllByDeletedAtNull()).thenReturn(productsJPA);

        var actual = repository.findlAll();

        assertIterableEquals(expected, actual);

        verify(productDAO, times(1)).findAllByDeletedAtNull();
    }

    @Test
    public void shouldFindAllProductsByCategory() {
        var category = Category.LANCHE;
        var productsJPA = Collections.singletonList(new ProductJPABuilder().withId(2L).build());
        var expected = productsJPA.stream().map(ProductJPA::toEntity).toList();

        when(productDAO.findAllByCategoryAndDeletedAtNull(category)).thenReturn(productsJPA);

        var actual = repository.findlAllBy(category);

        assertIterableEquals(expected, actual);

        verify(productDAO, times(1)).findAllByCategoryAndDeletedAtNull(category);
    }

    @Test
    public void shouldSaveProduct() {
        var productJPA = new ProductJPABuilder().withId(1L).build();
        var product = new ProductBuilder().withId(null).build();
        var expected = new ProductBuilder().withId(1L).build();

        when(productDAO.save(any())).thenReturn(productJPA);

        var actual = repository.save(product);

        assertEquals(expected.getId(), actual.getId());

        verify(productDAO, times(1)).save(any());
    }
}