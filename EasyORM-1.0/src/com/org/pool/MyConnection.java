package com.org.pool;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;

import com.org.exceptions.NoneUsableConnectionException;

public class MyConnection implements Connection {

	private Connection con;
	/* true 关闭中, false 使用 */
	private boolean status;
	
	private String index;

	private String catalog;

	/**
	 * @return the con
	 */
	public Connection getCon() {
		return con;
	}

	/**
	 * @param con
	 *            the con to set
	 */
	public void setCon(Connection con) {
		this.con = con;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setClosed(boolean status) {
		this.status = status;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	@Override
	public Statement createStatement() throws SQLException {
		return null;
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		PreparedStatement ps = null;
		if (con == null) {
			throw new NoneUsableConnectionException("没有可用的连接,请等待释放");
		}
		ps = con.prepareStatement(sql);
		return ps;
	}
	
	public PreparedStatement prepareStatement(String sql, Class<?>[] paramsType, String... params) throws SQLException {
		PreparedStatement ps = con.prepareStatement(sql);
		// 这地方得用反射写.也许得加个泛型
		for (int i = 0; i < paramsType.length; i++) {
			if(Integer.class == paramsType[i]){
				ps.setInt(i+1, Integer.valueOf(params[i]));
			} else if(Long.class == paramsType[i]){
				ps.setLong(i+1, Long.valueOf(params[i]));
			} else if(Double.class == paramsType[i]){
				ps.setDouble(i+1, Double.valueOf(params[i]));
			} else if(Float.class == paramsType[i]){
				ps.setFloat(i+1, Float.valueOf(params[i]));
			} else if(Byte.class == paramsType[i]){
				ps.setByte(i+1, Byte.valueOf(params[i]));
			} else if(Character.class == paramsType[i] || String.class == paramsType[i]){
				ps.setString(i+1, params[i]);
			} else if(Short.class == paramsType[i]){
				ps.setShort(i+1, Short.valueOf(params[i]));
			}
		}
		return ps;
	}

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		return null;
	}

	@Override
	public String nativeSQL(String sql) throws SQLException {
		return null;
	}

	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {

	}

	@Override
	public boolean getAutoCommit() throws SQLException {
		return false;
	}

	@Override
	public void commit() throws SQLException {

	}

	@Override
	public void rollback() throws SQLException {

	}

	@Override
	public void close() throws SQLException {

	}

	@Override
	public boolean isClosed() throws SQLException {
		return status;
	}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		return null;
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {

	}

	@Override
	public boolean isReadOnly() throws SQLException {
		return false;
	}

	@Override
	public void setCatalog(String catalog) throws SQLException {
		this.catalog = catalog;
	}

	@Override
	public String getCatalog() throws SQLException {
		return catalog;
	}

	@Override
	public void setTransactionIsolation(int level) throws SQLException {

	}

	@Override
	public int getTransactionIsolation() throws SQLException {
		return 0;
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return null;
	}

	@Override
	public void clearWarnings() throws SQLException {

	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency)
			throws SQLException {
		return null;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		return null;
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		return null;
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return null;
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {

	}

	@Override
	public void setHoldability(int holdability) throws SQLException {

	}

	@Override
	public int getHoldability() throws SQLException {
		return 0;
	}

	@Override
	public Savepoint setSavepoint() throws SQLException {
		return null;
	}

	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		return null;
	}

	@Override
	public void rollback(Savepoint savepoint) throws SQLException {

	}

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {

	}

	@Override
	public Statement createStatement(int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return null;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return null;
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		return null;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
			throws SQLException {
		return null;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
			throws SQLException {
		return null;
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames)
			throws SQLException {
		return null;
	}

	@Override
	public Clob createClob() throws SQLException {
		return null;
	}

	@Override
	public Blob createBlob() throws SQLException {
		return null;
	}

	@Override
	public NClob createNClob() throws SQLException {
		return null;
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		return null;
	}

	@Override
	public boolean isValid(int timeout) throws SQLException {
		return false;
	}

	@Override
	public void setClientInfo(String name, String value)
			throws SQLClientInfoException {

	}

	@Override
	public void setClientInfo(Properties properties)
			throws SQLClientInfoException {

	}

	@Override
	public String getClientInfo(String name) throws SQLException {
		return null;
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		return null;
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements)
			throws SQLException {
		return null;
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes)
			throws SQLException {
		return null;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	/**
	 * @return the index
	 */
	public String getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(String index) {
		this.index = index;
	}

}
