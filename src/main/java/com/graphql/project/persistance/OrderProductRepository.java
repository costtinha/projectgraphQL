package com.graphql.project.persistance;

import com.graphql.project.entity.OrderProductKey;
import com.graphql.project.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("OrderProductJpaRepository")
public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductKey> {
}
