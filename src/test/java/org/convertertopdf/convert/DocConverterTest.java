package org.convertertopdf.convert;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.convertertopdf.configuration.Configuration;
import org.convertertopdf.convert.implementation.DocConverter;
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

public class DocConverterTest extends BaseConverterTest {

	private String file;

	@Before
	public void init() {
		file = "file.doc";
	}

	@AfterClass
	public static void cleanFiles() {
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileDocConverted1.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileDocConverted2.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileDocConverted3.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileDocConverted4.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileDocConverted5.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileDocConverted6.pdf"));
	}
	
	@Test
	public void getFormat() {

		DocConverter converterFor = (DocConverter) ConverterManager.createConverterFor(EFormat.DOC);

		assertEquals(EFormat.DOC, converterFor.getFormat());
	}

	@Test
	public void convertByFile()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.DOC)
			.from(getSource(file))
			.to(createFileDestiny("fileDocConverted1.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileDocConverted1.pdf"));
	}

	@Test
	public void convertByFiles()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.DOC)
			.from(getSource(file), getSource("file1.doc"), getSource("file2.doc"))
			.to(createFileDestiny("fileDocConverted6.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileDocConverted6.pdf"));
	}
	
	@Test
	public void convertByBytes() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		byte[] bytes = FileUtils.readFileToByteArray(getSource(file));

		ConverterManager
			.createConverterFor(EFormat.DOC)
			.from(bytes)
			.to(createFileDestiny("fileDocConverted2.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileDocConverted2.pdf"));
	}

	@Test(expected = FileFormatException.class)
	public void convertFileFormatException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.DOC)
			.from(getSource("file.txt"))
			.to(createFileDestiny("fileDocConverted5.pdf"))
			.validade();
	}

	@Test(expected = FileValidationException.class)
	public void convertFileValidationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.DOC)
			.from(getSource(file))
			.to(createFileDestiny("fileDocConverted3.pdf"))
			.convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedExceptionSource()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.DOC)
			.to(createFileDestiny("fileDocConverted4.pdf"))
			.validade()
			.convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedExceptionDestiny()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		ConverterManager
			.createConverterFor(EFormat.DOC)
			.from(getSource(file))
			.validade()
			.convert();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void setConfigurationUnsupportedOperationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		DocConverter docConverter = (DocConverter) ConverterManager.createConverterFor(EFormat.DOC);
		docConverter.setConfigurations(new Configuration() {
		});
	}
}
