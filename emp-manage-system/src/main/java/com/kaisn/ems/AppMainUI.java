package com.kaisn.ems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.kaisn.dao.StudentMapper;
import com.kaisn.utils.MapperUtil;

public class AppMainUI extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JTextField searchField;
	private JTable tabel;
	private CommonTableModel comInfo;

	public static void main(String[] args) {
		// 显示应用 GUI
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new AppMainUI();
			}
		});
	}

	public AppMainUI() {
		initialize();
	}

	/**
	 * 初始化界面UI
	 */
	private void initialize() {
		JPanel northPanel = new JPanel();
		JLabel nameLabel = new JLabel("请输入姓名");
		searchField = new JTextField(10);
		JButton searchBut = new JButton("查询");
		searchBut.addActionListener(this);
		searchBut.setActionCommand(Constans.Action.SEARCH);
		JButton searchAllBut = new JButton("查询全部");
		searchAllBut.addActionListener(this);
		searchAllBut.setActionCommand(Constans.Action.SELECT_ALL);
		northPanel.add(nameLabel);
		northPanel.add(searchField);
		northPanel.add(searchBut);
		northPanel.add(searchAllBut);

		JPanel southPanel = new JPanel();
		JButton addBut = new JButton("添加");
		addBut.addActionListener(this);
		addBut.setActionCommand(Constans.Action.DIALOG_ADD);
		JButton updateBut = new JButton("修改");
		updateBut.addActionListener(this);
		updateBut.setActionCommand(Constans.Action.DIALOG_UPDATE);
		JButton deleteBut = new JButton("删除");
		deleteBut.addActionListener(this);
		deleteBut.setActionCommand(Constans.Action.DELETE);
		southPanel.add(addBut);
		southPanel.add(updateBut);
		southPanel.add(deleteBut);

		comInfo = new CommonTableModel();
		tabel = new JTable(comInfo);
		JScrollPane scrollPanel = new JScrollPane(tabel);

		this.setTitle("学生管理系统");
		this.add(scrollPanel);
		this.add(northPanel, "North");
		this.add(southPanel, "South");
		this.setSize(600, 450);
		this.setLocation(200, 150);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/**
	 * 事件监听
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(Constans.Action.SEARCH)) {// 查询
			String name = searchField.getText().trim();
			String sql = "select * from t_student where name like ?";
			comInfo = new CommonTableModel(sql, new Object[] { "%"+name+"%" });
//			comInfo = new CommonTableModel(name);
			tabel.setModel(comInfo);
		} else if (e.getActionCommand().equals(Constans.Action.SELECT_ALL)) {// 查询全部
			comInfo = new CommonTableModel();
			tabel.setModel(comInfo);
		} else if (e.getActionCommand().equals(Constans.Action.DIALOG_ADD)) {
			new AddDialog(this, "添加学生信息", true);
			comInfo = new CommonTableModel();
			tabel.setModel(comInfo);
		} else if (e.getActionCommand().equals(Constans.Action.DIALOG_UPDATE)) {// 更新
			int row = tabel.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "请选中要修改的行");
				return;
			}
			new UpdateDialog(this, "修改学生信息", true, comInfo, row);
			comInfo = new CommonTableModel();
			tabel.setModel(comInfo);
		} else if (e.getActionCommand().equals(Constans.Action.DELETE)) {// 删除
			int row = tabel.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "请选中要删除的行");
				return;
			}
			int result = JOptionPane.showConfirmDialog(this, "确认要删除该学生?", "提示", JOptionPane.WARNING_MESSAGE);
			// 取消
			if (result == 2) {
				return;
			}
			Object no = comInfo.getValueAt(row, 0);
			StudentMapper mapper = MapperUtil.getMapper(StudentMapper.class);
			mapper.deleteStudent(no.toString());
			//MapperUtil.closeUpdSession();
			//DbUtils.updateTable("delete from t_student where no=?", new Object[] { no });
			comInfo = new CommonTableModel();
			tabel.setModel(comInfo);
		}
	}

}