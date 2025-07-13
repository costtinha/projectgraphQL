package com.graphql.project.service.productService;

import com.graphql.project.dtos.CreateProduct;
import com.graphql.project.entity.Product;
import com.graphql.project.persistance.ProductRepository;
import org.springframework.stereotype.Service;

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
        return repository.findAll();
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
        product = mapper.productDtoToProduct(dto);
        return repository.save(product);
    }
}
