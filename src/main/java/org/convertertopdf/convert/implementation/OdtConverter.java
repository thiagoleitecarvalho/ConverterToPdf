package org.convertertopdf.convert.implementation;

import org.convertertopdf.convert.OfficeConverter;
import org.convertertopdf.util.EFormat;

/**
 * Class responsable to convert ODT files to PDF.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public final class OdtConverter extends OfficeConverter {

	/** {@inheritDoc} */
	public EFormat getFormat() {
		return EFormat.ODT;
	}

}