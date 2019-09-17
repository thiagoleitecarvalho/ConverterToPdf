package org.convertertopdf.convert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.convertertopdf.configuration.Configuration;
import org.convertertopdf.exception.FileValidationException;
import org.convertertopdf.exception.PdfConverterException;
import org.jodconverter.JodConverter;
import org.jodconverter.LocalConverter;
import org.jodconverter.LocalConverter.Builder;
import org.jodconverter.document.DefaultDocumentFormatRegistry;
import org.jodconverter.document.DocumentFormat;
import org.jodconverter.filter.Filter;
import org.jodconverter.filter.text.DocumentInserterFilter;
import org.jodconverter.office.LocalOfficeManager;
import org.jodconverter.office.OfficeException;
import org.jodconverter.office.OfficeUtils;

/**
 * Abstract class that is responsible for providing operations to convert office
 * files files to PDF. Office files: doc, docx, odt, rtf files to PDF.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public abstract class OfficeConverter extends AbstractConverter {
	
	/**
	 * Constructor.
	 * @param skipValidation Indicates that validation should not be performed
	 */
	protected OfficeConverter(boolean skipValidation) {
		super(skipValidation);
	}
	
	/**
	 * Returns the {@link DocumentFormat} to source file.
	 *  
	 * @return DocumentFormat {@link DocumentFormat}
	 */
	protected abstract DocumentFormat getDocumentFormat();
	
	/** {@inheritDoc} */
	@Override
	public AbstractConverter setConfigurations(Configuration configurations) {
		throw new UnsupportedOperationException("OfficeConverter doesn't support settings yet.");
	}
	
	/** {@inheritDoc} */
	public void convert() throws FileValidationException, PdfConverterException {

		final LocalOfficeManager officeManager = LocalOfficeManager.install();
		File tempFileWord = null;

		try {

			if (!isValidated()) {
				throw new FileValidationException("The file has not been validated.");
			}

			officeManager.start();
			
			if (bytesFileSource != null) {

				// doc/docx temp
				tempFileWord = File.createTempFile(UUID.randomUUID().toString(), getFormat().getExtension());
				Files.write(tempFileWord.toPath(), bytesFileSource, StandardOpenOption.WRITE);
				
				JodConverter.convert(new FileInputStream(tempFileWord)).as(getDocumentFormat()).to(out, true).as(DefaultDocumentFormatRegistry.PDF).execute();
			} else {
				
				if (file.length == 1) {
					
					LocalConverter.builder().build().convert(new FileInputStream(file[0])).as(getDocumentFormat()).to(out).as(DefaultDocumentFormatRegistry.PDF).execute();
				} else {
					
					Builder builder = LocalConverter.builder();
					Filter[] filters = new DocumentInserterFilter[file.length - 1];
					for (int i = 0; i < file.length - 1; i++) {
						
						filters[i] = new DocumentInserterFilter(file[i]);
					}
					builder.filterChain(filters).build().convert(new FileInputStream(file[file.length - 1])).as(getDocumentFormat()).to(out, false).as(DefaultDocumentFormatRegistry.PDF).execute();
				}
			}
		} catch (IOException e) {
			throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
		} catch (OfficeException e) {
			throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
		} finally {

			FileUtils.deleteQuietly(tempFileWord);
			
			try {
				
				if (officeManager.isRunning()) {
					OfficeUtils.stopQuietly(officeManager);
				}
			
				if (out != null) {
					out.close();
				}
				
				System.gc();
				
			} catch (IOException e) {
				throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);				
			}

		}
	}	
}