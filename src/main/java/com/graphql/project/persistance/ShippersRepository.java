package com.graphql.project.persistance;

import com.graphql.project.entity.Shippers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ShippersJpaRepository")
public interface ShippersRepository extends JpaRepository<Shippers,Integer> {
    List<Shippers> findShippersByCompanyName(String companyName);
}
