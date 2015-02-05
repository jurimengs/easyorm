package com.org.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtils {

	public static Method[] getObjectMethods(Object obj) {
		return obj.getClass().getMethods();
	}

	/**
	 * 返回一个对象里面的所有get方法
	 * @param obj
	 * 			要处理的对象.如果该对象存在父类,或者有继承接口, 应该避免使用.
	 * @return
	 * @throws Exception
	 */
	public static Method[] getObjectGetMethods(Object obj) throws Exception {
		Field[] fids = obj.getClass().getDeclaredFields();
		Method[] mds = new Method[fids.length];
		for (int i = 0; i < fids.length; i++) {
			PropertyDescriptor pd = new PropertyDescriptor(fids[i].getName(),
					obj.getClass());
			Method m = pd.getReadMethod();
			mds[i] = m;
		}
		return mds;
	}

	public static Field[] getObjectFields(Object obj) {
		return obj.getClass().getFields();
	}
	
}
