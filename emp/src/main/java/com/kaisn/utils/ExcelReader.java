package com.kaisn.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

public class ExcelReader extends XxlsAbstract{

	private List<List<String>> dataList=new ArrayList<List<String>>();
	
	@Override
	public void optRows(int sheetIndex, int curRow, List<String> rowlist) throws SQLException {
		List<String> newList = new ArrayList<String>();
		newList.addAll(rowlist);
		dataList.add(newList);
	}
	
	public List<List<String>> readExcel(String fileName,int sheetIndex) throws Exception{
		super.processOneSheet(fileName, sheetIndex);
		return this.dataList;
	}
	
	public static void main(String[] args) throws Exception {
		ExcelReader excelReader = new ExcelReader();
		String fileName="C:/Users/lwx515559/Desktop/Ա���б�-20190106210108784.xlsx";
		List<List<String>> dataList = excelReader.readExcel(fileName, 1);
		dataList.remove(0);
		dataList.remove(0);
		for (List<String> list : dataList) {
			System.out.println(JSON.toJSONString(list));
		}
	}
}
