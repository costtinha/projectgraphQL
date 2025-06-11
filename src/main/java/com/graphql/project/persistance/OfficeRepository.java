package com.graphql.project.persistance;
import com.graphql.project.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("officeJpaRepository")
public interface OfficeRepository  extends JpaRepository<Office, Integer> {
    List<Office> findOfficeByCity(String city);
    List<Office> findOfficeByState(String state);
    List<Office> findOfficeByCountry(String country);
}
