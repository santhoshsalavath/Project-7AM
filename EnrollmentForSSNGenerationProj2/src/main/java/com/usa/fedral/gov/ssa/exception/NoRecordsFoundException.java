package com.usa.fedral.gov.ssa.exception;

/**
 * 
 * @author Mohammed
 * class to handle No Records Found Exception
 */
public class NoRecordsFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8540499440957209192L;

	public NoRecordsFoundException(String msg) {
		super(msg);
	}
}
