package com.graphql.project.controller;

import com.graphql.project.dtos.CreateCustomer;
import com.graphql.project.entity.Customer;
import com.graphql.project.entity.Employee;
import com.graphql.project.service.customerService.CustomerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @QueryMapping
    public List<Customer> customers(){
        return service.findCustomers();
    }

    @QueryMapping
    public Customer customerById(@Argument("customerId") int id){
        return service.findCustomerById(id);
    }

    @MutationMapping
    public Customer createCustomer(@Argument("input")CreateCustomer dto){
        return service.createCustomer(dto);
    }

    @MutationMapping
    public Customer deleteCustomer(@Argument("customerId") int id){
        return service.deleteCustomerById(id);
    }


    @MutationMapping
    public Customer updateCustomer(@Argument("customerId") int id, @Argument("input") CreateCustomer dto){
        return service.updateCustomer(id,dto);
    }


    @SchemaMapping(typeName = "Employee",field = "customers")
    public List<Customer> resolveCustomers(Employee employee){
        return employee.getCustomers();
    }


}
