package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.Payment;
import com.example.demo.common.TransactionRequest;
import com.example.demo.common.TransactionResponse;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/bookOrder" )
	public  TransactionResponse bookOrder(@RequestBody TransactionRequest request) {
		
		return orderService.saveOrder(request);
	}
}
