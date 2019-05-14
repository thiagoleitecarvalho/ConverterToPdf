package org.convertertopdf.exception;

import org.convertertopdf.util.EFormat;

/**
 * Exception class, which represents errors during conversion.
 * @author Thiago Leite
 * e-mail: thiagoleiteecarvalho@gmail.com
 */
public final class PdfConverterException extends Exception {

	/**
	 * Serial Id.
	 */
	private static final long serialVersionUID = -1518830516253807858L;
	
	/**
	 * Exception message to pdf converter.
	 */
	private static String pdfConverterMessageException = "Unable to convert %s to PDF.";
	
	public PdfConverterException(String message, Throwable e) {
		super(message, e);
	}

	/**
	 * Formats the message for the appropriate file format.
	 * @param format Format of file
	 * @return The appropriate message for the file format
	 */
	public static String formatMessage(EFormat format) {
		return String.format(new String(pdfConverterMessageException), format.name());
	}
}
