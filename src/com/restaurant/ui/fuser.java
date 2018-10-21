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
		this.setSize(804, 628);//���ô�С
		//������Ļ����
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((screenWidth-804)/2,(screenHeight-628)/2);//����λ��
		this.setResizable(false);//��ֹ�Ŵ󴰿�

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ���潫����һ�Զ�����б�����������ӵ����ڵ�������
		final myJPanel panel = new myJPanel(this.getClass().getResource(
				"/img/denglubg.png"));// ����һ��������
		panel.setSize(800, 600);
		panel.setLocation(0, 0);
		panel.setLayout( null);
		getContentPane().add(panel, BorderLayout.CENTER);
		contentPane.add(panel);

		//�����Ķ���
		passwordField = new JPasswordField();
		passwordField.setBounds(174, 277, 158, 32);
		panel.add(passwordField);

		//�û����ı���Ķ���
		textField = new JTextField();
		textField.setBounds(174, 194, 158, 32);
		panel.add(textField);
		textField.setColumns(10);





		JButton btnzhuce = new JButton();

		//-------------------              ����ť���ͼƬЧ����ʼ                                    --------------------------//
		URL landUrl = this.getClass().getResource("/img/zhuce1.png");// ���Ĭ�������ע�ᰴť��ʾͼƬ��URL
		URL landOverUrl = this.getClass().getResource(
				"/img/zhuce2.png");// ��õ���꾭��ע�ᰴťʱ��ʾͼƬ��URL
		URL landPressedUrl = this.getClass().getResource(
				"/img/zhuce3.png");// ��õ���¼ע�ᱻ����ʱ��ʾͼƬ��URL

		btnzhuce.setContentAreaFilled(false);// ���ò����ư�ť����������
		btnzhuce.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		btnzhuce.setIcon(new ImageIcon(landUrl));// ����Ĭ������µ�¼��ť��ʾ��ͼƬ
		btnzhuce.setRolloverIcon(new ImageIcon(landOverUrl));// ���õ���꾭����¼��ťʱ��ʾ��ͼƬ
		btnzhuce.setPressedIcon(new ImageIcon(landPressedUrl));// ���õ���¼��ť������ʱ��ʾ��ͼƬ
		//----------------------    ��ע�ᰴť���ͼƬЧ������--------------------------------------------------//

		//ע�ᰴť��Ӽ����¼�
		btnzhuce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterFrame frame = new RegisterFrame();
				frame.setVisible(true);// ����������ɼ�
			}
		});
		btnzhuce.setBounds(10, 369,158, 40);

		GridBagConstraints gbc_btnzhuce = new GridBagConstraints();
		gbc_btnzhuce.insets = new Insets(0, 0, 5, 5);
		gbc_btnzhuce.gridx = 0;
		gbc_btnzhuce.gridy = 3;
		panel.add(btnzhuce, gbc_btnzhuce);


		//����һ����½��ť
		JButton btndenglu = new JButton();
		//-------------------              ����ť���ͼƬЧ����ʼ                                    --------------------------//
		URL landUr2 = this.getClass().getResource("/img/denglu1.png");// ���Ĭ������µ�¼��ť��ʾͼƬ��URL
		URL landOverUr2 = this.getClass().getResource(
				"/img/denglu2.png");// ��õ���꾭����¼��ťʱ��ʾͼƬ��URL
		URL landPressedUr2 = this.getClass().getResource(
				"/img/denglu3.png");// ��õ���¼��ť������ʱ��ʾͼƬ��URL

		btndenglu.setContentAreaFilled(false);// ���ò����ư�ť����������
		btndenglu.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		btndenglu.setIcon(new ImageIcon(landUr2));// ����Ĭ������µ�¼��ť��ʾ��ͼƬ
		btndenglu.setRolloverIcon(new ImageIcon(landOverUr2));// ���õ���꾭����¼��ťʱ��ʾ��ͼƬ
		btndenglu.setPressedIcon(new ImageIcon(landPressedUr2));// ���õ���¼��ť������ʱ��ʾ��ͼƬ
		//----------------------    ��ע�ᰴť���ͼƬЧ������--------------------------------------------------//
		//��¼��ť�ļ����¼�
		btndenglu.addActionListener(new ActionListener() {//��½��ť�ļ����¼�
			public void actionPerformed(ActionEvent e) {

				String uname=textField.getText().toString();
				String pw=passwordField.getText().toString();
				Vector data1=new Vector();
				
			
				if(uname.equals("")||passwordField.getText().length()==0){
					JOptionPane.showMessageDialog(null, "�û��������벻��Ϊ�գ�", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);// ������ʾ
					
				}
				else if(!userDao.isLogin(uname, pw)){
					JOptionPane.showMessageDialog(null, "�����������", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);// ������ʾ
					textField.setText("");
					passwordField.setText("");
				}else if(userDao.isLogin(uname, pw)){
					System.out.println("�û�������ȷ����ת����ҳ��");
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



	protected void land(Vector data1) {//��¼�ɹ�����õķ�����������ʾ�����沢�����ص�ǰ�Ĵ���
		// TODO �Զ����ɵķ������

		mainFrame mainframe=new mainFrame(data1);// �������������
		mainframe.setVisible(true);// ����������ɼ�
		this.setVisible(false);// ���õ�¼���ڲ��ɼ�
	}



}
