package org.convertertopdf.convert;

import org.convertertopdf.configuration.implementation.TxtImageConfiguration;

/**
 * Abstract class that is responsible for providing operations to convert simple
 * files files to PDF. Simple files: txt, bmp, gif, jpeg, png, tiff.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public abstract class SimpleFileConverter extends AbstractConverter {

	/**
	 * Constructor.
	 * @param skipValidation Indicates that validation should not be performed
	 */
	protected SimpleFileConverter(boolean skipValidation) {
		super(skipValidation);
	}
	
	public TxtImageConfiguration getConfigurations() {

		if (configurations == null) {
			configurations = new TxtImageConfiguration();
		}

		return (TxtImageConfiguration) configurations;
	}
}
