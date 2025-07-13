package com.graphql.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class OrderProduct {
    @EmbeddedId
    private OrderProductKey orderProductId;

    @ManyToOne
    @MapsId("orderId")
    @JsonBackReference
    @JoinColumn(
            name = "Id"
    )
    private Order orderId;

    @ManyToOne
    @MapsId("productCode")
    @JsonBackReference
    @JoinColumn(
            name = "Code"
    )
    private Product productId;

    private int qty;

    @Column(
            precision = 19,
            scale = 0
    )
    private BigDecimal priceEach;

    public OrderProduct(OrderProductKey orderProductId,
                        Order orderId,
                        Product productId,
                        int qty,
                        BigDecimal priceEach) {
        this.orderProductId = orderProductId;
        this.orderId = orderId;
        this.productId = productId;
        this.qty = qty;
        this.priceEach = priceEach;
    }

    public OrderProduct() {
    }

    public OrderProductKey getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(OrderProductKey orderProductId) {
        this.orderProductId = orderProductId;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(BigDecimal priceEach) {
        this.priceEach = priceEach;
    }
}
