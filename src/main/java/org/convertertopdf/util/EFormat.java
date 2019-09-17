package org.convertertopdf.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Enum responsible for representing the supported files, which the component
 * can convert.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public enum EFormat {

	PDF(".pdf", "application/pdf"),
	
	TXT(".txt", "text/plain"),

	DOC(".doc", "application/msword"),

	DOCX(".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "application/zip"),

	XLS(".xls", "application/vnd.ms-excel"),

	XLSX(".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),

	PPT(".ppt", "application/vnd.ms-powerpoint"),

	PPTX(".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation"),

	ODT(".odt", "application/vnd.oasis.opendocument.text"),

	ODS(".ods", "application/vnd.oasis.opendocument.spreadsheet"),

	ODP(".odp", "application/vnd.oasis.opendocument.presentation"),

	SXW(".sxw", "application/vnd.sun.xml.writer"),

	SXC(".sxc", "application/vnd.sun.xml.calc"),

	SXI(".sxi", "application/vnd.sun.xml.impres"),

	RTF(".rtf", "application/rtf"),

	/**
	 * Format used to .html and .xhtml.
	 */
	HTML(".html", "text/html"),
	
	GIF(".gif", "image/gif"),

	BMP(".bmp", "image/x-ms-bmp"),

	PNG(".png", "image/png"),

	/**
	 * Format used to .jpg and .jpeg.
	 */
	JPEG(".jpeg", "image/jpeg"),
	
	/**
	 * Format used to .tif and .tiff.
	 */
	TIFF(".tiff", "image/tiff");
	
	/**
	 * Extensions of format.
	 */
	private String extension;

	/**
	 * Mime type of format.
	 */
	private String[] mimeType;

	/**
	 * Constructor.
	 * 
	 * @param extension Possible extensions of file
	 * @param mineType  MineType of file
	 */
	private EFormat(String extension, String... mineType) {

		this.extension = extension;
		this.mimeType = mineType;
	}

	/**
	 * Returns the extension of format.
	 * 
	 * @return The extension of format
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * Returns the mimeType of format.
	 * 
	 * @return The mimeType of format
	 */
	public String[] getMimeType() {
		return mimeType;
	}

	/**
	 * Returns the {@link EFormat} by the mime type which represents a format.
	 * 
	 * @param mimeType Mime type
	 * @return {@link EFormat}
	 */
	public static EFormat fromMineType(String mimeType) {
		if (mimeType != null) {
			for (EFormat format : EFormat.values()) {

				List<String> mimeTypes = Arrays.asList(format.getMimeType());

				if (mimeTypes.contains(mimeType)) {
					return format;
				}
			}
		}

		throw new IllegalArgumentException("Unable to determine format by reported mineType.");
	}

	/**
	 * Returns the {@link EFormat} by the String which represents a format.
	 * 
	 * @param format String which represents a format
	 * @return {@link EFormat}
	 */
	public static EFormat fromString(String format) {
		if (format != null) {
			for (EFormat eFormat : EFormat.values()) {

				if (eFormat.name().equalsIgnoreCase(format)) {
					return eFormat;
				}
			}
		}

		throw new IllegalArgumentException("Unable to determine format by reported String");
	}
	
	/**
	 * Returns an unmodifiable list of images formats.
	 * 
	 * @return List of images
	 */
	public static List<EFormat> getImagesType() {
	
		List<EFormat> imageFormats = new ArrayList<EFormat>();
		imageFormats.add(GIF);
		imageFormats.add(BMP);
		imageFormats.add(PNG);
		imageFormats.add(JPEG);
		imageFormats.add(TIFF);
		
		return Collections.unmodifiableList(imageFormats);
	}
	
	/**
	 * Returns an unmodifiable list of offices formats.
	 * 
	 * @return List of offices
	 */
	public static List<EFormat> getOfficesType() {
	
		List<EFormat> officeFormats = new ArrayList<EFormat>();
		officeFormats.add(DOC);
		officeFormats.add(DOCX);
		officeFormats.add(XLS);
		officeFormats.add(XLSX);
		officeFormats.add(PPT);
		officeFormats.add(PPTX);
		officeFormats.add(ODS);
		officeFormats.add(ODP);
		officeFormats.add(SXW);
		officeFormats.add(SXC);
		officeFormats.add(SXI);
		officeFormats.add(RTF);
		
		return Collections.unmodifiableList(officeFormats);
	}	
}
