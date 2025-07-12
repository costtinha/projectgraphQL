package com.graphql.project.service.employeeService;

import com.graphql.project.dtos.CreateEmployee;
import com.graphql.project.entity.Employee;
import com.graphql.project.persistance.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    public EmployeeService(EmployeeRepository repository, EmployeeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public List<Employee> employees() {
        return repository.findAll();
    }

    public Employee employeeById(int id) {
        return repository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Employee with id " + id+ " not Found"));
    }

    public Employee createEmployee(CreateEmployee dto) {
        Employee employee = mapper.employeeDtoToEmployee(dto);
        repository.save(employee);
        return employee;
    }

    public Employee deleteEmployeeById(int id) {
        Employee employee = employeeById(id);
        repository.deleteById(id);
        return employee;
    }

    public Employee updateEmployee(int id, CreateEmployee dto) {
        Employee employee = employeeById(id);
        employee = mapper.employeeDtoToEmployee(dto);
        return repository.save(employee);
    }
}
