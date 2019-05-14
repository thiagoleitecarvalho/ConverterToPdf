package org.convertertopdf.convert.implementation;

import org.convertertopdf.convert.OfficeConverter;
import org.convertertopdf.util.EFormat;

/**
 * Class responsable to convert DOC files to PDF.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public class DocConverter extends OfficeConverter {

	/** {@inheritDoc} */
	public EFormat getFormat() {
		return EFormat.DOC;
	}
}