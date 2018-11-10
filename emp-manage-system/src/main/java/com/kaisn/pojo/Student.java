package com.kaisn.pojo;

import com.kaisn.dao.StudentMapper;
import com.kaisn.utils.MapperUtil;

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

	public static void main(String[] args) {
		StudentMapper mapper = MapperUtil.getMapper(StudentMapper.class);
		Student studentInfo = mapper.getStudentInfo("1031");
		String name2 = studentInfo.getName();
		System.out.println(name2);
	}

}