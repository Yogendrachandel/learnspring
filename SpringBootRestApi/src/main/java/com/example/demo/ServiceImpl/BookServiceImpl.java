package com.example.demo.ServiceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.exception.BookNotFoundException;
import com.example.demo.exception.IdNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.service.BookService;


@Service
public class BookServiceImpl implements BookService{
	

	List<Book>bookList =new ArrayList();
	
	{	    bookList.add(new Book("java","cathy","tech",10));
	        bookList.add(new Book("Spring","Rod","tech",20));
	        bookList.add(new Book("Ferrari","Robin","Fiction",13));
	        bookList.add(new Book("Captain","Hal","Comic",14));
	}
	
	@Override
	public void addBook(Book book) {
		addBookinList(book);
	}

	@Override
	public List<Book> getBookByAuthor(String author) {
		List<Book>booklist= getBookList().stream().
				            filter((book)->book.getAuthor().equals(author)).collect(Collectors.toList());
		
		if(booklist.isEmpty()) {
			throw new BookNotFoundException("Book Author is Not available");
		}
			return booklist;
		
	}

	@Override
	public List<Book> getBookByCategory(String category) {
		List<Book>booklist= getBookList().stream().
				          filter((book)->book.getCategory().equals(category)).collect(Collectors.toList());
		
		if(booklist.isEmpty()) {
			throw new BookNotFoundException("Book Author is Not available");
		}
			return booklist;
		
	}

	@Override
	public Book getBookById(int id) {
		if(id<=0) {
			throw new RuntimeException("Other Exception occured ");// this is used to call handleOtherException of GlobalExceptionHandler method of our class.
		}
		Optional<Book> optional=getBookList().stream().filter((book)->book.getBookId()==id).findAny();;
		 if(optional.isPresent()) {
			return  optional.get();
		 }
		 else {
			 throw new IdNotFoundException("Invalid BookId");
		 }
		
	}
	
	private List<Book> getBookList(){
		   	        return bookList;
				
	}
	
	private void addBookinList(Book book) {
		bookList.add(book);
	}

	@Override
	public void deleteBook(int id) {
		Optional<Book> optional=getBookList().stream().filter((book)->book.getBookId()==id).findAny();;
		 if(optional.isPresent()) {
			  optional.get();
		 }
		 else {
			 throw new IdNotFoundException("Invalid BookId");
		 }

		Iterator<Book> itr = getBookList().iterator();
		while (itr.hasNext()) {
			Book book = itr.next();
			if (book.getBookId() == id) {
				itr.remove();
			}

		}

	}

	@Override
	public List<Book> getAllList() {
		return getBookList();
	}
	
	

}
