package org.convertertopdf.convert.implementation;

import com.lowagie.text.DocumentException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.convertertopdf.configuration.Configuration;
import org.convertertopdf.convert.AbstractConverter;
import org.convertertopdf.exception.FileValidationException;
import org.convertertopdf.exception.PdfConverterException;
import org.convertertopdf.util.EFormat;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.util.XRRuntimeException;

/**
 * Class responsable to convert HTML/XHTML files to PDF.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public final class HtmlConverter extends AbstractConverter {

	/** {@inheritDoc} */
	@Override
	public AbstractConverter setConfigurations(Configuration configurations) {
		throw new UnsupportedOperationException("HtmlConverter doesn't support settings.");
	}

	/** {@inheritDoc} */
	@Override
	public EFormat getFormat() {
		return EFormat.HTML;
	}

	/** {@inheritDoc} */
	@Override
	public byte[] convert() throws PdfConverterException, FileValidationException {

		org.w3c.dom.Document document = null;
		ITextRenderer pdfRenderer = null;
		SharedContext sharedContext = null;
		ByteArrayOutputStream byteArray = null;
		Tidy tidy = null;
		FileInputStream fileStream = null;
		File tempFileHTML = null;

		try {

			if (!isValidated()) {
				throw new FileValidationException("The file has not been validated.");
			}

			if (file != null) {

				fileStream = new FileInputStream(file);
			} else {

				tempFileHTML = File.createTempFile(UUID.randomUUID().toString(), EFormat.HTML.getExtension());
				Files.write(tempFileHTML.toPath(), bytesFile, StandardOpenOption.WRITE);

				fileStream = new FileInputStream(tempFileHTML);
			}

			tidy = new Tidy();
			tidy.setQuiet(true);
			tidy.setShowWarnings(false);
			document = tidy.parseDOM(fileStream, null);

			pdfRenderer = new ITextRenderer();
			sharedContext = pdfRenderer.getSharedContext();
			sharedContext.setPrint(true);
			sharedContext.setInteractive(false);
			sharedContext.getTextRenderer().setSmoothingThreshold(0);
			pdfRenderer.setDocument(document, null);

			pdfRenderer.layout();

			byteArray = new ByteArrayOutputStream();
			pdfRenderer.createPDF(byteArray);

			return byteArray.toByteArray();

		} catch (XRRuntimeException e) {
			throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
		} catch (DocumentException e) {
			throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
		} catch (IOException e) {
			throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
		} finally {
			try {

				if (byteArray != null) {
					byteArray.close();
				}

				if (fileStream != null) {
					fileStream.close();
				}

				FileUtils.deleteQuietly(tempFileHTML);

			} catch (IOException e) {
				throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
			}
		}
	}

}
