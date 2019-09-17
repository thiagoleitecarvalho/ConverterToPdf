package org.convertertopdf.convert;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;

import org.convertertopdf.exception.FileValidationException;
import org.convertertopdf.exception.PdfConverterException;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Abstract class that is responsible for providing adictional operations to
 * convert image files to PDF.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public abstract class ImageConverter extends SimpleFileConverter {
	
	/**
	 * Constructor.
	 * @param skipValidation Indicates that validation should not be performed
	 */
	protected ImageConverter(boolean skipValidation) {
		super(skipValidation);
	}
	
	/**
	 * Bytes of image to convert.
	 */
	private byte[] bytes = null;

	protected byte[] getBytes() {
		return bytes;
	}

	/**
	 * Checks if the image width or image height overflow the page size.
	 * 
	 * @param image Image to be compared with the page
	 * @return true if overflow, false otherwise
	 */
	public boolean isImageOverflowPage(Image image) {
		return (image.getWidth() > getConfigurations().getPageSize().getWidth())
				| (image.getHeight() > getConfigurations().getPageSize().getHeight());
	}

	/**
	 * Adjusts the width of image to 95% of page size.
	 * 
	 * @return A image width compatible with 95% of page size.
	 */
	public float adjustWidth() {
		return getConfigurations().getPageSize().getWidth() * 0.95f;
	}

	/**
	 * Adjusts the with of image to 95% of page size.
	 * 
	 * @return A image width compatible with 95% of page size.
	 */
	public float adjustHeight() {
		return getConfigurations().getPageSize().getHeight() * 0.95f;
	}

	/**
	 * Returns an image from bytes. The bytes is loaded during the conversion
	 * process and must be used to create the image.
	 * 
	 * @return Image which represents the bytes
	 * @throws BadElementException   {@link BadElementException}
	 * @throws MalformedURLException {@link MalformedURLException}
	 * @throws IOException           {@link IOException}
	 */
	protected abstract Image getImage() throws BadElementException, MalformedURLException, IOException;

	/** {@inheritDoc} */
	@Override
	public void convert() throws PdfConverterException, FileValidationException {

		Document document = null;
		PdfWriter pdf = null;

		try {

			if (!isValidated()) {
				throw new FileValidationException("The file has not been validated.");
			}

			document = new Document();
			pdf = PdfWriter.getInstance(document, out);
			document.open();
			
			if (bytesFileSource != null) {

				bytes = bytesFileSource;				
				createPdf(document, pdf);
			} else {

				for(File eachFile : file) {
					bytes = Files.readAllBytes(eachFile.toPath());
					createPdf(document, pdf);
				}
			}

			document.close();
			out.flush();
			pdf.close();

		} catch (BadElementException e) {
			throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
		} catch (MalformedURLException e) {
			throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
		} catch (IOException e) {
			throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
		} catch (DocumentException e) {
			throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
		} finally {
			
			try {
				if (out != null) {
					out.close();
				}
				if (document != null && document.isOpen()) {
					document.close();
				}

				if (pdf != null) {
					pdf.close();
				}
				
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
	 * @param pdfPdfWrite to create the PDF
	 * @throws DocumentException {@link DocumentException}
	 * @throws MalformedURLException {@link MalformedURLException}
	 * @throws IOException {@link IOException}
	 */
	private void createPdf(Document document, PdfWriter pdf) throws DocumentException, MalformedURLException, IOException {
		
		Image image = null;
		
		document.setMargins(5.0f, 5.0f, 5.0f, 5.0f);

		document.setPageSize(getConfigurations().getPageSize());

		if (!getConfigurations().isPortrait()) {
			document.setPageSize(document.getPageSize().rotate());
		}

		pdf.setStrictImageSequence(true);

		image = getImage();

		if (isImageOverflowPage(image)) {
			image.scaleToFit(adjustWidth(), adjustHeight());
		}

		document.newPage();
		document.add(image);
	}
}
