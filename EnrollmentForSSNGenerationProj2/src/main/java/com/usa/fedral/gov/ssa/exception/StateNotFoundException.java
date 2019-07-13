package com.usa.fedral.gov.ssa.exception;

/**
 * 
 * @author Mohammed
 *class to handle state not found
 *
 */
public class StateNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2063994945731453846L;

	public StateNotFoundException(String msg){
		super(msg);
	}
}
