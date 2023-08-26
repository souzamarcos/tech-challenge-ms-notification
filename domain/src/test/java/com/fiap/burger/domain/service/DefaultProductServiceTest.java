package com.fiap.burger.domain.service;

import com.fiap.burger.domain.adapter.repository.product.ProductRepository;
import com.fiap.burger.domain.entities.product.Category;
import com.fiap.burger.domain.entities.product.Product;
import com.fiap.burger.domain.misc.ProductBuilder;
import com.fiap.burger.domain.misc.exception.BlankAttributeException;
import com.fiap.burger.domain.misc.exception.InvalidAttributeException;
import com.fiap.burger.domain.misc.exception.NegativeOrZeroValueException;
import com.fiap.burger.domain.misc.exception.NullAttributeException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DefaultProductServiceTest {

    @Mock
    ProductRepository repository;

    @InjectMocks
    DefaultProductService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldFindById() {
        var id = 1L;
        var expected = new ProductBuilder().withId(1L).build();

        when(repository.findById(id)).thenReturn(expected);

        var actual = service.findById(repository, id);

        assertEquals(expected, actual);

        verify(repository, times(1)).findById(id);
    }

    @Test
    public void shouldFindAllProducts() {
        List<Product> expected = Arrays.asList(new ProductBuilder().withId(1L).build(), new ProductBuilder().withId(2L).build());

        when(repository.findAll()).thenReturn(expected);

        List<Product> actual = service.findAll(repository);

        assertEquals(expected, actual);

        verify(repository, times(1)).findAll();
    }

    @Test
    public void shouldFindAllProductsByCategory() {
        List<Product> expected = Collections.singletonList(new ProductBuilder().build());
        Category category = Category.BEBIDA;

        when(repository.findAllBy(category)).thenReturn(expected);

        List<Product> actual = service.findAllBy(repository, category);

        assertEquals(expected, actual);

        verify(repository, times(1)).findAllBy(category);
    }

    @Test
    public void shouldSaveProduct() {
        Product product = new ProductBuilder().withId(null).build();

        when(repository.save(product)).thenReturn(product);

        Product actual = service.insert(repository, product);

        assertEquals(product, actual);

        verify(repository, times(1)).save(product);
    }

    @Test
    public void shouldThrowInvalidAttributeExceptionWhenProductIdIsNotNullToInsert() {
        Product product = new ProductBuilder().withId(1L).build();

        assertThrows(InvalidAttributeException.class, () -> service.insert(repository, product));

        verify(repository, times(0)).save(product);
    }
    @Test
    public void shouldThrowNullAttributeExceptionWhenProductCategoryIsNullToInsert() {
        Product product = new ProductBuilder().withId(null).withCategory(null).build();

        assertThrows(NullAttributeException.class, () -> service.insert(repository, product));

        verify(repository, times(0)).save(product);
    }

    @Test
    public void shouldThrowNullAttributeExceptionWhenProductNameIsNullToInsert() {
        Product product = new ProductBuilder().withId(null).withName(null).build();

        assertThrows(NullAttributeException.class, () -> service.insert(repository, product));

        verify(repository, times(0)).save(product);
    }

    @Test
    public void shouldThrowBlankAttributeExceptionWhenProductNameIsBlankToInsert() {
        Product product = new ProductBuilder().withId(null).withName("  ").build();

        assertThrows(BlankAttributeException.class, () -> service.insert(repository, product));

        verify(repository, times(0)).save(product);
    }

    @Test
    public void shouldThrowNullAttributeExceptionWhenProductDescriptionIsNullToInsert() {
        Product product = new ProductBuilder().withId(null).withDescription(null).build();

        assertThrows(NullAttributeException.class, () -> service.insert(repository, product));

        verify(repository, times(0)).save(product);
    }

    @Test
    public void shouldThrowBlankAttributeExceptionWhenProductDescriptionIsBlankToInsert() {
        Product product = new ProductBuilder().withId(null).withDescription("  ").build();

        assertThrows(BlankAttributeException.class, () -> service.insert(repository, product));

        verify(repository, times(0)).save(product);
    }

    @Test
    public void shouldThrowNullAttributeExceptionWhenProductValueIsNullToInsert() {
        Product product = new ProductBuilder().withId(null).withValue(null).build();

        assertThrows(NullAttributeException.class, () -> service.insert(repository, product));

        verify(repository, times(0)).save(product);
    }

    @Test
    public void shouldThrowBlankAttributeExceptionWhenProductValueIsLessThanZeroToInsert() {
        Product product = new ProductBuilder().withId(null).withValue(-0.1).build();

        assertThrows(NegativeOrZeroValueException.class, () -> service.insert(repository, product));

        verify(repository, times(0)).save(product);
    }

    @Test
    public void shouldThrowBlankAttributeExceptionWhenProductValueIsZeroToInsert() {
        Product product = new ProductBuilder().withId(null).withValue(0.0).build();

        assertThrows(NegativeOrZeroValueException.class, () -> service.insert(repository, product));

        verify(repository, times(0)).save(product);
    }
}