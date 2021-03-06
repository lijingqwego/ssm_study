package com.kaisn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaisn.dao.DepartmentMapper;
import com.kaisn.pojo.Department;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	public List<Department> getDepartmentList(){
		return departmentMapper.getDepartmentList();
	}

}
