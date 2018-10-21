package com.restaurant.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.restaurant.dao.meun;
import com.restaurant.dao.waiterDao;
import com.restaurant.entity.Meun;
import com.restaurant.entity.Waiter;
import com.restaurant.myswing.myJPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * ���ν�
 */

public class MenuFrame extends JFrame {


	private JPanel contentPane;
	private JTable table;
	private static JTable table_1;
	static int MONEY = 0;
	private static int  NUMBER = 0; 
	static int NO = 0;
	static int MENUNO = 1;
	int liucheng=0;//�������Ƶ�����̣���Ӻ�ſ��Ե��
	int liucheng1=0;//�������ƹ���Ա��Ӳ�Ʒ�ǵĲ��裬�����Ƚ���������Ӳ�Ʒ
	//	static DefaultTableModel dtm = new DefaultTableModel();
	//	static Vector tableHeads = new Vector();
	//	static Vector data = new Vector();
	public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;  
	public static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	static ArrayList list = new ArrayList();
	static Socket server; 
	private JTextField textfoodNo;
	private JTextField textFoodNO;
	private JTextField textFoodNo2;
	private JTable table_2;
	private JTextField textFoodName;
	private JTextField textdanjia;
	private JButton btndelete ;
	private JButton btnadd ;
	private JButton btnudapte ;
	private int frameWidth = 833;//��̬����չ���Ŀ��

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuFrame frame = new MenuFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//��������ǰ����ݿ�ĵ�ͱ��ڵ�ͱ������ʾ�������˿�ÿ��һ�����Ӷ������������������µ�ͱ�
	//��ѯtest���������Ϣ
	public static void numberadd(String a){

		DefaultTableModel dtm = new DefaultTableModel();
		Vector tableHeads = new Vector();
		Vector data = new Vector();
		tableHeads.add("�˺�");
		tableHeads.add("����");
		tableHeads.add("����");
		tableHeads.add("���");
		tableHeads.add("����");
		tableHeads.add("����");
		tableHeads.add("�µ���");
		data = meun.meun("a");
		dtm.setDataVector(data,tableHeads);
		table_1.setModel(dtm);


		//���������ñ����п�
		int[] columnWith = {40,80,30,30,40,119,40};//�п������
		TableColumnModel columnModel = table_1.getColumnModel();//��ȡ��ģ��
		int count = columnModel.getColumnCount();//��ȡ������
		for(int i =0;i<count;i++){//����
			TableColumn column = columnModel.getColumn(i);//��ȡ�ж���
			column.setPreferredWidth(columnWith[i]);//�������Ԫ�������еĿ��
		}
	}

