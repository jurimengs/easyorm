package com.org.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.org.interfaces.SqlInterfaces;
import com.org.pool.ConnectionPool;
import com.org.pool.MyConnection;
import com.org.utils.ResultSetUtil;

public abstract class SqlDao<T> implements SqlInterfaces {

	/**
	 * 实体的实际类
	 */
	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public SqlDao() {
		Class<?> c = getClass();
		ParameterizedType ptype = null;

		do { // 遍历所有超类，直到找泛型定义
			try {
				ptype = (ParameterizedType) c.getGenericSuperclass();
			} catch (Exception e) {
			}
			c = c.getSuperclass();
		} while (ptype == null && c != null);

		if (ptype == null) {
		}
		Type[] params = ptype.getActualTypeArguments();
		entityClass = (Class<T>) params[0];

		System.out.println(entityClass);
	}

	private static MyConnection con = new MyConnection();
	static {
		try {
			ConnectionPool pool = ConnectionPool.getInstance();
			con = pool.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public boolean save(String sql) {
		boolean result = false;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			result = ps.execute();
		} catch (SQLException e) {
			result = false;
			System.out.println(e.getMessage());
		}
		System.out.println("add...");
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean save(String sql, Class[] paramsType, String... args) {
		boolean result = false;
		try {
			PreparedStatement ps = con.prepareStatement(sql, paramsType, args);
			result = ps.execute();
		} catch (SQLException e) {
			result = false;
			System.out.println(e.getMessage());
		}
		System.out.println("add...");
		return result;
	}

	@Override
	public boolean save(Object obj) {
		boolean result = false;
		return result;
	}

	@Override
	public void update() {
		System.out.println("update...");

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> query(String sql) {
		try {
			Object obj = entityClass.newInstance();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<T> list = (List<T>) ResultSetUtil.parseResultSet(rs, obj);
			return list;
		} catch (Exception e) {
			System.out.println("query..."+e.getMessage());
		}
		System.out.println("query...");
		return null;
	}

	@Override
	public Object query(Object obj) {
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object query(String sql, Class[] paramsType, String... args) {
		return null;
	}

}
