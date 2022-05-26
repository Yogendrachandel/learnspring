package com.example.demo.contoller;



import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

import com.example.demo.ServiceImpl.BookServiceImpl;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;

@RestController
@RequestMapping("mybook")
public class BookController {

	@Autowired
	private BookServiceImpl bookServiceImpl;
	
	
	@Value("${welcomeMessage}")
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
		bookServiceImpl.addNewBook(book);
		return  ResponseEntity.status(HttpStatus.CREATED).build();
		
	}
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBook(){
		List<Book>list=bookServiceImpl.getAllBookList();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id")int id ){
		Book book=bookServiceImpl.getBookById(id);
		return   ResponseEntity.status(HttpStatus.FOUND).body(book);
	}
	
	
	@GetMapping("/booksByAuthor/{author}")
	public ResponseEntity<List<Book>> getBookByAuthorName(@PathVariable("author")String author ){
		List<Book> book=bookServiceImpl.getAllBooksByAuthor(author);
		return   ResponseEntity.status(HttpStatus.FOUND).body(book);
	}
	
	

	
	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBookById(@PathVariable("id")int id ,@RequestBody Book book){
        System.out.println("Creating Executor Service of two fixed threads...");
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        System.out.println("Creating a Runnable...");
        Runnable runnableTaskOne = () -> {
            System.out.println("Inside : " + Thread.currentThread().getName());
             bookServiceImpl.updateBookById(id,book);
        };
        
        Runnable runnableTaskTwo = () -> {
            System.out.println("Inside : " + Thread.currentThread().getName());
             bookServiceImpl.updateBookById(id,book);
        };

        System.out.println("Submit the task specified by the runnable to the executor service.");
        executorService.execute(runnableTaskOne);
        executorService.execute(runnableTaskTwo);
        
		return  ResponseEntity.status(HttpStatus.OK).build();
		
	}
	
	
	@DeleteMapping("/books/{id}")
	public ResponseEntity<Void> deleteBookById(@PathVariable("id")int id ){
		bookServiceImpl.deleteBookById(id);
		return  ResponseEntity.status(HttpStatus.OK).build();
	}
	
	
	
	
	
}
