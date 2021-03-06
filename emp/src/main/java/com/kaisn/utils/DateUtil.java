package com.kaisn.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static final int MODE_DAY = 1;
	public static final int MODE_HOUR = 2;
	public static final int MODE_DEFALUT = 3;

	public static String convertDate(long time, int mode) {
		String pattern = null;
		switch (mode) {
		case MODE_DAY:
			pattern = "yyyy-MM-dd";
			break;
		case MODE_HOUR:
			pattern = "HH:mm";
			break;
		default:
			pattern = "yyyy-MM-dd HH:mm:ss";
			break;
		}
		DateFormat format = new SimpleDateFormat(pattern);
		Date date = new Date(time);
		String dateTime = format.format(date);
		return dateTime;
	}
	
	public static void main(String[] args) {
		Calendar calendar=Calendar.getInstance();
		calendar.set(2017,1,28);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		System.out.println(convertDate(calendar.getTimeInMillis(), DateUtil.MODE_DAY));
	}
	
	

}
