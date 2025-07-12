package com.graphql.project.controller;

import com.graphql.project.dtos.CreateProductLine;
import com.graphql.project.entity.ProductLine;
import com.graphql.project.service.productLineService.ProductLineService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductLineController {
    private final ProductLineService service;

    public ProductLineController(ProductLineService service) {
        this.service = service;
    }

    @QueryMapping
    public List<ProductLine> productLines(){
        return service.findProductLines();
    }

    @QueryMapping
    public ProductLine productLineById(@Argument("productLineId") int id){
        return service.findProductLinesById(id);
    }

    @MutationMapping
    public ProductLine createProductLine(@Argument("CreateProductLineInput")CreateProductLine dto){
        return service.createProductLine(dto);
    }

    @MutationMapping
    public ProductLine deleteProductLine(@Argument("productLineId")int id){
        return service.deleteProductLineById(id);
    }

    @MutationMapping
    public ProductLine updateProductLine(@Argument("productLineId")int id,
                                         @Argument("CreateProductLineInput")
                                         CreateProductLine dto){
        return service.updateProductLine(id,dto);
    }
}
