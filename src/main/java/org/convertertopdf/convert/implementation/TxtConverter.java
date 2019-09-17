package org.convertertopdf.convert.implementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.convertertopdf.convert.SimpleFileConverter;
import org.convertertopdf.exception.FileValidationException;
import org.convertertopdf.exception.PdfConverterException;
import org.convertertopdf.management.ConverterFactory;
import org.convertertopdf.util.EFormat;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Class responsable to convert TXT files to PDF.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public final class TxtConverter extends SimpleFileConverter {

	/**
	 * This constructor should not be used directly. Only {@link ConverterFactory} is allowed to use it.
	 * @param skipValidation Indicates that validation should not be performed
	 */
	public TxtConverter(boolean skipValidation) {
		super(skipValidation);
	}
	
	/** {@inheritDoc} */
	public EFormat getFormat() {
		return EFormat.TXT;
	}
	
	/** {@inheritDoc} */
	public void convert() throws PdfConverterException, FileValidationException {

		Document document = null;
		BufferedReader reader = null;
		File tempFileTXT = null;

		try {

			if (!isValidated()) {
				throw new FileValidationException("The file has not been validated.");
			}

			document = new Document();

			document.setPageSize(getConfigurations().getPageSize());

			if (!getConfigurations().isPortrait()) {
				document.setPageSize(document.getPageSize().rotate());
			}

			PdfWriter.getInstance(document, out);

			document.open();

			if (bytesFileSource != null) {

				tempFileTXT = File.createTempFile(UUID.randomUUID().toString(), getFormat().getExtension());
				Files.write(tempFileTXT.toPath(), bytesFileSource, StandardOpenOption.WRITE);
				
				reader = new BufferedReader(new FileReader(tempFileTXT));
				createPdf(document, reader);
			} else {

				for (File eachFile : file) {
					
					reader = new BufferedReader(new FileReader(eachFile));
					createPdf(document, reader);
				}
			}

			document.close();
			out.flush();

		} catch (IOException e) {
			throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
		} catch (DocumentException e) {
			throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
		} catch (ExceptionConverter e) {
			throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				
				if (reader != null) {
					reader.close();
				}
				if (document != null && document.isOpen()) {
					document.close();
				}

				FileUtils.deleteQuietly(tempFileTXT);

				System.gc();
				
			} catch (IOException e) {
				throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
			}
		}
	}

	/**
	 * Create the PDF.
	 * 
	 * @param document The Document which represents the new PDF 
	 * @param reader Reader to source file
	 * @throws DocumentException {@link DocumentException}
	 * @throws IOException {@link IOException}
	 */
	private void createPdf(Document document, BufferedReader reader) throws IOException, DocumentException {
		
		String line;
		while ((line = reader.readLine()) != null) {
			Paragraph paragraph = new Paragraph(line);
			document.add(paragraph);
		}
	}
	
}