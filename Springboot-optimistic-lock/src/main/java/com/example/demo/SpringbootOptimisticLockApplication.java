package com.example.demo;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;

@SpringBootApplication
public class SpringbootOptimisticLockApplication {
	
	/*
	 * Read about optimistic::
	 * https://blog.mimacom.com/testing-optimistic-locking-handling-spring-boot-jpa/
	 * https://medium.com/slalom-build/optimistically-locking-your-spring-boot-web-services-187662eb8a91 
	 * https://www.baeldung.com/jpa-optimistic-locking
	 */
	@Autowired
	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootOptimisticLockApplication.class, args);
	}
	
	
	@PostConstruct
	public void insertRecordsinH2Db() {
		
		Book book ;
		
		book =new Book("java", "cathy", "tech");
		bookService.save(book);
		
		book =new Book("spring", "Rod", "tech");
		bookService.save(book);
		
		
		book =new Book("Ferrari", "Robin", "Fiction");
		bookService.save(book);
		
		book =new Book("Captain", "Hal", "Comic");
		bookService.save(book);
		
	}

}
