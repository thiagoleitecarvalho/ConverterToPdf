package org.convertertopdf.convert.implementation;

import java.io.IOException;
import java.net.MalformedURLException;

import org.convertertopdf.convert.ImageConverter;
import org.convertertopdf.management.ConverterFactory;
import org.convertertopdf.util.EFormat;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;

/**
 * Class responsable to convert TIF files to PDF.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public final class TiffConverter extends ImageConverter {
	
	/**
	 * This constructor should not be used directly. Only {@link ConverterFactory} is allowed to use it.
	 * @param skipValidation Indicates that validation should not be performed
	 */
	public TiffConverter(boolean skipValidation) {
		super(skipValidation);
	}
	
	/** {@inheritDoc} */
	@Override
	public EFormat getFormat() {
		return EFormat.TIFF;
	}

	/** {@inheritDoc} */
	@Override
	public Image getImage() throws BadElementException, MalformedURLException, IOException {
		return Image.getInstance(getBytes());
	}
		
}
