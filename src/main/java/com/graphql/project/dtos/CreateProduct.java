package com.graphql.project.dtos;

import com.graphql.project.entity.ProductLine;

import java.math.BigDecimal;

public record CreateProduct(ProductLine productLine_id,
                            String name,int scale, String vendor,
                            String pdtDescription, int qntyInStock,
                            BigDecimal buyPrice, String MSRP) {
}
