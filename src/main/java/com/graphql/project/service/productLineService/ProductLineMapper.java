package com.graphql.project.service.productLineService;

import com.graphql.project.dtos.CreateProductLine;
import com.graphql.project.entity.ProductLine;

import java.util.Collections;

public class ProductLineMapper {
    public ProductLine productLineDtoToProductLine(CreateProductLine dto){
        ProductLine productLine = new ProductLine();
        productLine.setProducts(Collections.emptyList());
        productLine.setImage(dto.image());
        productLine.setDescInHTML(dto.descInHTML());
        productLine.setDescInText(dto.descInText());
        return productLine;
    }
}
