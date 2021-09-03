package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
@EnableEurekaClient//Because this is a client service
@SpringBootApplication
public class MicroserviceOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceOrderApplication.class, args);
	}
	
	
	@Bean
	@LoadBalanced //this allows you to use "logical identifiers" for the URLs you pass to the RestTemplate
	//like we are using in our service class -"http://PAYMENT-SERVICE/payment/doPayment" ,here PAYMENT-SERVICE is logical identifier.
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
