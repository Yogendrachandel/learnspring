package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableEurekaClient//This api gateway is also registered in Eureka Server,so act as a client .
@SpringBootApplication
@EnableHystrix //to see the hystrix stream .Type this localhost:<cloudGatwayPort>/actuator/hystrix.stream
public class CloudApiGatwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudApiGatwayApplication.class, args);
	}

}
