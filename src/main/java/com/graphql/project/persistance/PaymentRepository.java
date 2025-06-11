package com.graphql.project.persistance;

import com.graphql.project.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PaymentJpaRepository")
public interface PaymentRepository extends JpaRepository<Payment, String> {
    List<Payment> findPaymentByCustomerId(int customerId);
}
