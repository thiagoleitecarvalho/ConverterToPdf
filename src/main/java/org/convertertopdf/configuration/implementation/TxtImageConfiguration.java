package org.convertertopdf.configuration.implementation;

import com.lowagie.text.Rectangle;

import org.convertertopdf.configuration.Configuration;
import org.convertertopdf.util.EOrientation;
import org.convertertopdf.util.EPageSize;

/**
 * Utility class for optional settings when converting PDF, TXT and Images (GIF, BMP,
 * PNG, JPEG, TIFF) to PDF.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public final class TxtImageConfiguration extends Configuration {

	/**
	 * Default constructor.
	 */
	public TxtImageConfiguration() {
		super();
	}

	/**
	 * Configuration which represents the page orientation.
	 */
	private final static String ORIENTATION = "org.convertertopdf.configuration.TxtImageConfiguration.Orientation";

	/**
	 * Configuration which represents the page size.
	 */
	private final static String PAGE_SIZE = "org.convertertopdf.configuration.TxtImageConfiguration.PageSize";

	/**
	 * Sets the paige size desired.
	 * 
	 * @param pageSize The page sise for the PDF
	 */
	public void setPageSize(EPageSize pageSize) {
		getConfigurations().put(PAGE_SIZE, pageSize);
	}

	/**
	 * Sets the paige orientation desired.
	 * 
	 * @param orientation The page orientation for the PDF
	 */
	public void setOrientation(EOrientation orientation) {
		getConfigurations().put(ORIENTATION, orientation);
	}

	/**
	 * Gets the configured page size.
	 * 
	 * @return The iText rectangle which represents the page size. The default
	 *         return is A4.
	 */
	public Rectangle getPageSize() {

		if (getConfigurations().get(TxtImageConfiguration.PAGE_SIZE) != null) {
			return (Rectangle) ((EPageSize) getConfigurations().get(TxtImageConfiguration.PAGE_SIZE)).getSize();
		}

		return EPageSize.A4.getSize();
	}

	/**
	 * Gets the configured page orientation.
	 * 
	 * @return True is portrait. False is landscape.
	 */
	public boolean isPortrait() {

		EOrientation orientation = (EOrientation) getConfigurations().get(TxtImageConfiguration.ORIENTATION);

		if (orientation == null) {
			return true;
		}

		return EOrientation.PORTRAIT.equals(orientation);
	}
}
