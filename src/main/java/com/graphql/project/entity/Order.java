package com.graphql.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "\"order\"")
@NamedQueries({
        @NamedQuery(name = "order.findOrderByStatus", query = "SELECT o FROM Order o WHERE o.status = :status")
})
public class Order {

    @Id
    @GeneratedValue
    private int orderId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(
            name = "Id"
    )
    private Customer customerId;
    private LocalDateTime orderDate;
    private LocalDateTime requiredDate;
    private LocalDateTime shippedDate;
    private int status;
    private String comments;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(
            name = "ShipId"
    )
    private Shippers shippingId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(
            name = "StoreId"
    )
    private Store storeId;

    @OneToMany(mappedBy = "OrderId")
    @JsonManagedReference
    private List<Order_Product> orderProducts;

    public Order(int orderId, Customer customerId,
                 LocalDateTime orderDate,
                 LocalDateTime requiredDate,
                 LocalDateTime shippedDate,
                 int status, String comments,
                 Shippers shippingId,
                 Store storeId,
                 List<Order_Product> orderProducts) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.status = status;
        this.comments = comments;
        this.shippingId = shippingId;
        this.storeId = storeId;
        this.orderProducts = orderProducts;
    }

    public Order(Customer customerId, LocalDateTime orderDate,
                 LocalDateTime requiredDate,
                 LocalDateTime shippedDate,
                 int status, String comments,
                 Shippers shipId, Store storeId) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.status = status;
        this.comments = comments;
        shippingId = shipId;
        this.storeId = storeId;
    }


    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(LocalDateTime requiredDate) {
        this.requiredDate = requiredDate;
    }

    public LocalDateTime getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(LocalDateTime shippedDate) {
        this.shippedDate = shippedDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Shippers getShippingId() {
        return shippingId;
    }

    public void setShippingId(Shippers shipId) {
        shippingId = shipId;
    }

    public Store getStoreId() {
        return storeId;
    }

    public void setStoreId(Store storeId) {
        this.storeId = storeId;
    }

    public List<Order_Product> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<Order_Product> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
