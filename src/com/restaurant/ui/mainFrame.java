package com.restaurant.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;

















import com.restaurant.dao.meun;
import com.restaurant.dao.userDao;
import com.restaurant.myswing.myJPanel;
import com.restaurant.tool.Day;

import javax.swing.JTextField;

import java.awt.SystemColor;

import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * 易承东
 */

public class mainFrame extends JFrame {

	public static String job=null;//获取登录人员的职务
	String liucheng = "";//控制同一下单号不能多次添加
	public static String meunNO=null;
	int Number=0;
	public static Vector dataOftable;
	private JPanel contentPane;
	public JLabel timeLabel ;
	private static Vector title ;
	private static Vector datamenu = null;//
	private Vector data ;
	private static JTable table ;
	static DefaultTableModel dtm = new DefaultTableModel();


	final static Vector user1=new Vector();
	public static JTextField txtTableno;
	private static JTextField txtTablename;
	private static JTextField txtLocation;
	private static JTextField txtTime;
	private static JTextField menuNo;
	private static JTextField textmeunNo;
	public static JLabel LabeltableNo ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame(user1);
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
	public mainFrame(final Vector user) {
		System.out.println("从登录界面传来的数据："+user);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 691);//设置大小
		//设置屏幕居中
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((screenWidth-900)/2,(screenHeight-691)/2);//设置位置
		this.setResizable(false);//禁止放大窗口
		this.setTitle("餐厅管理系统主界面");//设置窗口标题

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//--------------------------面板最上面的内容（当前的用户和时间）---------------------------
		// 下面将创建一自定义带有背景面板对象并添加到窗口的容器中
		final myJPanel panel = new myJPanel(this.getClass().getResource(
				"/img/mainfrabg.png"));
		panel.setBounds(0, 0, 900, 100);
		panel.setLayout( null);// 设置面板的布局管理器为网格组布局
		getContentPane().add(panel, BorderLayout.CENTER);
		contentPane.add(panel);

		final JLabel eClueOnLabel = new JLabel();
		eClueOnLabel.setSize(98, 40);
		eClueOnLabel.setLocation(10, 10);
		panel.add(eClueOnLabel);
		eClueOnLabel.setFont(new Font("", Font.BOLD, 12));
		eClueOnLabel.setText("  当前操作员：");

		final JLabel fClueOnLabel = new JLabel();
		fClueOnLabel.setSize(75, 35);
		fClueOnLabel.setLocation(101, 10);
		fClueOnLabel.setFont(new Font("", Font.BOLD, 12));
		panel.add(fClueOnLabel);
		fClueOnLabel.setHorizontalAlignment(SwingConstants.CENTER);

		if (user == null)
			fClueOnLabel.setText("系统默认用户");
		else
			fClueOnLabel.setText(user.get(0).toString());


		JLabel lblNewLabel_1 = new JLabel("\u804C\u52A1\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(20, 47, 54, 28);
		panel.add(lblNewLabel_1);

		JLabel Joblabel = new JLabel("");
		Joblabel.setFont(new Font("", Font.BOLD, 12));
		Joblabel.setBounds(59, 47, 83, 28);
		panel.add(Joblabel);
		Joblabel.setHorizontalAlignment(SwingConstants.CENTER);

		if (user.get(2).toString()== null)
			Joblabel.setText("服务员");
		else{
			Joblabel.setText(user.get(2).toString());
			System.out.println(user.get(2));
		}
		job=Joblabel.getText().toString();

		final JLabel aClueOnLabel = new JLabel();
		aClueOnLabel.setSize(75, 35);
		aClueOnLabel.setLocation(654, 0);
		panel.add(aClueOnLabel);
		aClueOnLabel.setFont(new Font("", Font.BOLD, 12));
		aClueOnLabel.setText("  今天是：");

		//显示具体日期的标签：2015-3-30
		final JLabel dateJlabel = new JLabel();
		dateJlabel.setSize(122, 18);
		dateJlabel.setLocation(637, 32);
		dateJlabel.setFont(new Font("", Font.BOLD, 12));
		panel.add(dateJlabel);
		dateJlabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateJlabel.setText(Day.getDateOfShow());

		//显示周几的标签
		final JLabel weekJlabel = new JLabel();
		weekJlabel.setSize(67, 38);
		weekJlabel.setLocation(749, 22);
		weekJlabel.setFont(new Font("", Font.BOLD, 12));
		panel.add(weekJlabel);
		weekJlabel.setHorizontalAlignment(SwingConstants.CENTER);
		weekJlabel.setText(Day.getDayOfWeek());

		timeLabel = new JLabel();// 创建用于显示时间的标签对象
		timeLabel.setSize(83, 40);
		timeLabel.setLocation(807, 20);
		timeLabel.setFont(new Font("宋体", Font.BOLD, 14));// 设置标签中的文字为宋体、粗体、14号
		timeLabel.setForeground(new Color(255, 0, 0));// 设置标签中的文字为红色
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);// 设置标签中的文字居中显示
		panel.add(timeLabel);// 将标签添加到上级容器中

