package com.kaisn.pojo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.filechooser.FileSystemView;

import com.kaisn.utils.Constans;
import com.kaisn.utils.ExcelUtils;

public class Student {
	
	private String no;
	private String name;
	private String gender;
	private String age;
	private String place;
	private String dept;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public static void main(String[] args) throws FileNotFoundException {
//		StudentMapper mapper = MapperUtil.getMapper(StudentMapper.class);
//		mapper.deleteStudent("1031");
//		MapperUtil.closeUpdSession();
		
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File com=fsv.getHomeDirectory();    //这便是读取桌面路径的方法了
		File file = new File(com.getAbsolutePath(),"学生列表.xls");
		FileOutputStream out=new FileOutputStream(file);
		Map<String, Object> param = new HashMap<String,Object>();
		param.put("title", Constans.TITLES);
		ExcelUtils.writeExcel(param, out, Constans.Excel_Export_Template);	
		System.out.println(com.getPath());
	}

}
