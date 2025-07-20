package com.graphql.project.service.orderService;

import com.graphql.project.dtos.CreateOrder;
import com.graphql.project.entity.Customer;
import com.graphql.project.entity.Order;
import com.graphql.project.entity.Shippers;
import com.graphql.project.entity.Store;
import com.graphql.project.persistance.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public Order createOrder(CreateOrder dto)
    {
        try {


            Order mappedOrder = mapper.orderDtoToOrder(dto);
            System.out.println("Teste de mapeamento " + mappedOrder.getOrderId() + " " + mappedOrder.getComments());
            Order savedOrder = repository.save(mappedOrder);
            if (savedOrder == null) {
                throw new RuntimeException("Failed to save order: repository is null");

            }
            return savedOrder;
        }
        catch (Exception e){
            throw new RuntimeException("Error creating order: " + e.getMessage(), e);
        }
    }

    public Order deleteOrderById(int id) {
        Order order = findOrderById(id);
        repository.deleteById(id);
        return order;
    }

    public Order updateOrder(int id, CreateOrder dto) {
        Order order = findOrderById(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dto.orderDate(),formatter);
        order.setOrderDate(dateTime);
        order.setComments(dto.comments());
        dateTime = LocalDateTime.parse(dto.requiredDate(), formatter);
        order.setRequiredDate(dateTime);
        dateTime = LocalDateTime.parse(dto.shippedDate(), formatter);
        order.setShippedDate(dateTime);
        order.setStatus(dto.status());
        Customer customer = new Customer();
        customer.setCustomerId(dto.customerId());
        order.setCustomerId(customer);
        Shippers shippers = new Shippers();
        shippers.setShipId(dto.shipId());
        order.setShipId(shippers);
        Store store = new Store();
        store.setStoreId(dto.storeId());
        order.setStoreId(store);
        return repository.save(order);
    }
}
