package com.usa.fedral.gov.ssa.exception;

public class ApiException {

	private Integer errorCode;
	private String errorData;
	
	public ApiException(Integer errorCode, String errorData) {
		super();
		this.errorCode = errorCode;
		this.errorData = errorData;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorData() {
		return errorData;
	}

	public void setErrorData(String errorData) {
		this.errorData = errorData;
	}
	
	
}
