package com.graphql.project.service.customerService;

import com.graphql.project.dtos.CreateCustomer;
import com.graphql.project.entity.Customer;
import com.graphql.project.entity.Employee;
import com.graphql.project.persistance.CustomerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
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
        return repository.findCustomerAll();
    }

    public Customer findCustomerById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer with id " + id + " not found"));
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
        customer.setCity(dto.city());
        customer.setAddress1(dto.address1());
        customer.setAddress2(dto.address2());
        customer.setCountry(dto.country());
        customer.setFirstName(dto.firstName());
        customer.setLastName(dto.lastName());
        customer.setPhone(dto.phone());
        BigDecimal creditLimit = new BigDecimal(dto.creditLimit());
        customer.setCreditLimit(creditLimit);
        customer.setPostalCode(dto.postalCode());
        if(dto.salesRepEmployeeNum() != null) {
            Employee employee = new Employee();
            employee.setEmployeeId(dto.salesRepEmployeeNum());
            customer.setSalesRepEmployeeNum(employee);
        }
        customer.setState(dto.state());
        customer.setPayments(Collections.emptyList());
        return repository.save(customer);
    }
}
