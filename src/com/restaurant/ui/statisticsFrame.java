package com.restaurant.ui;
import com.restaurant.dao.salesDao;
import com.restaurant.db.JDBC;
import com.restaurant.myswing.myJPanel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpringLayout;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

/**
 * 邱文浩
 */

public class statisticsFrame extends JFrame {
	
	private JTable table;
	private JLabel lblncom ;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					statisticsFrame frame = new statisticsFrame();
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
	public statisticsFrame() {
		
		setTitle("\u9500\u552E\u6392\u884C\u7EDF\u8BA1");
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(10, 10, 535, 382);
		
		// 下面将创建一自定义带有背景面板对象并添加到窗口的容器中
		final myJPanel panel = new myJPanel(this.getClass().getResource(
				"/img/sales2bg.png"));// 创建一个面板对象
		panel.setSize(654, 380);//设置大小
		panel.setLayout( null);// 设置面板的布局管理器为网格组布局
		getContentPane().add(panel, BorderLayout.CENTER);

		JButton btnNewButton = new JButton("销量统计查询");
		btnNewButton.setBounds(0, 10, 134, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JDBC conn = new JDBC();
				Connection con = conn.getConnection();
				JDBC conn1 = new JDBC();
				Connection con1 = conn.getConnection();
				//String sql = "select menu.foodno,menu.foodname,price,fnumber from menu left join orderdata on menu.foodno = fno";
				String sql = "select menu.foodno,menu.foodname,price,foodNumber from menu left join dingcan on menu.foodno = dingcan.foodNo";
				String sql1 = "select * from menu";//查询菜单表的全部信息
				ResultSet rs = null;
				ResultSet rs1 = null;
				try {
					PreparedStatement pst = con.prepareStatement(sql);
					PreparedStatement pst1 = con1.prepareStatement(sql1);
					Vector data = new Vector();
					rs = pst.executeQuery();
					rs1 = pst1.executeQuery();

					/*String[] fno = new String[]{"01","02","03","04","05","06","07","08","09","10",
							"11","12","13","14","15","16","17","18","19","20"};//菜号*/

					//通过查询菜单表获得菜号的数组****************易承东
					Vector foodNodata = new Vector();//
					foodNodata = salesDao.menuFoodNo();//查询菜单表
					String[] fno = new String[foodNodata.size()];//菜号
					int foodleng = fno.length;//菜号的数量
					for(int i=0; i<foodNodata.size();i++){
						Vector a = new Vector();
						a = (Vector) foodNodata.get(i);
						fno[i]=(String) a.get(0);
					}
					


					Vector head = new Vector();
					DefaultTableModel dtm = new DefaultTableModel();
					int[] fnon = new int[foodleng];//单个菜品的销量数量	
					int[] fnom = new int[foodleng];//总金额
					String[] fname = new String[foodleng];//总金额

					while(rs.next()){
						for(int q = 0;q<foodleng;q++)
							if(rs.getString(1).equals(fno[q])){	//遍历菜号
								fnon[q]=fnon[q]+rs.getInt(4); //rs中的菜号和数组中的菜号相同时：数量相加并且保存在下标一样的数组中
							}
					}

					while(rs1.next()){
						Vector line = new Vector();
						String k = null;
						for (int i = 1; i <=3; i++){

							line.add(rs1.getObject(i));//行加入菜号，菜名，单价
						}  
						for(int q = 0;q<foodleng;q++)
							if(rs1.getString(1).equals(fno[q])){//菜号相等
								line.add(fnon[q]);//行添加该菜的销售数量

								line.add(fnon[q]*rs1.getInt(3));//行加入该菜销售金额
							}

						data.add(line);//二维data（菜品号，菜名，单价，销售数量，销售金额）
					}

					
					head.add("菜品号");
					head.add("菜品名称");
					head.add("单价");
					head.add("销售数量");
					head.add("销售金额");

					dtm.setDataVector(data,head);
					table.setModel(dtm);
				} catch (SQLException e) {
					e.printStackTrace();
				}		
				showIncom();
			}
		});
		getContentPane().setLayout(null);
		panel.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 39, 515, 295);
		panel.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"\u83DC\u54C1\u53F7", "\u83DC\u54C1\u540D\u79F0",  "\u5355\u4EF7","\u9500\u552E\u6570\u91CF", "\u9500\u552E\u91D1\u989D"
				}
				));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("退出");
		btnNewButton_1.setBounds(432, 10, 77, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EXit();
			}
		});
		panel.add(btnNewButton_1);
		
		lblncom = new JLabel("");
		lblncom.setBounds(144, 10, 187, 23);
		panel.add(lblncom);
		
		JButton btnNewButton_2 = new JButton("返回");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				salesFrame st = new salesFrame();
				st.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_2.setBounds(329, 10, 93, 23);
		panel.add(btnNewButton_2);
		
		
	}
		//构造函数外的函数
		protected void EXit() {
			System.err.println("******************************你离开文浩的代码世界***********************************");
			this.setVisible(false);
			// TODO 自动生成的方法存根

		}
		
		//显示总收入
		void showIncom(){
			int sum = 0;
			int a;
			int row = table.getRowCount();//行数
			DefaultTableModel dtm = (DefaultTableModel)table.getModel();
			
			for(int i=0;i<row;i++){
				a= (int) dtm.getValueAt(i, 4);//表格的一个元素
				sum =sum+a;
			}
			String income = "总的收入是（元）："+Integer.toString(sum);
			lblncom.setText(income);//显示在标签上
			System.out.println(income);
		}
}
