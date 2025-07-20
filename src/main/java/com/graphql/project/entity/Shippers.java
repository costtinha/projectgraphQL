package com.graphql.project.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Shippers.findShippersByCompanyName",
                query = "SELECT s FROM Shippers s WHERE s.companyName = :companyName"),
        @NamedQuery(name = "Shippers.findShippersAll",
                query = "SELECT s FROM Shippers s LEFT JOIN FETCH s.shipperOrders")
})
public class Shippers {
    @Id
    @GeneratedValue
    private int shipId;
    private String companyName;
    private String phoneNumber;

    @OneToMany(mappedBy = "shipId", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Order> shipperOrders;

    public Shippers(int shipId,
                    String companyName,
                    String phoneNumber,
                    List<Order> shipperOrders) {
        this.shipId = shipId;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.shipperOrders = shipperOrders;
    }

    public Shippers(String companyName, String phoneNumber) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
    }

    public Shippers() {
    }

    public int getShipId() {
        return shipId;
    }

    public void setShipId(int shipId) {
        this.shipId = shipId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Order> getShipperOrders() {
        return shipperOrders;
    }

    public void setShipperOrders(List<Order> shipperOrders) {
        this.shipperOrders = shipperOrders;
    }
}
