package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.enitity.User;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
public class SpringBootSecurityByDaoApplication {

	@Autowired
	private UserRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityByDaoApplication.class, args);
	}

	
	@PostConstruct
	public void createUser(){
	 List<User> userList=Stream.of(
			 new User("yogendra", new BCryptPasswordEncoder().encode("yogendra1")),
			 new User("john", new BCryptPasswordEncoder().encode("john1")) ).collect(Collectors.toList());
	  
	 repository.saveAll(userList);
	 
	}

}
