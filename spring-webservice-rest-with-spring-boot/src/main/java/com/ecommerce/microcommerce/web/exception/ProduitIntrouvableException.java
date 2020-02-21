package com.ecommerce.microcommerce.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ACCEPTED)
public class ProduitIntrouvableException extends RuntimeException{

	private static final long serialVersionUID = -2245906670269392872L;

	public ProduitIntrouvableException(String message) {
		super(message);
	}
}
