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
	 * Returns the format of file.
	 * 
	 * @param file File to detect the format.
	 * @return String that represents the format
	 * @throws IOException {@link IOException}
	 */
	public static String checkFormat(File file) throws IOException {

		Tika tika = new Tika();

		String detected = tika.detect(file);

		return detected;
	}

	/**
	 * Returns the format of file.
	 * 
	 * @param bytesFile Bytes which represents the file to detect the format.
	 * @return String that represents the format
	 */
	public static String checkFormat(byte[] bytesFile) {

		Tika tika = new Tika();

		String detected = tika.detect(bytesFile);

		return detected;
	}
}
