package com.graphql.project.service.orderProductService;

import com.graphql.project.dtos.CreateOrderProduct;
import com.graphql.project.dtos.UpdateOrderProduct;
import com.graphql.project.entity.Order;
import com.graphql.project.entity.OrderProduct;
import com.graphql.project.entity.OrderProductKey;
import com.graphql.project.entity.Product;
import com.graphql.project.persistance.OrderProductRepository;
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

public class OrderProductServiceTest {
    @Mock
    private OrderProductRepository repository;

    @Mock
    private OrderProductMapper mapper;

    @InjectMocks
    private OrderProductService service;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindOrderProducts(){
        List<OrderProduct> mockedList = List.of(new OrderProduct(),new OrderProduct());
        when(repository.findAll()).thenReturn(mockedList);

        List<OrderProduct> returnedList = service.findOrderProducts();
        assertEquals(2,returnedList.size());
        verify(repository).findAll();
    }

    @Test
    void testFindOrderProductById_Success(){
        OrderProductKey key = new OrderProductKey(1,1);
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrderProductId(key);
        Product product = new Product();
        product.setProductCode(1);
        Order order = new Order();
        order.setOrderId(1);
        orderProduct.setOrderId(order);
        orderProduct.setProductId(product);

        when(repository.findById(any())).thenReturn(Optional.of(orderProduct));

        OrderProduct found = service.findOrderProductById(1,1);

        assertEquals(1,found.getOrderId().getOrderId());
        assertEquals(1,found.getProductId().getProductCode());
        assertEquals(1,found.getOrderProductId().getOrderId());
        assertEquals(1,found.getOrderProductId().getProductCode());

        verify(repository).findById(any());

    }

    @Test
    void testFindOrderProductById_Not_Found(){
        when(repository.findById(any())).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.findOrderProductById(99,99));

        assertEquals("Order product with id orderId: 99 productCode: 99 not found",ex.getMessage());

        verify(repository).findById(any());

    }

    @Test
    void testCreateOrderProduct(){
        CreateOrderProduct orderProductDto = new CreateOrderProduct(
                1001,
                2001,
                5,
                99
        );
        OrderProduct product = new OrderProduct();
        OrderProductKey key = new OrderProductKey(1001,2001);
        product.setOrderProductId(key);
        product.setQty(5);
        when(mapper.OrderProductDtoToOrderProduct(orderProductDto)).thenReturn(product);
        when(repository.save(product)).thenReturn(product);

        OrderProduct savedOP = service.createOrderProduct(orderProductDto);

        assertEquals(1001,savedOP.getOrderProductId().getOrderId());
        assertEquals(2001,savedOP.getOrderProductId().getProductCode());
        assertEquals(5,savedOP.getQty());

        verify(repository).save(product);
        verify(mapper).OrderProductDtoToOrderProduct(orderProductDto);
    }

    @Test
    void testDeleteOrderProductById(){
        OrderProductKey key = new OrderProductKey(1,1);
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrderProductId(key);

        when(repository.findById(any())).thenReturn(Optional.of(orderProduct));

        OrderProduct deleted = service.deleteOrderProductById(1,1);

        assertEquals(1,deleted.getOrderProductId().getProductCode());
        assertEquals(1,deleted.getOrderProductId().getOrderId());


        verify(repository).findById(any());
        verify(repository).delete(orderProduct);

    }

    @Test
    void testUpdateOrderProduct(){
        UpdateOrderProduct orderProductDto = new UpdateOrderProduct(
                5,
                99);
        OrderProduct orderProduct = new OrderProduct();
        OrderProductKey key = new OrderProductKey(1001,2001);
        orderProduct.setOrderProductId(key);
        when(repository.findById(any())).thenReturn(Optional.of(orderProduct));
        when(repository.save(any())).thenReturn(orderProduct);

        OrderProduct updated = service.updateOrderProduct(1001,2001,orderProductDto);

        assertEquals(5,updated.getQty());
        assertEquals(new BigDecimal(99),updated.getPriceEach());
        assertEquals(1001,updated.getOrderProductId().getOrderId());
        assertEquals(2001,updated.getOrderProductId().getProductCode());

        verify(repository).findById(any());
        verify(repository).save(any());

    }


}
