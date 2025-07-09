package com.graphql.project.dtos;


public record CreateEmployee(Integer officeCode,
                             Integer reportsTo,
                             String lastName,
                             String firstName,
                             String extension, String email,
                             String jobTitle) {
}
