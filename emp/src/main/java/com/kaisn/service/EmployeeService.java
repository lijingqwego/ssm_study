package com.kaisn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaisn.dao.EmployeeMapper;
import com.kaisn.pojo.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	
	public List<Employee> getEmployeeList(Employee employee){
		return employeeMapper.getEmployeeList(employee);
	}

	public boolean isExistEmpName(String empName) {
		int count=employeeMapper.getEmployeeCountByName(empName);
		return count>0?true:false;
	}

	public void saveEmployee(Employee employee) {
		employeeMapper.addEmployee(employee);
	}

	public void delEmployee(String[] empIds) {
		employeeMapper.delEmployee(empIds);
	}

	public Employee getEmployeeInfo(Long empId) {
		return employeeMapper.getEmployeeInfo(empId);
	}

	public void updateEmployee(Employee employee) {
		employeeMapper.updateEmployee(employee);
	}

	public void addEmployeeList(List<Employee> emps) {
		employeeMapper.addEmployeeList(emps);
	}
}
