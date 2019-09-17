package org.convertertopdf.convert;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
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
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OdtConverterTest extends BaseConverterTest {

	private String file;

	@Before
	public void init() {
		file = "file.odt";
	}

	@AfterClass
	public static void cleanFiles() {
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileOdtConverted1.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileOdtConverted2.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileOdtConverted3.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileOdtConverted4.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileOdtConverted5.pdf"));
	}
	
	@Test
	public void getFormat() {

		OdtConverter converterFor = (OdtConverter) ConverterManager.createConverterFor(EFormat.ODT);

		assertEquals(EFormat.ODT, converterFor.getFormat());
	}

	@Test
	public void convertByFile()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.ODT)
			.from(getSource(file))
			.to(createFileDestiny("fileOdtConverted1.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileOdtConverted1.pdf"));
	}

	@Test
	public void convertByBytes() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		byte[] bytes = FileUtils.readFileToByteArray(getSource(file));

		ConverterManager
			.createConverterFor(EFormat.ODT)
			.from(bytes)
			.to(createFileDestiny("fileOdtConverted2.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileOdtConverted2.pdf"));
	}

	@Test(expected = FileFormatException.class)
	public void convertFileFormatException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.ODT)
			.from(getSource("file.txt"))
			.to(createFileDestiny("fileOdtConverted5.pdf"))
			.validade();
	}

	@Test(expected = FileValidationException.class)
	public void convertFileValidationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.ODT)
			.from(getSource(file))
			.to(createFileDestiny("fileOdtConverted3.pdf"))
			.convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedExceptionSource()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.ODT)
			.to(createFileDestiny("fileOdtConverted4.pdf"))
			.validade()
			.convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedExceptionDestiny()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		ConverterManager
			.createConverterFor(EFormat.ODT)
			.from(getSource(file))
			.validade()
			.convert();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void setConfigurationUnsupportedOperationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		OdtConverter odtConverter = (OdtConverter) ConverterManager.createConverterFor(EFormat.ODT);
		odtConverter.setConfigurations(new Configuration() {
		});
	}
}
