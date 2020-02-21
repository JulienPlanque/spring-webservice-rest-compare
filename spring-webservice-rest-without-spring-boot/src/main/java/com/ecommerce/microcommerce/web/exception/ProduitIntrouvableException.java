package com.ecommerce.microcommerce.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ACCEPTED)
public class ProduitIntrouvableException extends CustomException{

	private static final long serialVersionUID = 4002799672602157308L;

	public ProduitIntrouvableException() {
		super();
	}

	public ProduitIntrouvableException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProduitIntrouvableException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProduitIntrouvableException(String message) {
		super(message);
	}

	public ProduitIntrouvableException(Throwable cause) {
		super(cause);
	}
}
