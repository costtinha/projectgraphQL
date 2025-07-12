package com.graphql.project.service.employeeService;

import com.graphql.project.dtos.CreateEmployee;
import com.graphql.project.entity.Employee;
import com.graphql.project.entity.Office;

import java.util.Collections;

public class EmployeeMapper {
    public Employee employeeDtoToEmployee(CreateEmployee dto){
        Employee employee = new Employee();
        Employee reportsTo = new Employee();
        reportsTo.setEmployeeId(dto.reportsTo());
        employee.setReportsTo(reportsTo);
        employee.setCustomers(Collections.emptyList());
        employee.setEmail(dto.email());
        employee.setExtension(dto.extension());
        employee.setFirstName(dto.firstName());
        employee.setLastName(dto.lastName());
        employee.setJobTitle(dto.jobTitle());
        Office office = new Office();
        office.setCode(dto.officeCode());
        employee.setOfficeCode(office);

        return  employee;
    }
}
