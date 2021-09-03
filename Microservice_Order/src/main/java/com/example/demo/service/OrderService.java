package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.common.Payment;
import com.example.demo.common.TransactionRequest;
import com.example.demo.common.TransactionResponse;
import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;

@Service
@RefreshScope//I use the @RefreshScope annotation so that if any property value changes in configServer that can be propagated without restarting the application  
public class OrderService {

	@Autowired
	private OrderRepository repo;
	
	
	@Autowired
	@Lazy
	private RestTemplate restTemplate;
	
	@Value("${microservice.payment-service.endpoints.endpoint.uri}")
	private String ENDPOINT_URL;;//this payment  endpoint picked from the github with the help of springCloudServer
	
	
	public TransactionResponse saveOrder(TransactionRequest request) {
		String response="";
		Order order=request.getOrder();
		Payment payment=request.getPayment();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());
		
		//APi Call
		//Payment paymentResponse=restTemplate.postForObject("http://localhost:9191/payment/doPayment", payment, Payment.class); 
		//The PAYMENT-SERVICE is now register with Eureka server now no need to hardcode as above line
		//Payment paymentResponse=restTemplate.postForObject("http://PAYMENT-SERVICE/payment/doPayment", payment, Payment.class);
		//Now the payment  endpoint pick from GitHub with the help of spring Cloud server
		System.out.println("Endpoint="+ENDPOINT_URL);
		Payment paymentResponse=restTemplate.postForObject(ENDPOINT_URL, payment, Payment.class);
		response=paymentResponse.getPaymentStatus().equals("success")?"payment processing succesful and order placed ":"there is failure in payment Api,order is added to cart";
		repo.save(order);
		return new TransactionResponse(order,order.getPrice(),paymentResponse.getTransactionId(),response);
	}
}
