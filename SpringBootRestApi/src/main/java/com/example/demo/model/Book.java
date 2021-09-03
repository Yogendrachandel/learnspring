package com.example.demo.model;

public class Book {
	private String title;
	private String author;
	private String category;

	private int id;

	public Book(String title, String author, String category, int bookId) {
		super();
		this.title = title;
		this.author = author;
		this.category = category;
		this.id = bookId;
	}

	public Book() {
		// TODO Auto-generated constructor stub
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
		return "Book [title=" + title + ", author=" + author + ", category=" + category + ", bookId=" + id + "]";
	}

}
