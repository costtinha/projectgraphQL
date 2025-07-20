package com.graphql.project.controller;

import com.graphql.project.dtos.CreateProduct;
import com.graphql.project.entity.Product;
import com.graphql.project.entity.ProductLine;
import com.graphql.project.service.productService.ProductService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @QueryMapping
    public List<Product> products(){
        return service.findProducts();
    }
    @QueryMapping
    public Product productById(@Argument("productCode")int id){
        return service.findProductById(id);
    }

    @MutationMapping
    public Product createProduct(@Argument("input")CreateProduct dto){
        return service.createProduct(dto);
    }

    @MutationMapping
    public Product deleteProduct(@Argument("productCode") int id){
        return service.deleteProductById(id);
    }

    @MutationMapping
    public Product updateProduct(@Argument("productCode")int id,
                                 @Argument("CreateProductInput")CreateProduct dto){
        return service.updateProduct(id,dto);
    }

    @SchemaMapping(typeName = "ProductLine", field = "products")
    public List<Product> resolveProducts(ProductLine productLine){
        return productLine.getProducts();
    }
}
