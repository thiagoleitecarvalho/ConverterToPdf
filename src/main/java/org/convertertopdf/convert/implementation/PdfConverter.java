package org.convertertopdf.convert.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.convertertopdf.configuration.Configuration;
import org.convertertopdf.convert.AbstractConverter;
import org.convertertopdf.exception.FileValidationException;
import org.convertertopdf.exception.PdfConverterException;
import org.convertertopdf.management.ConverterFactory;
import org.convertertopdf.util.EFormat;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Class responsable to convert PDF files to PDF.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public final class PdfConverter extends AbstractConverter {

	/**
	 * This constructor should not be used directly. Only {@link ConverterFactory} is allowed to use it.
	 * @param skipValidation Indicates that validation should not be performed
	 */
	public PdfConverter(boolean skipValidation) {
		super(skipValidation);
	}
	
	/** {@inheritDoc} */
	@Override
	public AbstractConverter setConfigurations(Configuration configurations) {
		throw new UnsupportedOperationException("PdfConverter doesn't support settings.");
	}
	
	/** {@inheritDoc} */
	@Override
	public EFormat getFormat() {
		return EFormat.PDF;
	}

	/** {@inheritDoc} */
	@Override
	public void convert() throws PdfConverterException, FileValidationException {

		Document document = null;
		
		try {

			if (!isValidated()) {
				throw new FileValidationException("The file has not been validated.");
			}

			if (bytesFileSource != null) {
				out.write(bytesFileSource);
			} else {

				document = new Document();
				
		        PdfWriter pdfWriter = PdfWriter.getInstance(document, out);
		        document.open();
		        PdfContentByte pdfContentByte = pdfWriter.getDirectContent();
				
				for (File eachFile : file) {
				
		            PdfReader reader = new PdfReader(new FileInputStream(eachFile));
		            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
		            	
		                document.newPage();
		                
		                PdfImportedPage pageImported = pdfWriter.getImportedPage(reader, i);
		                
		                pdfContentByte.addTemplate(pageImported, 0, 0);
		            }
				}
			}
			
			out.flush();
		} catch (IOException e) {
			throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
		} catch (DocumentException e) {
			throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
		} finally {
			try {
				
				if (document != null) {
					document.close();
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
