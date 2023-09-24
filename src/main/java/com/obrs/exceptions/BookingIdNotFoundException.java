package com.obrs.exceptions;

public class BookingIdNotFoundException extends RuntimeException{
	
	public BookingIdNotFoundException() {
		super();
	}
	public BookingIdNotFoundException(String msg) {
		super(msg);
	}

}
