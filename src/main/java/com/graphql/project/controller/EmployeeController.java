package com.graphql.project.controller;

import com.graphql.project.dtos.CreateEmployee;
import com.graphql.project.entity.Employee;
import com.graphql.project.entity.Office;
import com.graphql.project.service.employeeService.EmployeeService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @QueryMapping
    public List<Employee> employees(){
        return service.employees();
    }

    @QueryMapping
    public Employee employeeById(@Argument("employeeId") int id){
        return service.employeeById(id);
    }

    @MutationMapping
    public Employee createEmployee(@Argument("CreateEmployeeInput") CreateEmployee dto){
        return service.createEmployee(dto);
    }

    @MutationMapping
    public Employee deleteEmployee(@Argument("employeeId")int id){
        return service.deleteEmployeeById(id);
    }

    @MutationMapping
    public Employee updateEmployee(@Argument("employeeId") int id,@Argument("CreateEmployeeInput") CreateEmployee dto){
        return service.updateEmployee(id,dto);
    }

    @SchemaMapping(typeName = "Office", field = "employees")
    public List<Employee> resolveEmployees(Office office){
        return office.getEmployees();
    }

    @SchemaMapping(typeName = "Employee", field = "employees")
    public List<Employee> resolveReports(Employee employee){
        return employee.getEmployees();
    }
}
