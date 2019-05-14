package org.convertertopdf.configuration;

import java.util.HashMap;

/**
 * Base utility class for optional settings when converting files to PDF.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public abstract class Configuration {

	/**
	 * Map which stores the configurations.
	 */
	protected HashMap<String, Object> configurations;

	/**
	 * Default constructor.
	 */
	public Configuration() {
		this.configurations = new HashMap<String, Object>();
	}

	public HashMap<String, Object> getConfigurations() {
		return configurations;
	}
}
