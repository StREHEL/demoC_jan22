package com.afklm.offertest.service.errors;

public class BadRequestAlertException extends RuntimeException {

	public BadRequestAlertException() {
		super();
	}

	public BadRequestAlertException(String message) {
		super(message);
	}
}
