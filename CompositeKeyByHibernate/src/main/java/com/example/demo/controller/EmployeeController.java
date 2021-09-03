package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeIdentity;
import com.example.demo.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String welcome() {
		return "Welcome to Spring boot";
	}

	// Retrieving an Employee Record with the composite primary key
		@RequestMapping(value = "/emp/{employeeId}/{companyId}", method = RequestMethod.GET)
	public Employee getEmployeeByCompositeKey(@PathVariable String employeeId, @PathVariable String companyId) {
			System.out.println(employeeId+" "+companyId);
		Optional<Employee> optionalEmployee = employeeRepository.findById(new EmployeeIdentity(employeeId, companyId));
		return optionalEmployee.get();

	}
	 
		
		@RequestMapping(value = "/emp/{companyId}", method = RequestMethod.GET)
		public List<Employee> getEmployeeByCompanyId(@PathVariable String companyId) {
			List<Employee> optionalEmployee = employeeRepository.findByEmployeeIdentityCompanyId(companyId);

			
			return optionalEmployee;

		}

}