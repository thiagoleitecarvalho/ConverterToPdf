package org.convertertopdf.convert.implementation;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
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
import org.convertertopdf.util.EFormat;

/**
 * Class responsable to convert TXT files to PDF.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public final class TxtConverter extends SimpleFileConverter {

	/** {@inheritDoc} */
	public EFormat getFormat() {
		return EFormat.TXT;
	}

	/** {@inheritDoc} */
	public byte[] convert() throws PdfConverterException, FileValidationException {

		Document document = null;
		BufferedReader reader = null;
		ByteArrayOutputStream out = null;
		File tempFileTXT = null;

		try {

			if (!isValidated()) {
				throw new FileValidationException("The file has not been validated.");
			}

			out = new ByteArrayOutputStream();

			document = new Document();

			document.setPageSize(getConfigurations().getPageSize());

			if (!getConfigurations().isPortrait()) {
				document.setPageSize(document.getPageSize().rotate());
			}

			PdfWriter.getInstance(document, out);

			document.open();

			if (file != null) {

				reader = new BufferedReader(new FileReader(file));
			} else {

				tempFileTXT = File.createTempFile(UUID.randomUUID().toString(), getFormat().getExtension());
				Files.write(tempFileTXT.toPath(), bytesFile, StandardOpenOption.WRITE);

				reader = new BufferedReader(new FileReader(tempFileTXT));
			}

			String line;
			while ((line = reader.readLine()) != null) {
				Paragraph paragraph = new Paragraph(line);
				document.add(paragraph);
			}

			document.close();
			out.flush();
			return out.toByteArray();

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

			} catch (IOException e) {
				throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
			}
		}
	}

}