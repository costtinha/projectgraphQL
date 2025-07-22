package com.graphql.project.service.customerService;

import com.graphql.project.dtos.CreateCustomer;
import com.graphql.project.entity.Customer;
import com.graphql.project.entity.Employee;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;

@Component
public class CustomerMapper {
    public Customer customerDtoToCustomer(CreateCustomer dto){
        Customer customer = new Customer();
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
        customer.setCustomerOrders(Collections.emptyList());
        return customer;

    }
}
