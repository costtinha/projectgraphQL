package com.graphql.project.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.graphql.project.entity.Employee;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Office.findOfficeByCity", query = "SELECT o FROM Office o WHERE o.city = :city"),
        @NamedQuery(name = "Office.findOfficeByState", query = "SELECT o FROM Office o WHERE o.state = :state"),
        @NamedQuery(name = "Office.findOfficeByCountry", query = "SELECT o FROM Office o WHERE o.country = :country"),
        @NamedQuery(name = "Office.findAllOffice", query = "SELECT o FROM Office o LEFT JOIN FETCH o.employees")})
public class Office implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int code;
    private String city;
    private String phone;
    private String address1;
    private String address2;
    private String state;
    private String country;
    private int postalCode;
    @Column(
            length = 100
    )
    private String territory;
    @OneToMany(mappedBy = "officeCode",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Employee> employees;

    public Office(int code, String city, String phone,
                  String address1, String address2,
                  String state, String country,
                  int postalCode, String territory,
                  List<Employee> employees) {
        this.code = code;
        this.city = city;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.territory = territory;
        this.employees = employees;
    }

    public Office(String city,
                  String phone,
                  String address1,
                  String address2, String state,
                  String country,
                  int postalCode, String territory) {
        this.city = city;
        this.phone = phone;
        this.address1 = address1;
        this.address2 = address2;
        this.state = state;
        this.country = country;
        this.postalCode = postalCode;
        this.territory = territory;
    }

    public Office() {
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
