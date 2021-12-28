package com.example.course.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.course.services.exceptions.DatabaseException;
import com.example.course.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	//this annotation below says that the method will intercept any exception
	//that the type resourceNotFoundException find, and will handle inside the method.
	@ExceptionHandler(ResourceNotFoundException.class)
	
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request)
	{
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(),status.value(), error,e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);  //to return a custom message
	}
	
	
	@ExceptionHandler(DatabaseException.class)
	
	public ResponseEntity<StandardError> Database(DatabaseException e, HttpServletRequest request)
	{
		String error = "Database error";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(),status.value(), error,e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);  //to return a custom message
	}
	
	

}
