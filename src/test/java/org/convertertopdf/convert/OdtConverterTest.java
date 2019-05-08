package org.convertertopdf.convert;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.convertertopdf.configuration.Configuration;
import org.convertertopdf.convert.implementation.OdtConverter;
import org.convertertopdf.exception.FileFormatException;
import org.convertertopdf.exception.FileNotInformedException;
import org.convertertopdf.exception.FileValidationException;
import org.convertertopdf.exception.PdfConverterException;
import org.convertertopdf.management.ConverterManager;
import org.convertertopdf.util.EFormat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OdtConverterTest extends BaseConverterTest {

	private ConverterManager converterManager;

	private String file;

	@Before
	public void init() {
		converterManager = new ConverterManager();
		file = "file.odt";
	}

	@Test
	public void getFormat() {

		OdtConverter converterFor = (OdtConverter) converterManager.createFor(EFormat.ODT);

		assertEquals(EFormat.ODT, converterFor.getFormat());
	}

	@Test
	public void convertByFile()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile(file);

		byte[] result = converterManager.createFor(EFormat.ODT).setFile(source).validade().convert();

		Assert.assertNotNull(result);
	}

	@Test
	public void convertByByte() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		File source = getResourceAsFile(file);

		byte[] bytes = FileUtils.readFileToByteArray(source);

		byte[] result = converterManager.createFor(EFormat.ODT).setFile(bytes).validade().convert();

		Assert.assertNotNull(result);
	}

	@Test(expected = FileFormatException.class)
	public void convertFileFormatException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile("file.txt");

		converterManager.createFor(EFormat.ODT).setFile(source).validade().convert();
	}

	@Test(expected = FileValidationException.class)
	public void convertFileValidationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile(file);

		converterManager.createFor(EFormat.ODT).setFile(source).convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		converterManager.createFor(EFormat.ODT).validade().convert();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void setConfigurationUnsupportedOperationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		OdtConverter odtConverter = (OdtConverter) converterManager.createFor(EFormat.ODT);
		odtConverter.setConfigurations(new Configuration() { });
	}

}
