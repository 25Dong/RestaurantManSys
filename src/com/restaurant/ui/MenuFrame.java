package com.restaurant.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.restaurant.dao.meun;
import com.restaurant.dao.waiterDao;
import com.restaurant.entity.Meun;
import com.restaurant.entity.Waiter;
import com.restaurant.myswing.myJPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * 梁嘉健
 */

public class MenuFrame extends JFrame {


	private JPanel contentPane;
	private JTable table;
	private static JTable table_1;
	static int MONEY = 0;
	private static int  NUMBER = 0; 
	static int NO = 0;
	static int MENUNO = 1;
	int liucheng=0;//用来控制点菜流程：添加后才可以点菜
	int liucheng1=0;//用来控制管理员添加菜品是的步骤，控制先解锁才能添加菜品
	//	static DefaultTableModel dtm = new DefaultTableModel();
	//	static Vector tableHeads = new Vector();
	//	static Vector data = new Vector();
	public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;  
	public static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	static ArrayList list = new ArrayList();
	static Socket server; 
	private JTextField textfoodNo;
	private JTextField textFoodNO;
	private JTextField textFoodNo2;
	private JTable table_2;
	private JTextField textFoodName;
	private JTextField textdanjia;
	private JButton btndelete ;
	private JButton btnadd ;
	private JButton btnudapte ;
	private int frameWidth = 833;//动态窗口展开的宽度

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuFrame frame = new MenuFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//这个方法是把数据库的点餐表在点餐表格中显示出来，顾客每按一次增加都会调用这个方法来更新点餐表。
	//查询test表的所有信息
	public static void numberadd(String a){

		DefaultTableModel dtm = new DefaultTableModel();
		Vector tableHeads = new Vector();
		Vector data = new Vector();
		tableHeads.add("菜号");
		tableHeads.add("菜名");
		tableHeads.add("数量");
		tableHeads.add("金额");
		tableHeads.add("桌号");
		tableHeads.add("日期");
		tableHeads.add("下单号");
		data = meun.meun("a");
		dtm.setDataVector(data,tableHeads);
		table_1.setModel(dtm);


		//用数组设置表格的列宽
		int[] columnWith = {40,80,30,30,40,119,40};//列宽的数组
		TableColumnModel columnModel = table_1.getColumnModel();//获取列模型
		int count = columnModel.getColumnCount();//获取列数量
		for(int i =0;i<count;i++){//遍历
			TableColumn column = columnModel.getColumn(i);//获取列对象
			column.setPreferredWidth(columnWith[i]);//用数组的元素设置列的宽度
		}
	}

