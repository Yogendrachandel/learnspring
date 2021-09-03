package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Employee;

public interface EmployeeService {

	  List<Employee> getAllEmployee();
	  Employee getSingleEmployeeDetail();
	  void addEmployee(Employee employee);
	  void deleteEmployee(int  id);
	  void updateEmployee(Employee employee);
}
