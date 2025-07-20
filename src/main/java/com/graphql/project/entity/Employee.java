package com.graphql.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Employee.findEmployeeByJobTitle", query = "SELECT e FROM Employee e WHERE e.jobTitle = :jobTitle"),
        @NamedQuery(name = "Employee.findEmployeeByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email"),
        @NamedQuery(name = "Employee.findEmployeeAll", query = "SELECT e FROM Employee e LEFT JOIN FETCH e.employees")
})
public class Employee {
    @Id
    @GeneratedValue
    private int employeeId;


    @ManyToOne
    @JsonBackReference
    @JoinColumn( name = "code")
    private Office officeCode;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "reports_to_id")
    private Employee reportsTo;

    private String lastName;
    private String firstName;
    private String extension;
    private String email;
    @Column(
            length = 100
    )
    private String jobTitle;

    @OneToMany(mappedBy = "reportsTo",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Employee> employees;

    @OneToMany(mappedBy = "salesRepEmployeeNum",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Customer> customers;

    public Employee(int employeeId, Office officeCode,
                    Employee reportsTo,
                    String lastName,
                    String firstName,
                    String extension, String email,
                    String jobTitle, List<Employee> employees,
                    List<Customer> customers) {
        this.employeeId = employeeId;
        this.officeCode = officeCode;
        this.reportsTo = reportsTo;
        this.lastName = lastName;
        this.firstName = firstName;
        this.extension = extension;
        this.email = email;
        this.jobTitle = jobTitle;
        this.employees = employees;
        this.customers = customers;
    }

    public Employee(Office OfficeCode, Employee reportsTo, String lastName, String firstName, String extension, String email, String jobTitle) {
        this.officeCode = OfficeCode;
        this.reportsTo = reportsTo;
        this.lastName = lastName;
        this.firstName = firstName;
        this.extension = extension;
        this.email = email;
        this.jobTitle = jobTitle;
    }

    public Employee() {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Employee getReportsTo() {
        return reportsTo;
    }

    public void setReportsTo(Employee reportsTo) {
        this.reportsTo = reportsTo;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Office getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(Office officeCode) {
        this.officeCode = officeCode;
    }
}
