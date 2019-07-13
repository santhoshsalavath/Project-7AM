package com.usa.fedral.gov.ssa.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.usa.fedral.gov.ssa.util.Constant;

/**
 * 
 * @author Mohammed
 * controller Advice to handle all the exception occur in our application 
 */
@Controller
@ControllerAdvice
public class AppHandligException {
	
	@Autowired
	private Constant constant;
	
	/**
	 * method to handle user not store exception
	 * @return Error page
	 * 
	 */
	
	public AppHandligException() {
		System.out.println("AppHandligException.AppHandligException()");
	}
	@ExceptionHandler(UserNotStoreException.class)
	public String userNotStoreException(Model map) {
		System.out.println("AppHandligException.userNotStoreException()");
		map.addAttribute("userErrorMsg",constant.properties.get("errorInsertion"));
		return "customError";
	}

	/**
	 * method to handle state not found exception
	 * @return Error page
	 * 
	 */
	@ExceptionHandler(StateNotFoundException.class)
	public String stateNotFoundException(Model map) {
		System.out.println("AppHandligException.stateNotFoundException()");
		map.addAttribute("stateErrorMsg",constant.properties.get("statesFaliure"));
		return "customError";
	}
	
	/**
	 * method to handle records not found exception
	 * @return Error page
	 * 
	 */
	@ExceptionHandler(NoRecordsFoundException.class)
	public String recordNotFoundException(Model map) {
		System.out.println("AppHandligException.stateNotFoundException()");
		map.addAttribute("recordErrorMsg",constant.properties.get("tableEmpty"));
		return "customError";
	}
}
