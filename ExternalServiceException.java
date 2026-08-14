package com.cleverCRM.exception;

/**
 * Thrown when an upstream dependency (OpenAI, vector store) fails or returns a
 * response that cannot be interpreted. Mapped to HTTP 502 by
 * {@link GlobalExceptionHandler}.
 */
public class ExternalServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExternalServiceException(String message) {
		super(message);
	}

	public ExternalServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
