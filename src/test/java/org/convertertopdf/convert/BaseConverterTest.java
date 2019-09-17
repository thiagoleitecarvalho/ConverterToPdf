package org.convertertopdf.convert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import com.lowagie.text.pdf.PdfReader;

public class BaseConverterTest {

	/**
	 * Test resource base folder.
	 */
	protected static final String SRC_TEST_RESOURCES = "./src/test/resources/";

	protected OutputStream createFileDestiny(String fileName) throws FileNotFoundException {
		
		String fileConverted = SRC_TEST_RESOURCES + fileName;
		return new FileOutputStream(fileConverted, true); 
	}
	
	/**
	 * Checks if the file was converted correctly.
	 * 
	 * @param fileName Name of the file converted
	 * @return true if file exist. False otherwise.
	 */
	protected boolean fileCorrupted(String fileName) {
		
		try {
			return new PdfReader(SRC_TEST_RESOURCES + fileName).isRebuilt();
		} catch (IOException e) {
			e.printStackTrace();			
			return true;
		}
	}
	
	
	/**
	 * Retunrs the file source to convert in PDF.
	 * 
	 * @param fileName Name of the file converted
	 * @return The file source to convert
	 */
	protected File getSource(String fileName) {
		
		URL resource = getResource(fileName);
		if (resource != null) {
			return new File(resource.getFile());
		}
		return null;
	}

	/**
	 * Finds a resource in the actual Classloader of current thread.
	 * 
	 * @param name Full-qualified name do resource.
	 * @return URL do Resource.
	 */
	private URL getResource(String name) {
		URL resource = getContextClassLoader().getResource(name);
		if (resource == null && name.startsWith("/")) {
			resource = getContextClassLoader().getResource(name.substring(1));
		}
		return resource;
	}

	/**
	 * Recover the {@link ClassLoader} to search resources and class.
	 * 
	 * @return {@link ClassLoader}
	 */
	private ClassLoader getContextClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}
}
