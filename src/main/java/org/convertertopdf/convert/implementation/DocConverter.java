package org.convertertopdf.convert.implementation;

import org.convertertopdf.convert.OfficeConverter;
import org.convertertopdf.management.ConverterFactory;
import org.convertertopdf.util.EFormat;
import org.jodconverter.document.DefaultDocumentFormatRegistry;
import org.jodconverter.document.DocumentFormat;

/**
 * Class responsable to convert DOC files to PDF.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public final class DocConverter extends OfficeConverter {
	
	/**
	 * This constructor should not be used directly. Only {@link ConverterFactory} is allowed to use it.
	 * @param skipValidation Indicates that validation should not be performed
	 */
	public DocConverter(boolean skipValidation) {
		super(skipValidation);
	}
	
	/** {@inheritDoc} */
	public EFormat getFormat() {
		return EFormat.DOC;
	}
	
	/** {@inheritDoc} */
	@Override
	protected DocumentFormat getDocumentFormat() {
		return DefaultDocumentFormatRegistry.DOC;
	}
	
}