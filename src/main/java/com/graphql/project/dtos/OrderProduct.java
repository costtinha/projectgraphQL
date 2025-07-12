package com.graphql.project.dtos;

import com.graphql.project.entity.Order;
import com.graphql.project.entity.Product;

import java.math.BigDecimal;

public record OrderProduct(int orderId,
                           int productId,
                           int qty,
                           BigDecimal priceEach) {
}
