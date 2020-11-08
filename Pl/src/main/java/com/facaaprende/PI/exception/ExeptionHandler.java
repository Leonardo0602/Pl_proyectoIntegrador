package com.facaaprende.PI.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ExeptionHandler extends ResponseEntityExceptionHandler{
			
	@ExceptionHandler(NotFoundModelException.class)
	public final ResponseEntity<ExcptionWrapper> manejadorModelException(NotFoundModelException ex,
			WebRequest request) {
		   ExcptionWrapper er = new ExcptionWrapper(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), ex.getMessage(), request.getDescription(false));
		   return new ResponseEntity<ExcptionWrapper>(er, HttpStatus.NOT_FOUND);					
	}	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {				
		ExcptionWrapper er = new ExcptionWrapper(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExcptionWrapper er = new ExcptionWrapper(HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED.toString(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(er, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExcptionWrapper er = new ExcptionWrapper(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);		
	}	
		
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
				HttpStatus status, WebRequest request) {
		ExcptionWrapper er = new ExcptionWrapper(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), ex.getMessage(), request.getDescription(false));
			return new ResponseEntity<Object>(er, HttpStatus.NOT_FOUND);
		}	
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExcptionWrapper er = new ExcptionWrapper(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString(), ex.getMessage(), request.getDescription(false));	
		return new ResponseEntity<Object>(er, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}		
	
	@ExceptionHandler(ArgumentRequiredException.class)
	public final ResponseEntity<ExcptionWrapper> manejarArgumentRequiredException(ArgumentRequiredException ex,
			WebRequest request) {
		   ExcptionWrapper er = new ExcptionWrapper(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(), ex.getMessage(), request.getDescription(false));
		   return new ResponseEntity<ExcptionWrapper>(er, HttpStatus.BAD_REQUEST);					
	}		
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ExcptionWrapper> manejarConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {
		   ExcptionWrapper er = new ExcptionWrapper(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(), ex.getMessage(), request.getDescription(false));
		   return new ResponseEntity<ExcptionWrapper>(er, HttpStatus.BAD_REQUEST);					
	}		

	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExcptionWrapper> manejarException(Exception ex,
			WebRequest request) {		   	
		   ExcptionWrapper er = new ExcptionWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage(), request.getDescription(false));
		   return new ResponseEntity<ExcptionWrapper>(er, HttpStatus.INTERNAL_SERVER_ERROR);					
	}		

}
