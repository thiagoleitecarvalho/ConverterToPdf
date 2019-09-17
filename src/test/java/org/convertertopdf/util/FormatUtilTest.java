package org.convertertopdf.util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.convertertopdf.convert.BaseConverterTest;
import org.junit.Test;

public class FormatUtilTest extends BaseConverterTest {

	@Test
	public void getTypeTrueFile() throws IOException {
		
		assertTrue(EFormat.GIF.equals(FormatUtils.getType(getSource("file.gif"))));
	}
	
	@Test
	public void isTypeFalseByte() throws IOException {
		
		File source = getSource("file.jpg");
		
		byte[] bytes = Files.readAllBytes(source.toPath());
		
		assertFalse(EFormat.DOC.equals(FormatUtils.getType(bytes)));
	}
	
	@Test
	public void isImageTrueFile() throws IOException {
		
		assertTrue(FormatUtils.isImage(getSource("file.gif")));
	}
	
	@Test
	public void isImageFalseFile() throws IOException {
		
		assertFalse(FormatUtils.isImage(getSource("file.doc")));
	}
	
	@Test
	public void isImageTrueByte() throws IOException {
		
		File source = getSource("file.jpg");
		
		byte[] bytes = Files.readAllBytes(source.toPath());
		
		assertTrue(FormatUtils.isImage(bytes));
	}
	
	@Test
	public void isImageFalseByte() throws IOException {
		
		File source = getSource("file.doc");
		
		byte[] bytes = Files.readAllBytes(source.toPath());
		
		assertFalse(FormatUtils.isImage(bytes));
	}
	
	@Test
	public void isOfficeTrueFile() throws IOException {
		
		assertTrue(FormatUtils.isOffice(getSource("file.docx")));
	}
	
	@Test
	public void isOfficeFalseFile() throws IOException {
		
		assertFalse(FormatUtils.isOffice(getSource("file.png")));
	}
	
	@Test
	public void isOfficeTrueByte() throws IOException {
		
		File source = getSource("file.ppt");
		
		byte[] bytes = Files.readAllBytes(source.toPath());
		
		assertTrue(FormatUtils.isOffice(bytes));
	}
	
	@Test
	public void isOfficeFalseByte() throws IOException {
		
		File source = getSource("file.png");
		
		byte[] bytes = Files.readAllBytes(source.toPath());
		
		assertFalse(FormatUtils.isOffice(bytes));
	}	
	
	@Test
	public void isTxtTrueFile() throws IOException {
		
		assertTrue(FormatUtils.isTxt(getSource("file.txt")));
	}
	
	@Test
	public void isTxtFalseFile() throws IOException {
		
		assertFalse(FormatUtils.isTxt(getSource("file.html")));
	}

	@Test
	public void isTxtTrueByte() throws IOException {
		
		File source = getSource("file.txt");
		
		byte[] bytes = Files.readAllBytes(source.toPath());
		
		assertTrue(FormatUtils.isTxt(bytes));
	}
	
	@Test
	public void isTxtFalseByte() throws IOException {
		
		File source = getSource("file.html");
		
		byte[] bytes = Files.readAllBytes(source.toPath());
		
		assertFalse(FormatUtils.isTxt(bytes));
	}	
	
	@Test
	public void isHtmlTrueFile() throws IOException {
		
		assertTrue(FormatUtils.isHtml(getSource("file.html")));
	}
	
	@Test
	public void isHtmlFalseFile() throws IOException {
		
		assertFalse(FormatUtils.isHtml(getSource("file.txt")));
	}

	@Test
	public void isHtmlTrueByte() throws IOException {
		
		File source = getSource("file.html");
		
		byte[] bytes = Files.readAllBytes(source.toPath());
		
		assertTrue(FormatUtils.isHtml(bytes));
	}
	
	@Test
	public void isHtmlFalseByte() throws IOException {
		
		File source = getSource("file.txt");
		
		byte[] bytes = Files.readAllBytes(source.toPath());
		
		assertFalse(FormatUtils.isHtml(bytes));
	}
	
	@Test
	public void isPdfFalseByte() throws IOException {
		
		File source = getSource("file.html");
		
		byte[] bytes = Files.readAllBytes(source.toPath());
		
		assertFalse(FormatUtils.isPdf(bytes));
	}
	
	@Test
	public void isPdfTrueFile() throws IOException {
		
		File source = getSource("file.pdf");
		
		assertTrue(FormatUtils.isPdf(source));
	}	

	@Test
	public void getMimeTypeTrueFile() throws IOException {
		
		assertTrue(EFormat.PNG.getMimeType()[0].equals(FormatUtils.getMimeType(getSource("file.png"))));
	}
	
	@Test
	public void isMimeTypeFalseByte() throws IOException {
		
		File source = getSource("file.odt");
		
		byte[] bytes = Files.readAllBytes(source.toPath());
		
		assertFalse(EFormat.PDF.getMimeType()[0].equals(FormatUtils.getMimeType(bytes)));
	}
}
