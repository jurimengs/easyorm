package com.org.interfaces;

import java.util.List;


public interface SqlInterfaces {
	public boolean save(String sql);

	public boolean save(String sql, Class<?>[] paramsType, String... args);

	public boolean save(Object obj);

	public void update();

	public void delete(String id);

	public <T> List<T> query(String sql);

	public Object query(String sql, Class<?>[] paramsType, String... args);

	public Object query(Object obj);
}
