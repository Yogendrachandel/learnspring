package com.example.demo.common;

import com.example.demo.entity.Order;

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
public class TransactionResponse {
  private Order order;
  private double amount;
  private String transactionId;
  private String message;
}
