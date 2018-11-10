package com.kaisn.test.javabase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kaisn.http.HttpsClientUtil;
import com.kaisn.utils.StringUtils;

public class EmpTest {

	public static void main(String[] args) {
		try {
			String result = HttpsClientUtil.doGet("http://192.168.109.129:8080/emp/list/");
			JSONObject parseObject = JSON.parseObject(result);
			System.out.println(parseObject.getString("msg"));
			JSONObject extend = parseObject.getJSONObject("extend");
			JSONArray jsonArray = extend.getJSONArray("list");
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File("C:/Users/lijing/Desktop/emp-2018-09-27.json")));
			for (int i=0;i<jsonArray.size();i++) {
				
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				
				JSONObject dataJson = new JSONObject();
				String empId = jsonObject.getString("empId");
				String empName = jsonObject.getString("empName");
				String gender = jsonObject.getString("gender");
				String email = jsonObject.getString("email");
				JSONObject department = jsonObject.getJSONObject("department");
				String deptName = department.getString("deptName");
				JSONObject position = jsonObject.getJSONObject("position");
				String posName = position.getString("posName");
				
				dataJson.put("empId", empId);
				dataJson.put("empName", empName);
				dataJson.put("gender", StringUtils.equals("M", gender)?"ÄÐ":"Å®");
				dataJson.put("email", email);
				dataJson.put("deptName", deptName);
				dataJson.put("posName", posName);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
				String timestamp = simpleDateFormat.format(new Date());
				dataJson.put("timestamp", timestamp);
				writer.write(JSON.toJSONString(dataJson));
				writer.newLine();
				writer.flush();
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
