package com.restaurant.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.restaurant.dao.tableDao;
import com.restaurant.myswing.myBtnton;
import com.restaurant.myswing.myJPanel;
import com.restaurant.tool.TablebunmberToInt;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.awt.Font;
import java.awt.Color;

/*
 * 张皓
 */

public class TableFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tzhuoming;
	private JTextField tzhuozhuangtai;
	private JTextField tzhuoleixing;
	private JTextField tbeizhu;
	private JTextField tzhuojieshushijian;
	private JTextField tzhuokaitaishijian;
	private JTextField tzhuoweizhi;
	private JTextField tzhuohao;
	SimpleDateFormat df = new SimpleDateFormat("YY-MM-dd HH:mm:ss");
	Vector data=new Vector();   
	Vector returndata = null;
	int numbei = 0;//操作次数，在确定预定，和确定确定用到
	int row=0;
	int frameHeigth = 759;
	public static int a3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableFrame frame = new TableFrame();
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
	public TableFrame() {
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		//setBounds(10, 10, 552, 759);
		setBounds(10, 10, 552, 0);
		//动态张开界面
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                do_this_windowOpened(e);
            }
        });
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("餐桌预订界面");//设置窗口标题

		// 下面将创建一自定义带有背景面板对象并添加到窗口的容器中
		final myJPanel panel = new myJPanel(this.getClass().getResource(
				"/img/tablebg.png"));// 创建一个面板对象
		panel.setSize(537, 719);
		panel.setLocation(0, 0);
		panel.setLayout( null);//
		getContentPane().add(panel, BorderLayout.CENTER);
		contentPane.add(panel);

		//************************************************** 文本框的定义 ********************************************
		tzhuoming = new JTextField();
		tzhuoming.setColumns(10);
		tzhuoming.setBounds(93, 55, 139, 26);
		panel.add(tzhuoming);

		tzhuozhuangtai = new JTextField();
		tzhuozhuangtai.setFont(new Font("宋体", Font.BOLD, 18));// 设置文字为宋体、粗体、18号
		tzhuozhuangtai.setForeground(new Color(255, 0, 0));// 设置文字为红色
		tzhuozhuangtai.setColumns(10);
		tzhuozhuangtai.setBounds(93, 93, 139, 26);
		panel.add(tzhuozhuangtai);

		tzhuoleixing = new JTextField();
		tzhuoleixing.setColumns(10);
		tzhuoleixing.setBounds(93, 137, 139, 26);
		panel.add(tzhuoleixing);

		tbeizhu = new JTextField();
		tbeizhu.setColumns(10);
		tbeizhu.setBounds(373, 137, 139, 26);
		panel.add(tbeizhu);

		tzhuojieshushijian = new JTextField();
		tzhuojieshushijian.setColumns(10);
		tzhuojieshushijian.setBounds(373, 97, 139, 26);
		panel.add(tzhuojieshushijian);

		tzhuokaitaishijian = new JTextField();
		tzhuokaitaishijian.setColumns(10);
		tzhuokaitaishijian.setBounds(373, 61, 139, 26);
		panel.add(tzhuokaitaishijian);

		tzhuoweizhi = new JTextField();
		tzhuoweizhi.setColumns(10);
		tzhuoweizhi.setBounds(373, 17, 139, 26);
		panel.add(tzhuoweizhi);

		tzhuohao = new JTextField();
		tzhuohao.setColumns(10);
		tzhuohao.setBounds(93, 17, 139, 26);
		panel.add(tzhuohao);

		//************************************************** 16个按钮 的定义（16张桌子）********************************************
		JButton btntable1 = new JButton("");
		btntable1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//显示第一条数据
				row=0;
				showTextData(row);
			}
		});
		btntable1.setBounds(0, 180, 110, 110);
		panel.add( btntable1);
		URL landUrl = this.getClass().getResource("/img/table1.png");// 获得默认情况下按钮显示图片的URL
		btntable1.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		btntable1.setBorderPainted(false);// 设置不绘制按钮的边框
		btntable1.setIcon(new ImageIcon(landUrl));// 设置默认情况下按钮显示的图片

		JButton btntable2 = new JButton("2");
		btntable2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//显示第一条数据
				row=1;
				showTextData(row);
			}
		});
		btntable2.setBounds(122, 180, 110, 110);
		panel.add(btntable2);
		URL landUr2 = this.getClass().getResource("/img/table2.png");// 获得默认情况下按钮显示图片的URL
		btntable2.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		btntable2.setBorderPainted(false);// 设置不绘制按钮的边框
		btntable2.setIcon(new ImageIcon(landUr2));// 设置默认情况下按钮显示的图片

		JButton btntable3 = new JButton("");
		btntable3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//显示第一条数据
				row=2;
				showTextData(row);
			}
		});
		btntable3.setBounds(297, 180, 110, 110);
		panel.add(btntable3);
		URL landUr3 = this.getClass().getResource("/img/table3.png");// 获得默认情况下按钮显示图片的URL
		btntable3.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		btntable3.setBorderPainted(false);// 设置不绘制按钮的边框
		btntable3.setIcon(new ImageIcon(landUr3));// 设置默认情况下按钮显示的图片

		JButton btntable4  = new JButton("");
		btntable4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//显示第一条数据
				row=3;
				showTextData(row);
			}
		});
		btntable4.setBounds(417, 180, 110, 110);
		panel.add(btntable4);
		URL landUr4 = this.getClass().getResource("/img/table4.png");// 获得默认情况下按钮显示图片的URL
		btntable4.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		btntable4.setBorderPainted(false);// 设置不绘制按钮的边框
		btntable4.setIcon(new ImageIcon(landUr4));// 设置默认情况下按钮显示的图片


		JButton btntable5 = new JButton("");
		btntable5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//显示第一条数据
				row=4;
				showTextData(row);
			}
		});
		btntable5.setBounds(0, 284, 110, 110);
		panel.add(btntable5);
		URL landUr5 = this.getClass().getResource("/img/table5.png");// 获得默认情况下按钮显示图片的URL
		btntable5.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		btntable5.setBorderPainted(false);// 设置不绘制按钮的边框
		btntable5.setIcon(new ImageIcon(landUr5));// 设置默认情况下按钮显示的图片

		JButton btntable6 = new JButton("");
		btntable6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//显示第一条数据
				row=5;
				showTextData(row);
			}
		});
		btntable6.setBounds(118, 286, 100, 102);
		panel.add(btntable6);
		URL landUr6 = this.getClass().getResource("/img/table6.png");// 获得默认情况下按钮显示图片的URL
		btntable6.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		btntable6.setBorderPainted(false);// 设置不绘制按钮的边框
		btntable6.setIcon(new ImageIcon(landUr6));// 设置默认情况下按钮显示的图片

		JButton btntable7 = new JButton("");
		btntable7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//显示第一条数据
				row=6;
				showTextData(row);
			}
		});
		btntable7.setBounds(297, 284, 110, 110);
		panel.add(btntable7);
		URL landUr7 = this.getClass().getResource("/img/table7.png");// 获得默认情况下按钮显示图片的URL
		btntable7.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		btntable7.setBorderPainted(false);// 设置不绘制按钮的边框
		btntable7.setIcon(new ImageIcon(landUr7));// 设置默认情况下按钮显示的图片


		JButton btntable8 = new JButton("8");
		btntable8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//显示第一条数据
				row=7;
				showTextData(row);
			}
		});
		btntable8.setBounds(425, 284, 110, 110);
		panel.add(btntable8);
		URL landUr8 = this.getClass().getResource("/img/table8.png");// 获得默认情况下按钮显示图片的URL
		btntable8.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		btntable8.setBorderPainted(false);// 设置不绘制按钮的边框
		btntable8.setIcon(new ImageIcon(landUr8));// 设置默认情况下按钮显示的图片

		JButton btntable9 = new JButton("9");
		 btntable9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//显示第一条数据
				row=8;
				showTextData(row);
			}
		});
		 btntable9.setBounds(8, 386, 110, 110);
		 panel.add( btntable9);
		URL landUr9 = this.getClass().getResource("/img/table9.png");// 获得默认情况下按钮显示图片的URL
		btntable9.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		btntable9.setBorderPainted(false);// 设置不绘制按钮的边框
		btntable9.setIcon(new ImageIcon(landUr9));// 设置默认情况下按钮显示的图片

		JButton btntable10 = new JButton("10");
		 btntable10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//显示第一条数据
				row=9;
				showTextData(row);
			}
		});
		 btntable10.setBounds(122, 386, 110, 110);
		 panel.add( btntable10);
		URL landUr10 = this.getClass().getResource("/img/table10.png");// 获得默认情况下按钮显示图片的URL
		btntable10.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		btntable10.setBorderPainted(false);// 设置不绘制按钮的边框
		btntable10.setIcon(new ImageIcon(landUr10));// 设置默认情况下按钮显示的图片

		JButton btntable11 = new JButton("11");
		btntable11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//显示第一条数据
				row=10;
				showTextData(row);
			}
		});
		btntable11.setBounds(303, 386, 112, 110);
		panel.add(btntable11);
		URL landUr11 = this.getClass().getResource("/img/table11.png");// 获得默认情况下按钮显示图片的URL
		btntable11.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		btntable11.setBorderPainted(false);// 设置不绘制按钮的边框
		btntable11.setIcon(new ImageIcon(landUr11));// 设置默认情况下按钮显示的图片

		JButton btntable12= new JButton("12");
		 btntable12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//显示第一条数据
				row=11;
				showTextData(row);
			}
		});
		 btntable12.setBounds(425, 386, 110, 110);
		 panel.add( btntable12);
		URL landUr12 = this.getClass().getResource("/img/table12.png");// 获得默认情况下按钮显示图片的URL
		btntable12.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		btntable12.setBorderPainted(false);// 设置不绘制按钮的边框
		btntable12.setIcon(new ImageIcon(landUr12));// 设置默认情况下按钮显示的图片

		JButton btntable13 = new JButton("13");
		btntable13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//显示第一条数据
				row=12;
				showTextData(row);
			}
		});
		btntable13.setBounds(5, 495, 123, 110);
		panel.add(btntable13);
		URL landUr13 = this.getClass().getResource("/img/table13.png");// 获得默认情况下按钮显示图片的URL
		btntable13.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		btntable13.setBorderPainted(false);// 设置不绘制按钮的边框
		btntable13.setIcon(new ImageIcon(landUr13));// 设置默认情况下按钮显示的图片

		JButton btntale14 = new JButton("14");
		btntale14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//显示第一条数据
				row=13;
				showTextData(row);
			}
		});
		btntale14.setBounds(122, 495, 110, 110);
		panel.add(btntale14);
		URL landUr14 = this.getClass().getResource("/img/table14.png");// 获得默认情况下按钮显示图片的URL
		btntale14 .setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		btntale14 .setBorderPainted(false);// 设置不绘制按钮的边框
		btntale14 .setIcon(new ImageIcon(landUr14));// 设置默认情况下按钮显示的图片


		JButton btntable15 = new JButton("15");
		 btntable15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//显示第一条数据
				row=14;
				showTextData(row);
			}
		});
		 btntable15.setBounds(303, 495, 110, 110);
		 panel.add( btntable15);
		URL landUr15 = this.getClass().getResource("/img/table15.png");// 获得默认情况下按钮显示图片的URL
		btntable15 .setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		btntable15 .setBorderPainted(false);// 设置不绘制按钮的边框
		btntable15 .setIcon(new ImageIcon(landUr15));// 设置默认情况下按钮显示的图片


		JButton btntable16= new JButton("16");
		btntable16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//显示第一条数据
				row=15;
				showTextData(row);
			}
		});
		btntable16.setBounds(425, 495, 110, 110);
		panel.add(btntable16);
		URL landUr16 = this.getClass().getResource("/img/table16.png");// 获得默认情况下按钮显示图片的URL
		btntable16 .setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		btntable16 .setBorderPainted(false);// 设置不绘制按钮的边框
		btntable16 .setIcon(new ImageIcon(landUr16));// 设置默认情况下按钮显示的图片

		//************************************************** 5个按钮 的定义（预订，取消却预订，确认预订，换桌，退出）********************************************
		JButton btntable35 = new JButton("预定");
		btntable35.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取当前桌子的信息
				String zhuohao=tzhuohao.getText();
				a3=TablebunmberToInt.Duquhangshu(zhuohao);
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				row=a3;
				Vector line=(Vector)data.get(row);

				String zhuotaizhuangtai=line.get(2).toString();
				System.out.println("该桌子的状态是："+ zhuotaizhuangtai);
				//判断是否空闲,是表示能预定,否表示不能预定
				if(zhuotaizhuangtai.equals("空闲")){
					++numbei;//标记已经进行了预订
					zhuotaizhuangtai="预定";
					Date time=new Date();
					String b=df.format(time);
					System.out.println("预订的时间是："+b);
					//执行SQL语句
					String sql="update zhuozi set zhuotaizhuangtai='"+zhuotaizhuangtai+"' ,kaitaishijian='"+b+"'where zhuohao='"+zhuohao+"'";

					System.out.println(sql);
					if(tableDao.updateZhuozi(sql)){
						data=tableDao.queryVectorZhuozi("Select * from zhuozi");
						//显示第一条数据
						showTextData(row);
					}else{
						JOptionPane.showMessageDialog(null,"预定失败！","系统提示", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null,"此桌有人！","系统提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btntable35.setBounds(8, 625, 93, 23);
		panel.add(btntable35);

		JButton btntable36 = new JButton("取消预订");
		btntable36.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取当前桌子的信息
				String zhuohao=tzhuohao.getText();
				a3=TablebunmberToInt.Duquhangshu(zhuohao);
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				row=a3;
				Vector line=(Vector)data.get(row);

				String zhuotaizhuangtai=line.get(2).toString();
				System.out.println("该桌子的状态是："+ zhuotaizhuangtai);
				//判断是否空闲,是表示能预定,否表示不能预定
				if(zhuotaizhuangtai.equals("预定")){
					zhuotaizhuangtai="空闲";
					//执行SQL语句
					String sql="update zhuozi set zhuotaizhuangtai='"+zhuotaizhuangtai+"' where zhuohao='"+zhuohao+"'";
					System.out.println(sql);
					if(tableDao.updateZhuozi(sql)){
						data=tableDao.queryVectorZhuozi("Select * from zhuozi");
						//显示第一条数据
						showTextData(row);
					}else{
						JOptionPane.showMessageDialog(null,"取消预定失败！","系统提示", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null,"此桌没有被预订！取消预订失败！","系统提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btntable36.setBounds(122, 625, 93, 23);
		panel.add(btntable36);

		JButton btnExit = new JButton("退出");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exit();
			}
		});
		btnExit.setBounds(434, 688, 93, 23);
		panel.add(btnExit);

		JButton btnNewButton_2 = new JButton("确定预订");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numbei==0){
					JOptionPane.showMessageDialog(null, "请先预订餐桌!");
				}else{
				
				String zhuohao=tzhuohao.getText();
				a3=TablebunmberToInt.Duquhangshu(zhuohao);
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				row=a3;
				Vector line=(Vector)data.get(row);

				String zhuotaizhuangtai=line.get(2).toString();
				System.out.println("点击的桌子的状态是："+ zhuotaizhuangtai);
				//判断是否空闲,是表示能预定,否表示不能预定
				if(zhuotaizhuangtai.equals("预定")){

					returndata = new Vector();//存放确认预订时的桌号、桌名、位置、时间
					String tableno = tzhuohao.getText();
					String tablename = tzhuoming.getText();
					String location = tzhuoweizhi.getText();
					String time= tzhuokaitaishijian.getText();

					returndata.add(tableno);
					returndata.add(tablename);
					returndata.add(location);
					returndata.add(time);

					mainFrame.dataOftable = null;
					mainFrame.dataOftable= returndata;
					System.out.println("从台桌界面返回到主界面的预订餐桌信息是："+mainFrame.dataOftable);
					mainFrame.showDataOfTable();
					JOptionPane.showMessageDialog(null, "预订成功!你可以点菜了");
					setVisible(false);//确定后退出界面
				}else{
					JOptionPane.showMessageDialog(null,"确定预订失败！，需要先预订餐桌，才可以确定预订","系统提示", JOptionPane.ERROR_MESSAGE);
				}

			}
			}
		});
		btnNewButton_2.setBounds(425, 625, 93, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("换桌");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				   //获取当前桌子的信息
				 String zhuohao=tzhuohao.getText();
			        //把桌号转化成int型并把值传到换桌界面的a1
				 changeTableFrame.a1=TablebunmberToInt.Duquhangshu(zhuohao);
				 changeTableFrame.main(null);
			}
		});
		btnNewButton.setBounds(301, 625, 93, 23);
		panel.add(btnNewButton);

		data=tableDao.queryVectorZhuozi("Select * from zhuozi");
		//row初始值为0  显示第1条数据
		showTextData(row);

	}
	
	//************************************************** 构造函数外的方法********************************************

	public void Exit(){//退出
		String a=mainFrame.txtTableno.getText();
		if(a.equals("")){
			JOptionPane.showMessageDialog(null, "请确认预订，才能退出!");
		}else{
			System.err.println("******************************你离开张皓的代码世界***********************************");
		this.setVisible(false);
		}
	}
	
	//在文本框显示数据
	public void showTextData(int row){
		Vector line=(Vector)data.get(row);
		tzhuohao.setText(line.get(0).toString());
		tzhuoming.setText(line.get(1).toString());
		tzhuozhuangtai.setText(line.get(2).toString());
		tzhuoleixing.setText(line.get(3).toString());
		tzhuoweizhi.setText(line.get(4).toString());
		tzhuokaitaishijian.setText(line.get(5).toString());
		tzhuojieshushijian.setText(line.get(6).toString());
		tbeizhu.setText(line.get(7).toString());
		lockText();//显示数据时要锁住
	}

	//锁住文本框方法
	public void lockText(){
		tzhuohao.setEnabled(false);
		tzhuoming.setEnabled(false);
		tzhuozhuangtai.setEnabled(false);
		tzhuoleixing.setEnabled(false);
		tzhuoweizhi.setEnabled(false);
		tzhuokaitaishijian.setEnabled(false);
		tzhuojieshushijian.setEnabled(false);
		tbeizhu.setEnabled(false);
	}
	
    protected void do_this_windowOpened(WindowEvent e) {
        final int weith = getWidth();// 记录窗体高度
        new Thread() {// 创建新线程
            public void run() {
                Rectangle rec = getBounds();
                for (int i = 0; i < frameHeigth; i += 10) {// 循环拉伸窗体
                    setBounds(rec.x , rec.y, weith, i);// x,y不变,宽也不变化，高度变化
                    //System.out.println(rec.x);
                    //System.out.println(rec.y);
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
