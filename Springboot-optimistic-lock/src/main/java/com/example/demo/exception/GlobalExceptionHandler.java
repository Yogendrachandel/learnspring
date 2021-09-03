package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.model.ApiErrors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String msg =ex.getMessage();
		List<String>details=new ArrayList<>();
		details.add("Request Method not supported");
		ApiErrors apiError=new ApiErrors(msg,details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(apiError);
	}

	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

 
		String msg =ex.getMessage();
		List<String>details=new ArrayList<>();
		details.add("Mediatype not supported");
		ApiErrors apiError=new ApiErrors(msg,details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(apiError);
	}



	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		String msg =ex.getMessage();
		List<String>details=new ArrayList<>();
		details.add("Path variable is Missing..");
		ApiErrors apiError=new ApiErrors(msg,details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		

		String msg =ex.getMessage();
		List<String>details=new ArrayList<>();
		details.add("Request parameter is missing.");
		ApiErrors apiError=new ApiErrors(msg,details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(apiError);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		

		String msg =ex.getMessage();
		List<String>details=new ArrayList<>();
		details.add("Mismatch of type");
		ApiErrors apiError=new ApiErrors(msg,details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(apiError);
		
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		

		String msg =ex.getMessage();
		List<String>details=new ArrayList<>();
		details.add("Request body is not readable.");
		ApiErrors apiError=new ApiErrors(msg,details,status,LocalDateTime.now());
		return ResponseEntity.status(status).body(apiError);
		
	}
	
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException ex){
		
		String msg =ex.getMessage();
		List<String>details=new ArrayList<>();
		details.add("Book Not found ");
		ApiErrors apiError=new ApiErrors(msg,details,HttpStatus.BAD_REQUEST,LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<Object> handleIdNotFoundException(IdNotFoundException ex){
		
		String msg =ex.getMessage();
		List<String>details=new ArrayList<>();
		details.add("Book Id  Not found ");
		ApiErrors apiError=new ApiErrors(msg,details,HttpStatus.NOT_FOUND,LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleOtherException(Exception ex){
		
		String msg =ex.getMessage();
		List<String>details=new ArrayList<>();
		details.add("Other Exception ");
		details.add(ex.getMessage());
		ApiErrors apiError=new ApiErrors(msg,details,HttpStatus.BAD_REQUEST,LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
}
