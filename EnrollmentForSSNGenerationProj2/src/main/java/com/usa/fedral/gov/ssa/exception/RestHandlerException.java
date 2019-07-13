package com.usa.fedral.gov.ssa.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.usa.fedral.gov.ssa.util.Constant;

/**
 * 
 * @author Mohammed
 * Rest controller Advice to handle Rest exception occur in our application 
 */
@RestController
@ControllerAdvice
public class RestHandlerException {

	@Autowired
	private Constant constant;
	
	/**
	 * method to handle SSN not found exception
	 * @return Error page
	 * 
	 */
	@ExceptionHandler(SSNNotFoundException.class)
	public ResponseEntity<ApiException> ssnNotFoundException() {
		ApiException error=new ApiException(400, constant.properties.get("ssnFailure"));
		return new ResponseEntity<ApiException>(error,HttpStatus.BAD_REQUEST);
	}
}
