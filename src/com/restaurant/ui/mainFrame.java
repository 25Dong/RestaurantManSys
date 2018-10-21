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
 * �׳ж�
 */

public class mainFrame extends JFrame {

	public static String job=null;//��ȡ��¼��Ա��ְ��
	String liucheng = "";//����ͬһ�µ��Ų��ܶ�����
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
		System.out.println("�ӵ�¼���洫�������ݣ�"+user);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 691);//���ô�С
		//������Ļ����
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation((screenWidth-900)/2,(screenHeight-691)/2);//����λ��
		this.setResizable(false);//��ֹ�Ŵ󴰿�
		this.setTitle("��������ϵͳ������");//���ô��ڱ���

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//--------------------------�������������ݣ���ǰ���û���ʱ�䣩---------------------------
		// ���潫����һ�Զ�����б�����������ӵ����ڵ�������
		final myJPanel panel = new myJPanel(this.getClass().getResource(
				"/img/mainfrabg.png"));
		panel.setBounds(0, 0, 900, 100);
		panel.setLayout( null);// �������Ĳ��ֹ�����Ϊ�����鲼��
		getContentPane().add(panel, BorderLayout.CENTER);
		contentPane.add(panel);

		final JLabel eClueOnLabel = new JLabel();
		eClueOnLabel.setSize(98, 40);
		eClueOnLabel.setLocation(10, 10);
		panel.add(eClueOnLabel);
		eClueOnLabel.setFont(new Font("", Font.BOLD, 12));
		eClueOnLabel.setText("  ��ǰ����Ա��");

		final JLabel fClueOnLabel = new JLabel();
		fClueOnLabel.setSize(75, 35);
		fClueOnLabel.setLocation(101, 10);
		fClueOnLabel.setFont(new Font("", Font.BOLD, 12));
		panel.add(fClueOnLabel);
		fClueOnLabel.setHorizontalAlignment(SwingConstants.CENTER);

		if (user == null)
			fClueOnLabel.setText("ϵͳĬ���û�");
		else
			fClueOnLabel.setText(user.get(0).toString());


		JLabel lblNewLabel_1 = new JLabel("\u804C\u52A1\uFF1A");
		lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(20, 47, 54, 28);
		panel.add(lblNewLabel_1);

		JLabel Joblabel = new JLabel("");
		Joblabel.setFont(new Font("", Font.BOLD, 12));
		Joblabel.setBounds(59, 47, 83, 28);
		panel.add(Joblabel);
		Joblabel.setHorizontalAlignment(SwingConstants.CENTER);

		if (user.get(2).toString()== null)
			Joblabel.setText("����Ա");
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
		aClueOnLabel.setText("  �����ǣ�");

		//��ʾ�������ڵı�ǩ��2015-3-30
		final JLabel dateJlabel = new JLabel();
		dateJlabel.setSize(122, 18);
		dateJlabel.setLocation(637, 32);
		dateJlabel.setFont(new Font("", Font.BOLD, 12));
		panel.add(dateJlabel);
		dateJlabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateJlabel.setText(Day.getDateOfShow());

		//��ʾ�ܼ��ı�ǩ
		final JLabel weekJlabel = new JLabel();
		weekJlabel.setSize(67, 38);
		weekJlabel.setLocation(749, 22);
		weekJlabel.setFont(new Font("", Font.BOLD, 12));
		panel.add(weekJlabel);
		weekJlabel.setHorizontalAlignment(SwingConstants.CENTER);
		weekJlabel.setText(Day.getDayOfWeek());

		timeLabel = new JLabel();// ����������ʾʱ��ı�ǩ����
		timeLabel.setSize(83, 40);
		timeLabel.setLocation(807, 20);
		timeLabel.setFont(new Font("����", Font.BOLD, 14));// ���ñ�ǩ�е�����Ϊ���塢���塢14��
		timeLabel.setForeground(new Color(255, 0, 0));// ���ñ�ǩ�е�����Ϊ��ɫ
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);// ���ñ�ǩ�е����־�����ʾ
		panel.add(timeLabel);// ����ǩ��ӵ��ϼ�������

		new Time().start();// �����߳�


		//--------------------�м������-----------------------------------------------------------------\


		// ���潫����һ�Զ�����б�����������ӵ����ڵ�������
		final myJPanel pane2 = new myJPanel(this.getClass().getResource(
				"/img/mainfrabg2.png"));
		pane2.setBounds(0, 99, 900, 359);
		pane2.setLayout( null);// �������Ĳ��ֹ�����Ϊ�����鲼��
		getContentPane().add(pane2, BorderLayout.CENTER);
		contentPane.add(pane2);

		//�������
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
				System.out.println("������������Ĳ˵���");
			}
		});
		table.setBounds(26, 25, 844, 310);

		JTableHeader jaTableHeader = table.getTableHeader();
		jaTableHeader .setBounds(26, 1, 844,22);
		pane2.add(jaTableHeader );
		table.setBackground(new Color(240, 255, 240));
		scrollPane.setViewportView(table);//���������ų����˹�����

		//***************************************************�ײ���������*******************************************
		//�Զ���һ�����
		final myJPanel pane3 = new myJPanel(this.getClass().getResource(
				"/img/mainframebg3.png"));
		pane3.setBounds(0, 451, 900, 221);
		pane3.setLayout( null);// �������Ĳ��ֹ�����Ϊ�����鲼��
		getContentPane().add(pane3, BorderLayout.CENTER);
		contentPane.add(pane3);
		
		//---------��ǩ----------
	    LabeltableNo = new JLabel("");//������ʾ���˵�����
		LabeltableNo.setBounds(372, 149, 50, 19);
		pane3.add(LabeltableNo);

		//---------�ı���----------------
		txtTableno = new JTextField();//����
		txtTableno.setBounds(90, 95, 74, 34);
		pane3.add(txtTableno);
		txtTableno.setColumns(10);

		txtTablename = new JTextField();//����
		txtTablename.setColumns(10);
		txtTablename.setBounds(274, 95, 74, 34);
		pane3.add(txtTablename);

		txtLocation = new JTextField();//λ��
		txtLocation.setColumns(10);
		txtLocation.setBounds(442, 95, 99, 34);
		pane3.add(txtLocation);

		txtTime = new JTextField();//ʱ��
		txtTime.setColumns(10);
		txtTime.setBounds(588, 95, 112, 34);
		pane3.add(txtTime);

		menuNo = new JTextField();//�µ���
		menuNo.setBounds(759, 98, 84, 31);
		pane3.add(menuNo);
		menuNo.setColumns(10);

		textmeunNo = new JTextField();//���˰�ť�Ϸ����µ���
		textmeunNo.setBounds(421, 149, 26, 19);
		pane3.add(textmeunNo);
		textmeunNo.setColumns(10);
		//-------------�ı������--------------------------------

		
		//---------------------��ť------------------------
		JButton btncaidan = new JButton("���");
		btncaidan.setBounds(111, 148, 93, 43);
		btncaidan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tableNo = mainFrame.txtTableno.getText().toString();//2016\4\17�����ж�
				if(tableNo.equals("")){
					JOptionPane.showMessageDialog(null, "��û��ѡ�����Ŷ����");
				}else{
					System.err.println("******************************���Ѿ�����ν��Ĵ�������***********************************");
				MenuFrame.main(null);
				}
			}
		});
		pane3.add(btncaidan);

		JButton btntable = new JButton("ѡ�����");
		btntable.setBounds(10, 148, 93, 43);
		btntable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.err.println("******************************���Ѿ��������Ĵ�������***********************************");
				TableFrame.main(null);
			}
		});
		pane3.add(btntable);


		JButton btndiancai = new JButton("�µ�");
		btndiancai.setBounds(214, 148, 93, 43);
		btndiancai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String tableNo = txtTableno.getText();
				String foodNo = menuNo.getText();
				if(tableNo.equals("")){
					JOptionPane.showMessageDialog(null, "�µ�ʧ�ܣ��㻹û��ѡ�����������ѡ�������", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);// ������ʾ
				}
				else if(foodNo.equals("")){
					JOptionPane.showMessageDialog(null, "�µ�ʧ�ܣ��㻹û�е�ˣ�����", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);// ������ʾ

				}
				else if(liucheng.equals(menuNo.getText())){
					JOptionPane.showMessageDialog(null, "�µ�ʧ��!�����ظ��µ�Ŷ");
				}
				else{
					showTableData();
					liucheng=menuNo.getText().toString();
				}

			}
		});
		pane3.add( btndiancai);


		JButton btnMoney = new JButton("����");
		btnMoney.setBounds(361, 168, 105, 23);
		btnMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				meunNO = textmeunNo.getText();
				if(meunNO .equals("")){
					JOptionPane.showMessageDialog(null, "��վʧ�ܣ��㻹ûѡ����Ҫ���˵��˵��ţ�", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);// ������ʾ
				}
				else{
					System.err.println("******************************���Ѿ�����ж��Ĵ�������***********************************");
					PayFrame.main(null);
				}
			}
		});
		pane3.add(btnMoney);


		JButton btnxiaoshou = new JButton("����ͳ��");
		btnxiaoshou.setBounds(666, 158, 93, 23);
		btnxiaoshou.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user1 = (String) user.get(2);
				System.out.println(user1);
				if(!user1.equals("����Ա")){
					JOptionPane.showMessageDialog(null, "��û�в鿴��Ȩ��!");
				}else{
				System.err.println("******************************���Ѿ������ĺƵĴ�������***********************************");
				salesFrame.main(null);
				}
			}
		});
		pane3.add( btnxiaoshou);

		JButton btnwaiter = new JButton("����Ա��Ϣ����");
		btnwaiter.setBounds(523, 158, 133, 23);
		btnwaiter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user1 = (String) user.get(2);
				if(!user1.equals("����Ա")){
					JOptionPane.showMessageDialog(null, "��û�в鿴��Ȩ��!");
				}else{
				System.err.println("******************************���Ѿ��������ɵĴ�������***********************************");
				waiterFrame.main(null);
				}
			}
		});
		pane3.add( btnwaiter);


		JButton btnExit = new JButton("�˳�");
		btnExit.setBounds(781, 180, 93, 23);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(null,"��Ҫ�˳���ϵͳ��ȷ����","�˳�ϵͳ",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION){
					System.out.println((new Date()).toString()+"�˳�ϵͳ");
					System.exit(0);
				}
			}
		});
		pane3.add(btnExit);

		JButton btnNewButton_2 = new JButton("���");
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
		
		JButton btnNewButton = new JButton("ȡ������");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String menuNo = textmeunNo.getText().toString();
				String tableNo = LabeltableNo.getText().toString().substring(0, 3);
				if(menuNo.equals("")){
					JOptionPane.showMessageDialog(null, "���ڱ����ѡ����Ҫȡ���Ķ���!");
				}else{
					meun.deleDC(menuNo, tableNo);
					deleteRow();//�ӱ��ɾ��һ��
				}
			}
		});
		btnNewButton.setBounds(678, 10, 93, 23);
		pane3.add(btnNewButton);
		
		showfirstData();
		lockText();//��ס�ı���
		
	}
	//********************************************** ���췽������ *********************************************************
	//**********************************************���췽�������********************************************************
	
	//�ӱ�����Ƴ����ʱ����һ�С������ȡ������ʱ����
	protected static void deleteRow() {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.removeRow(table.getSelectedRow());
	}

	//��Ԥ��̨�������У�����Ԥ������Ϣ������ʾ����������
	public static  void showDataOfTable() {
		// TODO �Զ����ɵķ������
		System.out.println("�ڽ�������ʾ�Ĵ����ӽ��淵�ص���Ϣ�ǣ�"+dataOftable);
		txtTableno.setText(dataOftable.get(0).toString());
		txtTablename.setText(dataOftable.get(1).toString());
		txtLocation.setText(dataOftable.get(2).toString());
		txtTime.setText(dataOftable.get(3).toString());
	}

	//�����������ǻ���øú���������ʾ���ݿ��л�û�и����ȫ����Ϣ
	public static  void showfirstData(){
		title.add("�µ���");
		title.add("����");
		title.add("�˺�");
		title.add("����");
		title.add("����");
		title.add("����");
		title.add("����ʱ��");
		title.add("�Ƿ����");
		Vector data = new Vector ();
		String pay = "��";
		
		data=meun.firstData(pay);//���ݸ������Ϊ���񡱣���ѯdingcan���е���Ϣ
		dtm.setDataVector(data, title);
		table.setModel(dtm);
	}
	//����µ���ťʱ���á������µ�����Ϣ���µ�������ı����
	private void showTableData() {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		for(int i = 0;i <datamenu.size();i++){//datamenu�����������������Ӻţ���ʱ����ҵĵ���Ϣ�����Ϣ
			Vector data = new Vector();
			data = (Vector) datamenu.get(i);
			model.addRow(data);
		}
	}
	
	//��ɽ��˺󣬰ѽ�����Ӧ�Ķ�����Ϣ��������ı���Ƴ���
	public static void delePay(){
		int a= table.getRowCount();
		for(int i =0;i<a;i++){
			int j= (int) dtm.getValueAt(i, 0);
			System.out.println(i+"   �ǣ�"+j);
			if(j==28){
				
				dtm.removeRow(i);
				a = a-1;
				--i;
			}
		}
	}
	//�ڱ�ǩ�϶�̬��ʾʱ��
	class Time extends Thread {// �����ڲ���
		public void run() {// �ع�����ķ���
			while (true) {
				Date date = new Date();// �������ڶ���
				timeLabel.setText(date.toString().substring(11, 19));// ��ȡ��ǰ��ʱ���룬����ʾ��ʱ���ǩ��
				try {
					Thread.sleep(1000);// ���߳�����1��
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//����ѡ�еı��е��С�
	private void processSelectionRow(){
		int row = table.getSelectedRow();//��ȡ���������
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		textmeunNo.setText(model.getValueAt(row, 0).toString());//��ȡ�µ���
		LabeltableNo.setText(model.getValueAt(row, 1).toString()+"����");//��ȡ����

	}

	//�������ź�Ԥ��ʱ�������ҵ�ͱ����Ϣ����������Ҫ��Ҫ��ȡ��ͱ��е��µ���
	public static void getDianCandata(){
		datamenu = new Vector();
		String tableNo = txtTableno.getText();//�ӽ���������ı����ȡ����
		String tabletime = txtTime.getText().toString();//�ӽ���Ļ�ȡԤ��������ʱ��
		System.out.println("���ڵ�˵�������"+tableNo);
		datamenu = meun.MenuTableNo(tableNo,tabletime);//���ص�datameun��һ����ά��Vector
		System.out.println("�������ź�ʱ������ݿ�ı���ж�ȡ�Ķ�ά��Ϣ��"+datamenu);

		Vector datamenu1 = new Vector();
		datamenu1 = ((Vector) datamenu.get(0));
		System.out.println("�������ź�ʱ������ݿ�ı���ж�ȡ��һλ��Ϣ��"+datamenu1);
		String a=datamenu1.get(0)+"";
		menuNo.setText(a);
	}
	
	//�Ƴ�����һ�л��߶���
	public static void removeRow(){
		String mo = textmeunNo.getText().trim();//����ʱ���˵���
		int r = Integer.parseInt(mo);//�˵���תint
		int row = table.getRowCount();//����
		for(int i=0;i<row;i++){
			int a=(int) dtm.getValueAt(i, 0);//����һ��Ԫ��
			if(a==r){
				dtm.removeRow(i);
				--row;
				--i;
			}
		}
	}

	//���ı���
	public void lockText(){
		txtTableno.setEnabled(false);
		txtTablename.setEnabled(false);
		txtLocation.setEnabled(false);
		txtTime.setEnabled(false);
		menuNo.setEnabled(false);
		textmeunNo.setEnabled(false);
	}
	
}
