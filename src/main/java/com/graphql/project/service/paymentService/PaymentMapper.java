package com.graphql.project.service.paymentService;

import com.graphql.project.dtos.CreatePayment;
import com.graphql.project.entity.Customer;
import com.graphql.project.entity.Payment;

public class PaymentMapper {
    public Payment paymentDtoToPayment(CreatePayment dto){
        Payment payment = new Payment();
        payment.setPaymentDate(dto.paymentDate());
        payment.setAmount(dto.amount());
        Customer customer = new Customer();
        customer.setCustomerId(dto.customerId());
        payment.setCustomerId(customer);
        return payment;
    }
}
