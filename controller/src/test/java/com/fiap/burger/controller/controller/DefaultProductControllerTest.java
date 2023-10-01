package com.fiap.burger.controller.controller;

import com.fiap.burger.entity.product.Category;
import com.fiap.burger.entity.product.Product;
import com.fiap.burger.usecase.misc.exception.ProductNotFoundException;
import com.fiap.burger.usecase.usecase.DefaultProductUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DefaultProductControllerTest {

    @Mock
    DefaultProductUseCase useCase;

    @InjectMocks
    DefaultProductController controller;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldListAll() {
        List<Product> expected = List.of(new Product(1L, Category.LANCHE, "Nome", "Descrição", 10.0));

        when(useCase.findAll()).thenReturn(expected);

        List<Product> actual = controller.list(null);

        assertEquals(expected, actual);

        verify(useCase, times(1)).findAll();
        verify(useCase, times(0)).findAllBy(any());
    }

    @Test
    public void shouldListAllByCategory() {
        List<Product> expected = List.of(new Product(1L, Category.LANCHE, "Nome", "Descrição", 10.0));

        when(useCase.findAllBy(Category.LANCHE)).thenReturn(expected);

        List<Product> actual = controller.list(Category.LANCHE);

        assertEquals(expected, actual);

        verify(useCase, times(0)).findAll();
        verify(useCase, times(1)).findAllBy(Category.LANCHE);
    }

    @Test
    public void shouldFindById() {
        var id = 1L;
        var expected = new Product(1L, Category.LANCHE, "Nome", "Descrição", 10.0);

        when(useCase.findById(id)).thenReturn(expected);

        Product actual = controller.findById(id);

        assertEquals(expected, actual);

        verify(useCase, times(1)).findById(id);
    }

    @Test
    public void shouldThrownProductNotFoundExceptionWhenProductNotFoundById() {
        var id = 1L;

        when(useCase.findById(id)).thenReturn(null);

        assertThrows(ProductNotFoundException.class, () -> controller.findById(id));

        verify(useCase, times(1)).findById(id);
    }

    @Test
    public void shouldInsertProduct() {
        var product = new Product(1L, Category.LANCHE, "Nome", "Descrição", 10.0);

        when(useCase.insert(product)).thenReturn(product);

        Product actual = controller.insert(product);

        assertEquals(product, actual);

        verify(useCase, times(1)).insert(product);
    }

    @Test
    public void shouldUpdateProduct() {
        var product = new Product(1L, Category.LANCHE, "Nome", "Descrição", 10.0);

        when(useCase.update(product)).thenReturn(product);

        Product actual = controller.update(product);

        assertEquals(product, actual);

        verify(useCase, times(1)).update(product);
    }

    @Test
    public void shouldDeleteProduct() {
        var id = 1L;
        var expected = "Product has been successfully deleted.";

        String actual = controller.deleteBy(id);

        assertEquals(expected, actual);

        verify(useCase, times(1)).deleteBy(id);
    }
}
