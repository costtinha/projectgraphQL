package com.graphql.project.persistance;

import com.graphql.project.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CustomerJpaRepository")
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    List<Customer> findCustomerByName(@Param("firstName") String firstName, @Param("lastName") String lastName);
    List<Customer> findCustomerByState(String state);
    List<Customer> findCustomerByCity(String city);
}
