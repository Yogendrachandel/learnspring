package com.example.demo.service;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Book;

public interface BookService  extends JpaRepository<Book, Integer>{
	
	
	List<Book >findAllByAuthor(String authorName);
	 
}
