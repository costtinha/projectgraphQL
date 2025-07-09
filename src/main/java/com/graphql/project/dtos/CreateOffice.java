package com.graphql.project.dtos;

public record CreateOffice(String city, String phone,
                           String address1,
                           String state, String country,
                           int postalCode, String territory) {
}
