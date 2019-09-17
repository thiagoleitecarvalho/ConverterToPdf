package org.convertertopdf.convert.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.convertertopdf.convert.AbstractConverter;
import org.convertertopdf.exception.FileNotInformedException;
import org.convertertopdf.exception.FileValidationException;
import org.convertertopdf.exception.PdfConverterException;
import org.convertertopdf.management.ConverterFactory;
import org.convertertopdf.util.EFormat;
import org.convertertopdf.util.ESkipValidation;
import org.convertertopdf.util.FormatUtils;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Class responsible for converting a heterogeneous list of files. This is useful 
 * when you want to convert files whose a sequence(temporal, types, etc) is more important
 * than type-specific settings.
 * When this converter is used, no configuration can be passed.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public final class GenericConverter {

	/**
	 * Map containing the converters that will be used for conversions.
	 */
	private HashMap<EFormat, AbstractConverter> converters = new HashMap<EFormat, AbstractConverter>();
	
	/**
	 * File(s) source.
	 */
	private File[] file;
	
	/**
	 * Pdf destiny.
	 */
	private OutputStream out;
	
	/**
	 * Sets the source by file, that will be converted to desired format.
	 * 
	 * @param file Files to convert
	 * @return {@link AbstractConverter}
	 */
	public GenericConverter from(File... file) {

		this.file = file;
		return this;
	}
	
	/**
	 * Sets the OutputStream, that will be the pdf converted to desired format.
	 * 
	 * @param outputStream pdf destiny
	 * @return {@link AbstractConverter}
	 */
	public GenericConverter to(OutputStream outputStream) {

		this.out = outputStream;
		return this;
	}
	
	/**
	 * Convert the file to the desired format.
	 * @throws PdfConverterException {@link PdfConverterException}
	 * @throws FileNotInformedException {@link FileNotInformedException}
	 */
	public void convert() throws PdfConverterException, FileNotInformedException {

		File temporaryFile = null;
		OutputStream convertedTemporaryFile = null;
		Document document = null;
		PdfReader reader = null;
		
		try {

			validateParameters();
			
			document = new Document();
	        PdfWriter pdfWriter = PdfWriter.getInstance(document, out);
	        document.open();
	        PdfContentByte pdfContent = pdfWriter.getDirectContent();
			
			for(File file: file) {
			
				temporaryFile = File.createTempFile(UUID.randomUUID().toString(), EFormat.PDF.getExtension());
				convertedTemporaryFile = new FileOutputStream(temporaryFile);
				
				EFormat eFormat = FormatUtils.getType(file); 
				
				AbstractConverter converter;
				if (converters.containsKey(eFormat)) {
					converter = converters.get(eFormat);
				} else {
					converter = new ConverterFactory(eFormat, ESkipValidation.TRUE).getConverter();
					converters.put(eFormat, converter);					
				}
				
				converter.from(file).to(convertedTemporaryFile).convert();
				
				convertedTemporaryFile.flush();
				convertedTemporaryFile.close();

	            reader = new PdfReader(new FileInputStream(temporaryFile));
	            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
	            	
	                document.newPage();

	                PdfImportedPage pdfImportedPage = pdfWriter.getImportedPage(reader, i);

	                pdfContent.addTemplate(pdfImportedPage, 0, 0);
	            }
				
				FileUtils.deleteQuietly(temporaryFile);
			}
			
			reader.close();
			document.close();
			converters.clear();
		} catch (IOException e) {
			throw new PdfConverterException(e.getMessage(), e);
		} catch (PdfConverterException e) {
			throw e;
		} catch (FileValidationException e) {
			//Nothing to do, because generic conversion does not validate.
		} catch (DocumentException e) {
			throw new PdfConverterException(e.getMessage(), e);
		} finally {
			
			try {
				
				if (reader != null) {
					reader.close();
				}
				
				if (document != null) {
					document.close();
				}
				
				if (out != null) {
					out.close();
				}

				if (convertedTemporaryFile != null) {
					convertedTemporaryFile.close();
				}
				
				FileUtils.deleteQuietly(temporaryFile);
				
				converters.clear();
				
				System.gc();
				
			} catch (IOException e) {
				throw new PdfConverterException(e.getMessage(), e);
			}
		}
	}

	/**
	 * Checks if the inputs and outputs has been informed.
	 * 
	 * @throws IOException {@link IOException}
	 * @throws FileNotInformedException {@link FileNotInformedException}
	 */
	private void validateParameters() throws IOException, FileNotInformedException {
		
		if (file == null || file.length == 0) {
			
			if (out != null) {
				out.close();
			}
			throw new FileNotInformedException("The file source has not been informed.");
		}

		if (out == null) {
			throw new FileNotInformedException("The file destiny has not been informed.");
		}
	}
	
}