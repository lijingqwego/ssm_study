package com.kaisn.ems;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

class CommonTableModel extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;
	
	private Vector<Vector<Object>> rowData;
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
	
	public CommonTableModel(String sql,Object[] values)
	{
		this.setColnumNames();
		this.rowData=DbUtils.selectTable(sql, values);
	}
	
	public CommonTableModel(){
		this.setColnumNames();
		this.rowData=DbUtils.selectTable(Constans.SELECT_SQL, new Object[]{});
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
}
