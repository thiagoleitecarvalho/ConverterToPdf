package org.convertertopdf.convert;

import java.io.File;

import org.convertertopdf.configuration.Configuration;
import org.convertertopdf.exception.FileFormatException;
import org.convertertopdf.exception.FileNotInformedException;
import org.convertertopdf.exception.FileValidationException;
import org.convertertopdf.exception.PdfConverterException;
import org.convertertopdf.util.EFormat;
import org.convertertopdf.validation.FileValidator;

/**
 * Abstract class that is responsible for providing operations to convert files
 * to PDF.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public abstract class AbstractConverter {

	/**
	 * File.
	 */
	protected File file;

	/**
	 * File stream.
	 */
	protected byte[] bytesFile;

	/**
	 * HashMap which stores specific settings to the converter.
	 */
	protected Configuration configurations;

	/**
	 * Checks if the file has been validated.
	 */
	private boolean flagValidation = false;

	/**
	 * Returns the current format of the converter.
	 * 
	 * @return Format currently in use
	 */
	protected abstract EFormat getFormat();

	/**
	 * Sets the file by {@link File}, that will be converted to desired format.
	 * 
	 * @param file File to convert
	 * @return {@link AbstractConverter}
	 */
	public AbstractConverter setFile(File file) {

		this.file = file;
		return this;
	}

	/**
	 * Sets the file by bytes, that will be converted to desired format.
	 * 
	 * @param bytesFile bytes to convert
	 * @return {@link AbstractConverter}
	 */
	public AbstractConverter setFile(byte[] bytesFile) {

		this.bytesFile = bytesFile;
		return this;
	}

	public boolean isValidated() {
		return flagValidation;
	}

	/**
	 * Sets appropriate configurations for the converter.
	 * 
	 * @param configurations Specifics settings to the converter
	 * @return {@link AbstractConverter}
	 */
	public AbstractConverter setConfigurations(Configuration configurations) {

		this.configurations = configurations;
		return this;
	}

	/**
	 * Validates the data, as if the file passed is according to the type indicated
	 * for the conversion and if any source was passed.
	 * 
	 * @return {@link AbstractConverter}
	 * @throws FileFormatException      {@link FileFormatException}
	 * @throws FileNotInformedException {@link FileNotInformedException}
	 * @throws FileValidationException  {@link FileValidationException}
	 */
	public AbstractConverter validade() throws FileFormatException, FileNotInformedException, FileValidationException {

		if (file == null && bytesFile == null) {
			throw new FileNotInformedException("The file has not been informed.");
		}

		if (file != null) {

			FileValidator.checkFormatFile(file, getFormat());
		} else {

			FileValidator.checkFormatFile(bytesFile, getFormat());
		}

		this.flagValidation = true;

		return this;
	}

	/**
	 * Convert the file to the desired format.
	 * 
	 * @return Byte array that represents the converted file
	 * @throws PdfConverterException   {@link PdfConverterException}
	 * @throws FileValidationException {@link FileValidationException}
	 */
	public abstract byte[] convert() throws PdfConverterException, FileValidationException;

}
