package com.restaurant.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.restaurant.db.JDBC;
import com.restaurant.myswing.myJPanel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

/*
 * ���ĺ�
 */

public class salesFrame extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JRadioButton caipin;
	private JRadioButton dindan;
	private JButton btnNewButton;
	private JRadioButton time;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private JButton btnNewButton_1;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					salesFrame frame = new salesFrame();
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
	public salesFrame() {
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 670, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// ���潫����һ�Զ�����б�����������ӵ����ڵ�������
		final myJPanel panel = new myJPanel(this.getClass().getResource(
				"/img/sales1bg.png"));// ����һ��������
		panel.setSize(654, 380);//���ô�С
		//panel.setLocation(0, 338);//����λ��
		panel.setLayout( null);// �������Ĳ��ֹ�����Ϊ�����鲼��
		getContentPane().add(panel, BorderLayout.CENTER);
		contentPane.add(panel);
		

		dindan = new JRadioButton("��������");

		btnNewButton = new JButton("\u6761\u4EF6\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ѯ������");
				// String sql ="select * from order where";
				String sql ="select * from dingcan where";
				int num = 0;
				if(dindan. isSelected()){

					if(textField.getText().length()!=0){//�����ŵ��ı���Ϊ��

						System.out.println("�������ţ�" + textField.getText());
						sql = sql +  " menuNo like  "+ "\'" + textField.getText() + "\'";

						num++;
						System.out.println(sql);
					}}

				if(caipin. isSelected()){

					if(textField_1.getText().length()!=0){
						//			                System.out.println("��Ʒ�ű�ѡ���ҳ��Ȳ�Ϊ0~��");
						System.out.println("��Ʒ�ţ�"+textField_1.getText());
						if(num!=0)
							sql = sql + " and ";
						sql = sql + " foodNo like " + "\'"+ textField_1.getText()+ "\'";
						num++;
					}}
				if(time. isSelected()){

					comboBox.getSelectedIndex();
					System.out.println("ʱ�䣺"+ comboBox.getSelectedItem()+"��"+comboBox_1.getSelectedItem()+"��"+comboBox_2.getSelectedItem()+"��");
					if(num!=0)
						sql = sql + " and ";
					sql = sql + " date like " + "\'%"+comboBox.getSelectedItem()+"-"+comboBox_1.getSelectedItem()+ "-" +comboBox_2.getSelectedItem()+"%\'";
					System.out.println(sql);

					num++;
				}
				if(num==0){
					JOptionPane.showMessageDialog(null, "������ѡ��һ��������", "����",JOptionPane.ERROR_MESSAGE);
				}else{
					JDBC link = new  JDBC();
					Connection con = link.getConnection();
					Vector data = new Vector();
					Vector head = new Vector();

					try {
						ResultSet res = null;
						PreparedStatement pst = con.prepareStatement(sql);
						DefaultTableModel dtm = new DefaultTableModel();
						res = pst.executeQuery();
						while (res.next()) {
							Vector line = new Vector();
							for (int i = 1; i <= 8; i++) {

								if(i!=2)
									line.add(res.getObject(i));
								if(i==8){

									line.add(res.getInt(5)*res.getInt(6));
								}

							}
							data.add(line);

						}
						head.add("�µ���");
						head.add("�˺�");
						head.add("����");
						head.add("����");
						head.add("����");
						head.add("ʱ��");
						head.add("�Ƿ񸶿�");
						head.add("�ܼ�");


						dtm.setDataVector(data,head);
						table.setModel(dtm);

						res.close();
						pst.close();
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "��ѯʧ�ܣ�");
					}
				}       
			}
		});

		caipin = new JRadioButton("\u83DC\u54C1\u53F7");

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"16", "15", "14", "13", "12"}));

		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));

		comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));

		JLabel lblNewLabel = new JLabel("\u5E74");

		JLabel lblNewLabel_1 = new JLabel("\u6708");

		JLabel label = new JLabel("\u65E5");

		//��ѯ���м�¼��ť
		JButton button = new JButton("\u67E5\u8BE2\u6240\u6709\u8BB0\u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JDBC link = new  JDBC();
				Connection con = link.getConnection();
				Vector data = new Vector();
				Vector head = new Vector();

				try {
					ResultSet res = null;
					PreparedStatement pst = con.prepareStatement("select * from dingcan ");
					DefaultTableModel dtm = new DefaultTableModel();
					res = pst.executeQuery();
					while (res.next()) {
						Vector line = new Vector();
						for (int i = 1; i <= 8; i++) {

							if(i!=2)
								line.add(res.getObject(i));
							if(i==8){

								line.add(res.getInt(5)*res.getInt(6));
							}

						}
						data.add(line);

					}

					head.add("�µ���");
					head.add("�˺�");
					head.add("����");
					head.add("����");
					head.add("����");
					head.add("ʱ��");
					head.add("�Ƿ񸶿�");
					head.add("�ܼ�");


					dtm.setDataVector(data,head);
					table.setModel(dtm);

					res.close();
					pst.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "��ѯʧ�ܣ�");
				}

			}
		});

		JScrollPane scrollPane = new JScrollPane();

		JScrollPane scrollPane_1 = new JScrollPane();

		time = new JRadioButton("\u8BA2\u5355\u65F6\u95F4");

		textField = new JTextField();
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JButton button_1 = new JButton("\u83DC\u54C1\u9500\u552E\u60C5\u51B5");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				statisticsFrame st = new statisticsFrame();
				st.setVisible(true);
				setVisible(false);
			}
		});
		//�˳���ť
		btnNewButton_1 = new JButton("�˳�");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			EXit(); 
				
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(panel);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(dindan)
								.addComponent(caipin))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField)
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton)
								.addComponent(time))
							.addGap(6)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(2)
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(button))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(button_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnNewButton_1))))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(dindan)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(time)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(caipin)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton)
						.addComponent(button)
						.addComponent(button_1)
						.addComponent(btnNewButton_1))
					.addGap(18)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
						{null, null, null, null, null, null, null, null},
				},
				new String[] {
						"\u83DC\u53F7", "\u83DC\u54C1", "\u6570\u91CF", "\u5355\u4EF7", "\u65F6\u95F4", "\u8BA2\u5355\u53F7", "\u662F\u5426\u7ED3\u8D26", "\u603B\u4EF7"
				}
				));
		scrollPane_1.setViewportView(table);
		panel.setLayout(gl_contentPane);
	}
	

	//���캯����ĺ���
	protected void EXit() {
		System.err.println("******************************���뿪�ĺƵĴ�������***********************************");
		this.setVisible(false);
		// TODO �Զ����ɵķ������

	}


}
