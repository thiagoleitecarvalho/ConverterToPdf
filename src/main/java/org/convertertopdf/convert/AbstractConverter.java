package org.convertertopdf.convert;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

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
	 * Constructor.
	 * @param skipValidation Indicates that validation should not be performed
	 */
	protected AbstractConverter(boolean skipValidation) {
		this.flagValidation = skipValidation;
	}
	
	/**
	 * File(s) source.
	 */
	protected File[] file;

	/**
	 * File stream.
	 */
	protected byte[] bytesFileSource;

	/**
	 * Pdf destiny.
	 */
	protected OutputStream out;
	
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
	 * Sets the source by bytes, that will be converted to desired format.
	 * 
	 * @param bytesFile bytes to convert
	 * @return {@link AbstractConverter}
	 */
	public AbstractConverter from(byte[] bytesFile) {

		this.bytesFileSource = bytesFile;
		return this;
	}
	
	/**
	 * Sets the source by file, that will be converted to desired format.
	 * 
	 * @param file Files to convert
	 * @return {@link AbstractConverter}
	 */
	public AbstractConverter from(File... file) {

		this.file = file;
		return this;
	}
	
	/**
	 * Sets the OutputStream, that will be the pdf converted to desired format.
	 * 
	 * @param outputStream pdf destiny
	 * @return {@link AbstractConverter}
	 */
	public AbstractConverter to(OutputStream outputStream) {

		this.out = outputStream;
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
		
		try {
			if (file == null && bytesFileSource == null) {
				
				if (out != null) {
					out.close();
				}
				throw new FileNotInformedException("The file source has not been informed.");
			}
	
			if (out == null) {
				throw new FileNotInformedException("The file destiny has not been informed.");
			}
	
			if (file != null) {
	
				for(File eachFile : file) {
					
					FileValidator.checkFormatFile(eachFile, getFormat());
				}
			} else {
	
				FileValidator.checkFormatFile(bytesFileSource, getFormat());
			}
	
			this.flagValidation = true;
	
			return this;
		} catch (Exception e) {
			
			try {
				if (out != null) {
					out.close();
				}
				
				throw e;
			} catch (IOException e1) {
				throw new FileValidationException("Validation failed.", e);
			}
		}
		
	}

	/**
	 * Convert the file to the desired format.
	 * 
	 * @throws PdfConverterException   {@link PdfConverterException}
	 * @throws FileValidationException {@link FileValidationException}
	 */
	public abstract void convert() throws PdfConverterException, FileValidationException;

	/**{@inheritDoc}*/
	@Override
	protected void finalize() throws Throwable {

		this.configurations = null;
		this.bytesFileSource = null;
		this.file = null;
		this.out = null;
		this.flagValidation = false;
	}
}
