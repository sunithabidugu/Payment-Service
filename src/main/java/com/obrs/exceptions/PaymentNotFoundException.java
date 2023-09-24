package com.obrs.exceptions;

public class PaymentNotFoundException extends RuntimeException{
	public PaymentNotFoundException() {
		super();
	}
	public PaymentNotFoundException(String msg) {
		super(msg);
	}


}
