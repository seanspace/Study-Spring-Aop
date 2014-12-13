package com.bin.spring.transaction.xml;

public class UserAccountException extends RuntimeException {

	private static final long serialVersionUID = -7701681404736626664L;

	public UserAccountException() {
		super();
	}

	public UserAccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserAccountException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAccountException(String message) {
		super(message);
	}

	public UserAccountException(Throwable cause) {
		super(cause);
	}
	
	

}
