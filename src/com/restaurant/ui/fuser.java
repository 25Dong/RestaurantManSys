package com.restaurant.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;







import com.restaurant.dao.userDao;
import com.restaurant.db.JDBC;
import com.restaurant.myswing.myJPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.net.URL;
import java.util.Vector;
import java.awt.GridBagConstraints;

public class fuser extends JFrame {

	private JPanel contentPane;
	static JPasswordField passwordField;
    static JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					fuser frame = new fuser();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	//-----------------------------------------------------------------//
	/**
	 * Create the frame.
	 */
	public fuser() {
		setForeground(Color.WHITE);
		setTitle("\u6B22\u8FCE\u60A8\u8FDB\u5165\u9910\u5385\u7BA1\u7406\u7CFB\u7EDF\u7684\u767B\u5F55\u7A97\u53E3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setSize(804, 628);//设置大小
		//设置屏幕居中
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((screenWidth-804)/2,(screenHeight-628)/2);//设置位置
		this.setResizable(false);//禁止放大窗口

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 下面将创建一自定义带有背景面板对象并添加到窗口的容器中
		final myJPanel panel = new myJPanel(this.getClass().getResource(
				"/img/denglubg.png"));// 创建一个面板对象
		panel.setSize(800, 600);
		panel.setLocation(0, 0);
		panel.setLayout( null);
		getContentPane().add(panel, BorderLayout.CENTER);
		contentPane.add(panel);

		//密码框的定义
		passwordField = new JPasswordField();
		passwordField.setBounds(174, 277, 158, 32);
		panel.add(passwordField);

		//用户名文本框的定义
		textField = new JTextField();
		textField.setBounds(174, 194, 158, 32);
		panel.add(textField);
		textField.setColumns(10);





		JButton btnzhuce = new JButton();

		//-------------------              给按钮添加图片效果开始                                    --------------------------//
		URL landUrl = this.getClass().getResource("/img/zhuce1.png");// 获得默认情况下注册按钮显示图片的URL
		URL landOverUrl = this.getClass().getResource(
				"/img/zhuce2.png");// 获得当鼠标经过注册按钮时显示图片的URL
		URL landPressedUrl = this.getClass().getResource(
				"/img/zhuce3.png");// 获得当登录注册被按下时显示图片的URL

		btnzhuce.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		btnzhuce.setBorderPainted(false);// 设置不绘制按钮的边框
		btnzhuce.setIcon(new ImageIcon(landUrl));// 设置默认情况下登录按钮显示的图片
		btnzhuce.setRolloverIcon(new ImageIcon(landOverUrl));// 设置当鼠标经过登录按钮时显示的图片
		btnzhuce.setPressedIcon(new ImageIcon(landPressedUrl));// 设置当登录按钮被按下时显示的图片
		//----------------------    给注册按钮添加图片效果结束--------------------------------------------------//

		//注册按钮添加监听事件
		btnzhuce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterFrame frame = new RegisterFrame();
				frame.setVisible(true);// 设置主窗体可见
			}
		});
		btnzhuce.setBounds(10, 369,158, 40);

		GridBagConstraints gbc_btnzhuce = new GridBagConstraints();
		gbc_btnzhuce.insets = new Insets(0, 0, 5, 5);
		gbc_btnzhuce.gridx = 0;
		gbc_btnzhuce.gridy = 3;
		panel.add(btnzhuce, gbc_btnzhuce);


		//定义一个登陆按钮
		JButton btndenglu = new JButton();
		//-------------------              给按钮添加图片效果开始                                    --------------------------//
		URL landUr2 = this.getClass().getResource("/img/denglu1.png");// 获得默认情况下登录按钮显示图片的URL
		URL landOverUr2 = this.getClass().getResource(
				"/img/denglu2.png");// 获得当鼠标经过登录按钮时显示图片的URL
		URL landPressedUr2 = this.getClass().getResource(
				"/img/denglu3.png");// 获得当登录按钮被按下时显示图片的URL

		btndenglu.setContentAreaFilled(false);// 设置不绘制按钮的内容区域
		btndenglu.setBorderPainted(false);// 设置不绘制按钮的边框
		btndenglu.setIcon(new ImageIcon(landUr2));// 设置默认情况下登录按钮显示的图片
		btndenglu.setRolloverIcon(new ImageIcon(landOverUr2));// 设置当鼠标经过登录按钮时显示的图片
		btndenglu.setPressedIcon(new ImageIcon(landPressedUr2));// 设置当登录按钮被按下时显示的图片
		//----------------------    给注册按钮添加图片效果结束--------------------------------------------------//
		//登录按钮的监听事件
		btndenglu.addActionListener(new ActionListener() {//登陆按钮的监听事件
			public void actionPerformed(ActionEvent e) {

				String uname=textField.getText().toString();
				String pw=passwordField.getText().toString();
				Vector data1=new Vector();
				
			
				if(uname.equals("")||passwordField.getText().length()==0){
					JOptionPane.showMessageDialog(null, "用户名和密码不能为空！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);// 弹出提示
					
				}
				else if(!userDao.isLogin(uname, pw)){
					JOptionPane.showMessageDialog(null, "您的密码错误！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);// 弹出提示
					textField.setText("");
					passwordField.setText("");
				}else if(userDao.isLogin(uname, pw)){
					System.out.println("用户密码正确！跳转到主页面");
					data1 = userDao.prepareQueryDate(uname,pw);
					land(data1);
					
				}
			}

		});

		btndenglu.setBounds(174, 369, 158, 40);
		GridBagConstraints gbc_btndenglu = new GridBagConstraints();
		gbc_btndenglu.gridx = 2;
		gbc_btndenglu.gridy = 4;
		panel.add(btndenglu, gbc_btndenglu);
	}



	protected void land(Vector data1) {//登录成功后调用的方法，用来显示主界面并且隐藏当前的窗口
		// TODO 自动生成的方法存根

		mainFrame mainframe=new mainFrame(data1);// 创建主窗体对象
		mainframe.setVisible(true);// 设置主窗体可见
		this.setVisible(false);// 设置登录窗口不可见
	}



}
