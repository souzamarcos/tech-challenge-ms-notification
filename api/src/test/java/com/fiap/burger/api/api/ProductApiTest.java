package com.fiap.burger.api.api;

import com.fiap.burger.api.dto.product.request.ProductInsertRequestDto;
import com.fiap.burger.api.dto.product.request.ProductUpdateRequestDto;
import com.fiap.burger.api.dto.product.response.ProductResponseByIdDto;
import com.fiap.burger.api.dto.product.response.ProductResponseDto;
import com.fiap.burger.controller.adapter.api.ProductController;
import com.fiap.burger.entity.product.Category;
import com.fiap.burger.entity.product.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductApiTest {

    @InjectMocks
    ProductApi api;

    @Mock
    ProductController controller;

    @Test
    void shouldList() {
        var products = List.of(new Product(1L, Category.LANCHE, "Nome", "Descrição", 10.0));
        var expected = List.of(new ProductResponseDto(1L, Category.LANCHE, "Nome", "Descrição", 10.0));

        when(controller.list(Category.LANCHE)).thenReturn(products);

        List<ProductResponseDto> actual = api.list(Category.LANCHE);

        assertEquals(expected, actual);

        verify(controller, times(1)).list(Category.LANCHE);
    }

    @Test
    void shouldFindById() {
        var id = 1L;
        var product = new Product(1L, Category.LANCHE, "Nome", "Descrição", 10.0);
        var expected = new ProductResponseByIdDto(1L, Category.LANCHE, "Nome", "Descrição", 10.0, null, null, null);

        when(controller.findById(id)).thenReturn(product);

        ProductResponseByIdDto actual = api.findById(id);

        assertEquals(expected, actual);

        verify(controller, times(1)).findById(id);
    }

    @Test
    void shouldInsert() {
        var request = new ProductInsertRequestDto(Category.LANCHE, "Nome", "Descrição", 10.0);
        var product = new Product(1L, Category.LANCHE, "Nome", "Descrição", 10.0);
        var expected = new ProductResponseDto(1L, Category.LANCHE, "Nome", "Descrição", 10.0);

        when(controller.insert(request.toEntity())).thenReturn(product);

        ProductResponseDto actual = api.insert(request);

        assertEquals(expected, actual);

        verify(controller, times(1)).insert(request.toEntity());
    }

    @Test
    void shouldUpdate() {
        var request = new ProductUpdateRequestDto(1L, Category.LANCHE, "Nome", "Descrição", 10.0);
        var product = new Product(1L, Category.LANCHE, "Nome", "Descrição", 10.0);
        var expected = new ProductResponseDto(1L, Category.LANCHE, "Nome", "Descrição", 10.0);

        when(controller.update(request.toEntity())).thenReturn(product);

        ProductResponseDto actual = api.update(request);

        assertEquals(expected, actual);

        verify(controller, times(1)).update(product);
    }

    @Test
    void shouldDelete() {
        var id = 1L;
        var expected = "Product has been successfully deleted.";

        when(controller.deleteBy(id)).thenReturn("Product has been successfully deleted.");

        String actual = api.deleteBy(id);

        assertEquals(expected, actual);

        verify(controller, times(1)).deleteBy(id);
    }
}
