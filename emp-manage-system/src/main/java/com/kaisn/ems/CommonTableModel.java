package com.kaisn.ems;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.kaisn.dao.StudentMapper;
import com.kaisn.pojo.Student;
import com.kaisn.utils.DbUtils;
import com.kaisn.utils.MapperUtil;

class CommonTableModel extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;
	
	private Vector<Vector<Object>> rowData=new Vector<Vector<Object>>();
	private Vector<String> colnumNames;

	public int getRowCount()
	{
		return this.rowData.size();
	}
    public int getColumnCount()
    {
		return this.colnumNames.size();
	}
	public Object getValueAt(int row, int col)
	{
		return this.rowData.get(row).get(col);
	}
	
	public String getColumnName(int i)
	{
		return this.colnumNames.get(i);
	}
	
	public CommonTableModel(String name)
	{
		this.setColnumNames();
		this.setRowData(name);
	}
	
	public CommonTableModel(String sql,Object[] values)
	{
		this.setColnumNames();
		this.rowData=DbUtils.selectTable(sql, values);
	}
	
	public CommonTableModel(){
		this.setColnumNames();
		this.setRowData(null);
		//this.rowData=DbUtils.selectTable(Constans.SELECT_SQL, new Object[]{});
	}
	
	private void setColnumNames(){
		colnumNames=new Vector<String>();
		colnumNames.add("学号");
		colnumNames.add("姓名");
		colnumNames.add("性别");
		colnumNames.add("年龄");
		colnumNames.add("籍贯");
		colnumNames.add("院系");	
	}
	
	private void setRowData(String name){
		Vector<Student> studentList=null;
		try {
			StudentMapper mapper = MapperUtil.getMapper(StudentMapper.class);
			studentList = mapper.getStudentList(name);
			for (Student student : studentList) {
				Vector<Object> vector = new Vector<Object>();
				vector.add(student.getNo());
				vector.add(student.getName());
				vector.add("1".equals(student.getGender())?"女":"男");
				vector.add(student.getAge());
				vector.add(student.getPlace());
				vector.add(student.getDept());
				this.rowData.add(vector);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//MapperUtil.closeSession();
		}
	}
}
