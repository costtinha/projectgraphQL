package com.graphql.project.controller;

import com.graphql.project.dtos.CreateOrderProduct;
import com.graphql.project.dtos.OrderProductKeyInput;
import com.graphql.project.dtos.UpdateOrderProduct;
import com.graphql.project.entity.Order;
import com.graphql.project.entity.OrderProduct;
import com.graphql.project.entity.Product;
import com.graphql.project.service.orderProductService.OrderProductService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class OrderProductController {
    private final OrderProductService service;

    public OrderProductController(OrderProductService service) {
        this.service = service;
    }
    @QueryMapping
    public List<OrderProduct> orderProducts(){
        return service.findOrderProducts();
    }
    @QueryMapping
    public OrderProduct orderProductById(@Argument("orderId") int orderId, @Argument("productCode")int productCode){
        return service.findOrderProductById(orderId, productCode);
    }

    @MutationMapping
    public OrderProduct createOrderProduct(@Argument("input") CreateOrderProduct dto){
        return service.createOrderProduct(dto);
    }

    @MutationMapping
    public OrderProduct deleteOrderProduct(@Argument("orderId") int orderId, @Argument("productCode")int productCode){
        return service.deleteOrderProductById(orderId,productCode);
    }

    @MutationMapping
    public OrderProduct updateOrderProduct(@Argument("orderId")int orderId,
                                           @Argument("productCode")int productCode,
                                           @Argument("input")UpdateOrderProduct dto){
        return service.updateOrderProduct(orderId,productCode,dto);
    }

    @SchemaMapping(typeName = "Product", field = "productOrderProducts")
    public List<OrderProduct> resolveProductOrderProducts(Product product){
        return product.getProductOrderProducts();
    }

    @SchemaMapping(typeName = "Order",field = "orderProducts")
    public List<OrderProduct> resolveOrderProducts(Order order){
        return order.getOrderProducts();
    }
}
