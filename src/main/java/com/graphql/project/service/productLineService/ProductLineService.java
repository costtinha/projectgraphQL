package com.graphql.project.service.productLineService;

import com.graphql.project.dtos.CreateProductLine;
import com.graphql.project.entity.ProductLine;
import com.graphql.project.persistance.ProductLineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductLineService {
    private final ProductLineRepository repository;
    private final ProductLineMapper mapper;

    public ProductLineService(ProductLineRepository repository, ProductLineMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ProductLine> findProductLines() {
        return repository.findProductLineAll();
    }

    public ProductLine findProductLinesById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Line with id "+ id + " not found"));
    }

    public ProductLine createProductLine(CreateProductLine dto) {
        return repository.save(mapper.productLineDtoToProductLine(dto));
    }

    public ProductLine deleteProductLineById(int id) {
        ProductLine productLine = findProductLinesById(id);
        repository.delete(productLine);
        return productLine;
    }

    public ProductLine updateProductLine(int id, CreateProductLine dto) {
        ProductLine productLine = findProductLinesById(id);
        productLine.setImage(dto.image());
        productLine.setDescInHTML(dto.descInHTML());
        productLine.setDescInText(dto.descInText());
        return repository.save(productLine);
    }
}
