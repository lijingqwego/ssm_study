package com.kaisn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {

	public static String getUniqueString(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return format.format(new Date());
	}
	
	public static void main(String[] args) {
		System.out.println(getUniqueString());
	}
}
