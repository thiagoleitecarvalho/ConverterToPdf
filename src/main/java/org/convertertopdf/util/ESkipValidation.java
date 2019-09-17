package org.convertertopdf.util;

/**
 * Enum responsible for indicating that validations should not be performed.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public enum ESkipValidation {

	TRUE(true);
	
	/**
	 * Informs that validations should be ignored.
	 */
	private boolean skipVaidation;

	/**
	 * Constructor.
	 * @param skipValidation Informs that validations should be ignored
	 */
	private ESkipValidation(boolean skipValidation) {
		this.skipVaidation = skipValidation;
	}
	
	/**
	 * Gets the value of {@link ESkipValidation} attribute.
	 * @return {@link ESkipValidation#skipVaidation}.
	 */
	public boolean getValue() {
		return skipVaidation;		
	}
}
