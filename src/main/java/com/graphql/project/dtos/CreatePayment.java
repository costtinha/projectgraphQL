package com.graphql.project.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreatePayment(Integer customerId, String paymentDate, int amount) {
}
