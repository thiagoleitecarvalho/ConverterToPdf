package org.convertertopdf.convert;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.convertertopdf.configuration.Configuration;
import org.convertertopdf.convert.implementation.PdfConverter;
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

public class PdfConverterTest extends BaseConverterTest {

	private String file;

	@Before
	public void init() {
		file = "file.pdf";
	}

	@AfterClass
	public static void cleanFiles() {
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "filePdfConverted1.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "filePdfConverted2.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "filePdfConverted3.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "filePdfConverted4.pdf"));
	}	
	
	@Test
	public void getFormat() {

		PdfConverter converterFor = (PdfConverter) ConverterManager.createConverterFor(EFormat.PDF);

		assertEquals(EFormat.PDF, converterFor.getFormat());
	}	
	
	@Test
	public void convertByFiles()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {
		
		ConverterManager
			.createConverterFor(EFormat.PDF)
			.from(getSource(file), getSource("file2.pdf"))
			.to(createFileDestiny("filePdfConverted1.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("filePdfConverted1.pdf"));
	}
	
	@Test
	public void convertByFile()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {
		
		ConverterManager
			.createConverterFor(EFormat.PDF)
			.from(getSource(file))
			.to(createFileDestiny("filePdfConverted2.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("filePdfConverted2.pdf"));
	}
	
	@Test
	public void convertByBytes() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		byte[] bytes = FileUtils.readFileToByteArray(getSource("file2.pdf"));
		
		ConverterManager
			.createConverterFor(EFormat.PDF)
			.from(bytes)
			.to(createFileDestiny("filePdfConverted3.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("filePdfConverted3.pdf"));
	}	
	
	@Test(expected = FileValidationException.class)
	public void convertFileValidationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		ConverterManager
			.createConverterFor(EFormat.PDF)
			.from(getSource(file))
			.convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedExceptionDestiny()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		ConverterManager
			.createConverterFor(EFormat.PDF)
			.from(getSource(file))
			.validade()
			.convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedExceptionSource()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.PDF)
			.to(createFileDestiny("filePdfConverted4.pdf"))
			.validade()
			.convert();
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void convertByBytesUnsupportedOperationException() throws PdfConverterException, FileValidationException, FileFormatException,
		FileNotInformedException, IOException {

		Configuration configurations = new Configuration() {
		};
		
		ConverterManager
			.createConverterFor(EFormat.PDF)
			.setConfigurations(configurations);
	}
	
}