		new Time().start();// 开启线程


		//--------------------中间的内容-----------------------------------------------------------------\


		// 下面将创建一自定义带有背景面板对象并添加到窗口的容器中
		final myJPanel pane2 = new myJPanel(this.getClass().getResource(
				"/img/mainfrabg2.png"));
		pane2.setBounds(0, 99, 900, 359);
		pane2.setLayout( null);// 设置面板的布局管理器为网格组布局
		getContentPane().add(pane2, BorderLayout.CENTER);
		contentPane.add(pane2);

		//滚动面板
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 25, 844, 310);
		pane2.add(scrollPane);

		title = new Vector();
		data = new Vector();
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				processSelectionRow();   
				System.out.println("你点击了主界面的菜单表");
			}
		});
		table.setBounds(26, 25, 844, 310);

		JTableHeader jaTableHeader = table.getTableHeader();
		jaTableHeader .setBounds(26, 1, 844,22);
		pane2.add(jaTableHeader );
		table.setBackground(new Color(240, 255, 240));
		scrollPane.setViewportView(table);//加上了这句才出现了滚动条

		//***************************************************底部面板的内容*******************************************
		//自定义一个面板
		final myJPanel pane3 = new myJPanel(this.getClass().getResource(
				"/img/mainframebg3.png"));
		pane3.setBounds(0, 451, 900, 221);
		pane3.setLayout( null);// 设置面板的布局管理器为网格组布局
		getContentPane().add(pane3, BorderLayout.CENTER);
		contentPane.add(pane3);
		
		//---------标签----------
	    LabeltableNo = new JLabel("");//用来显示结账的桌号
		LabeltableNo.setBounds(372, 149, 50, 19);
		pane3.add(LabeltableNo);

		//---------文本框----------------
		txtTableno = new JTextField();//桌号
		txtTableno.setBounds(90, 95, 74, 34);
		pane3.add(txtTableno);
		txtTableno.setColumns(10);

		txtTablename = new JTextField();//桌名
		txtTablename.setColumns(10);
		txtTablename.setBounds(274, 95, 74, 34);
		pane3.add(txtTablename);

		txtLocation = new JTextField();//位置
		txtLocation.setColumns(10);
		txtLocation.setBounds(442, 95, 99, 34);
		pane3.add(txtLocation);

		txtTime = new JTextField();//时间
		txtTime.setColumns(10);
		txtTime.setBounds(588, 95, 112, 34);
		pane3.add(txtTime);

		menuNo = new JTextField();//下单号
		menuNo.setBounds(759, 98, 84, 31);
		pane3.add(menuNo);
		menuNo.setColumns(10);

		textmeunNo = new JTextField();//结账按钮上方的下单号
		textmeunNo.setBounds(421, 149, 26, 19);
		pane3.add(textmeunNo);
		textmeunNo.setColumns(10);
		//-------------文本框结束--------------------------------

		
		//---------------------按钮------------------------
		JButton btncaidan = new JButton("点菜");
		btncaidan.setBounds(111, 148, 93, 43);
		btncaidan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tableNo = mainFrame.txtTableno.getText().toString();//2016\4\17加入判断
				if(tableNo.equals("")){
					JOptionPane.showMessageDialog(null, "你没有选择餐桌哦！亲");
				}else{
					System.err.println("******************************你已经进入嘉健的代码世界***********************************");
				MenuFrame.main(null);
				}
			}
		});
		pane3.add(btncaidan);

		JButton btntable = new JButton("选择餐桌");
		btntable.setBounds(10, 148, 93, 43);
		btntable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.err.println("******************************你已经进入张皓的代码世界***********************************");
				TableFrame.main(null);
			}
		});
		pane3.add(btntable);


		JButton btndiancai = new JButton("下单");
		btndiancai.setBounds(214, 148, 93, 43);
		btndiancai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String tableNo = txtTableno.getText();
				String foodNo = menuNo.getText();
				if(tableNo.equals("")){
					JOptionPane.showMessageDialog(null, "下单失败！你还没有选择餐桌，请先选择餐桌！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);// 弹出提示
				}
				else if(foodNo.equals("")){
					JOptionPane.showMessageDialog(null, "下单失败！你还没有点菜，请点菜", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);// 弹出提示

				}
				else if(liucheng.equals(menuNo.getText())){
					JOptionPane.showMessageDialog(null, "下单失败!不能重复下单哦");
				}
				else{
					showTableData();
					liucheng=menuNo.getText().toString();
				}

			}
		});
		pane3.add( btndiancai);


		JButton btnMoney = new JButton("结账");
		btnMoney.setBounds(361, 168, 105, 23);
		btnMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				meunNO = textmeunNo.getText();
				if(meunNO .equals("")){
					JOptionPane.showMessageDialog(null, "接站失败！你还没选择需要结账的账单号！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);// 弹出提示
				}
				else{
					System.err.println("******************************你已经进入承东的代码世界***********************************");
					PayFrame.main(null);
				}
			}
		});
		pane3.add(btnMoney);


		JButton btnxiaoshou = new JButton("销售统计");
		btnxiaoshou.setBounds(666, 158, 93, 23);
		btnxiaoshou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user1 = (String) user.get(2);
				System.out.println(user1);
				if(!user1.equals("管理员")){
					JOptionPane.showMessageDialog(null, "你没有查看的权限!");
				}else{
				System.err.println("******************************你已经进入文浩的代码世界***********************************");
				salesFrame.main(null);
				}
			}
		});
		pane3.add( btnxiaoshou);

		JButton btnwaiter = new JButton("服务员信息管理");
		btnwaiter.setBounds(523, 158, 133, 23);
		btnwaiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user1 = (String) user.get(2);
				if(!user1.equals("管理员")){
					JOptionPane.showMessageDialog(null, "你没有查看的权限!");
				}else{
				System.err.println("******************************你已经进入龙飞的代码世界***********************************");
				waiterFrame.main(null);
				}
			}
		});
		pane3.add( btnwaiter);


		JButton btnExit = new JButton("退出");
		btnExit.setBounds(781, 180, 93, 23);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(null,"您要退出本系统，确定吗？","退出系统",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION){
					System.out.println((new Date()).toString()+"退出系统");
					System.exit(0);
				}
			}
		});
		pane3.add(btnExit);

		JButton btnNewButton_2 = new JButton("清空");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model =(DefaultTableModel) table.getModel();
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}

			}
		});
		btnNewButton_2.setBounds(781, 10, 93, 23);
		pane3.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("取消订单");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String menuNo = textmeunNo.getText().toString();
				String tableNo = LabeltableNo.getText().toString().substring(0, 3);
				if(menuNo.equals("")){
					JOptionPane.showMessageDialog(null, "请在表格中选择需要取消的订单!");
				}else{
					meun.deleDC(menuNo, tableNo);
					deleteRow();//从表个删除一行
				}
			}
		});
		btnNewButton.setBounds(678, 10, 93, 23);
		pane3.add(btnNewButton);
		
		showfirstData();
		lockText();//锁住文本框
		
	}
	//********************************************** 构造方法结束 *********************************************************
	//**********************************************构造方法外的类********************************************************
	
	//从表格中移除点击时点中一行——点击取消订单时调用
	protected static void deleteRow() {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.removeRow(table.getSelectedRow());
	}

	//从预订台桌界面中，返回预订的信息，并显示在主界面上
	public static  void showDataOfTable() {
		// TODO 自动生成的方法存根
		System.out.println("在界面中显示的从桌子界面返回的信息是："+dataOftable);
		txtTableno.setText(dataOftable.get(0).toString());
		txtTablename.setText(dataOftable.get(1).toString());
		txtLocation.setText(dataOftable.get(2).toString());
		txtTime.setText(dataOftable.get(3).toString());
	}

	//启动主界面是会调用该函数——显示数据库中还没有付款的全部信息
	public static  void showfirstData(){
		title.add("下单号");
		title.add("桌号");
		title.add("菜号");
		title.add("菜名");
		title.add("数量");
		title.add("单价");
		title.add("消费时间");
		title.add("是否结账");
		Vector data = new Vector ();
		String pay = "否";
		
		data=meun.firstData(pay);//根据付款情况为“否”，查询dingcan表中的信息
		dtm.setDataVector(data, title);
		table.setModel(dtm);
	}
	//点击下单按钮时调用——把下单的信息更新到主界面的表格中
	private void showTableData() {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		for(int i = 0;i <datamenu.size();i++){//datamenu是依据主界面上桌子号，和时间查找的的信息点餐信息
			Vector data = new Vector();
			data = (Vector) datamenu.get(i);
			model.addRow(data);
		}
	}
	
	//完成结账后，把结账相应的订单信息从主界面的表格移除掉
	public static void delePay(){
		int a= table.getRowCount();
		for(int i =0;i<a;i++){
			int j= (int) dtm.getValueAt(i, 0);
			System.out.println(i+"   是："+j);
			if(j==28){
				
				dtm.removeRow(i);
				a = a-1;
				--i;
			}
		}
	}
	//在标签上动态显示时间
	class Time extends Thread {// 创建内部类
		public void run() {// 重构父类的方法
			while (true) {
				Date date = new Date();// 创建日期对象
				timeLabel.setText(date.toString().substring(11, 19));// 获取当前的时分秒，并显示到时间标签中
				try {
					Thread.sleep(1000);// 令线程休眠1秒
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//处理选中的表中的行。
	private void processSelectionRow(){
		int row = table.getSelectedRow();//获取点击的行数
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		textmeunNo.setText(model.getValueAt(row, 0).toString());//获取下单号
		LabeltableNo.setText(model.getValueAt(row, 1).toString()+"桌子");//获取桌号

	}

	//根据桌号和预订时间来查找点餐表的信息——这里主要是要获取点餐表中的下单号
	public static void getDianCandata(){
		datamenu = new Vector();
		String tableNo = txtTableno.getText();//从界面的桌号文本框获取桌号
		String tabletime = txtTime.getText().toString();//从界面的获取预订餐桌的时间
		System.out.println("正在点菜的桌号是"+tableNo);
		datamenu = meun.MenuTableNo(tableNo,tabletime);//返回的datameun是一个二维的Vector
		System.out.println("按照桌号和时间从数据库的表格中读取的二维信息是"+datamenu);

		Vector datamenu1 = new Vector();
		datamenu1 = ((Vector) datamenu.get(0));
		System.out.println("按照桌号和时间从数据库的表格中读取的一位信息是"+datamenu1);
		String a=datamenu1.get(0)+"";
		menuNo.setText(a);
	}
	
	//移除表格的一行或者多行
	public static void removeRow(){
		String mo = textmeunNo.getText().trim();//结账时的账单号
		int r = Integer.parseInt(mo);//账单号转int
		int row = table.getRowCount();//行数
		for(int i=0;i<row;i++){
			int a=(int) dtm.getValueAt(i, 0);//表格的一个元素
			if(a==r){
				dtm.removeRow(i);
				--row;
				--i;
			}
		}
	}

	//锁文本框
	public void lockText(){
		txtTableno.setEnabled(false);
		txtTablename.setEnabled(false);
		txtLocation.setEnabled(false);
		txtTime.setEnabled(false);
		menuNo.setEnabled(false);
		textmeunNo.setEnabled(false);
	}
	
}
