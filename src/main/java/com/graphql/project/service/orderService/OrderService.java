package com.graphql.project.service.orderService;

import com.graphql.project.dtos.CreateOrder;
import com.graphql.project.entity.Order;
import com.graphql.project.persistance.OrderRepository;
import org.springframework.stereotype.Service;

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
        return repository.findAll();
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
        order = mapper.orderDtoToOrder(dto);
        return repository.save(order);
    }
}
