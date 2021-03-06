package com.kaisn.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kaisn.pojo.Department;
import com.kaisn.pojo.Msg;
import com.kaisn.service.DepartmentService;

@Controller
@RequestMapping(value="/dept")
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	@ResponseBody
	@RequestMapping(value="/list" ,method=RequestMethod.GET)
	public Msg getDepartmentList(HttpServletRequest request,HttpServletResponse response){
		List<Department> deptList=null;
		try {
			deptList = departmentService.getDepartmentList();
		} catch (Exception e) {
			return Msg.fail();
		}
		return Msg.success().add("list", deptList);
	}

}
