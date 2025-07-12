package com.graphql.project.controller;

import com.graphql.project.dtos.CreatePayment;
import com.graphql.project.entity.Customer;
import com.graphql.project.entity.Payment;
import com.graphql.project.service.paymentService.PaymentService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PaymentController {
    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }


    @QueryMapping
    public List<Payment> payments(){
        return service.findPayments();
    }

    @QueryMapping
    public Payment paymentById(@Argument("checkNum") int id){
        return service.findPaymentById(id);
    }

    @MutationMapping
    public Payment createPayment(@Argument("CreatePaymentInput") CreatePayment dto){
        return service.createPayment(dto);
    }

    @MutationMapping
    public Payment deletePayment(@Argument("checkNum") int id){
        return service.deletePayment(id);
    }

    @MutationMapping
    public Payment updatePayment(@Argument("checkNum")int id, @Argument("CreatePaymentInput") CreatePayment dto){
        return service.updatePayment(id,dto);
    }





    @SchemaMapping(typeName = "Customer", field = "payments")
    public List<Payment> resolvePayments(Customer customer){
        return customer.getPayments();
    }

}
