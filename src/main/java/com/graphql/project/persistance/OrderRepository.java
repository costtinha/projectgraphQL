package com.graphql.project.persistance;


import com.graphql.project.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("OrderJpaRepository")
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findOrderByStatus(@Param("status") int status);
    List<Order> findOrderAll();
}
