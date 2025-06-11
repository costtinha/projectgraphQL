package com.graphql.project.persistance;

import com.graphql.project.entity.ProductLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ProductLineJpaRepository")
public interface ProductLineRepository extends JpaRepository<ProductLine,Integer> {
    List<ProductLine> findProductLineByImage(@Param("image") String image);
}
