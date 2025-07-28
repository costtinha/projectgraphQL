package com.graphql.project.dtos;

import com.graphql.project.entity.Order;
import com.graphql.project.entity.OrderProductKey;
import com.graphql.project.entity.Product;

import java.math.BigDecimal;

public record CreateOrderProduct(int orderId,
                                 int productCode,
                                 int qty,
                                 int priceEach) {
}
