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
 * 邱文浩
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
		
		// 下面将创建一自定义带有背景面板对象并添加到窗口的容器中
		final myJPanel panel = new myJPanel(this.getClass().getResource(
				"/img/sales1bg.png"));// 创建一个面板对象
		panel.setSize(654, 380);//设置大小
		//panel.setLocation(0, 338);//设置位置
		panel.setLayout( null);// 设置面板的布局管理器为网格组布局
		getContentPane().add(panel, BorderLayout.CENTER);
		contentPane.add(panel);
		

		dindan = new JRadioButton("订单单号");

		btnNewButton = new JButton("\u6761\u4EF6\u67E5\u8BE2");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("查询表。。。");
				// String sql ="select * from order where";
				String sql ="select * from dingcan where";
				int num = 0;
				if(dindan. isSelected()){

					if(textField.getText().length()!=0){//订单号的文本框不为空

						System.out.println("订单单号：" + textField.getText());
						sql = sql +  " menuNo like  "+ "\'" + textField.getText() + "\'";

						num++;
						System.out.println(sql);
					}}

				if(caipin. isSelected()){

					if(textField_1.getText().length()!=0){
						//			                System.out.println("菜品号被选中且长度不为0~！");
						System.out.println("菜品号："+textField_1.getText());
						if(num!=0)
							sql = sql + " and ";
						sql = sql + " foodNo like " + "\'"+ textField_1.getText()+ "\'";
						num++;
					}}
				if(time. isSelected()){

					comboBox.getSelectedIndex();
					System.out.println("时间："+ comboBox.getSelectedItem()+"年"+comboBox_1.getSelectedItem()+"月"+comboBox_2.getSelectedItem()+"日");
					if(num!=0)
						sql = sql + " and ";
					sql = sql + " date like " + "\'%"+comboBox.getSelectedItem()+"-"+comboBox_1.getSelectedItem()+ "-" +comboBox_2.getSelectedItem()+"%\'";
					System.out.println(sql);

					num++;
				}
				if(num==0){
					JOptionPane.showMessageDialog(null, "请至少选择一个条件！", "警告",JOptionPane.ERROR_MESSAGE);
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
						head.add("下单号");
						head.add("菜号");
						head.add("菜名");
						head.add("数量");
						head.add("单价");
						head.add("时间");
						head.add("是否付款");
						head.add("总价");


						dtm.setDataVector(data,head);
						table.setModel(dtm);

						res.close();
						pst.close();
						con.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "查询失败！");
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

		//查询所有记录按钮
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

					head.add("下单号");
					head.add("菜号");
					head.add("菜名");
					head.add("数量");
					head.add("单价");
					head.add("时间");
					head.add("是否付款");
					head.add("总价");


					dtm.setDataVector(data,head);
					table.setModel(dtm);

					res.close();
					pst.close();
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "查询失败！");
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
		//退出按钮
		btnNewButton_1 = new JButton("退出");
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
	

	//构造函数外的函数
	protected void EXit() {
		System.err.println("******************************你离开文浩的代码世界***********************************");
		this.setVisible(false);
		// TODO 自动生成的方法存根

	}


}
