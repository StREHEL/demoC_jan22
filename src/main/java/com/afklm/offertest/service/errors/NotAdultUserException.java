package com.afklm.offertest.service.errors;

public class NotAdultUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotAdultUserException() {
		super("User is not adult!");
	}

}
