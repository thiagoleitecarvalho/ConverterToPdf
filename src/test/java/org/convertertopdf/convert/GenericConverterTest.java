package org.convertertopdf.convert;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.commons.io.FileUtils;
import org.convertertopdf.exception.FileNotInformedException;
import org.convertertopdf.exception.PdfConverterException;
import org.convertertopdf.management.ConverterManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GenericConverterTest extends BaseConverterTest {

	File fileTxt;
	File fileTiff;
	File fileHtml;
	File fileOdt;
	File fileJpg;
	File filePdf;
	File filePdf2;

	@Before
	public void init() {
		
		fileTxt = getSource("file.txt");
		fileTiff = getSource("file.tif");
		fileHtml = getSource("file.html");
		fileOdt = getSource("file.odt");
		fileJpg = getSource("file.jpg");
		filePdf = getSource("file.pdf");
		filePdf2 = getSource("file2.pdf");
	}

	@AfterClass
	public static void cleanFiles() {
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "genericFileConverted.pdf"));
	}	
	
	@Test
	public void convert() throws PdfConverterException, FileNotFoundException, FileNotInformedException {

		ConverterManager
			.createGenericConverter()
			.from(fileTxt, filePdf2, fileTiff, fileHtml, fileOdt, fileTxt, filePdf, fileJpg)
			.to(createFileDestiny("genericFileConverted.pdf"))
			.convert();
		
		Assert.assertFalse(fileCorrupted("genericFileConverted.pdf"));
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFromNotInformed() throws PdfConverterException, FileNotFoundException, FileNotInformedException {

		ConverterManager
			.createGenericConverter()
			.to(createFileDestiny("genericFileConverted.pdf"))
			.convert();
	}
	
	
	@Test(expected = FileNotInformedException.class)
	public void convertToNotInformed() throws PdfConverterException, FileNotInformedException {

		ConverterManager
			.createGenericConverter()
			.from(fileTxt, filePdf2, fileTiff, fileHtml, fileOdt, fileTxt, filePdf, fileJpg)
			.convert();
	}
	
	@Test(expected = FileNotInformedException.class)
	public void convertFromNotInformedToNullFromEmpty() throws PdfConverterException, FileNotFoundException, FileNotInformedException {

		File[] files = new File[] {};
		ConverterManager
			.createGenericConverter()
			.from(files)
			.to(null)
			.convert();
	}	
}	
	