package com.graphql.project.service.customerService;

import com.graphql.project.dtos.CreateCustomer;
import com.graphql.project.entity.Customer;
import com.graphql.project.persistance.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository repository, CustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    public List<Customer> findCustomers() {
        return repository.findAll();
    }

    public Customer findCustomerById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer with id " + id + "not found"));
    }

    public Customer createCustomer(CreateCustomer dto) {
        return repository.save(mapper.customerDtoToCustomer(dto));
    }

    public Customer deleteCustomerById(int id) {
        Customer customer = findCustomerById(id);
        repository.deleteById(id);
        return customer;
    }

    public Customer updateCustomer(int id, CreateCustomer dto) {
        Customer customer = findCustomerById(id);
        customer = mapper.customerDtoToCustomer(dto);
        return repository.save(customer);
    }
}
