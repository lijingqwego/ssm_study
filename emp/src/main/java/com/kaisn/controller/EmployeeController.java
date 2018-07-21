package com.kaisn.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaisn.pojo.Department;
import com.kaisn.pojo.Employee;
import com.kaisn.pojo.Msg;
import com.kaisn.service.DepartmentService;
import com.kaisn.service.EmployeeService;
import com.kaisn.utils.Constans;
import com.kaisn.utils.ExcelUtils;
import com.kaisn.utils.StringUtils;

@Controller
@RequestMapping(value = "/emp")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	DepartmentService departmentService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Msg getEmployeeList(HttpServletRequest request, HttpServletResponse response) {
		List<Employee> empList = null;
		try {
			empList = employeeService.getEmployeeList(null);
		} catch (Exception e) {
			return Msg.fail();
		}
		return Msg.success().add("list", empList);
	}

	@RequestMapping(value = "/list/{curPage}", method = RequestMethod.POST)
	@ResponseBody
	public Msg getEmployeeLists(@PathVariable("curPage") int curPage, Employee employee) {
		PageInfo<Employee> page = null;
		System.out.println(employee);
		try {
			PageHelper.startPage(curPage, 5);// 10表示每页条数
			List<Employee> empList = employeeService.getEmployeeList(employee);
			page = new PageInfo<Employee>(empList, 5); // 5表示要连续显示的页数
		} catch (Exception e) {
			return Msg.fail();
		}
		return Msg.success().add("pageInfo", page);
	}

	@RequestMapping(value = "/checkName", method = RequestMethod.POST)
	@ResponseBody
	public Msg checkEmpName(@RequestParam("empName") String empName, HttpServletRequest request,
			HttpServletResponse response) {
		boolean isExist = false;
		try {
			isExist = employeeService.isExistEmpName(empName);
		} catch (Exception e) {
			return Msg.fail();
		}
		return Msg.success().add("isExist", isExist);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Msg saveEmployee(@Valid Employee employee, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (result.hasErrors()) {
				Map<String, Object> resultMap = new HashMap<String, Object>();
				List<FieldError> fieldErrors = result.getFieldErrors();
				for (FieldError fieldError : fieldErrors) {
					resultMap.put(fieldError.getField(), fieldError.getDefaultMessage());
				}
				return Msg.fail().add("errorFields", resultMap);
			} else {
				employeeService.saveEmployee(employee);
			}
		} catch (Exception e) {
			return Msg.fail();
		}
		return Msg.success();
	}

	@RequestMapping(value = "/dels", method = RequestMethod.DELETE)
	@ResponseBody
	public Msg delEmployee(@RequestBody String[] empIds, HttpServletRequest request, HttpServletResponse response) {
		try {
			employeeService.delEmployee(empIds);
		} catch (Exception e) {
			return Msg.fail();
		}
		return Msg.success();
	}

	@RequestMapping(value = "/info/{empId}", method = RequestMethod.GET)
	@ResponseBody
	public Msg getEmployeeInfo(@PathVariable("empId") Long empId, HttpServletRequest request,
			HttpServletResponse response) {
		Employee employee = null;
		try {
			employee = employeeService.getEmployeeInfo(empId);
		} catch (Exception e) {
			return Msg.fail();
		}
		return Msg.success().add("emp", employee);
	}

	@RequestMapping(value = "/update/{empId}", method = RequestMethod.PUT)
	@ResponseBody
	public Msg updateEmployee(@Valid Employee employee, BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			if (result.hasErrors()) {
				Map<String, Object> resultMap = new HashMap<String, Object>();
				List<FieldError> fieldErrors = result.getFieldErrors();
				for (FieldError fieldError : fieldErrors) {
					resultMap.put(fieldError.getField(), fieldError.getDefaultMessage());
				}
				return Msg.fail().add("errorFields", resultMap);
			} else {
				employeeService.updateEmployee(employee);
			}
		} catch (Exception e) {
			return Msg.fail();
		}
		return Msg.success();
	}
	
	@RequestMapping(value = "/download/{mode}", method = RequestMethod.GET)
	@ResponseBody
	public Msg download(@PathVariable("mode") String mode,HttpServletRequest request,HttpServletResponse response) {
		try {
			List<Employee> empList=null;
			String fileName=null;
			if(Constans.EXCEL_EXPORT_DATA.equals(mode))
			{
				empList = employeeService.getEmployeeList(null);
				fileName="员工列表-"+StringUtils.getUniqueString()+".xls";
			}else{
				fileName="清单模板-"+StringUtils.getUniqueString()+".xls";
			}
			// 2.导出
			// 这里设置的文件格式是application/x-excel
			response.setContentType("application/x-excel");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String(fileName.getBytes(), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			List<Department> deptList = departmentService.getDepartmentList();
			if(deptList!=null)
			{
				int size = deptList.size();
				String[] depts=new String[size];
				for (int i=0;i<size;i++) {
					depts[i]=deptList.get(i).getDeptName();
				}
				ExcelUtils.writeExcel(empList, depts,outputStream,mode);
			}
			if (outputStream != null)
				outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Msg.success();
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public Msg upload(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) {
		try {
			String filePath = request.getSession().getServletContext().getRealPath("/") + "WEB-INF/upload/";
			String filename = file.getOriginalFilename();
			InputStream inputStream = file.getInputStream();
			List<Department> deptList = departmentService.getDepartmentList();
			Map<String, Long> deptMap = new HashMap<String,Long>();
			for (Department dept : deptList) {
				deptMap.put(dept.getDeptName(),dept.getDeptId());
			}
			List<Employee> emps = ExcelUtils.readExcel(inputStream, filePath + filename,deptMap);
			employeeService.addEmployeeList(emps);
		} catch (IOException e) {
			return Msg.fail();
		}
		return Msg.success();
	}
}
