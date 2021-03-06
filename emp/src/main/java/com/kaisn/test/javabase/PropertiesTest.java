package com.kaisn.test.javabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.kaisn.utils.PropertiesUtil;

public class PropertiesTest {

	private static final String FILE_NAME_AND_PATH="/db.properties";
	public static void main(String[] args)
	{
//		Properties properties = PropertiesUtil.getProperties(FILE_NAME_AND_PATH);
//		String value = properties.getProperty("jdbc.password");
//		System.out.println(value);
//		Map<String, String> proMap = new HashMap<String,String>();
//		proMap.put("username", "lijing");
//		proMap.put("password", "asd3135");
//		PropertiesUtil.setPropertys(proMap, "src/main/resources/test.properties", "kv");
		/*
		jdbc.driver=com.mysql.jdbc.Driver
		jdbc.url=jdbc:mysql://localhost:3306/ssm_crud?useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true
		jdbc.username=root
		jdbc.password=asd3135
		 */
		/*Map<String, String> proMap = new HashMap<String,String>();
		proMap.put("getEmpList", "select * from t_emp limit 0,10");
		PropertiesUtil.setPropertys(proMap, "src/main/resources/sql.xml", "xml","sql����");*/
		Properties properties = PropertiesUtil.getProperties(FILE_NAME_AND_PATH,PropertiesUtil.defaultType);
		String driver = properties.getProperty("jdbc.driver");
		String url = properties.getProperty("jdbc.url");
		String username = properties.getProperty("jdbc.username");
		String password = properties.getProperty("jdbc.password");
		
		Connection conn=null;
		try {
			Class.forName(driver);
			conn=DriverManager.getConnection(url, username, password);
			PropertiesUtil.clear();
			properties = PropertiesUtil.getProperties("/sql.xml", PropertiesUtil.xmlType);
			String sql = properties.getProperty("emp.list");
			PropertiesUtil.clear();
			PreparedStatement prepareStatement = conn.prepareStatement(sql);
			ResultSet resultSet = prepareStatement.executeQuery();
			Map<String, String> proMap = new HashMap<String,String>();
			while(resultSet.next()){
				String emp_id = resultSet.getString("emp_id");
				String emp_name = resultSet.getString("emp_name");
				String email = resultSet.getString("email");
				proMap.put("emp_id", emp_id);
				proMap.put("emp_name", emp_name);
				proMap.put("email", email);
			}
			PropertiesUtil.setPropertys(proMap, "src/main/resources/result.properties", PropertiesUtil.xmlType,"��ѯ���");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
