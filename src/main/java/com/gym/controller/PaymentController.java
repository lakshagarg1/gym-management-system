package com.gym.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gym.model.Payment;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final List<Payment> payments = new ArrayList<>();
    private static AtomicLong idCounter = new AtomicLong(0);

    @GetMapping
    public List<Payment> getAllPayments() {
        return payments;
    }

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable Long id) {
        for (Payment payment : payments) {
            if (payment.getId().equals(id)) {
                return payment;
            }
        }
        throw new RuntimeException("Payment not found");
    }

    @PostMapping
    public Payment addPayment(@RequestBody Payment payment) {
        payment.setId(idCounter.incrementAndGet());
        payments.add(payment);
        return payment;
    }

    @PutMapping("/{id}")
    public Payment updatePayment(@PathVariable Long id, @RequestBody Payment paymentDetails) {
        for (Payment payment : payments) {
            if (payment.getId().equals(id)) {
                payment.setMemberId(paymentDetails.getMemberId());
                payment.setAmount(paymentDetails.getAmount());
                payment.setStatus(paymentDetails.getStatus());
                return payment;
            }
        }
        throw new RuntimeException("Payment not found");
    }

    @DeleteMapping("/{id}")
    public String deletePayment(@PathVariable Long id) {
        Iterator<Payment> iterator = payments.iterator();
        while (iterator.hasNext()) {
            Payment payment = iterator.next();
            if (payment.getId().equals(id)) {
                iterator.remove();
                return "Payment deleted successfully";
            }
        }
        return "Payment not found";
    }
}
