package com.kaisn.ems;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.kaisn.dao.StudentMapper;
import com.kaisn.pojo.Student;
import com.kaisn.utils.Constans;
import com.kaisn.utils.MapperUtil;

class AddDialog extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel[] labels = new JLabel[6];
	private JTextField[] textFields = new JTextField[6];
	private JComboBox<String> genderBox;

	/**
	 * 添加对话框
	 * @param owner
	 * @param title
	 * @param modal
	 */
	public AddDialog(Frame owner, String title, Boolean modal) {
		super(owner, title, modal);

		JPanel centerPanel = new JPanel();
		JPanel southPanel = new JPanel();

		centerPanel.setLayout(new GridLayout(6, 1));
		GridLayout gridLayout = new GridLayout(1, 3);
		for (int i = 0; i < 6; i++) {
			labels[i] = new JLabel(Constans.COLNUM_NAME[i]);
			labels[i].setHorizontalAlignment(SwingConstants.RIGHT);
			JPanel jPanel = new JPanel();
			jPanel.setLayout(gridLayout);
			jPanel.add(labels[i]);
			switch (i) {
			case 2:
				genderBox = new JComboBox<String>();
				genderBox.addItem("男");
				genderBox.addItem("女 ");
				jPanel.add(genderBox);
				break;
			default:
				textFields[i] = new JTextField(10);
				jPanel.add(textFields[i]);
				break;
			}
			jPanel.add(new JLabel());
			centerPanel.add(jPanel);
		}
		JButton addBut = new JButton("添加");
		addBut.addActionListener(this);
		addBut.setActionCommand(Constans.Action.ADD);
		JButton cancelBut = new JButton("取消");
		cancelBut.addActionListener(this);
		cancelBut.setActionCommand(Constans.Action.CANCLE);

		southPanel.add(addBut);
		southPanel.add(cancelBut);

		this.add(centerPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);

		this.setSize(400, 250);
		this.setLocation(400, 250);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * 事件监听
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(Constans.Action.ADD)) {
			String no = textFields[0].getText();
			String age = textFields[3].getText();
			if(no!=null && !no.matches("[0-9]{0,5}")){
				JOptionPane.showMessageDialog(this, "输入的编号必须是小于5位的数字");
				return ;
			}
			if(age!=null && !age.matches("^(?:0|[1-9][0-9]?|100)$")){
				JOptionPane.showMessageDialog(this, "输入的年龄必须是0~100之间的数字");
				return ;
			}
//			Object[] values=new Object[6];
//			for (int i = 0; i < textFields.length; i++) {
//				if(i==2){
//					values[i]=genderBox.getSelectedIndex();
//					continue;
//				}
//				values[i]=textFields[i].getText();
//			}
			StudentMapper mapper = MapperUtil.getMapper(StudentMapper.class);
			Student student = new Student();
			student.setNo(no);
			student.setName(textFields[1].getText());
			student.setGender(genderBox.getSelectedIndex()+"");
			student.setAge(age);
			student.setPlace(textFields[4].getText());
			student.setDept(textFields[5].getText());
			mapper.addStudent(student);
			//DbUtils.updateTable("insert into t_student values(?,?,?,?,?,?)", values);
			this.dispose();
			MapperUtil.closeUpdSession();
		} else if (e.getActionCommand().equals(Constans.Action.CANCLE)) {
			this.dispose();
		}
	}
}
