package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="ORDER_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
	
	@Id
	private int id;
	private String name;
	private int qnt;
	private double price;
	

}
