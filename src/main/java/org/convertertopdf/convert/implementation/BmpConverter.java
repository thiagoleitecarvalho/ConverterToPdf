package org.convertertopdf.convert.implementation;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.codec.BmpImage;

import java.io.IOException;
import java.net.MalformedURLException;

import org.convertertopdf.convert.ImageConverter;
import org.convertertopdf.util.EFormat;

/**
 * Class responsable to convert BMP files to PDF.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public final class BmpConverter extends ImageConverter {

	/** {@inheritDoc} */
	@Override
	public EFormat getFormat() {
		return EFormat.BMP;
	}

	/** {@inheritDoc} */
	@Override
	public Image getImage() throws BadElementException, MalformedURLException, IOException {
		return BmpImage.getImage(getBytes());
	}

}