	/**
	 * Create the frame.
	 */
	public MenuFrame() {
		setResizable(false);
		setResizable(false);
		setTitle("\u9910\u5385\u7BA1\u7406\u7CFB\u7EDF");

		//动态展开
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				do_this_windowOpened(e);
			}
		});

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(420, 100, 833, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);


		// 下面将创建一自定义带有背景面板对象并添加到窗口的容器中
		final myJPanel panel = new myJPanel(this.getClass().getResource(
				"/img/menubg.png"));// 创建一个面板对象
		panel.setSize(826, 525);
		panel.setLocation(0, 0);
		panel.setLayout( null);//
		getContentPane().add(panel, BorderLayout.CENTER);
		contentPane.add(panel);

		//*****************************************************滚动面板的定义*********************************************
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 29, 293, 374);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(422, 29, 379, 200);

		//*****************************************************表格的定义*********************************************


		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			//添加右边的表格的监听事件
			@Override
			public void mouseClicked(MouseEvent arg0) {
				processSelectionRow1();
				System.out.println("你点击了预览点菜的表格");
			}
		});
		scrollPane_1.setViewportView(table_1);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {//给左边的菜单表格添加鼠标点击监听事件
				processSelectionRow();   
				System.out.println("你点击了菜单表");
			}
		});
		table.setModel(new DefaultTableModel(
				new Object[][] {
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
				},
				new String[] {
						"\u83DC\u7F16\u53F7", "\u540D\u5B57", "\u4EF7\u683C"
				}
				));
		scrollPane.setViewportView(table);
		contentPane.setLayout(null);
		panel.add(scrollPane);
		panel.add(scrollPane_1);


		//*****************************************************文本框的定义*********************************************
		textFoodNO = new JTextField();//左边菜号
		textFoodNO.setColumns(10);
		textFoodNO.setBounds(471, 413, 43, 25);
		panel.add(textFoodNO);

		textFoodNo2 = new JTextField();//右边菜号
		textFoodNo2.setColumns(10);
		textFoodNo2.setBounds(473, 263, 72, 25);
		panel.add(textFoodNo2);

		textFoodName = new JTextField();//菜名
		textFoodName.setBounds(159, 413, 93, 27);
		panel.add(textFoodName);
		textFoodName.setColumns(10);

		textdanjia = new JTextField();//单价
		textdanjia.setBounds(342, 414, 32, 23);
		panel.add(textdanjia);
		textdanjia.setColumns(10);

		textfoodNo = new JTextField();//数量
		textfoodNo.setColumns(10);
		textfoodNo.setBounds(72, 413, 32, 25);
		panel.add(textfoodNo);

		//*****************************************************按钮的定义*********************************************
		JButton button = new JButton("点餐");
		//按了点餐按钮后把点餐表中的数据取出来转换为string，然后传到服务器端。
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(liucheng==0){//流程变脸等于0说明还没有点击添加按钮，
					JOptionPane.showMessageDialog(null, "请先添加菜品!");
				}else{
					int a = table_1.getRowCount();
					Date date = new Date();
					for(int i = 0;i < a;i++){
						meun.order_(table_1.getValueAt(i, 0).toString(), 
								table_1.getValueAt(i, 1).toString(), 
								table_1.getValueAt(i, 2).toString(), 
								table_1.getValueAt(i, 3).toString(), 
								table_1.getValueAt(i, 4).toString(),
								table_1.getValueAt(i, 5).toString(),
								Integer.parseInt(table_1.getValueAt(i, 6).toString()),//易承东
								"否");
					}
					JOptionPane.showMessageDialog(null, "点餐成功！请不要浪费食物！你可以下单了");
					mainFrame.getDianCandata();
					setVisible(false);//点餐成功后退出界面
				}
			}
		});
		button.setBounds(617, 413, 81, 27);
		panel.add(button);

		JButton btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//NO+=1;

				if(textfoodNo.getText().equals("")||textFoodNO.getText().equals("")){
					JOptionPane.showMessageDialog(null, "菜号或者数量不能为0!");

				}else if(Integer.parseInt(textFoodNO.getText())<=0){         //-----------------------2014/4/17
					JOptionPane.showMessageDialog(null, "菜号或者数量不能为负数哦亲们!");
				}
				else{
					int diyici=0;//第一次点击添加的时候
					++liucheng;//控制流程保证添加再到点餐
					int number;
					if(diyici == 0){//如果是第一次点击添加的时候，下单号取上一次的最大值再加一
						diyici++;
						number=meun.maxMeunNO();//获取目前订餐表的最大的订单号
						number++;
					}else{
						
						number = meun.TestMeunNO();//否则取的就是test-表的下单号
					}
					String tableNo= mainFrame.dataOftable.get(0).toString();//台桌号
					String time= mainFrame.dataOftable.get(3).toString();//时间
					//插入到test_表
					meun.sales_(textfoodNo.getText(), textFoodName.getText(), textFoodNO.getText(),textdanjia.getText(), tableNo,time,number);
					//在右边的表格显示数据
					numberadd("");

				}
			}
		});
		btnNewButton.setBounds(535, 412, 72, 27);
		panel.add(btnNewButton);

		//清空点餐表
		JButton button_1 = new JButton("清空");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				meun.Del_list();
				NO = 0;
				numberadd("");
			}
		});
		button_1.setActionCommand("");
		button_1.setBounds(693, 262, 93, 27);
		panel.add(button_1);

		JButton button_3 = new JButton("退餐");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(liucheng==0){
					JOptionPane.showMessageDialog(null, "清先添加!才能退餐");
				}else{
				meun.Del_select(textFoodNo2.getText());//根据菜号进行退餐
				//在数据库的test-表中删除信息后，界面上的表格也移除相应的一行
				DefaultTableModel model = (DefaultTableModel)table_1.getModel();//获得该表的表格模型
				model.removeRow(table_1.getSelectedRow());//在表格中移除该行
				}
			}
		});
		button_3.setBounds(568, 262, 93, 27);
		panel.add(button_3);

		btnadd = new JButton("添加");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(mainFrame.job.equals("管理员")){
					if(liucheng1==0){//等于0说明还没有执行解锁的代码，即还没有解锁
						JOptionPane.showMessageDialog(null, "请你先解锁!");
					}else{
						int maxFoodNo = meun.maxFoodNO()+1;//获取最大的目前菜单表中最大的菜号
						String foodNO = Integer.toString(maxFoodNo);//整形转换成String
						textfoodNo.setText(foodNO);//显示菜号

						String foodName = textFoodName.getText().toString();//获取菜名
						int money =Integer.parseInt( textdanjia.getText());//获取价格

						if (foodName.equals("")||money==0){
							JOptionPane.showConfirmDialog(null, "菜名不能为空，单价不能为0！", "输入有误！", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						}else{
							Meun meun1 = new Meun();//菜单对象
							Vector row = new Vector();//用来在表格上显示新添加的一行数据的信息

							meun1.setFoodNo(foodNO);//给对象赋值
							row.add(foodNO);

							meun1.setFoodName(foodName);
							row.add(foodName);

							meun1.setMoney(money);
							row.add(money);

							DefaultTableModel model = (DefaultTableModel)table.getModel();
							model.addRow(row);//表格上添加一行

							meun.insert(meun1);//菜单表中插入信息

						}
					}
				}else{
					JOptionPane.showMessageDialog(null, "你没有权限执行该操作！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);// 弹出提示
				}
			}
		});
		btnadd.setBounds(153, 463, 69, 23);
		panel.add(btnadd);

		btnudapte = new JButton("修改");
		btnudapte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mainFrame.job.equals("管理员")){
					String foodNO = textfoodNo.getText().toString();
					String foodName = textFoodName.getText().toString();
					int money =Integer.parseInt( textdanjia.getText());

					if(table.getSelectedRow()<0){
						JOptionPane.showConfirmDialog(null,"请选择表格中的一行","修改数据",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

					}else{
						Meun meun2 = new Meun();

						meun2.setFoodNo(foodNO);
						meun2.setFoodName(foodName);
						meun2.setMoney(money);

						DefaultTableModel model = (DefaultTableModel)table.getModel();
						//在表格上显示修改后的信息
						model.setValueAt(textfoodNo.getText(),table.getSelectedRow() , 0);
						model.setValueAt(textFoodName.getText(),table.getSelectedRow() , 1);
						model.setValueAt(Integer.parseInt(textdanjia.getText()),table.getSelectedRow() , 2);

						meun.update(meun2);//更新表格信息

					}
				}else{
					JOptionPane.showMessageDialog(null, "你没有权限执行该操作！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);// 弹出提示
				}
			}
		});
		btnudapte.setBounds(232, 463, 69, 23);
		panel.add(btnudapte);


		btndelete = new JButton("删除");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mainFrame.job.equals("管理员")){
					if(table.getSelectedRow()<0){//没有点中表格中任意一行
						JOptionPane.showConfirmDialog(null,"请选择表格中的一行","修改数据",
								JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

					}else{
						meun.delete(textfoodNo.getText());//根据菜号删除菜单信息
						
						DefaultTableModel model = (DefaultTableModel)table.getModel();//获得该表的表格模型
						model.removeRow(table.getSelectedRow());//在表格中移除该行

					}
				}else{
					JOptionPane.showMessageDialog(null, "你没有权限执行该操作！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);// 弹出提示
				}
			}
		});
		btndelete.setBounds(311, 463, 69, 23);
		panel.add(btndelete);

		JButton btnNewButton_5 = new JButton("解锁");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mainFrame.job.equals("管理员")){
					++liucheng1;//控制流程变量
					textFoodName.setEnabled(true);//解锁住菜名文本框
					textdanjia.setEnabled(true);//解锁住单价框
					textFoodName.setText("");
					textdanjia.setText("0");

				}else{
					JOptionPane.showMessageDialog(null, "你没有权限执行该操作！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);// 弹出提示
				}

			}
		});
		btnNewButton_5.setBounds(81, 463, 62, 23);
		panel.add(btnNewButton_5);


		JButton btnNewButton_1 = new JButton("退出");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exit();
			}
		});
		btnNewButton_1.setBounds(724, 495, 93, 23);
		panel.add(btnNewButton_1);


		//******************************************************其他******************************
		ShowTableData();//显示菜单的所有信息
		meun.Del_list();//同时把test-表格清空

		textfoodNo.setEnabled(false);//锁住菜号文本框
		textFoodNo2.setEnabled(false);//锁住右边的菜号的文本框
		textFoodName.setEnabled(false);//锁住菜名文本框
		textdanjia.setEnabled(false);//锁住单价框
		textFoodNO.setText("1");//点菜默认设为1
		System.out.println("进入点菜模块的登录人员的职务是："+mainFrame.job);
	}

	//**********************************************构造函数外的函数****************************************************



	protected void Exit() {
		this.setVisible(false);
		System.err.println("******************************你离开嘉健的代码世界***********************************");
	}

	//显示菜单的所有信息
	public void ShowTableData(){
		DefaultTableModel dtm = new DefaultTableModel();
		Vector tableHeads = new Vector();
		Vector data = new Vector();
		tableHeads.add("菜编号");
		tableHeads.add("名字");
		tableHeads.add("价格");
		data = meun.meun(5);//注意这里的5只是一个形参，因为meun类重载了meun方法，另一个方法的参数是String
		dtm.setDataVector(data,tableHeads);
		table.setModel(dtm);
	}

	//处理选中的菜单表中的行。
	private void processSelectionRow(){
		int row = table.getSelectedRow();
		DefaultTableModel model = (DefaultTableModel)table.getModel();

		textfoodNo.setText(model.getValueAt(row, 0).toString());
		textFoodName.setText(model.getValueAt(row, 1).toString());
		textdanjia.setText(model.getValueAt(row, 2).toString());

	}
	
	//处理选中的菜单表中的行。
		private void processSelectionRow1(){
			int row = table_1.getSelectedRow();
			DefaultTableModel model = (DefaultTableModel)table_1.getModel();

			textFoodNo2.setText(model.getValueAt(row, 0).toString());//在菜号文本框显示点击一行的菜号

		}
		
	//动态窗体
	protected void do_this_windowOpened(WindowEvent e) {
		final int height = getHeight();// 记录窗体高度
		new Thread() {// 创建新线程
			public void run() {
				Rectangle rec = getBounds();
				for (int i = 0; i < frameWidth; i += 10) {// 循环拉伸窗体
					setBounds(rec.x - i / 2, rec.y, i, height);// 不断设置窗体大小与位置
					try {
						Thread.sleep(10);// 线程休眠10毫秒
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		}.start();// 启动线程
	}


}

