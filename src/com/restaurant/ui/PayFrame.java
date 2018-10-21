package com.restaurant.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.Color;

import javax.swing.JButton;

import com.restaurant.dao.meun;
import com.restaurant.dao.payDao;
import com.restaurant.entity.Pay;
import com.restaurant.myswing.myJPanel;
import com.restaurant.tool.Day;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/*
 * 易承东
 */

public class PayFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textpayNo;
	private JTable table;
	private JTextField textzongfen;
	private JButton btnNewButton;
	private JTextField textshouqiang;
	private JTextField textzonger;
	private JTextField textzhaoling;
	private JLabel labeldata;
	private JLabel labetime;
	DefaultTableModel dtm = new DefaultTableModel();
	private Vector title ;
	private Vector data ;
	private JButton button;
	private JButton button_1;
	int zongjia=0;//总价
	int c=0;//总份数
	int payNo;//账单号
	int liucheng=0;//控制流程
	private String meunNo;//点餐时的下单号

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayFrame frame = new PayFrame();
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
	public PayFrame() {
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 379, 582);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("结账");

		// 下面将创建一自定义带有背景面板对象并添加到窗口的容器中
		final myJPanel panel = new myJPanel(this.getClass().getResource(
				"/img/paybg.png"));// 创建一个面板对象
		panel.setSize(365, 546);
		panel.setLocation(0, 0);
		panel.setLayout( null);//
		getContentPane().add(panel, BorderLayout.CENTER);
		contentPane.add(panel);

		//显示具体日期的标签：2015-3-30
		labeldata = new JLabel("");
		labeldata.setBounds(15, 20, 105, 15);
		labeldata.setFont(new Font("", Font.BOLD, 12));
		labeldata.setHorizontalAlignment(SwingConstants.CENTER);
		labeldata.setText(Day.getDateOfShow());
		panel.add(labeldata);

		//显示具体的时间
		labetime = new JLabel("");
		labetime.setBounds(97, 21, 84, 15);
		labetime.setFont(new Font("", Font.BOLD, 12));
		labetime.setHorizontalAlignment(SwingConstants.CENTER);
		labetime.setText(Day.getTime());
		panel.add(labetime);

		//滚动面板
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 80, 335, 323);
		panel.add(scrollPane);

		//表格
		table = new JTable(dtm);
		table.setBackground(new Color(250, 250, 210));
		table.setBounds(0, 35, 335, 298);
		table.setForeground(new Color(0, 0, 0));
		scrollPane.setViewportView(table);

		//***************************************************文本框的定义开始********************************************
		textpayNo = new JTextField();
		textpayNo.setBounds(294, 17, 66, 21);
		panel.add(textpayNo);
		textpayNo.setColumns(10);

		textzongfen = new JTextField();
		textzongfen.setBounds(304, 413, 46, 21);
		panel.add(textzongfen);
		textzongfen.setColumns(10);

		textshouqiang = new JTextField();
		textshouqiang.setBounds(103, 413, 66, 21);
		panel.add(textshouqiang);
		textshouqiang.setColumns(10);

		textzonger = new JTextField();
		textzonger.setBounds(103, 448, 66, 21);
		panel.add(textzonger);
		textzonger.setColumns(10);

		textzhaoling = new JTextField();
		textzhaoling.setBounds(103, 488, 66, 21);
		panel.add(textzhaoling);
		textzhaoling.setColumns(10);

		//***************************************************按钮的定义开始********************************************
		btnNewButton = new JButton("找零：");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textshouqiang.getText().equals("")){
					JOptionPane.showMessageDialog(null, "请先输入实收金额！");
				}else{
					String  a =textshouqiang.getText();
					int b= Integer.parseInt(a);//String 转 int
					System.out.println("shouqina"+b);
					int c = b - zongjia;
					System.out.println("zongjia"+zongjia);
					System.out.println("找零"+c);
					if(c<0){
						JOptionPane.showMessageDialog(null, "亲！您给钱不够哦", "友情提示",
								JOptionPane.INFORMATION_MESSAGE);// 弹出提示
					}else{
						textzhaoling.setText(Integer.toString(c));
					}
				}
			}
		});
		btnNewButton.setBounds(10, 487, 83, 23);
		panel.add(btnNewButton);

		button = new JButton("保存账单");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textzhaoling.getText().equals("")){
					JOptionPane.showMessageDialog(null,"请先找零，才能保存账单!");
				}else{
					++liucheng;
					Pay pay =new  Pay();
					pay.setPayNo(payNo);
					pay.setSumMenoy(zongjia);
					pay.setSumNumber(c);
					pay.setTime(labeldata.getText().toString());
					pay.setMeunNO(meunNo);
					System.out.println("将要往账单表格插入的账单信息是(账单号，总金额，总份数，时间)："+pay.getPayNo()+
							"   "+pay.getSumMenoy()+"   "+pay.getSumNumber()+"   "+pay.getTime());

					payDao.insert(pay);//把账单信息插入账单表 

					payDao.update(meunNo);//根据订单号更新订餐表——更新付款情况

					Date d = new Date();
					SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String time = ss.format(d);//获取当前的时间
					String tableNo = mainFrame.LabeltableNo.getText();//获取桌号
					payDao.updateZhuozi(tableNo,time);//根据桌号更新桌子的状态信息

					//mainFrame.delePay();//结账后，在主界面中移除相应的订单信息移除
					mainFrame.removeRow();
					setVisible(false);
				}
			}
		});
		button.setBounds(218, 461, 132, 23);
		panel.add(button);

		button_1 = new JButton("退出");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(liucheng==0){
					JOptionPane.showMessageDialog(null, "请先保存账单!");
				}else{
					Exit();
				}

			}
		});
		button_1.setBounds(277, 514, 83, 23);
		panel.add(button_1);

		meunNo=mainFrame.meunNO;
		System.out.println("正在为"+meunNo+"号账单结账！");
		showTableData();
		textzongfen.setEnabled(false);
		textzonger.setEnabled(false);
		textzhaoling.setEnabled(false);
		textpayNo.setEnabled(false);
	}

	//*************************************************************构造函数外的方法*************************************
	protected void Exit() {
		System.err.println("******************************你离开承东的代码世界***********************************");
		this.setVisible(false);
	}

	//显示数据
	private void showTableData() {
		title = new Vector();
		data = new Vector();
		title.add("菜名编号");
		title.add("菜名");
		title.add("数量");
		title.add("单价");
		data =  meun.MenuTable(meunNo);//根据下单号meunNo来查找
		System.out.println(data);

		for(int i =0;i<data.size(); i++){
			Vector<String > a = new Vector<String>();
			a = ((Vector) data.get(i));
			zongjia+=Integer.parseInt(a.get(3));//计算总价
			c+=Integer.parseInt(a.get(2));//计算总的份数
		}
		payNo = payDao.payNO();//获取上一次账单号
		textzongfen.setText(Integer.toString(c));//整形转换成String
		textzonger.setText(Integer.toString(zongjia));
		textpayNo.setText(Integer.toString(++payNo));

		System.out.println("总价是**********"+zongjia);
		System.out.println("总份数是*********"+c);

		dtm.setDataVector(data, title);
		table.setModel(dtm);
	}
	
}
