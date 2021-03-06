package org.convertertopdf.convert.implementation;

import java.io.IOException;
import java.net.MalformedURLException;

import org.convertertopdf.convert.ImageConverter;
import org.convertertopdf.management.ConverterFactory;
import org.convertertopdf.util.EFormat;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.codec.PngImage;

/**
 * Class responsable to convert PNG files to PDF.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public final class PngConverter extends ImageConverter {
	
	/**
	 * This constructor should not be used directly. Only {@link ConverterFactory} is allowed to use it.
	 * @param skipValidation Indicates that validation should not be performed
	 */
	public PngConverter(boolean skipValidation) {
		super(skipValidation);
	}
	
	/** {@inheritDoc} */
	@Override
	public EFormat getFormat() {
		return EFormat.PNG;
	}

	/** {@inheritDoc} */
	@Override
	public Image getImage() throws BadElementException, MalformedURLException, IOException {
		return PngImage.getImage(getBytes());
	}
	
}