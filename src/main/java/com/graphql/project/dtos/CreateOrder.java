package com.graphql.project.dtos;

import com.graphql.project.entity.Shippers;
import com.graphql.project.entity.Store;

import java.time.LocalDateTime;

public record CreateOrder(Integer customerId,
                          LocalDateTime orderDate,
                          LocalDateTime requiredDate,
                          LocalDateTime shippedDate,
                          int status, String comments,
                          int shippingId,
                          int storeId) {
}
