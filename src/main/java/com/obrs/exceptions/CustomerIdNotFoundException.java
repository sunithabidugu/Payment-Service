package com.obrs.exceptions;

public class CustomerIdNotFoundException extends RuntimeException {
	public CustomerIdNotFoundException() {
		super();
	}
	public CustomerIdNotFoundException(String msg) {
		super(msg);
	}


}
