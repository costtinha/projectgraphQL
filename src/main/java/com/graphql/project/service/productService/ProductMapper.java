package com.graphql.project.service.productService;

import com.graphql.project.dtos.CreateProduct;
import com.graphql.project.entity.Product;
import com.graphql.project.entity.ProductLine;

import java.util.Collections;

public class ProductMapper {
    public Product productDtoToProduct(CreateProduct dto){
        Product product = new Product();
        product.setProductOrderProducts(Collections.emptyList());
        product.setMSRP(dto.MSRP());
        product.setName(dto.name());
        product.setBuyPrice(dto.buyPrice());
        product.setQntyInStock(dto.qntyInStock());
        product.setPdtDescription(dto.pdtDescription());
        product.setScale(dto.scale());
        product.setVendor(dto.vendor());
        ProductLine productLine = new ProductLine();
        productLine.setProductLineId(dto.productLine_id());
        product.setProductLineId(productLine);

        return product;
    }
}
