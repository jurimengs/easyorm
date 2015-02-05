package com.org.utils.schema;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;

import com.org.annotations.Entity;
import com.org.interfaces.SqlInterfaces;
import com.org.pool.ConnectionPool;
import com.org.pool.MyConnection;
import com.org.utils.ResultSetUtil;

public class CommonDao implements SqlInterfaces {

	private Class<?> entityClass;

	private static MyConnection con = new MyConnection();
	private static ConnectionPool pool = null;

	public CommonDao(Class<?> c) {
		this.entityClass = c;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getConnection();
			if(con == null){
				System.out.println("Connections in Pool were all in used");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("CommonDao local app construction exception");
		}
	}

	public CommonDao(Class<?> c, ServletContext webContext) {
		this.entityClass = c;
		try {
			pool = ConnectionPool.getInstance(webContext);
			con = pool.getConnection();
		} catch (SQLException e1) {
			System.out.println("CommonDao Webapp construction exception");
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

	@Override
	public boolean save(String sql, Class<?>[] paramsType, String... args) {
		boolean result = false;
		try {
			PreparedStatement ps = con.prepareStatement(sql, paramsType, args);
			result = ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(con.getIndex());
		pool.releaseConnection(con);
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

	@Override
	public void delete(String id) {
		try {
			Object obj = entityClass.newInstance();
			Entity a = obj.getClass().getAnnotation(Entity.class);
			String tableName = a.tableName();
			String sql = "delete from "+tableName+" where id = ?";
//			PreparedStatement ps = con.prepareStatement(sql);
			PreparedStatement ps = con.prepareStatement(sql, new Class[]{String.class}, id);
			ps.execute();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public <T> List<T> query(String sql) {
		System.out.println("query...");
		try {
			Object obj = entityClass.newInstance();
			ResultSet rs = getResultSet(sql);
			List<T> list = (List<T>) ResultSetUtil.parseResultSet(rs, obj);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Object> queryObject(String sql) {
		System.out.println("query...");
		try {
			Object obj = entityClass.newInstance();
			ResultSet rs = getResultSet(sql);
			return ResultSetUtil.parseResultSet(rs, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet getResultSet(String sql) throws SQLException{
		if(con == null){
			System.out.println("Connections in Pool were all in used");
			throw new SQLException("Connections in Pool were all in used");
		}
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		return rs;
	}

	@Override
	public Object query(Object obj) {
		return false;
	}

	@Override
	public Object query(String sql, Class<?>[] paramsType, String... args) {
		return null;
	}

}
