package com.graphql.project.service.paymentService;

import com.graphql.project.dtos.CreatePayment;
import com.graphql.project.entity.Payment;
import com.graphql.project.persistance.PaymentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;

    public PaymentService(PaymentRepository repository, PaymentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Payment> findPayments() {
        return repository.findAll();
    }

    public Payment findPaymentById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment with id "+ id  + " not found"));
    }


    public Payment createPayment(CreatePayment dto) {
        return repository.save(mapper.paymentDtoToPayment(dto));
    }

    public Payment deletePayment(int id) {
        Payment payment = findPaymentById(id);
        repository.delete(payment);
        return payment;
    }

    public Payment updatePayment(int id, CreatePayment dto) {
        Payment payment = findPaymentById(id);
        payment = mapper.paymentDtoToPayment(dto);
        return repository.save(payment);
    }
}
