package com.usa.fedral.gov.ssa.exception;

/**
 * 
 * @author Mohammed
 * class to handle user not store exception
 */
public class UserNotStoreException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3401478618802753066L;

	public UserNotStoreException(String msg) {
		super(msg);
	}
}
