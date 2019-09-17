package org.convertertopdf.management;

import org.convertertopdf.convert.AbstractConverter;
import org.convertertopdf.convert.implementation.BmpConverter;
import org.convertertopdf.convert.implementation.DocConverter;
import org.convertertopdf.convert.implementation.DocxConverter;
import org.convertertopdf.convert.implementation.GifConverter;
import org.convertertopdf.convert.implementation.HtmlConverter;
import org.convertertopdf.convert.implementation.JpegConverter;
import org.convertertopdf.convert.implementation.OdtConverter;
import org.convertertopdf.convert.implementation.PdfConverter;
import org.convertertopdf.convert.implementation.PngConverter;
import org.convertertopdf.convert.implementation.RtfConverter;
import org.convertertopdf.convert.implementation.TiffConverter;
import org.convertertopdf.convert.implementation.TxtConverter;
import org.convertertopdf.util.EFormat;
import org.convertertopdf.util.ESkipValidation;

/**
 * Factory class that is responsible for create the appropriate convert class.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public final class ConverterFactory {

	/**
	 * File format, will create the file converter.
	 */
	private EFormat format;

	/**
	 * Indicates that the converter to be created should not perform validations.
	 */
	private boolean skipValidation;
	
	/**
	 * Constructor.
	 * 
	 * @param format {@link EFormat}
	 */
	ConverterFactory(EFormat format) {
		this.format = format;
		this.skipValidation = false;
	}

	/**
	 * Constructor.
	 * 
	 * @param format {@link EFormat}
	 * @param skipValidation Indicates that the converter to be created should not perform validations. 
	 * If this constructor is used, the true value must be passed. 
	 */
	public ConverterFactory(EFormat format, ESkipValidation skipValidation) {
		this.format = format;
		this.skipValidation = skipValidation.getValue();
	}

	/**
	 * Create the converter by the format.
	 * 
	 * @return The appropriate converter.
	 */
	public AbstractConverter getConverter() {

		switch (format) {
		case TXT:
			return new TxtConverter(skipValidation);
		case DOC:
			return new DocConverter(skipValidation);
		case DOCX:
			return new DocxConverter(skipValidation);
		case ODT:
			return new OdtConverter(skipValidation);
		case HTML:
			return new HtmlConverter(skipValidation);
		case JPEG:
			return new JpegConverter(skipValidation);
		case TIFF:
			return new TiffConverter(skipValidation);
		case PNG:
			return new PngConverter(skipValidation);
		case BMP:
			return new BmpConverter(skipValidation);
		case GIF:
			return new GifConverter(skipValidation);
		case RTF:
			return new RtfConverter(skipValidation);
		case PDF:
			return new PdfConverter(skipValidation);
		default:
			throw new IllegalArgumentException("Unable to determine file converter.");
		}
	}
}