	/**
	 * Create the frame.
	 */
	public MenuFrame() {
		setResizable(false);
		setResizable(false);
		setTitle("\u9910\u5385\u7BA1\u7406\u7CFB\u7EDF");

		//��̬չ��
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				do_this_windowOpened(e);
			}
		});

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(420, 100, 833, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);


		// ���潫����һ�Զ�����б�����������ӵ����ڵ�������
		final myJPanel panel = new myJPanel(this.getClass().getResource(
				"/img/menubg.png"));// ����һ��������
		panel.setSize(826, 525);
		panel.setLocation(0, 0);
		panel.setLayout( null);//
		getContentPane().add(panel, BorderLayout.CENTER);
		contentPane.add(panel);

		//*****************************************************�������Ķ���*********************************************
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 29, 293, 374);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(422, 29, 379, 200);

		//*****************************************************���Ķ���*********************************************


		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			//����ұߵı��ļ����¼�
			@Override
			public void mouseClicked(MouseEvent arg0) {
				processSelectionRow1();
				System.out.println("������Ԥ����˵ı��");
			}
		});
		scrollPane_1.setViewportView(table_1);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {//����ߵĲ˵�����������������¼�
				processSelectionRow();   
				System.out.println("�����˲˵���");
			}
		});
		table.setModel(new DefaultTableModel(
				new Object[][] {
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
						{null, null, null},
				},
				new String[] {
						"\u83DC\u7F16\u53F7", "\u540D\u5B57", "\u4EF7\u683C"
				}
				));
		scrollPane.setViewportView(table);
		contentPane.setLayout(null);
		panel.add(scrollPane);
		panel.add(scrollPane_1);


		//*****************************************************�ı���Ķ���*********************************************
		textFoodNO = new JTextField();//��߲˺�
		textFoodNO.setColumns(10);
		textFoodNO.setBounds(471, 413, 43, 25);
		panel.add(textFoodNO);

		textFoodNo2 = new JTextField();//�ұ߲˺�
		textFoodNo2.setColumns(10);
		textFoodNo2.setBounds(473, 263, 72, 25);
		panel.add(textFoodNo2);

		textFoodName = new JTextField();//����
		textFoodName.setBounds(159, 413, 93, 27);
		panel.add(textFoodName);
		textFoodName.setColumns(10);

		textdanjia = new JTextField();//����
		textdanjia.setBounds(342, 414, 32, 23);
		panel.add(textdanjia);
		textdanjia.setColumns(10);

		textfoodNo = new JTextField();//����
		textfoodNo.setColumns(10);
		textfoodNo.setBounds(72, 413, 32, 25);
		panel.add(textfoodNo);

		//*****************************************************��ť�Ķ���*********************************************
		JButton button = new JButton("���");
		//���˵�Ͱ�ť��ѵ�ͱ��е�����ȡ����ת��Ϊstring��Ȼ�󴫵��������ˡ�
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(liucheng==0){//���̱�������0˵����û�е����Ӱ�ť��
					JOptionPane.showMessageDialog(null, "������Ӳ�Ʒ!");
				}else{
					int a = table_1.getRowCount();
					Date date = new Date();
					for(int i = 0;i < a;i++){
						meun.order_(table_1.getValueAt(i, 0).toString(), 
								table_1.getValueAt(i, 1).toString(), 
								table_1.getValueAt(i, 2).toString(), 
								table_1.getValueAt(i, 3).toString(), 
								table_1.getValueAt(i, 4).toString(),
								table_1.getValueAt(i, 5).toString(),
								Integer.parseInt(table_1.getValueAt(i, 6).toString()),//�׳ж�
								"��");
					}
					JOptionPane.showMessageDialog(null, "��ͳɹ����벻Ҫ�˷�ʳ�������µ���");
					mainFrame.getDianCandata();
					setVisible(false);//��ͳɹ����˳�����
				}
			}
		});
		button.setBounds(617, 413, 81, 27);
		panel.add(button);

		JButton btnNewButton = new JButton("���");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//NO+=1;

				if(textfoodNo.getText().equals("")||textFoodNO.getText().equals("")){
					JOptionPane.showMessageDialog(null, "�˺Ż�����������Ϊ0!");

				}else if(Integer.parseInt(textFoodNO.getText())<=0){         //-----------------------2014/4/17
					JOptionPane.showMessageDialog(null, "�˺Ż�����������Ϊ����Ŷ����!");
				}
				else{
					int diyici=0;//��һ�ε����ӵ�ʱ��
					++liucheng;//�������̱�֤����ٵ����
					int number;
					if(diyici == 0){//����ǵ�һ�ε����ӵ�ʱ���µ���ȡ��һ�ε����ֵ�ټ�һ
						diyici++;
						number=meun.maxMeunNO();//��ȡĿǰ���ͱ�����Ķ�����
						number++;
					}else{
						
						number = meun.TestMeunNO();//����ȡ�ľ���test-����µ���
					}
					String tableNo= mainFrame.dataOftable.get(0).toString();//̨����
					String time= mainFrame.dataOftable.get(3).toString();//ʱ��
					//���뵽test_��
					meun.sales_(textfoodNo.getText(), textFoodName.getText(), textFoodNO.getText(),textdanjia.getText(), tableNo,time,number);
					//���ұߵı����ʾ����
					numberadd("");

				}
			}
		});
		btnNewButton.setBounds(535, 412, 72, 27);
		panel.add(btnNewButton);

		//��յ�ͱ�
		JButton button_1 = new JButton("���");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				meun.Del_list();
				NO = 0;
				numberadd("");
			}
		});
		button_1.setActionCommand("");
		button_1.setBounds(693, 262, 93, 27);
		panel.add(button_1);

		JButton button_3 = new JButton("�˲�");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(liucheng==0){
					JOptionPane.showMessageDialog(null, "�������!�����˲�");
				}else{
				meun.Del_select(textFoodNo2.getText());//���ݲ˺Ž����˲�
				//�����ݿ��test-����ɾ����Ϣ�󣬽����ϵı��Ҳ�Ƴ���Ӧ��һ��
				DefaultTableModel model = (DefaultTableModel)table_1.getModel();//��øñ�ı��ģ��
				model.removeRow(table_1.getSelectedRow());//�ڱ�����Ƴ�����
				}
			}
		});
		button_3.setBounds(568, 262, 93, 27);
		panel.add(button_3);

		btnadd = new JButton("���");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(mainFrame.job.equals("����Ա")){
					if(liucheng1==0){//����0˵����û��ִ�н����Ĵ��룬����û�н���
						JOptionPane.showMessageDialog(null, "�����Ƚ���!");
					}else{
						int maxFoodNo = meun.maxFoodNO()+1;//��ȡ����Ŀǰ�˵��������Ĳ˺�
						String foodNO = Integer.toString(maxFoodNo);//����ת����String
						textfoodNo.setText(foodNO);//��ʾ�˺�

						String foodName = textFoodName.getText().toString();//��ȡ����
						int money =Integer.parseInt( textdanjia.getText());//��ȡ�۸�

						if (foodName.equals("")||money==0){
							JOptionPane.showConfirmDialog(null, "��������Ϊ�գ����۲���Ϊ0��", "��������", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
						}else{
							Meun meun1 = new Meun();//�˵�����
							Vector row = new Vector();//�����ڱ������ʾ����ӵ�һ�����ݵ���Ϣ

							meun1.setFoodNo(foodNO);//������ֵ
							row.add(foodNO);

							meun1.setFoodName(foodName);
							row.add(foodName);

							meun1.setMoney(money);
							row.add(money);

							DefaultTableModel model = (DefaultTableModel)table.getModel();
							model.addRow(row);//��������һ��

							meun.insert(meun1);//�˵����в�����Ϣ

						}
					}
				}else{
					JOptionPane.showMessageDialog(null, "��û��Ȩ��ִ�иò�����", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);// ������ʾ
				}
			}
		});
		btnadd.setBounds(153, 463, 69, 23);
		panel.add(btnadd);

		btnudapte = new JButton("�޸�");
		btnudapte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mainFrame.job.equals("����Ա")){
					String foodNO = textfoodNo.getText().toString();
					String foodName = textFoodName.getText().toString();
					int money =Integer.parseInt( textdanjia.getText());

					if(table.getSelectedRow()<0){
						JOptionPane.showConfirmDialog(null,"��ѡ�����е�һ��","�޸�����",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

					}else{
						Meun meun2 = new Meun();

						meun2.setFoodNo(foodNO);
						meun2.setFoodName(foodName);
						meun2.setMoney(money);

						DefaultTableModel model = (DefaultTableModel)table.getModel();
						//�ڱ������ʾ�޸ĺ����Ϣ
						model.setValueAt(textfoodNo.getText(),table.getSelectedRow() , 0);
						model.setValueAt(textFoodName.getText(),table.getSelectedRow() , 1);
						model.setValueAt(Integer.parseInt(textdanjia.getText()),table.getSelectedRow() , 2);

						meun.update(meun2);//���±����Ϣ

					}
				}else{
					JOptionPane.showMessageDialog(null, "��û��Ȩ��ִ�иò�����", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);// ������ʾ
				}
			}
		});
		btnudapte.setBounds(232, 463, 69, 23);
		panel.add(btnudapte);


		btndelete = new JButton("ɾ��");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mainFrame.job.equals("����Ա")){
					if(table.getSelectedRow()<0){//û�е��б��������һ��
						JOptionPane.showConfirmDialog(null,"��ѡ�����е�һ��","�޸�����",
								JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);

					}else{
						meun.delete(textfoodNo.getText());//���ݲ˺�ɾ���˵���Ϣ
						
						DefaultTableModel model = (DefaultTableModel)table.getModel();//��øñ�ı��ģ��
						model.removeRow(table.getSelectedRow());//�ڱ�����Ƴ�����

					}
				}else{
					JOptionPane.showMessageDialog(null, "��û��Ȩ��ִ�иò�����", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);// ������ʾ
				}
			}
		});
		btndelete.setBounds(311, 463, 69, 23);
		panel.add(btndelete);

		JButton btnNewButton_5 = new JButton("����");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mainFrame.job.equals("����Ա")){
					++liucheng1;//�������̱���
					textFoodName.setEnabled(true);//����ס�����ı���
					textdanjia.setEnabled(true);//����ס���ۿ�
					textFoodName.setText("");
					textdanjia.setText("0");

				}else{
					JOptionPane.showMessageDialog(null, "��û��Ȩ��ִ�иò�����", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);// ������ʾ
				}

			}
		});
		btnNewButton_5.setBounds(81, 463, 62, 23);
		panel.add(btnNewButton_5);


		JButton btnNewButton_1 = new JButton("�˳�");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exit();
			}
		});
		btnNewButton_1.setBounds(724, 495, 93, 23);
		panel.add(btnNewButton_1);


		//******************************************************����******************************
		ShowTableData();//��ʾ�˵���������Ϣ
		meun.Del_list();//ͬʱ��test-������

		textfoodNo.setEnabled(false);//��ס�˺��ı���
		textFoodNo2.setEnabled(false);//��ס�ұߵĲ˺ŵ��ı���
		textFoodName.setEnabled(false);//��ס�����ı���
		textdanjia.setEnabled(false);//��ס���ۿ�
		textFoodNO.setText("1");//���Ĭ����Ϊ1
		System.out.println("������ģ��ĵ�¼��Ա��ְ���ǣ�"+mainFrame.job);
	}

	//**********************************************���캯����ĺ���****************************************************



	protected void Exit() {
		this.setVisible(false);
		System.err.println("******************************���뿪�ν��Ĵ�������***********************************");
	}

	//��ʾ�˵���������Ϣ
	public void ShowTableData(){
		DefaultTableModel dtm = new DefaultTableModel();
		Vector tableHeads = new Vector();
		Vector data = new Vector();
		tableHeads.add("�˱��");
		tableHeads.add("����");
		tableHeads.add("�۸�");
		data = meun.meun(5);//ע�������5ֻ��һ���βΣ���Ϊmeun��������meun��������һ�������Ĳ�����String
		dtm.setDataVector(data,tableHeads);
		table.setModel(dtm);
	}

	//����ѡ�еĲ˵����е��С�
	private void processSelectionRow(){
		int row = table.getSelectedRow();
		DefaultTableModel model = (DefaultTableModel)table.getModel();

		textfoodNo.setText(model.getValueAt(row, 0).toString());
		textFoodName.setText(model.getValueAt(row, 1).toString());
		textdanjia.setText(model.getValueAt(row, 2).toString());

	}
	
	//����ѡ�еĲ˵����е��С�
		private void processSelectionRow1(){
			int row = table_1.getSelectedRow();
			DefaultTableModel model = (DefaultTableModel)table_1.getModel();

			textFoodNo2.setText(model.getValueAt(row, 0).toString());//�ڲ˺��ı�����ʾ���һ�еĲ˺�

		}
		
	//��̬����
	protected void do_this_windowOpened(WindowEvent e) {
		final int height = getHeight();// ��¼����߶�
		new Thread() {// �������߳�
			public void run() {
				Rectangle rec = getBounds();
				for (int i = 0; i < frameWidth; i += 10) {// ѭ�����촰��
					setBounds(rec.x - i / 2, rec.y, i, height);// �������ô����С��λ��
					try {
						Thread.sleep(10);// �߳�����10����
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}
		}.start();// �����߳�
	}


}

