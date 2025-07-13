package com.graphql.project.persistance;

import com.graphql.project.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("EmployeeJpaRepository")
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    List<Employee> findEmployeeByJobTitle(String jobTitle);
    Employee findEmployeeByEmail(String email);
    List<Employee> findEmployeeAll();
}
