package com.graphql.project.dtos;

import com.graphql.project.entity.Employee;

import java.math.BigDecimal;

public record CreateCustomer(Integer salesRepEmployeeNum,
                             String lastName, String firstName,
                             String phone, String address1,
                             String address2, String city,
                             String state, int postalCode,
                             String country,
                             BigDecimal creditLimit) {
}
