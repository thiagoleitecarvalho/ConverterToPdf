package org.convertertopdf.convert;

import java.io.File;
import java.net.URL;

public class BaseConverterTest {

	/**
	 * Finds a resource in the actual Classloader of current thread.
	 * 
	 * @param name Full-qualified name do resource.
	 * @return URL do Resource.
	 */
	protected URL getResource(String name) {
		URL resource = getContextClassLoader().getResource(name);
		if (resource == null && name.startsWith("/")) {
			resource = getContextClassLoader().getResource(name.substring(1));
		}
		return resource;
	}

	/**
	 * Finds a resource in the actual Classloader of current thread.
	 * 
	 * @param name Full-qualified name do resource.
	 * @return Resource em um InputStream.
	 */
	protected File getResourceAsFile(String name) {
		URL resource = getResource(name);
		if (resource != null) {
			return new File(resource.getFile());
		}
		return null;
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
