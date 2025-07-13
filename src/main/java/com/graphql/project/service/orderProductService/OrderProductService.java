package com.graphql.project.service.orderProductService;

import com.graphql.project.dtos.CreateOrderProduct;
import com.graphql.project.dtos.OrderProductKeyInput;
import com.graphql.project.dtos.UpdateOrderProduct;
import com.graphql.project.entity.OrderProduct;
import com.graphql.project.entity.OrderProductKey;
import com.graphql.project.persistance.OrderProductRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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

    public OrderProduct findOrderProductById(OrderProductKeyInput id) {
        return repository.findById(new OrderProductKey(id.orderId(), id.productCode()))
                .orElseThrow(() -> new RuntimeException("Order product with id orderId: "
                        + id.orderId() + " productCode: "+ id.productCode() +
                        " not found"));
    }


    public OrderProduct createOrderProduct(CreateOrderProduct dto) {
        return repository.save(mapper.OrderProductDtoToOrderProduct(dto));
    }

    public OrderProduct deleteOrderProductById(OrderProductKeyInput id) {
        OrderProduct op = findOrderProductById(id);
        repository.delete(op);
        return op;
    }

    public OrderProduct updateOrderProduct(int orderId, int productCode, UpdateOrderProduct dto) {
        OrderProduct orderProduct = findOrderProductById(new OrderProductKeyInput(orderId,productCode));
        orderProduct.setQty(dto.qnty());
        orderProduct.setPriceEach(dto.priceEach());
        return repository.save(orderProduct);
    }
}
