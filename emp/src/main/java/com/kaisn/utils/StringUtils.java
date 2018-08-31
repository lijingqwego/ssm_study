package com.kaisn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {

	public static String getUniqueString() 
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return format.format(new Date());
	}

	public static boolean isBlack(String str) 
	{
		if(str == null)
		{
			return true;
		}
		if("".equals(str))
		{
			return true;
		}
		return false;
	}
	
	public static boolean isNotBlack(String str) 
	{
		if(str == null)
		{
			return false;
		}
		if("".equals(str))
		{
			return false;
		}
		return true;
	}
	
	public static boolean equals(String str1,String str2)
	{
		boolean flag=false;
		if(str1!=null)
		{
			flag=str1.equals(str1);
		}
		if(str2!=null){
			flag=str2.equals(str1);
		}
		return flag;
	}

	public static void main(String[] args) 
	{
		System.out.println(getUniqueString());
	}
}
