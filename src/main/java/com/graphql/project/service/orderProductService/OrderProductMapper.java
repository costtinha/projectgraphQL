package com.graphql.project.service.orderProductService;

import com.graphql.project.dtos.CreateOrderProduct;
import com.graphql.project.dtos.UpdateOrderProduct;
import com.graphql.project.entity.Order;
import com.graphql.project.entity.OrderProduct;
import com.graphql.project.entity.OrderProductKey;
import com.graphql.project.entity.Product;
import com.graphql.project.persistance.OrderRepository;
import com.graphql.project.persistance.ProductRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderProductMapper {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderProductMapper(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrderProduct OrderProductDtoToOrderProduct(CreateOrderProduct dto){
        OrderProduct orderProduct = new OrderProduct();
        BigDecimal bigDecimal = new BigDecimal(dto.priceEach());
        orderProduct.setPriceEach(bigDecimal);
        orderProduct.setQty(dto.qty());
        Order order = orderRepository.findById(dto.orderId())
                .orElseThrow(() -> new RuntimeException("Order with id: " + dto.orderId()+" not found"));
        Product product = productRepository.findById(dto.productCode())
                .orElseThrow(() -> new RuntimeException("Product with id "+ dto.productCode()+" not found" ));
        OrderProductKey orderProductKey = new OrderProductKey(order.getOrderId(),product.getProductCode());
        orderProduct.setOrderId(order);
        orderProduct.setProductId(product);
        orderProduct.setOrderProductId(orderProductKey);
        return orderProduct;
    }

}
