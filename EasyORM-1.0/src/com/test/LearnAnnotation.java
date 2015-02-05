package com.test;

import com.org.annotations.Entity;

public class LearnAnnotation {
	public static void main(String[] args) {
		ATest test = new ATest();
		System.out.println(test.getClass().isAnnotation());
		System.out.println(test.getClass().isAnnotationPresent(Entity.class));
		Entity a = test.getClass().getAnnotation(Entity.class);
		String tableName = a.tableName();
		System.out.println(tableName);
	}
}
