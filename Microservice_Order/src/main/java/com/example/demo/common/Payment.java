package com.example.demo.common;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {
	
	private int paymentId;
	private String paymentStatus;
	private String transactionId;
	private int orderId;
	private double amount;

}
