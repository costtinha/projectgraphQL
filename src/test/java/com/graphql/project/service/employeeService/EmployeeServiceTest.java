package com.graphql.project.service.employeeService;

import com.graphql.project.dtos.CreateEmployee;
import com.graphql.project.entity.Employee;
import com.graphql.project.entity.Office;
import com.graphql.project.persistance.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    @Mock
    private EmployeeMapper mapper;

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService service;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEmployees(){
        List<Employee> mocketList = List.of(new Employee(),new Employee());
        when(repository.findEmployeeAll()).thenReturn(mocketList);
        List<Employee> employees = service.employees();

        assertEquals(2,employees.size());

        verify(repository).findEmployeeAll();
    }

    @Test
    void testCreateEmployee(){
        CreateEmployee dto = new CreateEmployee(1,
                1,"silva","joao","test",
                "test@email","programer");
        Employee employee = new Employee();
        employee.setExtension("test");
        when(mapper.employeeDtoToEmployee(dto)).thenReturn(employee);
        when(repository.save(employee)).thenReturn(employee);

        Employee saved = service.createEmployee(dto);
        assertNotNull(saved);
        assertEquals("test",saved.getExtension());

        verify(mapper).employeeDtoToEmployee(dto);
        verify(repository).save(employee);

    }

    @Test
    void testEmployeeById_Success(){
        Employee existing = new Employee();
        existing.setEmployeeId(1);
        when(repository.findById(1)).thenReturn(Optional.of(existing));

        Employee found = service.employeeById(1);

        assertNotNull(found);
        assertEquals(1,found.getEmployeeId());

        verify(repository).findById(1);
    }

    @Test
    void testEmployeeById_Not_Found(){
        when(repository.findById(99)).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class,() -> service.employeeById(99));
        assertEquals("Employee with id 99 not Found", exception.getMessage());

    }

    @Test
    void testUpdateEmployee(){
        CreateEmployee dto = new CreateEmployee(1,
                1,"silva","joao","test",
                "test@email","programer");
        Employee employee = new Employee();
        employee.setEmployeeId(2);
        when(repository.findById(2)).thenReturn(Optional.of(employee));
        when(repository.save(any())).thenReturn(employee);

        Employee updated = service.updateEmployee(2,dto);

        assertNotNull(updated);
        assertEquals(1,updated.getReportsTo().getEmployeeId());
        assertEquals("silva",updated.getLastName());
        assertEquals("joao",updated.getFirstName());
        assertEquals("test@email",updated.getEmail());
        assertEquals("programer",updated.getJobTitle());
        assertEquals("test",updated.getExtension());

        verify(repository).findById(2);
        verify(repository).save(any());


    }

    @Test
    void testDeleteEmployeeById(){
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setJobTitle("tester");
        when(repository.findById(1)).thenReturn(Optional.of(employee));

        Employee deleted = service.deleteEmployeeById(1);

        assertNotNull(deleted);
        assertEquals(1,deleted.getEmployeeId());
        assertEquals("tester",employee.getJobTitle());

        verify(repository).findById(1);
        verify(repository).deleteById(1);
    }
}
