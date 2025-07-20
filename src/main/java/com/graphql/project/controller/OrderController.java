package com.graphql.project.controller;

import com.graphql.project.dtos.CreateOrder;
import com.graphql.project.entity.Customer;
import com.graphql.project.entity.Order;
import com.graphql.project.entity.Shippers;
import com.graphql.project.entity.Store;
import com.graphql.project.service.orderService.OrderService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @QueryMapping
    public List<Order> orders(){
        return service.findOrders();
    }

    @QueryMapping
    public Order orderById(@Argument("orderId")int id){
        return service.findOrderById(id);
    }

    @MutationMapping
    public Order createOrders(@Argument("input") CreateOrder dto){
        return service.createOrder(dto);
    }

    @MutationMapping
    public Order deleteOrder(@Argument("orderId") int id){
        return service.deleteOrderById(id);
    }

    @MutationMapping
    public Order updateOrder(@Argument("orderId") int id, @Argument("input") CreateOrder dto){
        return service.updateOrder(id,dto);
    }

    @SchemaMapping(typeName = "Customer",field = "customerOrders")
    public List<Order> resolveCustomerOrders(Customer customer){
        return customer.getCustomerOrders();
    }

    @SchemaMapping(typeName = "Store",field = "storeOrders")
    public List<Order> resolveStoreOrders(Store store){
        return store.getStoreOrders();
    }

    @SchemaMapping(typeName = "Shippers", field = "shipperOrders")
    public List<Order> resolveShipperOrders(Shippers shippers){
        return shippers.getShipperOrders();
    }


}
