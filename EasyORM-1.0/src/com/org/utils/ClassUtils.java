package com.org.utils;

public class ClassUtils {
	public static Object loadClass(String className){
		Object obj = null;
		try {
			obj = Class.forName(className);
		} catch (ClassNotFoundException e) {
			System.out.println("没找到Class: " + className);
			return null;
		}
		return obj;
	}
}
