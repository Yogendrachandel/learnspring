package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiErrors {

	private String message;
	private List<String>details;
	private HttpStatus httpStatus;
	private LocalDateTime dateTime;
	
	
	
	
	
	public ApiErrors() {
		super();
	}
	public ApiErrors(String message, List<String> details, HttpStatus httpStatus, LocalDateTime dateTime) {
		super();
		this.message = message;
		this.details = details;
		this.httpStatus = httpStatus;
		this.dateTime = dateTime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getDetails() {
		return details;
	}
	public void setDetails(List<String> details) {
		this.details = details;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	@Override
	public String toString() {
		return "ApiErrors [message=" + message + ", details=" + details + ", httpStatus=" + httpStatus + ", dateTime="
				+ dateTime + "]";
	}
	
	
	
	
	
	
}
