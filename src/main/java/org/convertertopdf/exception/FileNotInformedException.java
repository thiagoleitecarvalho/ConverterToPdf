package org.convertertopdf.exception;

/**
 * Exception class, which informs that the file was not informed.
 * @author Thiago Leite
 * e-mail: thiagoleiteecarvalho@gmail.com
 */
public final class FileNotInformedException extends Exception {

	/**
	 * Serial Id.
	 */
	private static final long serialVersionUID = -2208425586372912388L;
	
	public FileNotInformedException(String message) {
		super(message);
	}

}
