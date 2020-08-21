package com.hssa.ezybiz.exception;


public class GcbException extends Exception {

	private static final long serialVersionUID = 7718828512143293558L;

	public GcbException(String message, Throwable cause) {
		super(message, cause);
	}

	public GcbException(String message) {
		super(message);
	}

	public GcbException(Throwable cause) {
		super(cause);
	}
	
	public GcbException() {
		super();
	}
	
}