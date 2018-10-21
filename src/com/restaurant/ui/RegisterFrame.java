package com.restaurant.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import com.restaurant.dao.userDao;
import com.restaurant.db.JDBC;
import com.restaurant.entity.User;
import com.restaurant.myswing.myJPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.net.URL;

/*
 *����ȫ 
 */

public class RegisterFrame extends JFrame {

	private JPanel contentPane;
	private  JTextField textField;
	private  JPasswordField passwordField;
	private JPasswordField passwordField1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame frame = new RegisterFrame();
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
	public RegisterFrame() {
		setBounds(100, 100, 380, 485);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("ע�ᴰ��");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		// ���潫����һ�Զ�����б�����������ӵ����ڵ�������
				final myJPanel panel = new myJPanel(this.getClass().getResource(
						"/img/registerbg.png"));// ����һ��������
				panel.setBackground(Color.WHITE);
				panel.setSize(367,451 );
				panel.setLocation(0, 0);
				panel.setLayout( null);//
				getContentPane().add(panel, BorderLayout.CENTER);
				contentPane.add(panel);
		
		
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237\u540D");
		lblNewLabel.setBounds(48, 116, 54, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u8BBE\u7F6E\u5BC6\u7801");
		lblNewLabel_1.setBounds(48, 153, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u804C\u52A1\uFF1A");
		lblNewLabel_2.setBounds(72, 268, 54, 15);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBackground(null);
		textField.setBounds(175, 107, 130, 30);
		panel.add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnNewRadioWaiter = new JRadioButton("����Ա");
		rdbtnNewRadioWaiter.setBackground(new Color(184, 134, 11));
		rdbtnNewRadioWaiter.setBounds(175, 274, 67, 23);
		panel.add(rdbtnNewRadioWaiter);
		
		JRadioButton rdbtnNewRadioAdmini = new JRadioButton("����Ա");
		rdbtnNewRadioAdmini.setBackground(new Color(184, 134, 11));
		rdbtnNewRadioAdmini.setBounds(244, 274, 78, 23);
		panel.add(rdbtnNewRadioAdmini);
		
		ButtonGroup radioGroupjob = new ButtonGroup();
		radioGroupjob.add( rdbtnNewRadioWaiter);
		radioGroupjob.add(rdbtnNewRadioAdmini);
		radioGroupjob.setSelected(rdbtnNewRadioAdmini.getModel(),true);
		
		JButton btnzhuce = new JButton("");
		btnzhuce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user=textField.getText();
				String password=passwordField.getText();
				String password1 = passwordField1.getText();
				if(radioGroupjob.getSelection()==rdbtnNewRadioWaiter.getModel()){//��waiter���������Ա�
					User.setJob("����Ա");
				}else{
					User.setJob("����Ա");
				}
				String job = User.getJob();

				if(textField.getText().equals("")){
					JOptionPane.showMessageDialog(null, "�û�������Ϊ��", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);// ������ʾ
				}
				else if(passwordField.getText().equals("")){

					JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ���", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);// ������ʾ
				}
				else if(!password.equals(password1)){
					
					JOptionPane.showMessageDialog(null, "������������벻һ����", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);// ������ʾ
					
				}
				else {
					String sql=null;
					sql="insert into Tuser values('" + user + "','" + password + "','"+ job+"')";
					System.out.println(sql);
					userDao.updateUser(sql);
					JOptionPane.showMessageDialog(null, "��ϲ�㣡ע��ɹ�!");
					fuser.passwordField.setText(passwordField1.getText());
					fuser.textField.setText(textField.getText());
					setVisible(false);
				}
				
		
			}
		});
		btnzhuce.setBounds(74, 380, 67, 61);
		panel.add(btnzhuce);
		URL landUr1 = this.getClass().getResource("/img/registerbtn1.png");// ���Ĭ������°�ť��ʾͼƬ��URL
		btnzhuce .setContentAreaFilled(false);// ���ò����ư�ť����������
		btnzhuce .setBorderPainted(false);// ���ò����ư�ť�ı߿�
		btnzhuce .setIcon(new ImageIcon(landUr1));// ����Ĭ������°�ť��ʾ��ͼƬ
		btnzhuce.setHorizontalAlignment(SwingConstants.CENTER);

		
		JButton btnchexiao = new JButton("");
		btnchexiao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				passwordField.setText("");
				passwordField1.setText("");
				
			}
		});
		btnchexiao.setBounds(151, 380, 67, 61);
		panel.add(btnchexiao);
		URL landUr2 = this.getClass().getResource("/img/registerbtn2.png");// ���Ĭ������°�ť��ʾͼƬ��URL
		btnchexiao .setContentAreaFilled(false);// ���ò����ư�ť����������
		btnchexiao .setBorderPainted(false);// ���ò����ư�ť�ı߿�
		btnchexiao .setIcon(new ImageIcon(landUr2));// ����Ĭ������°�ť��ʾ��ͼƬ
		btnchexiao.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnexit = new JButton("");
		btnexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exit();
			}
		});
		btnexit.setBounds(222, 380, 67, 61);
		panel.add(btnexit);
		URL landUr3 = this.getClass().getResource("/img/registerbtn3.png");// ���Ĭ������°�ť��ʾͼƬ��URL
		btnexit  .setContentAreaFilled(false);// ���ò����ư�ť����������
		btnexit  .setBorderPainted(false);// ���ò����ư�ť�ı߿�
		btnexit  .setIcon(new ImageIcon(landUr3));// ����Ĭ������°�ť��ʾ��ͼƬ
		
		
		
		JLabel lblNewLabel_3 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		lblNewLabel_3.setBounds(48, 203, 54, 15);
		contentPane.add(lblNewLabel_3);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(getBackground());
		passwordField.setBounds(175, 213, 130, 30);
		panel.add(passwordField);
		
		passwordField1 = new JPasswordField();
		passwordField1.setBounds(175, 158, 130, 30);
		panel.add(passwordField1);
	}
	protected void Exit() {
		this.setVisible(false);
		// TODO �Զ����ɵķ������

	}
}
