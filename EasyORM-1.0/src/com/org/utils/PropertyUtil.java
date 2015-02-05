package com.org.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

public class PropertyUtil {

	public static Properties readProper(String filePath) throws IOException {
		Properties p = new Properties();
		InputStream ins;
		ins = new FileInputStream(filePath);
		p.load(ins);
		return p;
	}

	public static Properties readProper(String filePath, ServletContext webContext) throws IOException {
		Properties p = new Properties();
		InputStream ins = null;
		if(webContext == null){
			ins = new FileInputStream(filePath);
		} else {
			ins = webContext.getResourceAsStream(filePath);
		}
		p.load(ins);
		return p;
	}
}
