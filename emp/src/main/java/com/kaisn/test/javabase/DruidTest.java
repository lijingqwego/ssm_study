package com.kaisn.test.javabase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.kaisn.http.OKHttpClinent;
import com.kaisn.utils.ResourceReaderUtil;

public class DruidTest {

	public static void main(String[] args) throws IOException {
		Map<String, String> param = new HashMap<String, String>();
		param.put("dataSource", "emp");
		param.put("dimensions",
				JSON.toJSONString(new String[] { "empName", "gender", "email", "deptName", "posName" }));
//		param.put("dimension","empName");
		param.put("interval", "2018-09-27T23:11:10.318Z/2018-09-27T23:11:10.358Z");
//		param.put("limit", "10");
		param.put("threshold", "10");
//		String json = getGroupByJsonParam(param);
		String json = getSelectJsonParam(param);
		System.out.println(json);
//		String result = OKHttpClinent.httpJsonPost("http://192.168.109.129:8082/druid/v2?pretty", json);
//		System.out.println(result);
	}

	public static String getGroupByJsonParam(Map<String, String> param) {
		String dataSource = param.get("dataSource");
		String dimensions = param.get("dimensions");
		String interval = param.get("interval");
		String limit = param.get("limit");
		String json = ResourceReaderUtil.loadTemplate("/druid/template-groupBy.json");
		json = json.replace("%%dataSource%%", dataSource).replace("%%dimensions%%", dimensions)
				.replace("%%intervals%%", interval).replace("%%limit%%", limit).replace("%%columns%%", dimensions);
		return json;
	}

	public static String getSelectJsonParam(Map<String, String> param) {
		String dataSource = param.get("dataSource");
		String dimension = param.get("dimensions");
		String interval = param.get("interval");
		String threshold = param.get("threshold");
		String json = ResourceReaderUtil.loadTemplate("/druid/template-select.json");
		json = json.replace("%%dataSource%%", dataSource).replace("%%dimensions%%", dimension)
				.replace("%%intervals%%", interval).replace("%%threshold%%", threshold);
		return json;
	}

	public static String getTopNJsonParam(Map<String, String> param) {
		String dataSource = param.get("dataSource");
		String dimension = param.get("dimension");
		String interval = param.get("interval");
		String threshold = param.get("threshold");
		String json = ResourceReaderUtil.loadTemplate("/druid/template-topN.json");
		json = json.replace("%%dataSource%%", dataSource).replace("%%dimension%%", dimension)
				.replace("%%intervals%%", interval).replace("%%threshold%%", threshold);
		return json;
	}

}
