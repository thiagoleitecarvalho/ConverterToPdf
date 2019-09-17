package org.convertertopdf.util;

import java.io.File;
import java.io.IOException;

import org.apache.tika.Tika;

/**
 * Utility class for determinate the file format.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public final class FormatUtils {

	private FormatUtils() {
	}

	/**
	 * Returns the type of file.
	 * 
	 * @param file File to detect the format.
	 * @return {@link EFormat} that represents the file format
	 * @throws IOException {@link IOException}
	 */
	public static EFormat getType(File file) throws IOException {
		
		String mimeType = checkFormat(file);
		
		return EFormat.fromMineType(mimeType);
	}

	/**
	 * Returns the type of file.
	 * 
	 * @param bytesFile Bytes which represents the file to detect the format.
	 * @return {@link EFormat} that represents the file format
	 */
	public static EFormat getType(byte[] bytesFile) {
		
		String mimeType = checkFormat(bytesFile);
		
		return EFormat.fromMineType(mimeType);
	}

	/**
	 * Returns the mime type of file.
	 * 
	 * @param file File to detect the mime type.
	 * @return String that represents the mine type of file
	 * @throws IOException {@link IOException}
	 */
	public static String getMimeType(File file) throws IOException {
		
		return checkFormat(file);
	}
	
	/**
	 * Returns the mime type of file.
	 * 
	 * @param bytesFile Bytes which represents the file to detect the mime type.
	 * @return String that represents the mine type of file
	 */
	public static String getMimeType(byte[] bytesFile) {
		
		return checkFormat(bytesFile);
	}
	
	/**
	 * Checks if the file is an image.
	 * 
	 * @param file File to check if it is an image.
	 * @return true if the file is an image. false otherwise
	 * @throws IOException {@link IOException}
	 */
	public static boolean isImage(File file) throws IOException {

		String mimeType = checkFormat(file);
		
		EFormat format = EFormat.fromMineType(mimeType);

		return EFormat.getImagesType().indexOf(format) != -1;		
	}

	/**
	 * Checks if the file is an image.
	 * 
	 * @param bytesFile Bytes to check if it is an image.
	 * @return String that represents the format
	 */
	public static boolean isImage(byte[] bytesFile) {

		String mimeType = checkFormat(bytesFile);
		
		EFormat format = EFormat.fromMineType(mimeType);

		return EFormat.getImagesType().indexOf(format) != -1;		
	}
	
	/**
	 * Checks if the file is an office.
	 * 
	 * @param file File to check if it is an office.
	 * @return true if the file is an office. false otherwise
	 * @throws IOException {@link IOException}
	 */
	public static boolean isOffice(File file) throws IOException {

		String mimeType = checkFormat(file);
		
		EFormat format = EFormat.fromMineType(mimeType);

		return EFormat.getOfficesType().indexOf(format) != -1;		
	}

	/**
	 * Checks if the file is an office.
	 * 
	 * @param bytesFile Bytes to check if it is an office.
	 * @return true if the file is an office. false otherwise 
	 */
	public static boolean isOffice(byte[] bytesFile) {

		String mimeType = checkFormat(bytesFile);
		
		EFormat format = EFormat.fromMineType(mimeType);

		return EFormat.getOfficesType().indexOf(format) != -1;		
	}	
	
	/**
	 * Checks if the file is a TXT.
	 * 
	 * @param file File to check if it is a TXT.
	 * @return true if the file is a TXT. false otherwise
	 * @throws IOException {@link IOException}
	 */
	public static boolean isTxt(File file) throws IOException {

		String mimeType = checkFormat(file);
		
		EFormat format = EFormat.fromMineType(mimeType);

		return EFormat.TXT.equals(format);		
	}

	/**
	 * Checks if the file is an TXT.
	 * 
	 * @param bytesFile Bytes to check if it is a TXT.
	 * @return true if the file is a TXT. false otherwise
	 */
	public static boolean isTxt(byte[] bytesFile) {

		String mimeType = checkFormat(bytesFile);
		
		EFormat format = EFormat.fromMineType(mimeType);

		return EFormat.TXT.equals(format);		
	}	
	
	/**
	 * Checks if the file is a isHtml.
	 * 
	 * @param file File to check if it is a HTML.
	 * @return true if the file is a HTML. false otherwise
	 * @throws IOException {@link IOException}
	 */
	public static boolean isHtml(File file) throws IOException {

		String mimeType = checkFormat(file);
		
		EFormat format = EFormat.fromMineType(mimeType);

		return EFormat.HTML.equals(format);		
	}

	/**
	 * Checks if the file is an isHtml.
	 * 
	 * @param bytesFile Bytes to check if it is an HTML.
	 * @return true if the file is a HTML. false otherwise
	 */
	public static boolean isHtml(byte[] bytesFile) {

		String mimeType = checkFormat(bytesFile);
		
		EFormat format = EFormat.fromMineType(mimeType);

		return EFormat.HTML.equals(format);		
	}	

	/**
	 * Checks if the file is a pdf.
	 * 
	 * @param file File  to check if it is an PDF.
	 * @return true if the file is a PDF. false otherwise
	 * @throws IOException {@link IOException}
	 */
	public static boolean isPdf(File file) throws IOException {

		String mimeType = checkFormat(file);
		
		EFormat format = EFormat.fromMineType(mimeType);

		return EFormat.PDF.equals(format);		
	}

	/**
	 * Checks if the file is an pdf.
	 * 
	 * @param bytesFile Bytes to check if it is an HTML.
	 * @return true if the file is a PDF. false otherwise
	 */
	public static boolean isPdf(byte[] bytesFile) {

		String mimeType = checkFormat(bytesFile);
		
		EFormat format = EFormat.fromMineType(mimeType);

		return EFormat.PDF.equals(format);		
	}
	
	/**
	 * Detects the media type of file.
	 * 
	 * @param file File to detect the media type.
	 * @return String that represents the format
	 * @throws IOException {@link IOException}
	 */
	private static String checkFormat(File file) throws IOException {

		Tika tika = new Tika();

		String detected = tika.detect(file);

		return detected;
	}

	/**
	 * Detects the media type of file.
	 * 
	 * @param bytesFile Bytes which represents the file to detect the media type.
	 * @return String that represents the format
	 */
	private static String checkFormat(byte[] bytesFile) {

		Tika tika = new Tika();

		String detected = tika.detect(bytesFile);

		return detected;
	}

}