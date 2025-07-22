package com.graphql.project.service.orderService;

import com.graphql.project.dtos.CreateOrder;
import com.graphql.project.entity.Order;
import com.graphql.project.persistance.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderServiceTest {
    @Mock
    private OrderRepository repository;

    @Mock
    private OrderMapper mapper;

    @InjectMocks
    private OrderService service;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindOrders(){
        List<Order> mockedList = List.of(new Order(),new Order());
        when(repository.findOrderAll()).thenReturn(mockedList);

        List<Order> orderList = service.findOrders();

        assertEquals(2,orderList.size());

        verify(repository).findOrderAll();
    }

    @Test
    void testFindOrderById_Success(){
        Order order = new Order();
        order.setOrderId(1);
        order.setComments("positivo");
        when(repository.findById(1)).thenReturn(Optional.of(order));

        Order found = service.findOrderById(1);

        assertEquals(1,found.getOrderId());
        assertEquals("positivo",found.getComments());

        verify(repository).findById(1);
    }

    @Test
    void testFindOrderById_Not_Found(){
        when(repository.findById(99)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> service.findOrderById(99));

        assertEquals("Order with id 99 not found",exception.getMessage());

        verify(repository).findById(99);

    }

    @Test
    void testCreateOrder(){
        CreateOrder orderDto = new CreateOrder(
                1001,
                "22/07/2025 10:30:00",
                "29/07/2025 00:00:00",
                null,
                1,
                "Urgent order",
                1,
                1
        );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime orderDateTime = LocalDateTime.parse("22/07/2025 10:30:00",formatter);
        LocalDateTime requiredDateTime = LocalDateTime.parse("29/07/2025 00:00:00",formatter);

        Order order = new Order();

        order.setOrderId(1);
        order.setStatus(1);

        when(mapper.orderDtoToOrder(orderDto)).thenReturn(order);
        when(repository.save(order)).thenReturn(order);

        Order saved = service.createOrder(orderDto);

        assertEquals(1,saved.getOrderId());
        assertEquals(1,saved.getStatus());

        verify(mapper).orderDtoToOrder(orderDto);
        verify(repository).save(order);

    }

    @Test
    void testDeleteOrderById(){
        Order order = new Order();
        order.setOrderId(1);
        order.setStatus(1);
        when(repository.findById(1)).thenReturn(Optional.of(order));

        Order deleted = service.deleteOrderById(1);

        assertEquals(1,deleted.getStatus());
        assertEquals(1,deleted.getOrderId());

        verify(repository).findById(1);
        verify(repository).deleteById(1);
    }

    @Test
    void testUpdateOrder(){
        CreateOrder orderDto = new CreateOrder(
                1001,
                "22/07/2025 10:30:00",
                "29/07/2025 00:00:00",
                "25/07/2025 00:00:00",
                1,
                "Urgent order",
                1,
                1
        );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime orderDateTime = LocalDateTime.parse("22/07/2025 10:30:00",formatter);
        LocalDateTime requiredDateTime = LocalDateTime.parse("29/07/2025 00:00:00",formatter);
        LocalDateTime shippedDate = LocalDateTime.parse("25/07/2025 00:00:00",formatter);
        Order order = new Order();
        order.setOrderId(1);
        when(repository.findById(1)).thenReturn(Optional.of(order));
        when(repository.save(order)).thenReturn(order);

        Order returned = service.updateOrder(1,orderDto);

        assertNotNull(returned);

        assertEquals(orderDateTime,returned.getOrderDate());
        assertEquals(requiredDateTime,returned.getRequiredDate());
        assertEquals(shippedDate,returned.getShippedDate());
        assertEquals("Urgent order",returned.getComments());
        assertEquals(1,returned.getStatus());
        assertEquals(1,returned.getShipId().getShipId());
        assertEquals(1,returned.getStoreId().getStoreId());

        verify(repository).findById(1);
        verify(repository).save(order);


    }
}
