package com.graphql.project.service.productService;

import com.graphql.project.dtos.CreateProduct;
import com.graphql.project.entity.Product;
import com.graphql.project.entity.ProductLine;
import com.graphql.project.persistance.ProductLineRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;

@Component
public class ProductMapper {
    public Product productDtoToProduct(CreateProduct dto){
        Product product = new Product();
        product.setProductOrderProducts(Collections.emptyList());
        product.setMSRP(dto.MSRP());
        product.setName(dto.name());
        BigDecimal buyPrice = new BigDecimal(dto.buyPrice());
        product.setBuyPrice(buyPrice);
        product.setQntyInStock(dto.qntyInStock());
        product.setPdtDescription(dto.pdtDescription());
        product.setScale(dto.scale());
        product.setVendor(dto.vendor());
        return product;
    }
}
