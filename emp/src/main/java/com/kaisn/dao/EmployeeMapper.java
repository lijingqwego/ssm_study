package com.kaisn.dao;

import java.util.List;

import com.kaisn.pojo.Employee;

public interface EmployeeMapper {

	List<Employee> getEmployeeList();

	int getEmployeeCountByName(String empName);

	void addEmployee(Employee employee);

	void delEmployee(String[] empIds);

	Employee getEmployeeInfo(Long empId);

	void updateEmployee(Employee employee);

	void addEmployeeList(List<Employee> emps);
	
}
