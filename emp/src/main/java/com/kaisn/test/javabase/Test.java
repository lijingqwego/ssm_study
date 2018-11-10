package com.kaisn.test.javabase;

import com.kaisn.utils.ResourceReaderUtil;

public class Test {

	public static void main(String[] args) {
		String data = ResourceReaderUtil.loadTemplate("/druid/template-topN.json");
		System.out.println(data);
		
	}
}
