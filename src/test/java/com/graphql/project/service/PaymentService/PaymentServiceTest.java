package com.graphql.project.service.PaymentService;

import com.graphql.project.dtos.CreatePayment;
import com.graphql.project.entity.Payment;
import com.graphql.project.persistance.PaymentRepository;
import com.graphql.project.service.paymentService.PaymentMapper;
import com.graphql.project.service.paymentService.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PaymentServiceTest {
    @Mock
    private PaymentRepository repository;

    @Mock
    private PaymentMapper mapper;

    @InjectMocks
    private PaymentService service;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindPayments(){
        List<Payment> mockedList = List.of(new Payment(),new Payment());
        when(repository.findAll()).thenReturn(mockedList);

        List<Payment> payments = service.findPayments();

        assertEquals(2,payments.size());

        verify(repository).findAll();
    }

    @Test
    void testFindPaymentById_Success(){
        Payment payment = new Payment();
        payment.setCheckNum(1);
        payment.setAmount(new BigDecimal(10));
        when(repository.findById(1)).thenReturn(Optional.of(payment));

        Payment found = service.findPaymentById(1);

        assertNotNull(found);
        assertEquals(new BigDecimal(10),payment.getAmount());
        assertEquals(1,found.getCheckNum());

        verify(repository).findById(1);

    }


    @Test
    void testFindPaymentById_Not_Found(){
        when(repository.findById(99)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.findPaymentById(99));

        assertEquals("Payment with id 99 not found", exception.getMessage());

        verify(repository).findById(99);
    }

    @Test
    void testCreatePayment(){
        CreatePayment paymentDto = new CreatePayment(
                1001,
                "22/07/2025 10:30:00",
                1000
        );
        Payment payment = new Payment();
        payment.setCheckNum(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse("22/07/2025 10:30:00",formatter);
        payment.setPaymentDate(dateTime);

        when(mapper.paymentDtoToPayment(paymentDto)).thenReturn(payment);
        when(repository.save(payment)).thenReturn(payment);

        Payment saved = service.createPayment(paymentDto);

        assertNotNull(saved);
        assertEquals(dateTime,saved.getPaymentDate());
        assertEquals(1,saved.getCheckNum());

        verify(mapper).paymentDtoToPayment(paymentDto);
        verify(repository).save(payment);

    }

    @Test
    void testDeletePayment(){
        Payment payment = new Payment();
        payment.setCheckNum(1);
        payment.setAmount(new BigDecimal(10));

        when(repository.findById(1)).thenReturn(Optional.of(payment));

        Payment deleted = service.deletePayment(1);

        assertEquals(1,deleted.getCheckNum());
        assertEquals(new BigDecimal(10),deleted.getAmount());

        verify(repository).findById(1);
        verify(repository).delete(payment);
    }

    @Test
    void testUpdatePayment(){
        CreatePayment paymentDto = new CreatePayment(
                1001,
                "22/07/2025 10:30:00",
                1000
        );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse("22/07/2025 10:30:00",formatter);
        Payment existed = new Payment();
        existed.setCheckNum(1);

        when(repository.findById(1)).thenReturn(Optional.of(existed));
        when(repository.save(any())).thenReturn(existed);

        Payment updated = service.updatePayment(1,paymentDto);

        assertEquals(1001,updated.getCustomerId().getCustomerId());
        assertEquals(new BigDecimal(1000),updated.getAmount());
        assertEquals(dateTime,updated.getPaymentDate());
        assertEquals(1,updated.getCheckNum());

        verify(repository).findById(1);
        verify(repository).save(existed);
    }

}
