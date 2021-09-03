package com.example.demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("mybook")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Value("${welcome.message}")
	private String welcomeMsg;
	
	
	@GetMapping("/greet")
	public ResponseEntity<String> getGreeting(){
		String msg=welcomeMsg;
		HttpHeaders header=new HttpHeaders();
		header.add("desc", "Online Book App");
		return new ResponseEntity<String>(msg,header,HttpStatus.OK);
	}
	
	
	@PostMapping("/books")
	public ResponseEntity<Void> addBook(@RequestBody Book book){
		bookService.addBook(book);
		return  ResponseEntity.status(HttpStatus.CREATED).build();
		
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id")int id ){
		Book book=bookService.getBookById(id);
		return   ResponseEntity.status(HttpStatus.FOUND).body(book);
	}
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBook(){
		List<Book>list=bookService.getAllList();
		return ResponseEntity.status(HttpStatus.OK).body(list);
		

	}
	
	@DeleteMapping("/books/{id}")
	public ResponseEntity<Void> deleteBookById(@PathVariable("id")int id ){
		bookService.deleteBook(id);
		return  ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBookById(@PathVariable("id")int id ,@RequestBody Book book){
		Book bookAvailable=bookService.getBookById(id);
		bookAvailable.setAuthor(book.getAuthor());
		bookAvailable.setCategory(book.getCategory());
		bookAvailable.setTitle(book.getTitle());
		
		return  ResponseEntity.status(HttpStatus.OK).body(bookAvailable);
		
	}
	
	
	@GetMapping("/booksByAuthor/{author}")
	public ResponseEntity<List<Book>> getBookByAuthorName(@PathVariable("author")String author ){
		List<Book> book=bookService.getBookByAuthor(author);
		return   ResponseEntity.status(HttpStatus.FOUND).body(book);
	}
	
	
}
