package com.graphql.project.service.paymentService;

import com.graphql.project.dtos.CreatePayment;
import com.graphql.project.entity.Customer;
import com.graphql.project.entity.Payment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PaymentMapper {
    public Payment paymentDtoToPayment(CreatePayment dto){
        Payment payment = new Payment();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dto.paymentDate(),formatter);
        payment.setPaymentDate(dateTime);
        BigDecimal bigDecimal = new BigDecimal(dto.amount());
        payment.setAmount(bigDecimal);
        Customer customer = new Customer();
        customer.setCustomerId(dto.customerId());
        payment.setCustomerId(customer);
        return payment;
    }
}
