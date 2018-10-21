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
 * Ҧ����
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
		setBounds(10, 10, 751, 664);//����λ�úʹ�С
		setTitle("����Ա");
		contentPane = new JPanel();

		//�������
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ���潫����һ�Զ�����б�����������ӵ����ڵ�������
		final myJPanel panel = new myJPanel(this.getClass().getResource(
				"/img/waiterbg.png"));// ����һ��������
		panel.setSize(735, 288);//���ô�С
		panel.setLocation(0, 338);//����λ��
		panel.setLayout( null);// �������Ĳ��ֹ�����Ϊ�����鲼��
		getContentPane().add(panel, BorderLayout.CENTER);
		contentPane.add(panel);

		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 735, 340);
		contentPane.add(scrollPane_1);

		//����һ����ű������
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
		scrollPane_1.setViewportView(table);//���������ų����˹�����
		//1******************��������¼�****************************
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
		//1******************��������¼�****************************
		jaTableHeader .setBounds(10, 1, 735,22);
		scrollPane_1.add(jaTableHeader );
		
	
		//�������ݿ���Ϣ�������
		showTableData();




		//---------------------------------�ı���Ķ���-----------------------------------
		textFieno = new JTextField();//���
		textFieno.setBounds(76, 33, 136, 23);
		panel.add(textFieno);
		textFieno.setColumns(10);

		textFiename = new JTextField();//����
		textFiename.setColumns(10);
		textFiename.setBounds(288, 33, 136, 23);
		panel.add(textFiename);

		textFiephone = new JTextField();//�绰����
		textFiephone.setColumns(10);
		textFiephone.setBounds(76, 90, 136, 23);
		panel.add(textFiephone);

		textFiedage = new JTextField();//ְ��
		textFiedage.setColumns(10);
		textFiedage.setBounds(296, 90, 48, 23);
		panel.add(textFiedage);

		textFiemoney = new JTextField();//����
		textFiemoney.setColumns(10);
		textFiemoney.setBounds(522, 33, 48, 23);
		panel.add(textFiemoney);

		textFielid = new JTextField();//���֤
		textFielid.setColumns(10);
		textFielid.setBounds(552, 90, 173, 23);
		panel.add(textFielid);
		//--------------------------------------�ı��������-----------------------------------
		//-----------------------------�Ա�ť��-----------------------------------------------
		radioButtonmale = new JRadioButton("��");
		radioButtonmale .setBounds(637, 33, 41, 23);
		panel.add(radioButtonmale );

		radioButtonfemal = new JRadioButton("Ů");
		radioButtonfemal.setBounds(680, 33, 41, 23);
		panel.add(radioButtonfemal);

		radioGroupSex = new ButtonGroup();
		radioGroupSex.add(radioButtonmale);
		radioGroupSex.add(radioButtonfemal);
		radioGroupSex.setSelected(radioButtonmale.getModel(),true);
		//----------------------------------------


		//*************************************��ť�Ķ����Լ��¼�����***************************************
		//��Ӱ�ť
		JButton btnNewButton_4 = new JButton("����");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearAll();
				unlockText();//�����ı���
			}
		});
		btnNewButton_4.setBounds(43, 181, 93, 23);
		panel.add(btnNewButton_4);

		//���水ť
		//�ð�ťʵ�ֵĹ��ܾ��ǴӲ�ͬ���ı����л�ȡ��Ӧ��ֵ����waiter������Ӧ����ֵͨ��waiter.Dao.insert�����Ѷ��󴫹�ȥȻ��ʵ�ֲ������ݿ�
		//��ͬʱҲ���ı������ֵ����һ��Vector�͵�row,������ʾ�������
		JButton btnadd = new JButton("ȷ�����");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String number =   textFieno.getText().toString();
				String name =  textFiename.getText().toString();
				String id= textFielid.getText().toString();
				String age =  textFiedage.getText().toString();
				String money = textFiemoney.getText() .toString();
				String phone = textFiephone.getText().toString();

				if(number.equals("")||name.equals("")||id.equals("")||age.equals("")||money.equals("")||phone.equals("")){ 
					JOptionPane.showConfirmDialog(null, "��������������������������룡", "��������", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				}else{


					Waiter waiter = new Waiter();
					Vector row = new Vector();

					waiter.setWaiterno(textFieno.getText().toString());//��waiter�������ñ��
					row.add(textFieno.getText().toString());

					waiter.setWname(textFiename.getText().toString());//��waiter������������
					row.add(textFiename.getText().toString());

					if(radioGroupSex.getSelection()==radioButtonmale.getModel()){//��waiter���������Ա�
						waiter.setWsex("��");
					}else{
						waiter.setWsex("Ů");
					}
					row.add(waiter.getWsex());

					waiter.setWid(textFielid.getText().toString());//��waiter�����������֤
					row.add(textFielid.getText().toString());

					waiter.setWphone(textFiephone.getText().toString());//��waiter�������õ绰����
					row.add(textFiephone.getText().toString());

					waiter.setWnomey(Integer.parseInt( textFiemoney.getText()));//��waiter�������ù���
					row.add(textFiemoney.getText().toString());

					waiter.setWage(Integer.parseInt(textFiedage.getText()));//��waiter��������ְ��
					row.add(textFiedage.getText().toString());

					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.addRow(row);

					waiterDao.insert(waiter);
					lockText();
					ShowSum();//������ʾ��Ů��������ķ���
				}
			}
		});
		btnadd.setBounds(171, 181, 93, 23);
		panel.add(btnadd);

		//������ť
		JButton btnNewButton_3 = new JButton("����");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lockText();
				textFieno.setEnabled(true);
			}
		});
		btnNewButton_3.setBounds(43, 214, 93, 23);
		panel.add(btnNewButton_3);

		//�޸İ�ť
		JButton btnNewButton_1 = new JButton("�޸�");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(table.getSelectedRow()<0){
					JOptionPane.showConfirmDialog(null,"��ѡ�����е�һ��","�޸�����",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

				}else{
					Waiter waiter = new Waiter();
					waiter.setWaiterno(textFieno.getText().toString());//��waiter�������ñ��
					waiter.setWname(textFiename.getText().toString());//��waiter������������
					if(radioGroupSex.getSelection()==radioButtonmale.getModel()){//��waiter���������Ա�
						waiter.setWsex("��");
					}else{
						waiter.setWsex("Ů");
					}
					waiter.setWid(textFielid.getText().toString());//��waiter�����������֤
					waiter.setWphone(textFiephone.getText().toString());//��waiter�������õ绰����
					waiter.setWnomey(Integer.parseInt( textFiemoney.getText()));//��waiter�������ù���
					waiter.setWage(Integer.parseInt(textFiedage.getText()));//��waiter��������ְ��

					DefaultTableModel model = (DefaultTableModel)table.getModel();
					String sex = "";
					if(radioGroupSex.getSelection()==radioButtonmale.getModel()){
						sex="��";
					}else{
						sex="Ů";
					}

					model.setValueAt(textFieno.getText(),table.getSelectedRow() , 0);
					model.setValueAt(textFiename.getText(),table.getSelectedRow() , 1);
					model.setValueAt(sex,table.getSelectedRow() , 2);
					model.setValueAt(textFielid.getText(),table.getSelectedRow() , 3);
					model.setValueAt(textFiephone.getText(),table.getSelectedRow() , 4);
					model.setValueAt(Integer.parseInt(textFiemoney.getText()),table.getSelectedRow() , 5);
					model.setValueAt(Integer.parseInt(textFiedage.getText()),table.getSelectedRow() , 6);

					waiterDao.update(waiter);
					ShowSum();//������ʾ��Ů��������ķ���
				}
			}
		});
		btnNewButton_1.setBounds(433, 181, 93, 23);
		panel.add(btnNewButton_1);

		//ɾ����ť
		JButton btnNewButton_2 = new JButton("ɾ��");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()<0){
					JOptionPane.showConfirmDialog(null,"��ѡ�����е�һ��","�޸�����",
							JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

				}else{
					waiterDao.delete(textFieno.getText());
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					model.removeRow(table.getSelectedRow());
					ShowSum();//������ʾ��Ů��������ķ���
				}
			}
		});
		btnNewButton_2.setBounds(573, 181, 93, 23);
		panel.add(btnNewButton_2);


		//�˳���ť
		JButton btnExit = new JButton("�˳�");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(null,"����Ҫ�˳�����Ա�����ȷ����","�˳�ϵͳ",JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION){
					System.out.println((new Date()).toString()+"�˳�ϵͳ");
					//  System.exit(0);
					EXit();
				}
			}



		});
		btnExit.setBounds(632, 255, 93, 23);
		panel.add(btnExit);

		JButton btnfindok = new JButton("ȷ������");
		btnfindok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( textFieno.getText().equals("")) {
					JOptionPane.showConfirmDialog(null, "��Ų���Ϊ�գ������������룡", "������Ϣ", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				} else {

					Vector waiter = waiterDao.findByWaiterNo( textFieno.getText());
					textFiename.setText(waiter.get(1).toString());
					String sex1 = waiter.get(2).toString();
					textFielid.setText(waiter.get(3).toString());
					textFiephone.setText(waiter.get(4).toString());
					textFiemoney.setText(waiter.get(5).toString());
					textFiedage.setText(waiter.get(6).toString());

					if (sex1.equals("��")) {
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
		lockText();//��ס�ı���
	}

	//���캯����ĺ���
	protected void EXit() {
		this.setVisible(false);
		System.err.println("******************************���뿪Ҧ���ɵĴ�������***********************************");
		// TODO �Զ����ɵķ������

	}

	private void showTableData() {//��ʾ���ݿ��з���Ա������Ϣ
		// TODO �Զ����ɵķ������
		title.add("���");
		title.add("����");
		title.add("�Ա�");
		title.add("���֤");
		title.add("��ϵ��ʽ");
		title.add("����");
		title.add("ְ��");
		data=waiterDao.waiterInfor();
		dtm.setDataVector(data, title);
		
		//���������ñ����п�
				table.setModel(dtm);
				int[] columnWith = {40,40,20,88,50,30,20};//�п������
				TableColumnModel columnModel = table.getColumnModel();//��ȡ��ģ��
				int count = columnModel.getColumnCount();//��ȡ������
				for(int i =0;i<count;i++){//����
					TableColumn column = columnModel.getColumn(i);//��ȡ�ж���
					column.setPreferredWidth(columnWith[i]);//�������Ԫ�������еĿ��
				}
	}

	//�����ı���
	public void unlockText(){
		textFieno.setEnabled(true);
		textFiename.setEnabled(true);
		textFiemoney.setEnabled(true);
		textFiephone.setEnabled(true);
		textFiedage.setEnabled(true);
		textFielid.setEnabled(true);
	}

	//���ı���
	public void lockText(){
		textFieno.setEnabled(false);
		textFiename.setEnabled(false);
		textFiemoney.setEnabled(false);
		textFiephone.setEnabled(false);
		textFiedage.setEnabled(false);
		textFielid.setEnabled(false);

	}

	//����ı���ķ���
	public void clearAll(){
		textFieno.setText("");
		textFiename.setText("");
		textFiephone.setText("");
		textFiedage.setText("");
		textFielid.setText("");
		textFiemoney.setText("");
		radioButtonmale.setSelected(true);
	}

	//����ѡ�еı��е��С�
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
		if("��".equals(sex)){
			radioGroupSex.setSelected(radioButtonmale.getModel(),true);
		}else{
			radioGroupSex.setSelected(radioButtonfemal.getModel(),true);
		}
	}

	//������һ�д������¼�
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
		float row = table.getRowCount();//����
		for(int i=0;i<row;i++){
			String a=(String) dtm.getValueAt(i, 2);//����һ��Ԫ��
			if (a.equals("��")){
				++sumMan;
			}else{
				++sumWomen;
			}
		}
		sumPeople = sumMan+sumWomen;
		float a =sumMan/sumPeople;
		float b =sumWomen/sumPeople;
		String lab1="��������"+Integer.toString((int)sumMan)+"��   ռ��������"+a;
		String lab2="Ů������"+Integer.toString((int)sumWomen)+"��  ռ��������"+b;
		String lab3="��������Ա��������"+Integer.toString((int)sumPeople)+"��";
		labelMan.setText(lab1);
		labelWomen.setText(lab2);
		labelPeople.setText(lab3);
	}
}
