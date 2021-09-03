package com.example.demo.controller;

import java.awt.print.Book;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeController {
	
	
	@Autowired
	private EmployeeService empService;

	
	@GetMapping("/emp")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		List<Employee>list= empService.getAllEmployee();
		if(!list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}
	
	
	@GetMapping("/emp/{id}")
	public ResponseEntity<Employee> getSingleEmployeeDetail(@PathVariable("id")int id){
		Employee emp=empService.getSingleEmployeeDetail();
		return ResponseEntity.status(HttpStatus.FOUND).body(emp);
	}
	
	@PostMapping("/emp/{id}")
	public ResponseEntity<Void> addNewEmployee(@RequestBody Employee emp){
		 empService.addEmployee(emp);
		 return  ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	
	@DeleteMapping("/emp/{id}")
	public ResponseEntity<Void> deleteEmplpyeeById(@PathVariable("id")int id ){
		empService.deleteEmployee(id);
		return  ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
	@PutMapping("/emp/{id}")
	public ResponseEntity<Book> updateEmployeeById(@PathVariable("id")int id ,@RequestBody Employee emp){
		
		return  ResponseEntity.status(HttpStatus.OK).body(bookAvailable);
		
	}
}
