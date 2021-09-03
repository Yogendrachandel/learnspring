package com.example.demo.service;



import java.util.List;

import com.example.demo.exception.BookNotFoundException;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.model.Book;

public interface BookService {
	
	
	  void addBook(Book book); 
	  List<Book> getBookByAuthor(String author) throws	  BookNotFoundException; 
	  List<Book> getBookByCategory(String category)throws	  BookNotFoundException; 
	  Book getBookById(int id)throws IdNotFoundException;
	  void deleteBook(int id) throws IdNotFoundException; 
	  List<Book> getAllList();
	 
}
