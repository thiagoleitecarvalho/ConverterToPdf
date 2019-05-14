package org.convertertopdf.convert;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.convertertopdf.configuration.implementation.TxtImageConfiguration;
import org.convertertopdf.convert.implementation.TxtConverter;
import org.convertertopdf.exception.FileFormatException;
import org.convertertopdf.exception.FileNotInformedException;
import org.convertertopdf.exception.FileValidationException;
import org.convertertopdf.exception.PdfConverterException;
import org.convertertopdf.management.ConverterManager;
import org.convertertopdf.util.EFormat;
import org.convertertopdf.util.EOrientation;
import org.convertertopdf.util.EPageSize;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TxtConverterTest extends BaseConverterTest {

	private ConverterManager converterManager;

	private String file;

	@Before
	public void init() {
		converterManager = new ConverterManager();
		file = "file.txt";
	}

	@Test
	public void getFormat() {

		TxtConverter converterFor = (TxtConverter) converterManager.createFor(EFormat.TXT);

		assertEquals(EFormat.TXT, converterFor.getFormat());
	}

	@Test
	public void convertByFile()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile(file);

		TxtImageConfiguration configuration = new TxtImageConfiguration();
		configuration.setPageSize(EPageSize.A4);

		byte[] result = converterManager.createFor(EFormat.TXT).setFile(source).setConfigurations(configuration)
				.validade().convert();

		Assert.assertNotNull(result);
	}

	@Test
	public void convertByBytes() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		File source = getResourceAsFile(file);

		byte[] bytes = FileUtils.readFileToByteArray(source);

		TxtImageConfiguration configuration = new TxtImageConfiguration();
		configuration.setPageSize(EPageSize.A4);

		byte[] result = converterManager.createFor(EFormat.TXT).setFile(bytes).setConfigurations(configuration)
				.validade().convert();

		Assert.assertNotNull(result);
	}

	@Test(expected = FileValidationException.class)
	public void convertFileValidationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile(file);

		converterManager.createFor(EFormat.TXT).setFile(source).convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		converterManager.createFor(EFormat.TXT).validade().convert();
	}

	@Test
	public void convertByFilePortrait()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		TxtConverter txtConverter = (TxtConverter) converterManager.createFor(EFormat.TXT);
		Assert.assertTrue(txtConverter.getConfigurations().isPortrait());
	}

	@Test
	public void convertByFileLandscape()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile(file);

		TxtImageConfiguration configuration = new TxtImageConfiguration();
		configuration.setOrientation(EOrientation.LANDSCAPE);

		TxtConverter txtConverter = (TxtConverter) converterManager.createFor(EFormat.TXT);
		txtConverter.setFile(source).setConfigurations(configuration).validade().convert();

		Assert.assertFalse(txtConverter.getConfigurations().isPortrait());
	}

	@Test(expected = PdfConverterException.class)
	public void convertByBytesExceptionConverter() throws PdfConverterException, FileValidationException,
			FileFormatException, FileNotInformedException, IOException {

		File source = getResourceAsFile(file);

		byte[] bytes = FileUtils.readFileToByteArray(source);

		TxtImageConfiguration configuration = new TxtImageConfiguration();
		configuration.setPageSize(EPageSize.A4);

		converterManager.createFor(EFormat.TXT).setFile(bytes).setConfigurations(configuration).validade();

		bytes = new byte[] {};
		converterManager.getConverter().setFile(bytes).convert();
	}

	@Test
	public void convertByBytesPdfConverterException()
			throws FileValidationException, FileFormatException, FileNotInformedException, IOException {

		File source = getResourceAsFile(file);

		byte[] bytes = FileUtils.readFileToByteArray(source);

		TxtImageConfiguration configuration = new TxtImageConfiguration();
		configuration.setPageSize(EPageSize.A4);

		converterManager.createFor(EFormat.TXT).setFile(bytes).setConfigurations(configuration).validade();

		bytes = new byte[] {};

		try {
			converterManager.getConverter().setFile(bytes).convert();
		} catch (PdfConverterException e) {
			assertEquals(e.getMessage(), PdfConverterException.formatMessage(EFormat.TXT));
		}
	}
}
