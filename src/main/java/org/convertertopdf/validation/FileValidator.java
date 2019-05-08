package org.convertertopdf.validation;

import java.io.File;
import java.io.IOException;

import org.convertertopdf.exception.FileFormatException;
import org.convertertopdf.exception.FileValidationException;
import org.convertertopdf.util.EFormat;
import org.convertertopdf.util.FormatUtils;

/**
 * Class that is responsible for validate format of files.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public final class FileValidator {

	/**
	 * Validates the format of file with the file passed.
	 * 
	 * @param file   File to detect the format.
	 * @param format The format to be checked in the file
	 * @throws FileFormatException     {@link FileFormatException}
	 * @throws FileValidationException {@link FileValidationException}
	 */
	public static void checkFormatFile(File file, EFormat format) throws FileFormatException, FileValidationException {

		try {

			String mineType = FormatUtils.checkFormat(file);

			EFormat eFormat = EFormat.fromMineType(mineType);

			if (!format.equals(eFormat))
				throw new FileFormatException("The file format is different from the converter.");

		} catch (IOException e) {
			throw new FileValidationException("Error determining file format.", e);
		} catch (IllegalArgumentException e) {
			throw new FileValidationException(e.getMessage());
		}

	}

	/**
	 * Validates the format of file with the file stream passed.
	 * 
	 * @param bytesFile Bytes for the file to detect the format.
	 * @param format    The format to be checked in the file
	 * @throws FileFormatException {@link FileFormatException}
	 */
	public static void checkFormatFile(byte[] bytesFile, EFormat format) throws FileFormatException {

		String mineType = FormatUtils.checkFormat(bytesFile);

		EFormat eFormat = EFormat.fromMineType(mineType);

		if (!format.equals(eFormat))
			throw new FileFormatException("The file format is different from the converter.");
	}
}
