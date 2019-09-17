package org.convertertopdf.exception;

/**
 * Exception class, which informs that the file is not compatible with the converter.
 * @author Thiago Leite
 * e-mail: thiagoleiteecarvalho@gmail.com
 */
public final class FileFormatException extends Exception {

	/**
	 * Serial Id.
	 */
	private static final long serialVersionUID = 2974169705043525777L;
	
	public FileFormatException(String message) {
		super(message);
	}

}
