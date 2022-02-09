package com.afklm.offertest.service.errors;

public class NotFrenchUserException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public NotFrenchUserException() {
		super("User must be french!");
	}
}
