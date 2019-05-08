package org.convertertopdf.management;

import org.convertertopdf.convert.AbstractConverter;
import org.convertertopdf.convert.implementation.BmpConverter;
import org.convertertopdf.convert.implementation.DocConverter;
import org.convertertopdf.convert.implementation.DocxConverter;
import org.convertertopdf.convert.implementation.GifConverter;
import org.convertertopdf.convert.implementation.HtmlConverter;
import org.convertertopdf.convert.implementation.JpegConverter;
import org.convertertopdf.convert.implementation.OdtConverter;
import org.convertertopdf.convert.implementation.PngConverter;
import org.convertertopdf.convert.implementation.RtfConverter;
import org.convertertopdf.convert.implementation.TiffConverter;
import org.convertertopdf.convert.implementation.TxtConverter;
import org.convertertopdf.util.EFormat;

/**
 * Factory class that is responsible for create the appropriate convert class.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public class ConverterFactory {

	/**
	 * File format, will create the file converter.
	 */
	private EFormat format;

	/**
	 * Constructor.
	 * 
	 * @param format {@link EFormat}
	 */
	public ConverterFactory(EFormat format) {
		this.format = format;
	}

	/**
	 * Create the converter by the format.
	 * 
	 * @return The appropriate converter.
	 */
	public AbstractConverter getConverter() {

		switch (format) {
		case TXT:
			return new TxtConverter();
		case DOC:
			return new DocConverter();
		case DOCX:
			return new DocxConverter();
		case ODT:
			return new OdtConverter();
		case HTML:
			return new HtmlConverter();
		case JPEG:
			return new JpegConverter();
		case TIFF:
			return new TiffConverter();
		case PNG:
			return new PngConverter();
		case BMP:
			return new BmpConverter();
		case GIF:
			return new GifConverter();
		case RTF:
			return new RtfConverter();
		default:
			throw new IllegalArgumentException("Unable to determine file converter.");
		}
	}
}
