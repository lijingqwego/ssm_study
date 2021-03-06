package com.kaisn.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.kaisn.pojo.Employee;


public class ExcelUtils {

	/**
	 * 将用户的信息导入到excel文件中去
	 * @param poss 
	 * @param userList 用户列表
	 * @param out 输出表
	 */
	public static void writeExcel(Map<String, Object> param, ServletOutputStream out,String mode) {
		try {
			//取出参数
			@SuppressWarnings("unchecked")
			List<Employee> empList=(List<Employee>) param.get("emps");
			String[] depts=(String[]) param.get("depts");
			String[] poss=(String[]) param.get("poss");
			
			// 1.创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 1.1创建合并单元格对象
			CellRangeAddress callRangeAddress = new CellRangeAddress(0, 0, 0, 4);// 起始行,结束行,起始列,结束列
			// 1.2头标题样式
			HSSFCellStyle headStyle = createCellStyle(workbook, (short) 16);
			// 1.3列标题样式
			HSSFCellStyle colStyle = createCellStyle(workbook, (short) 13);
			// 2.创建工作表
			HSSFSheet sheet = workbook.createSheet("员工列表");
			// 2.1加载合并单元格对象
			sheet.addMergedRegion(callRangeAddress);
			// 设置默认列宽
			sheet.setDefaultColumnWidth(25);
			// 3.创建行
			// 3.1创建头标题行;并且设置头标题
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell(0);

			// 加载单元格样式
			cell.setCellStyle(headStyle);
			cell.setCellValue("员工列表");

			// 3.2创建列标题;并且设置列标题
			HSSFRow row2 = sheet.createRow(1);
			String[] titles = { "员工姓名","性别","邮箱","所属部门","职位","描述" };
			String[] genders = { "男","女"};
			for (int i = 0; i < titles.length; i++) {
				HSSFCell cell2 = row2.createCell(i);
				// 加载单元格样式
				cell2.setCellStyle(colStyle);
				cell2.setCellValue(titles[i]);
			}
			//性别下拉选项
			setDropMenu(sheet,genders,2, Constans.Excel_Import_LineNumber, 1, 1);
			//部门下拉选项
			setDropMenu(sheet,depts,2, Constans.Excel_Import_LineNumber, 3, 3);
			//职位下拉选项
			setDropMenu(sheet,poss,2, Constans.Excel_Import_LineNumber, 4, 4);
			//如果mode=2导出数据
			if(Constans.Excel_Export_Data.equals(mode)){
				// 4.操作单元格;将用户列表写入excel
				if (empList != null) {
					for (int j = 0; j < empList.size(); j++) {
						// 创建数据行,前面有两行,头标题行和列标题行
						HSSFRow row3 = sheet.createRow(j + 2);
						HSSFCell cell1 = row3.createCell(0);
						cell1.setCellValue(empList.get(j).getEmpName());
						HSSFCell cell2 = row3.createCell(1);
						cell2.setCellValue("M".equals(empList.get(j).getGender()) ? "男" : "女");
						HSSFCell cell3 = row3.createCell(2);
						cell3.setCellValue(empList.get(j).getEmail());
						HSSFCell cell4 = row3.createCell(3);
						cell4.setCellValue(empList.get(j).getDepartment().getDeptName());
						HSSFCell cell5 = row3.createCell(4);
						cell5.setCellValue(empList.get(j).getDescription());
					}
				}
			}
			// 5.输出
			workbook.write(out);
			workbook.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param workbook
	 * @param fontsize
	 * @return 单元格样式
	 */
	private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontsize) {
		// TODO Auto-generated method stub
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);// 水平居中
		style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
		// 创建字体
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeightInPoints(fontsize);
		// 加载字体
		style.setFont(font);
		return style;
	}

	public static List<Employee> readExcel(InputStream inputStream, String excelFileName,
			Map<String, Long> deptMap,Map<String,Long> posMap) {
		List<Employee> emps = new ArrayList<Employee>();
		// 1.创建输入流
		try {
			boolean is03Excel = excelFileName.matches("^.+\\.(?i)(xls)$");
			// 1.读取工作簿
			Workbook workbook = is03Excel ? new HSSFWorkbook(inputStream) : new XSSFWorkbook(inputStream);
			// 2.读取工作表
			Sheet sheet = workbook.getSheetAt(0);
			// 3.读取行
			// 判断行数大于二,是因为数据从第三行开始插入
			if (sheet.getPhysicalNumberOfRows() > 2) {
				Employee emp = null;
				//最多导入1000行数据
				int count=Math.min(Constans.Excel_Import_LineNumber+1, sheet.getPhysicalNumberOfRows());
				// 跳过前两行
				for (int k = 2; k < count; k++) {
					// 读取单元格
					Row row0 = sheet.getRow(k);
					emp = new Employee();
					// 姓名
					Cell cell0 = row0.getCell(0);
					emp.setEmpName(cell0.getStringCellValue());
					// 性别
					Cell cell1 = row0.getCell(1);
					emp.setGender("男".equals(cell1.getStringCellValue()) ? "M" : "F");
					// 邮箱
					Cell cell2 = row0.getCell(2);
					emp.setEmail(cell2.getStringCellValue());
					// 所属部门
					Cell cell3 = row0.getCell(3);
					emp.setDeptId(deptMap.get(cell3.getStringCellValue()));
					// 职位
					Cell cell4 = row0.getCell(4);
					emp.setPosId(posMap.get(cell4.getStringCellValue()));
					// 描述
					Cell cell5 = row0.getCell(5);
					emp.setDescription(cell5.getStringCellValue());
					
					emps.add(emp);
				}
			}
			workbook.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emps;
	}
	
	/**
	 * 生成下拉列表
	 * @param sheet
	 * @param list
	 * @param firstRow
	 * @param lastRow
	 * @param firstCol
	 * @param lastCol
	 */
	private static void setDropMenu(Sheet sheet,String[] list,int firstRow, int lastRow, int firstCol, int lastCol){
		
		CellRangeAddressList regions = new CellRangeAddressList(firstRow,lastRow,firstCol,lastCol);
		//生成下拉框内容
		DVConstraint constraint = DVConstraint.createExplicitListConstraint(list);
		//绑定下拉框和作用区域
		HSSFDataValidation data_validation = new HSSFDataValidation(regions,constraint);
		//输入错误警告提示
		data_validation.setShowErrorBox(true);
		//对sheet页生效
		sheet.addValidationData(data_validation);
	}
}
