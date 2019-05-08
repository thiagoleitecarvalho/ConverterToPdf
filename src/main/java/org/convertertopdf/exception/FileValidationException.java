package org.convertertopdf.exception;

/**
 * Exception class, which informs that validations was not done.
 * @author Thiago Leite
 * e-mail: thiagoleiteecarvalho@gmail.com
 */
public final class FileValidationException extends Exception {

	/**
	 * Serial Id.
	 */
	private static final long serialVersionUID = -7535153482393314345L;
	
	public FileValidationException(String message) {
		super(message);
	}

	public FileValidationException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
