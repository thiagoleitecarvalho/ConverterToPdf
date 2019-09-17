package org.convertertopdf.management;

import java.util.Arrays;
import java.util.List;

import org.convertertopdf.convert.AbstractConverter;
import org.convertertopdf.convert.implementation.GenericConverter;
import org.convertertopdf.util.EFormat;

/**
 * Class that is responsible for managing and converting files.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public final class ConverterManager {

	/**
	 * Constructor.
	 */
	private ConverterManager() {
	}

	/**
	 * Provides the converter, which will be created from the appropriate format.
	 * 
	 * @param format The format to create the converter
	 * @return The appropriate converter for the format
	 */
	public static AbstractConverter createConverterFor(String format) {

		EFormat eFormat = EFormat.fromString(format);
		return createConverterFor(eFormat);
	}

	/**
	 * Provides the converter, which will be created from the appropriate format.
	 * 
	 * @param format The format to create the converter
	 * @return The appropriate converter for the format
	 */
	public static AbstractConverter createConverterFor(EFormat format) {
		return new ConverterFactory(format).getConverter();
	}

	/**
	 * Provides the generic converter. See {@link GenericConverter}.
	 * 
	 * @return {@link GenericConverter}
	 */
	public static GenericConverter createGenericConverter() {
		return new GenericConverter();
	}
	
	
	/**
	 * Returns the available formats of ConverterToPdf.
	 * 
	 * @return The available formats
	 */
	public static List<EFormat> getAvaliableFormats() {
		return Arrays.asList(EFormat.values());
	}

}