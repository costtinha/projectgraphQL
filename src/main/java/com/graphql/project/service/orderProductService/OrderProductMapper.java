package com.graphql.project.service.orderProductService;

import com.graphql.project.dtos.CreateOrderProduct;
import com.graphql.project.dtos.UpdateOrderProduct;
import com.graphql.project.entity.Order;
import com.graphql.project.entity.OrderProduct;
import com.graphql.project.entity.OrderProductKey;
import com.graphql.project.entity.Product;

public class OrderProductMapper {
    public OrderProduct OrderProductDtoToOrderProduct(CreateOrderProduct dto){
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setPriceEach(dto.priceEach());
        orderProduct.setQty(dto.qty());
        OrderProductKey orderProductKey = new OrderProductKey(dto.orderId(),dto.productId());
        Order order = new Order();
        order.setOrderId(dto.orderId());
        orderProduct.setOrderId(order);
        Product product = new Product();
        product.setProductCode(dto.productId());
        orderProduct.setProductId(product);
        orderProduct.setOrderProductId(orderProductKey);
        return orderProduct;
    }

}
