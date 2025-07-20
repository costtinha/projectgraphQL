package com.graphql.project.dtos;

public record CreateOrder(Integer customerId,
                          String orderDate,
                          String requiredDate,
                          String shippedDate,
                          int status, String comments,
                          int shipId,
                          int storeId) {
}
