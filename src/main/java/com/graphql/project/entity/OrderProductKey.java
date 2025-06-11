package com.graphql.project.entity;

import java.io.Serializable;
import java.util.Objects;

public class OrderProductKey implements Serializable {
    private int orderId;
    private int productCode;

    public OrderProductKey(){

    }

    public OrderProductKey(int orderId, int productCode) {
        this.orderId = orderId;
        this.productCode = productCode;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if( o == null || getClass() != o.getClass()) return false;
        OrderProductKey obj = (OrderProductKey) o;
        return Objects.equals(this.orderId, obj.orderId) &&
                Objects.equals(this.productCode, obj.productCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productCode);
    }
}
