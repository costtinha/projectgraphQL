package com.graphql.project.service.orderService;

import com.graphql.project.dtos.CreateOrder;
import com.graphql.project.entity.Customer;
import com.graphql.project.entity.Order;
import com.graphql.project.entity.Shippers;
import com.graphql.project.entity.Store;
import com.graphql.project.persistance.CustomerRepository;
import com.graphql.project.persistance.ShippersRepository;
import com.graphql.project.persistance.StoreRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@Component
public class OrderMapper {
    private final CustomerRepository customerRepository;
    private final ShippersRepository shippersRepository;
    private final StoreRepository storeRepository;

    public OrderMapper(CustomerRepository customerRepository, ShippersRepository shippersRepository, StoreRepository storeRepository) {
        this.customerRepository = customerRepository;
        this.shippersRepository = shippersRepository;
        this.storeRepository = storeRepository;
    }

    public Order orderDtoToOrder(CreateOrder dto){
        validateKeys(dto);
        Order order = new Order();
        order.setOrderProducts(Collections.emptyList());
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
        return order;

    }
    public void validateKeys(CreateOrder dto){
        if (!customerRepository.existsById(dto.customerId())){
            throw new RuntimeException("Customer Id with id "+ dto.customerId() + " does not exist");
        }
        if (!storeRepository.existsById(dto.storeId())){
            throw new RuntimeException("Store Id with id "+ dto.storeId() + " does not exist");
        }
        if (!shippersRepository.existsById(dto.shipId())){
            throw new RuntimeException("Shipping Id with id "+ dto.shipId() + " does not exist");
        }

    }
}
