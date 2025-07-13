package com.graphql.project.service.orderService;

import com.graphql.project.dtos.CreateOrder;
import com.graphql.project.entity.Customer;
import com.graphql.project.entity.Order;
import com.graphql.project.entity.Shippers;
import com.graphql.project.entity.Store;
import com.graphql.project.persistance.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final OrderMapper mapper;

    public OrderService(OrderRepository repository, OrderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Order> findOrders() {
        return repository.findOrderAll();
    }

    public Order findOrderById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order with id " + id + " not found"));
    }

    public Order createOrder(CreateOrder dto) {
        return repository.save(mapper.orderDtoToOrder(dto));
    }

    public Order deleteOrderById(int id) {
        Order order = findOrderById(id);
        repository.deleteById(id);
        return order;
    }

    public Order updateOrder(int id, CreateOrder dto) {
        Order order = findOrderById(id);
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
        return repository.save(order);
    }
}
