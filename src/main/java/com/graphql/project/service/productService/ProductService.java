package com.graphql.project.service.productService;

import com.graphql.project.dtos.CreateProduct;
import com.graphql.project.entity.Product;
import com.graphql.project.entity.ProductLine;
import com.graphql.project.persistance.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Product> findProducts() {
        return repository.findProductAll();
    }

    public Product findProductById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
    }

    public Product createProduct(CreateProduct dto) {
        return repository.save(mapper.productDtoToProduct(dto));
    }

    public Product deleteProductById(int id) {
        Product product = findProductById(id);
        repository.deleteById(id);
        return product;
    }

    public Product updateProduct(int id, CreateProduct dto) {
        Product product = findProductById(id);
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
        return repository.save(product);
    }
}
