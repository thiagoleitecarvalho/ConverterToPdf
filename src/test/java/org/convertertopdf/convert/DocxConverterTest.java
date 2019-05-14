package org.convertertopdf.convert;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.convertertopdf.configuration.Configuration;
import org.convertertopdf.convert.implementation.DocxConverter;
import org.convertertopdf.exception.FileFormatException;
import org.convertertopdf.exception.FileNotInformedException;
import org.convertertopdf.exception.FileValidationException;
import org.convertertopdf.exception.PdfConverterException;
import org.convertertopdf.management.ConverterManager;
import org.convertertopdf.util.EFormat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DocxConverterTest extends BaseConverterTest {

	private ConverterManager converterManager;

	private String file;

	@Before
	public void init() {
		converterManager = new ConverterManager();
		file = "file.docx";
	}

	@Test
	public void getFormat() {

		DocxConverter converterFor = (DocxConverter) converterManager.createFor(EFormat.DOCX);

		assertEquals(EFormat.DOCX, converterFor.getFormat());
	}

	@Test
	public void convertByFile()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile(file);

		byte[] result = converterManager.createFor(EFormat.DOCX).setFile(source).validade().convert();

		Assert.assertNotNull(result);
	}

	@Test
	public void convertByBytes() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		File source = getResourceAsFile(file);

		byte[] bytes = FileUtils.readFileToByteArray(source);

		byte[] result = converterManager.createFor(EFormat.DOCX).setFile(bytes).validade().convert();

		Assert.assertNotNull(result);
	}

	@Test(expected = FileFormatException.class)
	public void convertFileFormatException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile("file.txt");

		converterManager.createFor(EFormat.DOCX).setFile(source).validade().convert();
	}

	@Test(expected = FileValidationException.class)
	public void convertFileValidationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile(file);

		converterManager.createFor(EFormat.DOCX).setFile(source).convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		converterManager.createFor(EFormat.DOCX).validade().convert();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void setConfigurationUnsupportedOperationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		DocxConverter docxConverter = (DocxConverter) converterManager.createFor(EFormat.DOCX);
		docxConverter.setConfigurations(new Configuration() {
		});
	}

}
