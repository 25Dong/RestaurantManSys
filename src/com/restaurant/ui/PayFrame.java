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
 * �׳ж�
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
	int zongjia=0;//�ܼ�
	int c=0;//�ܷ���
	int payNo;//�˵���
	int liucheng=0;//��������
	private String meunNo;//���ʱ���µ���

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
		this.setTitle("����");

		// ���潫����һ�Զ�����б�����������ӵ����ڵ�������
		final myJPanel panel = new myJPanel(this.getClass().getResource(
				"/img/paybg.png"));// ����һ��������
		panel.setSize(365, 546);
		panel.setLocation(0, 0);
		panel.setLayout( null);//
		getContentPane().add(panel, BorderLayout.CENTER);
		contentPane.add(panel);

		//��ʾ�������ڵı�ǩ��2015-3-30
		labeldata = new JLabel("");
		labeldata.setBounds(15, 20, 105, 15);
		labeldata.setFont(new Font("", Font.BOLD, 12));
		labeldata.setHorizontalAlignment(SwingConstants.CENTER);
		labeldata.setText(Day.getDateOfShow());
		panel.add(labeldata);

		//��ʾ�����ʱ��
		labetime = new JLabel("");
		labetime.setBounds(97, 21, 84, 15);
		labetime.setFont(new Font("", Font.BOLD, 12));
		labetime.setHorizontalAlignment(SwingConstants.CENTER);
		labetime.setText(Day.getTime());
		panel.add(labetime);

		//�������
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 80, 335, 323);
		panel.add(scrollPane);

		//���
		table = new JTable(dtm);
		table.setBackground(new Color(250, 250, 210));
		table.setBounds(0, 35, 335, 298);
		table.setForeground(new Color(0, 0, 0));
		scrollPane.setViewportView(table);

		//***************************************************�ı���Ķ��忪ʼ********************************************
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

		//***************************************************��ť�Ķ��忪ʼ********************************************
		btnNewButton = new JButton("���㣺");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textshouqiang.getText().equals("")){
					JOptionPane.showMessageDialog(null, "��������ʵ�ս�");
				}else{
					String  a =textshouqiang.getText();
					int b= Integer.parseInt(a);//String ת int
					System.out.println("shouqina"+b);
					int c = b - zongjia;
					System.out.println("zongjia"+zongjia);
					System.out.println("����"+c);
					if(c<0){
						JOptionPane.showMessageDialog(null, "�ף�����Ǯ����Ŷ", "������ʾ",
								JOptionPane.INFORMATION_MESSAGE);// ������ʾ
					}else{
						textzhaoling.setText(Integer.toString(c));
					}
				}
			}
		});
		btnNewButton.setBounds(10, 487, 83, 23);
		panel.add(btnNewButton);

		button = new JButton("�����˵�");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textzhaoling.getText().equals("")){
					JOptionPane.showMessageDialog(null,"�������㣬���ܱ����˵�!");
				}else{
					++liucheng;
					Pay pay =new  Pay();
					pay.setPayNo(payNo);
					pay.setSumMenoy(zongjia);
					pay.setSumNumber(c);
					pay.setTime(labeldata.getText().toString());
					pay.setMeunNO(meunNo);
					System.out.println("��Ҫ���˵���������˵���Ϣ��(�˵��ţ��ܽ��ܷ�����ʱ��)��"+pay.getPayNo()+
							"   "+pay.getSumMenoy()+"   "+pay.getSumNumber()+"   "+pay.getTime());

					payDao.insert(pay);//���˵���Ϣ�����˵��� 

					payDao.update(meunNo);//���ݶ����Ÿ��¶��ͱ������¸������

					Date d = new Date();
					SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					String time = ss.format(d);//��ȡ��ǰ��ʱ��
					String tableNo = mainFrame.LabeltableNo.getText();//��ȡ����
					payDao.updateZhuozi(tableNo,time);//�������Ÿ������ӵ�״̬��Ϣ

					//mainFrame.delePay();//���˺������������Ƴ���Ӧ�Ķ�����Ϣ�Ƴ�
					mainFrame.removeRow();
					setVisible(false);
				}
			}
		});
		button.setBounds(218, 461, 132, 23);
		panel.add(button);

		button_1 = new JButton("�˳�");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(liucheng==0){
					JOptionPane.showMessageDialog(null, "���ȱ����˵�!");
				}else{
					Exit();
				}

			}
		});
		button_1.setBounds(277, 514, 83, 23);
		panel.add(button_1);

		meunNo=mainFrame.meunNO;
		System.out.println("����Ϊ"+meunNo+"���˵����ˣ�");
		showTableData();
		textzongfen.setEnabled(false);
		textzonger.setEnabled(false);
		textzhaoling.setEnabled(false);
		textpayNo.setEnabled(false);
	}

	//*************************************************************���캯����ķ���*************************************
	protected void Exit() {
		System.err.println("******************************���뿪�ж��Ĵ�������***********************************");
		this.setVisible(false);
	}

	//��ʾ����
	private void showTableData() {
		title = new Vector();
		data = new Vector();
		title.add("�������");
		title.add("����");
		title.add("����");
		title.add("����");
		data =  meun.MenuTable(meunNo);//�����µ���meunNo������
		System.out.println(data);

		for(int i =0;i<data.size(); i++){
			Vector<String > a = new Vector<String>();
			a = ((Vector) data.get(i));
			zongjia+=Integer.parseInt(a.get(3));//�����ܼ�
			c+=Integer.parseInt(a.get(2));//�����ܵķ���
		}
		payNo = payDao.payNO();//��ȡ��һ���˵���
		textzongfen.setText(Integer.toString(c));//����ת����String
		textzonger.setText(Integer.toString(zongjia));
		textpayNo.setText(Integer.toString(++payNo));

		System.out.println("�ܼ���**********"+zongjia);
		System.out.println("�ܷ�����*********"+c);

		dtm.setDataVector(data, title);
		table.setModel(dtm);
	}
	
}
