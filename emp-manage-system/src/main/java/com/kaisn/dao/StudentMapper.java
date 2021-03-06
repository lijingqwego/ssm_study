package com.kaisn.dao;

import java.util.Vector;

import org.apache.ibatis.annotations.Param;

import com.kaisn.pojo.Student;

public interface StudentMapper {

	Vector<Student> getStudentList(@Param("name") String name);
	
	Student getStudentInfo(@Param("no")String no);
	
	void addStudent(Student student);
	
	void addStudentList(Vector<Student> students);
	
	void deleteStudent(@Param("no")String no);
	
	void updateStudent(Student student);
}
