package com.graphql.project.service.productLineService;

import com.graphql.project.dtos.CreateProductLine;
import com.graphql.project.entity.ProductLine;
import com.graphql.project.persistance.ProductLineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductLineServiceTest {

    @Mock
    private ProductLineRepository repository;

    @Mock
    private ProductLineMapper mapper;

    @InjectMocks
    private ProductLineService service;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindProductLines(){
        List<ProductLine> mockedList = List.of(new ProductLine(),new ProductLine());
        when(repository.findProductLineAll()).thenReturn(mockedList);

        List<ProductLine> returned = service.findProductLines();
        assertEquals(2,returned.size());

        verify(repository).findProductLineAll();
    }

    @Test
    void testCreateProductLine(){
        CreateProductLine productLineDto = new CreateProductLine(
                "Classic car models from the 1950s",
                "<p>Classic car models from the 1950s</p>",
                "classic_cars.jpg"
        );
        ProductLine productLine = new ProductLine();
        productLine.setProductLineId(1);
        productLine.setImage("classic_cars.jpg");
        when(mapper.productLineDtoToProductLine(productLineDto)).thenReturn(productLine);
        when(repository.save(productLine)).thenReturn(productLine);

        ProductLine saved = service.createProductLine(productLineDto);

        assertNotNull(saved);
        assertEquals(1,saved.getProductLineId());
        assertEquals("classic_cars.jpg",saved.getImage());

        verify(repository).save(productLine);
        verify(mapper).productLineDtoToProductLine(productLineDto);
    }

    @Test
    void testFindProductLineById_Success(){
        ProductLine productLine = new ProductLine();
        productLine.setProductLineId(1);
        productLine.setImage("classic_cars.jpg");
        when(repository.findById(1)).thenReturn(Optional.of(productLine));

        ProductLine found = service.findProductLinesById(1);

        assertEquals("classic_cars.jpg",found.getImage());
        assertEquals(1,found.getProductLineId());
        verify(repository).findById(1);
    }

    @Test
    void testFindProductLineById_Not_Found(){
        when(repository.findById(99)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class
        , () -> service.findProductLinesById(99));

        assertEquals("Product Line with id 99 not found",exception.getMessage());
        verify(repository).findById(99);
    }

    @Test
    void testDeleteProductLineById(){
        ProductLine productLine = new ProductLine();
        productLine.setProductLineId(1);
        productLine.setImage("classic_cars.jpg");
        when(repository.findById(1)).thenReturn(Optional.of(productLine));

        ProductLine deleted = service.deleteProductLineById(1);

        assertEquals(1,deleted.getProductLineId());
        assertEquals("classic_cars.jpg",deleted.getImage());

        verify(repository).findById(1);
        verify(repository).delete(productLine);
    }

    @Test
    void testUpdateProductLine(){
        CreateProductLine productLineDto = new CreateProductLine(
                "Classic car models from the 1950s",
                "<p>Classic car models from the 1950s</p>",
                "classic_cars.jpg"
        );
        ProductLine productLine = new ProductLine();
        productLine.setProductLineId(1);

        when(repository.findById(1)).thenReturn(Optional.of(productLine));
        when(repository.save(any())).thenReturn(productLine);

        ProductLine updated = service.updateProductLine(1,productLineDto);

        assertEquals("Classic car models from the 1950s",updated.getDescInText());
        assertEquals("<p>Classic car models from the 1950s</p>",updated.getDescInHTML());
        assertEquals("classic_cars.jpg",updated.getImage());

        verify(repository).findById(1);
        verify(repository).save(productLine);

    }


}
