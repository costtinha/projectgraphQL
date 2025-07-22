package com.graphql.project.service.customerService;

import com.graphql.project.dtos.CreateCustomer;
import com.graphql.project.entity.Customer;
import com.graphql.project.persistance.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {
    @Mock
    private CustomerRepository repository;

    @Mock
    private CustomerMapper mapper;

    @InjectMocks
    private CustomerService service;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindCustomers(){
        List<Customer> mockedList = List.of(new Customer(),new Customer());
        when(repository.findCustomerAll()).thenReturn(mockedList);

        List<Customer> returned = service.findCustomers();
        assertEquals(2,returned.size());
        verify(repository).findCustomerAll();
    }


    @Test
    void testFindCustomerById_Success(){
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setState("SP");
        when(repository.findById(1)).thenReturn(Optional.of(customer));

        Customer returned = service.findCustomerById(1);
        assertNotNull(returned);
        assertEquals(1,returned.getCustomerId());
        assertEquals("SP",returned.getState());

        verify(repository).findById(1);
    }

    @Test
    void testFindCustomerById_Not_Found(){
        when(repository.findById(99)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.findCustomerById(99));

        assertEquals("Customer with id 99 not found",exception.getMessage());

        verify(repository).findById(99);
    }

    @Test
    void testCreateCustomer(){
        CreateCustomer customerDto = new CreateCustomer(
                101,
                "Smith",
                "John",
                "+1-555-987-6543",
                "123 Main St",
                "Apt 4B",
                "New York",
                "NY",
                10001,
                "USA",
                50000
        );
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setLastName("Smith");
        when(mapper.customerDtoToCustomer(customerDto)).thenReturn(customer);
        when(repository.save(customer)).thenReturn(customer);

        Customer savedCustomer = service.createCustomer(customerDto);

        assertNotNull(savedCustomer);
        assertEquals(1,savedCustomer.getCustomerId());
        assertEquals("Smith",savedCustomer.getLastName());

        verify(mapper).customerDtoToCustomer(customerDto);
        verify(repository).save(customer);


    }

    @Test
    void testDeleteCustomerById(){
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setCountry("Brasil");
        when(repository.findById(1)).thenReturn(Optional.of(customer));

        Customer deleted = service.deleteCustomerById(1);

        assertNotNull(deleted);

        assertEquals(1,deleted.getCustomerId());
        assertEquals("Brasil",deleted.getCountry());
        verify(repository).findById(1);
        verify(repository).deleteById(1);
    }

    @Test
    void testUpdateCustomer(){
        CreateCustomer customerDto = new CreateCustomer(
                101,
                "Smith",
                "John",
                "+1-555-987-6543",
                "123 Main St",
                "Apt 4B",
                "New York",
                "NY",
                10001,
                "USA",
                50000
        );
        Customer existing = new Customer();
        existing.setCustomerId(1);

        when(repository.findById(1)).thenReturn(Optional.of(existing));
        when(repository.save(any())).thenReturn(existing);

        Customer updated = service.updateCustomer(1,customerDto);
        BigDecimal creditLimit = new BigDecimal(50000);

        assertEquals(101,updated.getSalesRepEmployeeNum().getEmployeeId());
        assertEquals("Smith",updated.getLastName());
        assertEquals("John", updated.getFirstName());
        assertEquals("+1-555-987-6543",updated.getPhone());
        assertEquals("123 Main St",updated.getAddress1());
        assertEquals("Apt 4B", updated.getAddress2());
        assertEquals("New York",updated.getCity());
        assertEquals("NY",updated.getState());
        assertEquals(10001,updated.getPostalCode());
        assertEquals("USA",updated.getCountry());

        verify(repository).findById(1);
        verify(repository).save(any());



    }


}
