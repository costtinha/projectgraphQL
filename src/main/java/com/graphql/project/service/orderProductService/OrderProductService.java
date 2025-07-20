package com.graphql.project.service.orderProductService;

import com.graphql.project.dtos.CreateOrderProduct;
import com.graphql.project.dtos.UpdateOrderProduct;
import com.graphql.project.entity.OrderProduct;
import com.graphql.project.entity.OrderProductKey;
import com.graphql.project.persistance.OrderProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductService {
    private final OrderProductRepository repository;
    private final OrderProductMapper mapper;

    public OrderProductService(OrderProductRepository repository, OrderProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<OrderProduct> findOrderProducts() {
        return repository.findAll();
    }

    public OrderProduct findOrderProductById(int orderID,int productCode) {
        OrderProductKey key = new OrderProductKey(orderID,productCode);
        return repository.findById(key)
                .orElseThrow(() -> new RuntimeException("Order product with id orderId: "
                        + orderID + " productCode: "+ productCode +
                        " not found"));
    }


    public OrderProduct createOrderProduct(CreateOrderProduct dto) {
        return repository.save(mapper.OrderProductDtoToOrderProduct(dto));
    }

    public OrderProduct deleteOrderProductById(int orderID, int productCode) {
        OrderProduct op = findOrderProductById(orderID,productCode);
        repository.delete(op);
        return op;
    }

    public OrderProduct updateOrderProduct(int orderId, int productCode, UpdateOrderProduct dto) {
        OrderProduct orderProduct = findOrderProductById(orderId,productCode);
        orderProduct.setQty(dto.qnty());
        orderProduct.setPriceEach(dto.priceEach());
        return repository.save(orderProduct);
    }
}
