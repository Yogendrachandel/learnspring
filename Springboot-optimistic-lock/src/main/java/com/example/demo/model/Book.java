package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Table(name="Book")
@Entity
public class Book {
	 @Id
	 @GeneratedValue
	private int id;
	private String title;
	private String author;
	private String category;
	@Version
	private long version;


	public Book(String title, String author, String category) {
		this.title = title;
		this.author = author;
		this.category = category;
	}

	public Book() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getBookId() {
		return id;
	}

	public void setBookId(int bookId) {
		this.id = bookId;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", category=" + category + ", version="
				+ version + "]";
	}

	
}
