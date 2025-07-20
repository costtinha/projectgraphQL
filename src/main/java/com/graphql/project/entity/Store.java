package com.graphql.project.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedQueries(
        {
                @NamedQuery(name = "Store.findStoreByName", query = "SELECT s FROM Store s WHERE s.storeName = :storeName"),
                @NamedQuery(name = "Store.findStoreAll",
                        query = "SELECT s FROM Store s LEFT JOIN FETCH s.storeOrders")
        }
)
public class Store {
    @Id
    @GeneratedValue
    private int storeId;
    private String storeName;

    @OneToMany(mappedBy = "storeId", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Order> storeOrders;

    public Store(int storeId, String storeName, List<Order> storeOrders) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.storeOrders = storeOrders;
    }

    public Store(String storeName) {
        this.storeName = storeName;
    }

    public Store() {
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public List<Order> getStoreOrders() {
        return storeOrders;
    }

    public void setStoreOrders(List<Order> storeOrders) {
        this.storeOrders = storeOrders;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
