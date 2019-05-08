package org.convertertopdf.convert.implementation;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;

import java.io.IOException;
import java.net.MalformedURLException;

import org.convertertopdf.convert.ImageConverter;
import org.convertertopdf.util.EFormat;

/**
 * Class responsable to convert PNG files to PDF.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public final class GifConverter extends ImageConverter {

	/** {@inheritDoc} */
	@Override
	public EFormat getFormat() {
		return EFormat.GIF;
	}

	/** {@inheritDoc} */
	@Override
	public Image getImage() throws BadElementException, MalformedURLException, IOException {
		return Image.getInstance(getBytes());
	}

}