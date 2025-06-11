package com.graphql.project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NamedQueries({
        @NamedQuery(name="Payment.findPaymentByCustomerId", query = "SELECT p FROM Payment p WHERE p.customerId.Id = :customerId")
})
public class Payment {
    @Id
    private String checknum;

    @ManyToOne
    @JsonBackReference
    @JoinColumn( name = "customerId")
    private Customer customerId;

    private LocalDateTime paymentDate;

    @Column(
            precision = 19,
            scale = 0
    )
    private BigDecimal amount;

    public Payment(String CheckNum, Customer customerId, LocalDateTime paymentDate, BigDecimal amount) {
        this.checknum = CheckNum;
        this.customerId = customerId;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    public Payment() {
    }

    public String getChecknum() {
        return checknum;
    }

    public void setChecknum(String checknum) {
        this.checknum = checknum;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
