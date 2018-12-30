package com.kaisn.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourceReaderUtil {

	public static String loadTemplate(String resource) {
		StringBuffer buffer = new StringBuffer();
		String stripped = resource.startsWith("/") ? resource.substring(1) : resource;
		InputStream stream = null;
		InputStreamReader isr=null;
		BufferedReader bufr=null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader != null) {
			stream = classLoader.getResourceAsStream(stripped);
		}
		if (stream == null) {
			stream = ResourceReaderUtil.class.getResourceAsStream(resource);
		}
		if (stream == null) {
			stream = ResourceReaderUtil.class.getClassLoader().getResourceAsStream(stripped);
		}
		if (stream == null) {
			throw new RuntimeException(resource + " not found");
		}
		isr = new InputStreamReader(stream);
		bufr = new BufferedReader(isr);
		String line = null;
		try {
			while ((line = bufr.readLine()) != null) {
				buffer.append(line);
				buffer.append("\r\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(stream!=null){
					stream.close();
				}
				if(isr!=null){
					isr.close();
				}
				if(bufr!=null){
					bufr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return buffer.toString();
	}
}
