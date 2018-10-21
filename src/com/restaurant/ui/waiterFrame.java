package com.restaurant.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;

import com.restaurant.dao.userDao;
import com.restaurant.dao.waiterDao;
import com.restaurant.entity.Waiter;
import com.restaurant.myswing.myJPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

/*
 * 姚龙飞
 */

public class waiterFrame extends JFrame {

	private JPanel contentPane;
	Vector title ;
	Vector data ;
	private static JTable table;
	static DefaultTableModel dtm = new DefaultTableModel();
	private final JScrollPane scrollPane = new JScrollPane();
	private JRadioButton radioButtonmale;
	private JRadioButton radioButtonfemal;
	private ButtonGroup radioGroupSex;
	private JTextField textFieno;
	private JTextField textFiename;
	private JTextField textFiephone;
	private JTextField textFiedage;
	private JTextField textFiemoney;
	private JTextField textFielid;
	private JLabel labelMan;
	private JLabel labelWomen;
	private JLabel labelPeople;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					waiterFrame frame = new waiterFrame();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public waiterFrame() {
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 751, 664);//设置位置和大小
		setTitle("服务员");
		contentPane = new JPanel();

		//整体面板
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 下面将创建一自定义带有背景面板对象并添加到窗口的容器中
		final myJPanel panel = new myJPanel(this.getClass().getResource(
				"/img/waiterbg.png"));// 创建一个面板对象
		panel.setSize(735, 288);//设置大小
		panel.setLocation(0, 338);//设置位置
		panel.setLayout( null);// 设置面板的布局管理器为网格组布局
		getContentPane().add(panel, BorderLayout.CENTER);
		contentPane.add(panel);

		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 735, 340);
		contentPane.add(scrollPane_1);

		//定义一个存放表格的面板
		/*JPanel panel1 = new JPanel();
		panel1.setBounds(0, 0, 735, 340);
		contentPane.add(panel1);*/
		title = new Vector();
		data = new Vector();
		//panel1.setLayout(null);

		table = new JTable(dtm);
		//panel1.add(table);
		table.setBounds(10, 25, 715, 315);
		
		

		JTableHeader jaTableHeader = table.getTableHeader();
		table.setBackground(new Color(255, 245, 238));
		scrollPane_1.setViewportView(table);//加上了这句才出现了滚动条
		//1******************点击表格的事件****************************
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tableMouseClicked(evt);
			}
		});
		table.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				tableKeyPressed(evt);
			}
		});
		//1******************点击表格的事件****************************
		jaTableHeader .setBounds(10, 1, 735,22);
		scrollPane_1.add(jaTableHeader );
		
	
		//加载数据库信息到表格上
		showTableData();




		//---------------------------------文本框的定义-----------------------------------
		textFieno = new JTextField();//编号
		textFieno.setBounds(76, 33, 136, 23);
		panel.add(textFieno);
		textFieno.setColumns(10);

		textFiename = new JTextField();//姓名
		textFiename.setColumns(10);
		textFiename.setBounds(288, 33, 136, 23);
		panel.add(textFiename);

		textFiephone = new JTextField();//电话号码
		textFiephone.setColumns(10);
		textFiephone.setBounds(76, 90, 136, 23);
		panel.add(textFiephone);

		textFiedage = new JTextField();//职龄
		textFiedage.setColumns(10);
		textFiedage.setBounds(296, 90, 48, 23);
		panel.add(textFiedage);

		textFiemoney = new JTextField();//工资
		textFiemoney.setColumns(10);
		textFiemoney.setBounds(522, 33, 48, 23);
		panel.add(textFiemoney);

		textFielid = new JTextField();//身份证
		textFielid.setColumns(10);
		textFielid.setBounds(552, 90, 173, 23);
		panel.add(textFielid);
		//--------------------------------------文本框定义结束-----------------------------------
		//-----------------------------性别按钮组-----------------------------------------------
		radioButtonmale = new JRadioButton("男");
		radioButtonmale .setBounds(637, 33, 41, 23);
		panel.add(radioButtonmale );

		radioButtonfemal = new JRadioButton("女");
		radioButtonfemal.setBounds(680, 33, 41, 23);
		panel.add(radioButtonfemal);

		radioGroupSex = new ButtonGroup();
		radioGroupSex.add(radioButtonmale);
		radioGroupSex.add(radioButtonfemal);
		radioGroupSex.setSelected(radioButtonmale.getModel(),true);
		//----------------------------------------


		//*************************************按钮的定义以及事件监听***************************************
		//添加按钮
		JButton btnNewButton_4 = new JButton("解锁");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearAll();
				unlockText();//解锁文本框
			}
		});
		btnNewButton_4.setBounds(43, 181, 93, 23);
		panel.add(btnNewButton_4);

		//保存按钮
		//该按钮实现的功能就是从不同的文本框中获取相应的值，给waiter对象赋相应的数值通过waiter.Dao.insert（）把对象传过去然后实现插入数据库
		//，同时也吧文本框里的值赋给一个Vector型的row,用来显示到表格中
		JButton btnadd = new JButton("确定添加");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String number =   textFieno.getText().toString();
				String name =  textFiename.getText().toString();
				String id= textFielid.getText().toString();
				String age =  textFiedage.getText().toString();
				String money = textFiemoney.getText() .toString();
				String phone = textFiephone.getText().toString();

				if(number.equals("")||name.equals("")||id.equals("")||age.equals("")||money.equals("")||phone.equals("")){ 
					JOptionPane.showConfirmDialog(null, "你输入的内容有误！请您重新输入！", "输入有误！", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				}else{


					Waiter waiter = new Waiter();
					Vector row = new Vector();

					waiter.setWaiterno(textFieno.getText().toString());//给waiter对象设置编号
					row.add(textFieno.getText().toString());

					waiter.setWname(textFiename.getText().toString());//给waiter对象设置姓名
					row.add(textFiename.getText().toString());

					if(radioGroupSex.getSelection()==radioButtonmale.getModel()){//给waiter对象设置性别
						waiter.setWsex("男");
					}else{
						waiter.setWsex("女");
					}
					row.add(waiter.getWsex());

					waiter.setWid(textFielid.getText().toString());//给waiter对象设置身份证
					row.add(textFielid.getText().toString());

					waiter.setWphone(textFiephone.getText().toString());//给waiter对象设置电话号码
					row.add(textFiephone.getText().toString());

					waiter.setWnomey(Integer.parseInt( textFiemoney.getText()));//给waiter对象设置工资
					row.add(textFiemoney.getText().toString());

					waiter.setWage(Integer.parseInt(textFiedage.getText()));//给waiter对象设置职龄
					row.add(textFiedage.getText().toString());

					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.addRow(row);

					waiterDao.insert(waiter);
					lockText();
					ShowSum();//调用显示男女人数情况的方法
				}
			}
		});
		btnadd.setBounds(171, 181, 93, 23);
		panel.add(btnadd);

		//解锁按钮
		JButton btnNewButton_3 = new JButton("查找");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lockText();
				textFieno.setEnabled(true);
			}
		});
		btnNewButton_3.setBounds(43, 214, 93, 23);
		panel.add(btnNewButton_3);

		//修改按钮
		JButton btnNewButton_1 = new JButton("修改");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(table.getSelectedRow()<0){
					JOptionPane.showConfirmDialog(null,"请选择表格中的一行","修改数据",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

				}else{
					Waiter waiter = new Waiter();
					waiter.setWaiterno(textFieno.getText().toString());//给waiter对象设置编号
					waiter.setWname(textFiename.getText().toString());//给waiter对象设置姓名
					if(radioGroupSex.getSelection()==radioButtonmale.getModel()){//给waiter对象设置性别
						waiter.setWsex("男");
					}else{
						waiter.setWsex("女");
					}
					waiter.setWid(textFielid.getText().toString());//给waiter对象设置身份证
					waiter.setWphone(textFiephone.getText().toString());//给waiter对象设置电话号码
					waiter.setWnomey(Integer.parseInt( textFiemoney.getText()));//给waiter对象设置工资
					waiter.setWage(Integer.parseInt(textFiedage.getText()));//给waiter对象设置职龄

					DefaultTableModel model = (DefaultTableModel)table.getModel();
					String sex = "";
					if(radioGroupSex.getSelection()==radioButtonmale.getModel()){
						sex="男";
					}else{
						sex="女";
					}

					model.setValueAt(textFieno.getText(),table.getSelectedRow() , 0);
					model.setValueAt(textFiename.getText(),table.getSelectedRow() , 1);
					model.setValueAt(sex,table.getSelectedRow() , 2);
					model.setValueAt(textFielid.getText(),table.getSelectedRow() , 3);
					model.setValueAt(textFiephone.getText(),table.getSelectedRow() , 4);
					model.setValueAt(Integer.parseInt(textFiemoney.getText()),table.getSelectedRow() , 5);
					model.setValueAt(Integer.parseInt(textFiedage.getText()),table.getSelectedRow() , 6);

					waiterDao.update(waiter);
					ShowSum();//调用显示男女人数情况的方法
				}
			}
		});
		btnNewButton_1.setBounds(433, 181, 93, 23);
		panel.add(btnNewButton_1);

		//删除按钮
		JButton btnNewButton_2 = new JButton("删除");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()<0){
					JOptionPane.showConfirmDialog(null,"请选择表格中的一行","修改数据",
							JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

				}else{
					waiterDao.delete(textFieno.getText());
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.removeRow(table.getSelectedRow());
					ShowSum();//调用显示男女人数情况的方法
				}
			}
		});
		btnNewButton_2.setBounds(573, 181, 93, 23);
		panel.add(btnNewButton_2);


		//退出按钮
		JButton btnExit = new JButton("退出");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(null,"您将要退出服务员管理表，确定吗？","退出系统",JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION){
					System.out.println((new Date()).toString()+"退出系统");
					//  System.exit(0);
					EXit();
				}
			}



		});
		btnExit.setBounds(632, 255, 93, 23);
		panel.add(btnExit);

		JButton btnfindok = new JButton("确定查找");
		btnfindok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( textFieno.getText().equals("")) {
					JOptionPane.showConfirmDialog(null, "编号不能为空！请您重新输入！", "查找信息", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				} else {

					Vector waiter = waiterDao.findByWaiterNo( textFieno.getText());
					textFiename.setText(waiter.get(1).toString());
					String sex1 = waiter.get(2).toString();
					textFielid.setText(waiter.get(3).toString());
					textFiephone.setText(waiter.get(4).toString());
					textFiemoney.setText(waiter.get(5).toString());
					textFiedage.setText(waiter.get(6).toString());

					if (sex1.equals("男")) {
						radioGroupSex.setSelected(radioButtonmale.getModel(), true);
					} else {
						radioGroupSex.setSelected(radioButtonfemal.getModel(), true);
					}

				}
			}
		});
		btnfindok.setBounds(171, 214, 93, 23);
		panel.add(btnfindok);
	
		
		labelMan = new JLabel("New label");
		labelMan.setBounds(6, 0, 187, 23);
		panel.add(labelMan);
		
		 labelWomen = new JLabel("New label");
		labelWomen.setBounds(230, 0, 179, 23);
		panel.add(labelWomen);
		
		 labelPeople = new JLabel("New label");
		labelPeople.setBounds(456, 0, 145, 23);
		panel.add(labelPeople);
		
		ShowSum();
		lockText();//锁住文本框
	}

	//构造函数外的函数
	protected void EXit() {
		this.setVisible(false);
		System.err.println("******************************你离开姚龙飞的代码世界***********************************");
		// TODO 自动生成的方法存根

	}

	private void showTableData() {//显示数据库中服务员表格的信息
		// TODO 自动生成的方法存根
		title.add("编号");
		title.add("姓名");
		title.add("性别");
		title.add("身份证");
		title.add("联系方式");
		title.add("工资");
		title.add("职龄");
		data=waiterDao.waiterInfor();
		dtm.setDataVector(data, title);
		
		//用数组设置表格的列宽
				table.setModel(dtm);
				int[] columnWith = {40,40,20,88,50,30,20};//列宽的数组
				TableColumnModel columnModel = table.getColumnModel();//获取列模型
				int count = columnModel.getColumnCount();//获取列数量
				for(int i =0;i<count;i++){//遍历
					TableColumn column = columnModel.getColumn(i);//获取列对象
					column.setPreferredWidth(columnWith[i]);//用数组的元素设置列的宽度
				}
	}

	//解锁文本框
	public void unlockText(){
		textFieno.setEnabled(true);
		textFiename.setEnabled(true);
		textFiemoney.setEnabled(true);
		textFiephone.setEnabled(true);
		textFiedage.setEnabled(true);
		textFielid.setEnabled(true);
	}

	//锁文本框
	public void lockText(){
		textFieno.setEnabled(false);
		textFiename.setEnabled(false);
		textFiemoney.setEnabled(false);
		textFiephone.setEnabled(false);
		textFiedage.setEnabled(false);
		textFielid.setEnabled(false);

	}

	//清空文本框的方法
	public void clearAll(){
		textFieno.setText("");
		textFiename.setText("");
		textFiephone.setText("");
		textFiedage.setText("");
		textFielid.setText("");
		textFiemoney.setText("");
		radioButtonmale.setSelected(true);
	}

	//处理选中的表中的行。
	private void processSelectionRow(){
		Waiter water = new Waiter();
		int row = table.getSelectedRow();
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		textFieno.setText(model.getValueAt(row, 0).toString());
		textFiename.setText(model.getValueAt(row, 1).toString());
		String sex = model.getValueAt(row,2).toString();
		textFielid.setText(model.getValueAt(row, 3).toString());
		textFiephone.setText(model.getValueAt(row, 4).toString());
		textFiemoney.setText(model.getValueAt(row, 5).toString());
		textFiedage.setText(model.getValueAt(row, 6).toString());
		if("男".equals(sex)){
			radioGroupSex.setSelected(radioButtonmale.getModel(),true);
		}else{
			radioGroupSex.setSelected(radioButtonfemal.getModel(),true);
		}
	}

	//点击表格一行触发的事件
	private void tableMouseClicked(java.awt.event.MouseEvent evt) {                                     
		processSelectionRow();        
	}   

	private void tableKeyPressed(java.awt.event.KeyEvent evt) {                                   
		if(evt.getKeyCode()== KeyEvent.VK_DOWN ||evt.getKeyCode()==KeyEvent.VK_UP){
			processSelectionRow();
		}
	} 
	
	public  void ShowSum(){
		float sumMan = 0f,sumWomen =0f,sumPeople= 0f;
		float row = table.getRowCount();//行数
		for(int i=0;i<row;i++){
			String a=(String) dtm.getValueAt(i, 2);//表格的一个元素
			if (a.equals("男")){
				++sumMan;
			}else{
				++sumWomen;
			}
		}
		sumPeople = sumMan+sumWomen;
		float a =sumMan/sumPeople;
		float b =sumWomen/sumPeople;
		String lab1="男生共有"+Integer.toString((int)sumMan)+"人   占总人数的"+a;
		String lab2="女生共有"+Integer.toString((int)sumWomen)+"人  占总人数的"+b;
		String lab3="餐厅服务员人数共有"+Integer.toString((int)sumPeople)+"人";
		labelMan.setText(lab1);
		labelWomen.setText(lab2);
		labelPeople.setText(lab3);
	}
}
