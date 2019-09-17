package org.convertertopdf.convert;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.convertertopdf.configuration.Configuration;
import org.convertertopdf.convert.implementation.HtmlConverter;
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

public class HtmlConverterTest extends BaseConverterTest {

	private String file;

	@Before
	public void init() {
		file = "file.html";
	}

	@AfterClass
	public static void cleanFiles() {
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileHtmlConverted1.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileHtmlConverted2.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileHtmlConverted3.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileHtmlConverted4.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileHtmlConverted5.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileHtmlConverted7.pdf"));
	}

	@Test
	public void getFormat() {

		HtmlConverter converterFor = (HtmlConverter) ConverterManager.createConverterFor(EFormat.HTML);

		assertEquals(EFormat.HTML, converterFor.getFormat());
	}

	@Test
	public void convertByFiles()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.HTML)
			.from(getSource(file), getSource("file1.html"))
			.to(createFileDestiny("fileHtmlConverted7.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileHtmlConverted7.pdf"));
	}
	
	@Test
	public void convertByFile()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.HTML)
			.from(getSource(file))
			.to(createFileDestiny("fileHtmlConverted1.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileHtmlConverted1.pdf"));
	}

	@Test
	public void convertByBytes() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		byte[] bytes = FileUtils.readFileToByteArray(getSource(file));

		ConverterManager
			.createConverterFor(EFormat.HTML)
			.from(bytes)
			.to(createFileDestiny("fileHtmlConverted2.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileHtmlConverted2.pdf"));
	}

	@Test(expected = FileValidationException.class)
	public void convertFileValidationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.HTML)
			.from(getSource(file))
			.to(createFileDestiny("fileHtmlConverted3.pdf"))
			.convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedExceptionSource()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.HTML)
			.to(createFileDestiny("fileHtmlConverted3.pdf"))
			.validade()
			.convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedExceptionDestiny()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		ConverterManager
			.createConverterFor(EFormat.HTML)
			.from(getSource(file))
			.validade()
			.convert();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void convertByBytesUnsupportedOperationException() throws PdfConverterException, FileValidationException, FileFormatException,
		FileNotInformedException, IOException {

		Configuration configurations = new Configuration() {
		};
		
		ConverterManager
			.createConverterFor(EFormat.HTML)
			.setConfigurations(configurations);
	}
}
