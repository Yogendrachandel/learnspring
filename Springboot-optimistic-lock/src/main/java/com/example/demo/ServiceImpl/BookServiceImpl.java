package com.example.demo.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.BookNotFoundException;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;

@Service
public class BookServiceImpl {

	@Autowired
	private BookService bookservice;

	public void addNewBook(Book book) {
		book = bookservice.save(book);
	}

	public List<Book> getAllBookList() {

		List<Book> bookLists = bookservice.findAll();
		return bookLists;
	}

	public Book getBookById(int id) {
		Book book = bookservice.findById(id).orElseThrow(() -> new IdNotFoundException("Invalid BookId"));
		return book;
	}

	public List<Book> getAllBooksByAuthor(String author) {
		List<Book> authorBookList = bookservice.findAllByAuthor(author);
		return authorBookList;
	}
	
	/*
	 * here two independent thread call the same record simultaneously out of 2 only
	 * one thread will get sucessfully chance to update record and other will get
	 * ObjectOptimisticLockingFailureException
	 */
	public Book updateBookById(int id,Book book ) {
		System.out.println("## update method is going to call ##");
		Book bookExists = null ;
		try {
		    bookExists = bookservice.findById(id).orElseThrow(() -> new BookNotFoundException("Invalid book Id"));
			bookExists.setAuthor(book.getAuthor());
			bookExists.setCategory(book.getCategory());
			bookExists.setTitle(book.getTitle());
			
			return bookservice.save(bookExists);//after this version will incremented by 1.
		
	}catch (ObjectOptimisticLockingFailureException e) {
            System.out.println("##### Exception occured:################## \n");
            System.out.println(e.getCause());
            
            return null;
        }
		
	}


	
	public void deleteBookById(int id) {
		Book book = bookservice.findById(id).orElseThrow(() -> new BookNotFoundException("Invalid book Id"));
		bookservice.delete(book);
	}

}
