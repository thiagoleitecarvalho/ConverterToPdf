package org.convertertopdf.management;

import java.util.Arrays;
import java.util.List;

import org.convertertopdf.convert.AbstractConverter;
import org.convertertopdf.util.EFormat;

/**
 * Class that is responsible for managing and converting files.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public class ConverterManager {

	/**
	 * Converter which the manager will provide.
	 */
	private AbstractConverter converter;

	/**
	 * Constructor.
	 */
	public ConverterManager() {
	}

	/**
	 * Provides the converter, which will be created from the appropriate format.
	 * 
	 * @param format The format to create the converter
	 * @return The appropriate converter for the format
	 */
	public AbstractConverter createFor(String format) {

		EFormat eFormat = EFormat.fromString(format);
		return createFor(eFormat);
	}

	/**
	 * Provides the converter, which will be created from the appropriate format.
	 * 
	 * @param format The format to create the converter
	 * @return The appropriate converter for the format
	 */
	public AbstractConverter createFor(EFormat format) {

		this.converter = new ConverterFactory(format).getConverter();
		return converter;
	}

	/**
	 * Returns the available formats of ConverterToPdf.
	 * 
	 * @return The available formats
	 */
	public List<EFormat> getAvaliableFormats() {
		return Arrays.asList(EFormat.values());
	}

	/**
	 * Returns the current converter.
	 * 
	 * @return Current converter
	 */
	public AbstractConverter getConverter() {
		return converter;
	}

}