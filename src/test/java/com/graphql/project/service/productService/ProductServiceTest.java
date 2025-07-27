package com.graphql.project.service.productService;

import com.graphql.project.dtos.CreateProduct;
import com.graphql.project.entity.Product;
import com.graphql.project.entity.ProductLine;
import com.graphql.project.persistance.ProductLineRepository;
import com.graphql.project.persistance.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServiceTest {
    @Mock
    private ProductRepository repository;

    @Mock
    private ProductLineRepository productLineRepository;

    @Mock
    private ProductMapper mapper;

    @InjectMocks
    private ProductService service;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindProducts(){
        List<Product> mockedList = List.of(new Product(),new Product());
        when(repository.findProductAll()).thenReturn(mockedList);

        List<Product> productList = service.findProducts();

        assertEquals(2,productList.size());

        verify(repository).findProductAll();
    }

    @Test
    void testFindProductById_Not_Found(){
        when(repository.findById(99)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.findProductById(99));

        assertEquals("Product with id 99 not found",exception.getMessage());

        verify(repository).findById(99);

    }
    @Test
    void testFindProductById_Success(){
        Product product = new Product();
        product.setProductCode(1);
        product.setScale(10);

        when(repository.findById(1)).thenReturn(Optional.of(product));

        Product found = service.findProductById(1);

        assertEquals(1,found.getProductCode());
        assertEquals(10,found.getScale());

        verify(repository).findById(1);

    }


    @Test
    void testCreateProduct_Success(){
        CreateProduct productDto = new CreateProduct(
                1,
                "Classic Car Model",
                24,
                "AutoArt",
                "Vintage car replica",
                50,
                75,
                "99.99"
        );

        Product product = new Product();
        ProductLine pl = new ProductLine();
        pl.setProductLineId(1);
        product.setProductCode(1);
        product.setScale(24);
        product.setProductLineId(pl);

        when(mapper.productDtoToProduct(productDto)).thenReturn(product);
        when(productLineRepository.findById(1)).thenReturn(Optional.of(pl));
        when(repository.save(product)).thenReturn(product);

        Product newProduct = service.createProduct(productDto);

        assertEquals(1,newProduct.getProductLineId().getProductLineId());
        assertEquals(1,newProduct.getProductCode());
        assertEquals(24,newProduct.getScale());

        verify(productLineRepository).findById(1);
        verify(repository).save(product);
        verify(mapper).productDtoToProduct(productDto);



    }

    @Test
    void testCreateProduct_Failure(){
        CreateProduct productDto = new CreateProduct(
                1,
                "Classic Car Model",
                24,
                "AutoArt",
                "Vintage car replica",
                50,
                75,
                "99.99"
        );
        when(mapper.productDtoToProduct(productDto)).thenReturn(any());
        when(productLineRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.createProduct(productDto));

        assertEquals("ProductLine with id 1 not found",ex.getMessage());

        verify(mapper).productDtoToProduct(any());
        verify(productLineRepository).findById(1);

    }

    @Test
    void testDeleteProductById(){
        Product product = new Product();
        product.setProductCode(1);
        product.setName("car");
        when(repository.findById(1)).thenReturn(Optional.of(product));

        Product deleted = service.deleteProductById(1);

        assertEquals("car",deleted.getName());
        assertEquals(1,deleted.getProductCode());
        verify(repository).findById(1);
        verify(repository).deleteById(1);
    }

    @Test
    void testUpdateProduct(){
        CreateProduct productDto = new CreateProduct(
                1,
                "Classic Car Model",
                24,
                "AutoArt",
                "Vintage car replica",
                50,
                75,
                "99.99"
        );

        Product product = new Product();
        product.setProductCode(1);
        ProductLine pl = new ProductLine();
        pl.setProductLineId(1);
        when(repository.findById(1)).thenReturn(Optional.of(product));
        when(repository.save(any())).thenReturn(product);
        when(productLineRepository.findById(1)).thenReturn(Optional.of(pl));

        Product updated = service.updateProduct(1,productDto);

        assertEquals("AutoArt",updated.getVendor());
        assertEquals("99.99",updated.getMSRP());
        assertEquals("Classic Car Model",updated.getName());
        assertEquals("Vintage car replica",updated.getPdtDescription());
        assertEquals(24,updated.getScale());
        assertEquals(50,updated.getQntyInStock());
        assertEquals(new BigDecimal(75),updated.getBuyPrice());
        assertEquals(1,updated.getProductLineId().getProductLineId());
        verify(repository).findById(1);
        verify(repository).save(any());
        verify(productLineRepository).findById(1);
    }


}
