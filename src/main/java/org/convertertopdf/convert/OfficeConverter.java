package org.convertertopdf.convert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.convertertopdf.configuration.Configuration;
import org.convertertopdf.exception.FileValidationException;
import org.convertertopdf.exception.PdfConverterException;
import org.jodconverter.JodConverter;
import org.jodconverter.office.LocalOfficeManager;
import org.jodconverter.office.OfficeException;
import org.jodconverter.office.OfficeUtils;

/**
 * Abstract class that is responsible for providing operations to convert office
 * files files to PDF. Office files: doc, docx, odt, rtf files to PDF.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public abstract class OfficeConverter extends AbstractConverter {

	/** {@inheritDoc} */
	@Override
	public AbstractConverter setConfigurations(Configuration configurations) {
		throw new UnsupportedOperationException("OfficeConverter doesn't support settings yet.");
	}

	/** {@inheritDoc} */
	public byte[] convert() throws FileValidationException, PdfConverterException {

		final LocalOfficeManager officeManager = LocalOfficeManager.install();
		File tempFileWord = null;
		File tempFilePDF = null;

		try {

			if (!isValidated()) {
				throw new FileValidationException("The file has not been validated.");
			}

			// doc/docx temp
			tempFileWord = File.createTempFile(UUID.randomUUID().toString(), getFormat().getExtension());

			byte[] bytes = null;

			if (file != null) {

				bytes = Files.readAllBytes(file.toPath());
			} else {
				bytes = bytesFile;
			}

			Files.write(tempFileWord.toPath(), bytes, StandardOpenOption.WRITE);

			// pdf temp
			tempFilePDF = File.createTempFile(UUID.randomUUID().toString(), ".pdf");

			officeManager.start();

			JodConverter.convert(tempFileWord).to(tempFilePDF).execute();

			return Files.readAllBytes(tempFilePDF.toPath());
		} catch (IOException e) {
			throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
		} catch (OfficeException e) {
			throw new PdfConverterException(PdfConverterException.formatMessage(getFormat()), e);
		} finally {

			FileUtils.deleteQuietly(tempFilePDF);
			FileUtils.deleteQuietly(tempFileWord);

			if (officeManager.isRunning()) {
				OfficeUtils.stopQuietly(officeManager);
			}

		}
	}

}