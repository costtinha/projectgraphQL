package com.graphql.project.service.orderService;

import com.graphql.project.dtos.CreateOrder;
import com.graphql.project.entity.Customer;
import com.graphql.project.entity.Order;
import com.graphql.project.entity.Shippers;
import com.graphql.project.entity.Store;

import java.util.Collections;

public class OrderMapper {
    public Order orderDtoToOrder(CreateOrder dto){
        Order order = new Order();
        order.setOrderProducts(Collections.emptyList());
        order.setOrderDate(dto.orderDate());
        order.setComments(dto.comments());
        order.setRequiredDate(dto.requiredDate());
        order.setShippedDate(dto.shippedDate());
        order.setStatus(dto.status());
        Customer customer = new Customer();
        customer.setCustomerId(dto.customerId());
        order.setCustomerId(customer);
        Shippers shippers = new Shippers();
        shippers.setShipId(dto.shippingId());
        order.setShippingId(shippers);
        Store store = new Store();
        store.setStoreId(dto.storeId());
        order.setStoreId(store);
        return order;

    }
}
