package com.graphql.project.persistance;

import com.graphql.project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ProductJpaRepository")
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findProductByVendor(String vendor);
}
