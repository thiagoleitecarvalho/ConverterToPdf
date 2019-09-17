package org.convertertopdf.convert.implementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.convertertopdf.configuration.Configuration;
import org.convertertopdf.convert.AbstractConverter;
import org.convertertopdf.exception.FileValidationException;
import org.convertertopdf.exception.PdfConverterException;
import org.convertertopdf.management.ConverterFactory;
import org.convertertopdf.util.EFormat;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.util.XRRuntimeException;

import com.lowagie.text.DocumentException;

/**
 * Class responsable to convert HTML/XHTML files to PDF.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public final class HtmlConverter extends AbstractConverter {
	
	/**
	 * This constructor should not be used directly. Only {@link ConverterFactory} is allowed to use it.
	 * @param skipValidation Indicates that validation should not be performed
	 */
	public HtmlConverter(boolean skipValidation) {
		super(skipValidation);
	}
	
	/** {@inheritDoc} */
	@Override
	public AbstractConverter setConfigurations(Configuration configurations) {
		throw new UnsupportedOperationException("HtmlConverter doesn't support settings yet.");
	}

	/** {@inheritDoc} */
	@Override
	public EFormat getFormat() {
		return EFormat.HTML;
	}

	/** {@inheritDoc} */
	@Override
	public void convert() throws PdfConverterException, FileValidationException {

		File tempFileHTML = null;

		try {

			if (!isValidated()) {
				throw new FileValidationException("The file has not been validated.");
			}

			ITextRenderer pdfRenderer = new ITextRenderer();
			SharedContext sharedContext = pdfRenderer.getSharedContext();
			sharedContext.setPrint(true);
			sharedContext.setInteractive(false);
			sharedContext.getTextRenderer().setSmoothingThreshold(0);
			
			Tidy tidy = new Tidy();
			tidy.setQuiet(true);
			tidy.setShowWarnings(false);
			
			if (bytesFileSource != null) {

				tempFileHTML = File.createTempFile(UUID.randomUUID().toString(), getFormat().getExtension());
				Files.write(tempFileHTML.toPath(), bytesFileSource, StandardOpenOption.WRITE);
				
				Document document = tidy.parseDOM(new BufferedReader(new FileReader(tempFileHTML)), null);

				pdfRenderer.setDocument(document, null);

				pdfRenderer.layout();

				pdfRenderer.createPDF(out);
				pdfRenderer.finishPDF();				
			} else {

				Document document = null;
				for (int i = 0; i < file.length; i++) {
										
					if (i == 0) {
						
						document = tidy.parseDOM(new BufferedReader(new FileReader(file[i])), null);

						pdfRenderer.setDocument(document, null);
						pdfRenderer.layout();
						
						pdfRenderer.createPDF(out, false);
					} else {
						
						document = tidy.parseDOM(new BufferedReader(new FileReader(file[i])), null);

						pdfRenderer.setDocument(document, null);
						pdfRenderer.layout();
						pdfRenderer.writeNextDocument();
					}
				}
				
				pdfRenderer.finishPDF();
				out.flush();
			}

		} catch (XRRuntimeException e) {
			throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
		} catch (DocumentException e) {
			throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
		} catch (IOException e) {
			throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
		} finally {
			try {

				if (out != null) {
					out.close();
				}
				
				FileUtils.deleteQuietly(tempFileHTML);

				System.gc();
				
			} catch (IOException e) {
				throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
			}
		}
	}
	
}
