package com.org.pool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;

import com.org.utils.PropertyUtil;

/**
 * TODO 池中的资源得有一个开 和 关的状态
 * 
 * @author Administrator
 */
public class ConnectionPool {
	private List<MyConnection> list = new ArrayList<MyConnection>();
	private static ConnectionPool pool = null;
	private ServletContext webContext = null;
	private static int FIRST = 0;

	private ConnectionPool() {
	}
	
	private ConnectionPool(ServletContext webContext) {
		this.webContext = webContext;
	}

	public static ConnectionPool getInstance() {
		if (pool == null) {
			pool = new ConnectionPool();
			initConnectionPool(5,pool);
		}
		return pool;
	}
	
	
	public static ConnectionPool getInstance(ServletContext webContext) {
		if (pool == null) {
			pool =  new ConnectionPool(webContext);
			initConnectionPool(5,pool);
		}
		return pool;
	}

	/**
	 * 
	 * @param index
	 * @return
	 * @throws SQLException
	 */
	public synchronized MyConnection getConnection() throws SQLException {
		MyConnection con = null;
		if(list.size()>0){
			con = list.get(FIRST);
			con.setClosed(false);
			System.out.println(con.getIndex());
			list.remove(FIRST);
		}
		return con;
	}
	
	/**
	 * 初始化连接池
	 * 
	 * @param conCounts
	 */
	private static void initConnectionPool(int conCounts, ConnectionPool pool) {
		if(pool.list != null && pool.list.size() > 0){
			return ;
		}
		Properties p = null;
		String url = "";
		try {
			// 本地版
			p = PropertyUtil.readProper("src/jdbc.properties", pool.webContext);
			// web版
//			p = PropertyUtil.readProper("/WEB-INF/config/jdbc.properties", pool.webContext);
			url = p.getProperty("url");
//			System.out.println("debug: " + p + " url " + url +" drivername "+ p.getProperty("drivername"));
			// 加载驱动类到内存
			Class.forName(p.getProperty("drivername"));
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: can not find driver class");
			return ;
		}
		
		for (int i = 0; i < conCounts; i++) {
			MyConnection conn = new MyConnection();
			conn.setCon(createConnection(url, p));
			// 初始化的时候默认资源可用
			conn.setClosed(false);
			conn.setIndex("serialize:"+i);
			
			pool.list.add(conn);
		}
	}

	/**
	 * 创建连接
	 * 
	 * @return
	 */
	private static Connection createConnection(String url , Properties p) {
		Connection con = null;
		// 获取连接
		try {
			con = DriverManager.getConnection(url, p);
		} catch (SQLException e) {
			throw new RuntimeException("createConnection SQLException:get db connection exception");
		}
		return con;
	}
	
	/**
	 * 释放
	 * 
	 * @return
	 */
	public void releaseConnection(MyConnection con) {
		con.setClosed(true);
		list.add(con);
	}

	public List<MyConnection> getList() {
		return list;
	}

	public int getConnSize(){
		if(list != null){
			return list.size();
		}
		return 0;
	}
}
