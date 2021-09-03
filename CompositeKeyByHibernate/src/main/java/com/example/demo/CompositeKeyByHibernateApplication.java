package com.example.demo;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeIdentity;
import com.example.demo.repository.EmployeeRepository;

@SpringBootApplication
public class CompositeKeyByHibernateApplication {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CompositeKeyByHibernateApplication.class, args);
	}
	
	
	@PostConstruct
	public void createEmployee() {
		
		       // Insert a new Employee in the database
				Employee employee = new Employee(new EmployeeIdentity("E-123", "D-457"),
						"Rajeev Kumar Singh",
						"rajeev@callicoder.com",
						"+91-9999999999");

				employeeRepository.save(employee);
				
				/*
				 * // Retrieving an Employee Record with the composite primary key
				 * Optional<Employee> optionalEmployee=employeeRepository.findById(new
				 * EmployeeIdentity("E-123", "D-457"));
				 * System.out.println("id===="+optionalEmployee.get().toString()); // Retrieving
				 * all the employees of a particular company
				 * employeeRepository.findByEmployeeIdentityCompanyId("D-457");
				 */
				 
	}

}
