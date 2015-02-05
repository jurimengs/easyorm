package com.org.utils;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.org.reflect.ReflectDbModel;

public class ResultSetUtil {
	/**
	 * 遍历结果集,把值设置到Obj中,利用泛型确定List的集合元素类型
	 * @param rs
	 * @param <T>
	 * @return map
	 * @throws Exception
	 */
	public static <T> List<T> parseResultSet(ResultSet rs, T obj)
			throws Exception {
		List<T> list = new ArrayList<T>();
		ResultSetMetaData rsmd = rs.getMetaData();
		// 列数
		int columnCounts = rsmd.getColumnCount();
		//
		ReflectDbModel model = new ReflectDbModel();
		Method m = null;
		while (rs.next()) {
			// 这个地方相当于每用一次就new一次,否则数据会覆盖上一次的数据
			obj = (T) obj.getClass().newInstance();
			for (int i = 1; i <= columnCounts; i++) {
				initReflectDbModel(rs,model,i);
				if(model.getValue() != null && model.getValue() != ""){
					m = obj.getClass().getMethod("set"+model.getKey(), model.getValue().getClass());
					m.invoke(obj, model.getValue());
				}
			}
			list.add(obj);
		}
		return list;
	}

	private static void initReflectDbModel(ResultSet rs,ReflectDbModel model,int index) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		String key = rsmd.getColumnName(index);
		String paramType = rsmd.getColumnClassName(index);
		Object value = rs.getObject(index);
		
		key = String.valueOf(key.charAt(0)).toUpperCase()+key.substring(1);
		paramType = paramType.substring(paramType.lastIndexOf(".") + 1);
		
		model.setKey(key);
		model.setParamType(paramType);
		model.setValue(value);
	}

	/**
	 * 遍历结果集,把值设置到Obj中,利用泛型确定List的集合元素类型
	 * @param rs
	 * @param <T>
	 * @return map
	 * @throws Exception
	 */
	public static List<Map<String, Object>> parseRSToList(ResultSet rs)
			throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		ResultSetMetaData rsmd = rs.getMetaData();
		// 列数
		int columnCounts = rsmd.getColumnCount();
		//

		Map<String, Object> valMap = new HashMap<String, Object>();
		// 存放表字段名
		for (int i = 1; i <= columnCounts; i++) {
			String colName = rsmd.getColumnName(i);
			valMap.put(colName, colName);
		}
		list.add(valMap);
		
		while (rs.next()) {
			valMap = new HashMap<String, Object>();
			for (int i = 1; i <= columnCounts; i++) {
				String colName = rsmd.getColumnName(i);
				Object value = rs.getObject(i);
				valMap.put(colName, value);
			}
			list.add(valMap);
		}
		return list;
	}
}
