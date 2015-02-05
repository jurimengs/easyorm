package com.test;

import java.io.BufferedReader;
import java.io.Reader;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.org.utils.ResultSetUtil;
import com.org.utils.schema.CommonDao;

public class LearnJDBCApi {
	public static void main(String[] args) {
//		System.out.println(System.getProperty("os.name"));

		// Test search
		String sql = "select * from smp_macqcode";
		CommonDao dao = new CommonDao(MacqCode.class, null);
		List<MacqCode> resultList= dao.query(sql);
		for (MacqCode test : resultList) {
			System.out.println(test.getCODE());
		}
		
		sql = "select * from smp_dataevent_table";
		ResultSet rs;
		try {
			rs = dao.getResultSet(sql);
			while(rs.next()){
				System.out.println(rs.getString("ver"));
			}
//			
//			List<Map<String, Object>> resList = ResultSetUtil.parseRSToList(rs);
//			for (int i = 0; i < resList.size(); i++) {
//				Map<String, Object> resMap = resList.get(i);
//				for (Iterator<?> iterator = resMap.keySet().iterator(); iterator.hasNext();) {
//					String key  = (String) iterator.next();
//					Object value =  resMap.get(key);
//					System.out.print(key + ":" + value +"  ");
//				}
//				System.out.println();
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Test save
//		String sql = "insert into a_test(name, date, px) values (?,?,?)";
//		CommonDao dao = new CommonDao(MacqCode.class);
//		Class<?>[] paramsType = {
//			String.class, String.class, Long.class
//		};
//		dao.save(sql, paramsType, "line4", "2012-12-20 00:00:00.000","123");
		
		// Test delete
//		String sql = "delete a_test where id = 1";
//		CommonDao dao = new CommonDao(MacqCode.class);
//		dao.delete("3");
	}
}
