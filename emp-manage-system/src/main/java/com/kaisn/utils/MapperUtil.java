package com.kaisn.utils;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MapperUtil {
	
	private static final String config="SqlMapConfig.xml";
	
	private static SqlSession getSession() {
		SqlSession session=null;
		try {
			InputStream inputStream = Resources.getResourceAsStream(config);
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
			session = factory.openSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return session;
	}
	public static <T> T getMapper(Class<T> type){
		SqlSession session = getSession();
		T mapper = session.getMapper(type);
		return mapper;
	}
	
	public static void closeSession(){
		SqlSession session = getSession();
		if(session!=null){
			session.close();
		}
	}
	
	public static void closeUpdSession(){
		SqlSession session = getSession();
		if(session!=null){
			session.commit();
			session.close();
		}
	}
}
