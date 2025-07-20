package com.graphql.project.dtos;

import com.graphql.project.entity.ProductLine;

import java.math.BigDecimal;

public record CreateProduct(int productLineId,
                            String name,int scale, String vendor,
                            String pdtDescription, int qntyInStock,
                            int buyPrice, String MSRP) {
}
