package com.example.demo.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Payment;
import com.example.demo.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository repo;
	
	public Payment doPayment(Payment payment) {
		payment.setPaymentStatus(paymentProcessing());
		payment.setTransactionId(UUID.randomUUID().toString());
		return repo.save(payment);
		
	}
	
	public String paymentProcessing() {
		//This is third party call like paypal ,paytm etc.
		return new Random().nextBoolean()?"success":"fail";
	}

	public Payment getPaymentHistoryByorderId(int orderId) {
		
		return repo.findByOrderId(orderId);
	}
}
