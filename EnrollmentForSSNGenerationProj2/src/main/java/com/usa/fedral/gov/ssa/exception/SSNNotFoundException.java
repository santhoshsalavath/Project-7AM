package com.usa.fedral.gov.ssa.exception;
/**
 * 
 * @author Mohammed
 * class to handle SSN Not Found Exception
 */
public class SSNNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6607365113100861957L;

	public SSNNotFoundException(String msg) {
		super(msg);
	}
}
