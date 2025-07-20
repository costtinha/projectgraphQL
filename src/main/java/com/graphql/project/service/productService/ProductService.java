package com.graphql.project.service.productService;

import com.graphql.project.dtos.CreateProduct;
import com.graphql.project.entity.Product;
import com.graphql.project.entity.ProductLine;
import com.graphql.project.persistance.ProductLineRepository;
import com.graphql.project.persistance.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final ProductLineRepository productLineRepository;

    public ProductService(ProductRepository repository, ProductMapper mapper, ProductLineRepository productLineRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.productLineRepository = productLineRepository;
    }

    public List<Product> findProducts() {
        return repository.findProductAll();
    }

    public Product findProductById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
    }

    public Product createProduct(CreateProduct dto) {
        Product product = mapper.productDtoToProduct(dto);
        ProductLine productLine = productLineRepository.findById(dto.productLineId())
                .orElseThrow(() -> new RuntimeException("ProductLine with id " + dto.productLineId() + " not found"));
        product.setProductLineId(productLine);
        return repository.save(product);
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
        BigDecimal buyPrice = new BigDecimal(dto.buyPrice());
        product.setBuyPrice(buyPrice);
        product.setQntyInStock(dto.qntyInStock());
        product.setPdtDescription(dto.pdtDescription());
        product.setScale(dto.scale());
        product.setVendor(dto.vendor());
        ProductLine productLine = productLineRepository.findById(dto.productLineId())
                .orElseThrow(() -> new RuntimeException("ProductLine with id " + dto.productLineId() + " not found"));
        product.setProductLineId(productLine);
        return repository.save(product);
    }
}
