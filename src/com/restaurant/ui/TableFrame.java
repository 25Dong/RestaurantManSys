package com.restaurant.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.restaurant.dao.tableDao;
import com.restaurant.myswing.myBtnton;
import com.restaurant.myswing.myJPanel;
import com.restaurant.tool.TablebunmberToInt;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.awt.Font;
import java.awt.Color;

/*
 * ���
 */

public class TableFrame extends JFrame {

	private JPanel contentPane;
	private JTextField tzhuoming;
	private JTextField tzhuozhuangtai;
	private JTextField tzhuoleixing;
	private JTextField tbeizhu;
	private JTextField tzhuojieshushijian;
	private JTextField tzhuokaitaishijian;
	private JTextField tzhuoweizhi;
	private JTextField tzhuohao;
	SimpleDateFormat df = new SimpleDateFormat("YY-MM-dd HH:mm:ss");
	Vector data=new Vector();   
	Vector returndata = null;
	int numbei = 0;//������������ȷ��Ԥ������ȷ��ȷ���õ�
	int row=0;
	int frameHeigth = 759;
	public static int a3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableFrame frame = new TableFrame();
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
	public TableFrame() {
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		//setBounds(10, 10, 552, 759);
		setBounds(10, 10, 552, 0);
		//��̬�ſ�����
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                do_this_windowOpened(e);
            }
        });
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("����Ԥ������");//���ô��ڱ���

		// ���潫����һ�Զ�����б�����������ӵ����ڵ�������
		final myJPanel panel = new myJPanel(this.getClass().getResource(
				"/img/tablebg.png"));// ����һ��������
		panel.setSize(537, 719);
		panel.setLocation(0, 0);
		panel.setLayout( null);//
		getContentPane().add(panel, BorderLayout.CENTER);
		contentPane.add(panel);

		//************************************************** �ı���Ķ��� ********************************************
		tzhuoming = new JTextField();
		tzhuoming.setColumns(10);
		tzhuoming.setBounds(93, 55, 139, 26);
		panel.add(tzhuoming);

		tzhuozhuangtai = new JTextField();
		tzhuozhuangtai.setFont(new Font("����", Font.BOLD, 18));// ��������Ϊ���塢���塢18��
		tzhuozhuangtai.setForeground(new Color(255, 0, 0));// ��������Ϊ��ɫ
		tzhuozhuangtai.setColumns(10);
		tzhuozhuangtai.setBounds(93, 93, 139, 26);
		panel.add(tzhuozhuangtai);

		tzhuoleixing = new JTextField();
		tzhuoleixing.setColumns(10);
		tzhuoleixing.setBounds(93, 137, 139, 26);
		panel.add(tzhuoleixing);

		tbeizhu = new JTextField();
		tbeizhu.setColumns(10);
		tbeizhu.setBounds(373, 137, 139, 26);
		panel.add(tbeizhu);

		tzhuojieshushijian = new JTextField();
		tzhuojieshushijian.setColumns(10);
		tzhuojieshushijian.setBounds(373, 97, 139, 26);
		panel.add(tzhuojieshushijian);

		tzhuokaitaishijian = new JTextField();
		tzhuokaitaishijian.setColumns(10);
		tzhuokaitaishijian.setBounds(373, 61, 139, 26);
		panel.add(tzhuokaitaishijian);

		tzhuoweizhi = new JTextField();
		tzhuoweizhi.setColumns(10);
		tzhuoweizhi.setBounds(373, 17, 139, 26);
		panel.add(tzhuoweizhi);

		tzhuohao = new JTextField();
		tzhuohao.setColumns(10);
		tzhuohao.setBounds(93, 17, 139, 26);
		panel.add(tzhuohao);

		//************************************************** 16����ť �Ķ��壨16�����ӣ�********************************************
		JButton btntable1 = new JButton("");
		btntable1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//��ʾ��һ������
				row=0;
				showTextData(row);
			}
		});
		btntable1.setBounds(0, 180, 110, 110);
		panel.add( btntable1);
		URL landUrl = this.getClass().getResource("/img/table1.png");// ���Ĭ������°�ť��ʾͼƬ��URL
		btntable1.setContentAreaFilled(false);// ���ò����ư�ť����������
		btntable1.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		btntable1.setIcon(new ImageIcon(landUrl));// ����Ĭ������°�ť��ʾ��ͼƬ

		JButton btntable2 = new JButton("2");
		btntable2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//��ʾ��һ������
				row=1;
				showTextData(row);
			}
		});
		btntable2.setBounds(122, 180, 110, 110);
		panel.add(btntable2);
		URL landUr2 = this.getClass().getResource("/img/table2.png");// ���Ĭ������°�ť��ʾͼƬ��URL
		btntable2.setContentAreaFilled(false);// ���ò����ư�ť����������
		btntable2.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		btntable2.setIcon(new ImageIcon(landUr2));// ����Ĭ������°�ť��ʾ��ͼƬ

		JButton btntable3 = new JButton("");
		btntable3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//��ʾ��һ������
				row=2;
				showTextData(row);
			}
		});
		btntable3.setBounds(297, 180, 110, 110);
		panel.add(btntable3);
		URL landUr3 = this.getClass().getResource("/img/table3.png");// ���Ĭ������°�ť��ʾͼƬ��URL
		btntable3.setContentAreaFilled(false);// ���ò����ư�ť����������
		btntable3.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		btntable3.setIcon(new ImageIcon(landUr3));// ����Ĭ������°�ť��ʾ��ͼƬ

		JButton btntable4  = new JButton("");
		btntable4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//��ʾ��һ������
				row=3;
				showTextData(row);
			}
		});
		btntable4.setBounds(417, 180, 110, 110);
		panel.add(btntable4);
		URL landUr4 = this.getClass().getResource("/img/table4.png");// ���Ĭ������°�ť��ʾͼƬ��URL
		btntable4.setContentAreaFilled(false);// ���ò����ư�ť����������
		btntable4.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		btntable4.setIcon(new ImageIcon(landUr4));// ����Ĭ������°�ť��ʾ��ͼƬ


		JButton btntable5 = new JButton("");
		btntable5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//��ʾ��һ������
				row=4;
				showTextData(row);
			}
		});
		btntable5.setBounds(0, 284, 110, 110);
		panel.add(btntable5);
		URL landUr5 = this.getClass().getResource("/img/table5.png");// ���Ĭ������°�ť��ʾͼƬ��URL
		btntable5.setContentAreaFilled(false);// ���ò����ư�ť����������
		btntable5.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		btntable5.setIcon(new ImageIcon(landUr5));// ����Ĭ������°�ť��ʾ��ͼƬ

		JButton btntable6 = new JButton("");
		btntable6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//��ʾ��һ������
				row=5;
				showTextData(row);
			}
		});
		btntable6.setBounds(118, 286, 100, 102);
		panel.add(btntable6);
		URL landUr6 = this.getClass().getResource("/img/table6.png");// ���Ĭ������°�ť��ʾͼƬ��URL
		btntable6.setContentAreaFilled(false);// ���ò����ư�ť����������
		btntable6.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		btntable6.setIcon(new ImageIcon(landUr6));// ����Ĭ������°�ť��ʾ��ͼƬ

		JButton btntable7 = new JButton("");
		btntable7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//��ʾ��һ������
				row=6;
				showTextData(row);
			}
		});
		btntable7.setBounds(297, 284, 110, 110);
		panel.add(btntable7);
		URL landUr7 = this.getClass().getResource("/img/table7.png");// ���Ĭ������°�ť��ʾͼƬ��URL
		btntable7.setContentAreaFilled(false);// ���ò����ư�ť����������
		btntable7.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		btntable7.setIcon(new ImageIcon(landUr7));// ����Ĭ������°�ť��ʾ��ͼƬ


		JButton btntable8 = new JButton("8");
		btntable8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//��ʾ��һ������
				row=7;
				showTextData(row);
			}
		});
		btntable8.setBounds(425, 284, 110, 110);
		panel.add(btntable8);
		URL landUr8 = this.getClass().getResource("/img/table8.png");// ���Ĭ������°�ť��ʾͼƬ��URL
		btntable8.setContentAreaFilled(false);// ���ò����ư�ť����������
		btntable8.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		btntable8.setIcon(new ImageIcon(landUr8));// ����Ĭ������°�ť��ʾ��ͼƬ

		JButton btntable9 = new JButton("9");
		 btntable9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//��ʾ��һ������
				row=8;
				showTextData(row);
			}
		});
		 btntable9.setBounds(8, 386, 110, 110);
		 panel.add( btntable9);
		URL landUr9 = this.getClass().getResource("/img/table9.png");// ���Ĭ������°�ť��ʾͼƬ��URL
		btntable9.setContentAreaFilled(false);// ���ò����ư�ť����������
		btntable9.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		btntable9.setIcon(new ImageIcon(landUr9));// ����Ĭ������°�ť��ʾ��ͼƬ

		JButton btntable10 = new JButton("10");
		 btntable10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//��ʾ��һ������
				row=9;
				showTextData(row);
			}
		});
		 btntable10.setBounds(122, 386, 110, 110);
		 panel.add( btntable10);
		URL landUr10 = this.getClass().getResource("/img/table10.png");// ���Ĭ������°�ť��ʾͼƬ��URL
		btntable10.setContentAreaFilled(false);// ���ò����ư�ť����������
		btntable10.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		btntable10.setIcon(new ImageIcon(landUr10));// ����Ĭ������°�ť��ʾ��ͼƬ

		JButton btntable11 = new JButton("11");
		btntable11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//��ʾ��һ������
				row=10;
				showTextData(row);
			}
		});
		btntable11.setBounds(303, 386, 112, 110);
		panel.add(btntable11);
		URL landUr11 = this.getClass().getResource("/img/table11.png");// ���Ĭ������°�ť��ʾͼƬ��URL
		btntable11.setContentAreaFilled(false);// ���ò����ư�ť����������
		btntable11.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		btntable11.setIcon(new ImageIcon(landUr11));// ����Ĭ������°�ť��ʾ��ͼƬ

		JButton btntable12= new JButton("12");
		 btntable12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//��ʾ��һ������
				row=11;
				showTextData(row);
			}
		});
		 btntable12.setBounds(425, 386, 110, 110);
		 panel.add( btntable12);
		URL landUr12 = this.getClass().getResource("/img/table12.png");// ���Ĭ������°�ť��ʾͼƬ��URL
		btntable12.setContentAreaFilled(false);// ���ò����ư�ť����������
		btntable12.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		btntable12.setIcon(new ImageIcon(landUr12));// ����Ĭ������°�ť��ʾ��ͼƬ

		JButton btntable13 = new JButton("13");
		btntable13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//��ʾ��һ������
				row=12;
				showTextData(row);
			}
		});
		btntable13.setBounds(5, 495, 123, 110);
		panel.add(btntable13);
		URL landUr13 = this.getClass().getResource("/img/table13.png");// ���Ĭ������°�ť��ʾͼƬ��URL
		btntable13.setContentAreaFilled(false);// ���ò����ư�ť����������
		btntable13.setBorderPainted(false);// ���ò����ư�ť�ı߿�
		btntable13.setIcon(new ImageIcon(landUr13));// ����Ĭ������°�ť��ʾ��ͼƬ

		JButton btntale14 = new JButton("14");
		btntale14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//��ʾ��һ������
				row=13;
				showTextData(row);
			}
		});
		btntale14.setBounds(122, 495, 110, 110);
		panel.add(btntale14);
		URL landUr14 = this.getClass().getResource("/img/table14.png");// ���Ĭ������°�ť��ʾͼƬ��URL
		btntale14 .setContentAreaFilled(false);// ���ò����ư�ť����������
		btntale14 .setBorderPainted(false);// ���ò����ư�ť�ı߿�
		btntale14 .setIcon(new ImageIcon(landUr14));// ����Ĭ������°�ť��ʾ��ͼƬ


		JButton btntable15 = new JButton("15");
		 btntable15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//��ʾ��һ������
				row=14;
				showTextData(row);
			}
		});
		 btntable15.setBounds(303, 495, 110, 110);
		 panel.add( btntable15);
		URL landUr15 = this.getClass().getResource("/img/table15.png");// ���Ĭ������°�ť��ʾͼƬ��URL
		btntable15 .setContentAreaFilled(false);// ���ò����ư�ť����������
		btntable15 .setBorderPainted(false);// ���ò����ư�ť�ı߿�
		btntable15 .setIcon(new ImageIcon(landUr15));// ����Ĭ������°�ť��ʾ��ͼƬ


		JButton btntable16= new JButton("16");
		btntable16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				//��ʾ��һ������
				row=15;
				showTextData(row);
			}
		});
		btntable16.setBounds(425, 495, 110, 110);
		panel.add(btntable16);
		URL landUr16 = this.getClass().getResource("/img/table16.png");// ���Ĭ������°�ť��ʾͼƬ��URL
		btntable16 .setContentAreaFilled(false);// ���ò����ư�ť����������
		btntable16 .setBorderPainted(false);// ���ò����ư�ť�ı߿�
		btntable16 .setIcon(new ImageIcon(landUr16));// ����Ĭ������°�ť��ʾ��ͼƬ

		//************************************************** 5����ť �Ķ��壨Ԥ����ȡ��ȴԤ����ȷ��Ԥ�����������˳���********************************************
		JButton btntable35 = new JButton("Ԥ��");
		btntable35.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��ȡ��ǰ���ӵ���Ϣ
				String zhuohao=tzhuohao.getText();
				a3=TablebunmberToInt.Duquhangshu(zhuohao);
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				row=a3;
				Vector line=(Vector)data.get(row);

				String zhuotaizhuangtai=line.get(2).toString();
				System.out.println("�����ӵ�״̬�ǣ�"+ zhuotaizhuangtai);
				//�ж��Ƿ����,�Ǳ�ʾ��Ԥ��,���ʾ����Ԥ��
				if(zhuotaizhuangtai.equals("����")){
					++numbei;//����Ѿ�������Ԥ��
					zhuotaizhuangtai="Ԥ��";
					Date time=new Date();
					String b=df.format(time);
					System.out.println("Ԥ����ʱ���ǣ�"+b);
					//ִ��SQL���
					String sql="update zhuozi set zhuotaizhuangtai='"+zhuotaizhuangtai+"' ,kaitaishijian='"+b+"'where zhuohao='"+zhuohao+"'";

					System.out.println(sql);
					if(tableDao.updateZhuozi(sql)){
						data=tableDao.queryVectorZhuozi("Select * from zhuozi");
						//��ʾ��һ������
						showTextData(row);
					}else{
						JOptionPane.showMessageDialog(null,"Ԥ��ʧ�ܣ�","ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null,"�������ˣ�","ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btntable35.setBounds(8, 625, 93, 23);
		panel.add(btntable35);

		JButton btntable36 = new JButton("ȡ��Ԥ��");
		btntable36.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��ȡ��ǰ���ӵ���Ϣ
				String zhuohao=tzhuohao.getText();
				a3=TablebunmberToInt.Duquhangshu(zhuohao);
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				row=a3;
				Vector line=(Vector)data.get(row);

				String zhuotaizhuangtai=line.get(2).toString();
				System.out.println("�����ӵ�״̬�ǣ�"+ zhuotaizhuangtai);
				//�ж��Ƿ����,�Ǳ�ʾ��Ԥ��,���ʾ����Ԥ��
				if(zhuotaizhuangtai.equals("Ԥ��")){
					zhuotaizhuangtai="����";
					//ִ��SQL���
					String sql="update zhuozi set zhuotaizhuangtai='"+zhuotaizhuangtai+"' where zhuohao='"+zhuohao+"'";
					System.out.println(sql);
					if(tableDao.updateZhuozi(sql)){
						data=tableDao.queryVectorZhuozi("Select * from zhuozi");
						//��ʾ��һ������
						showTextData(row);
					}else{
						JOptionPane.showMessageDialog(null,"ȡ��Ԥ��ʧ�ܣ�","ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null,"����û�б�Ԥ����ȡ��Ԥ��ʧ�ܣ�","ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btntable36.setBounds(122, 625, 93, 23);
		panel.add(btntable36);

		JButton btnExit = new JButton("�˳�");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exit();
			}
		});
		btnExit.setBounds(434, 688, 93, 23);
		panel.add(btnExit);

		JButton btnNewButton_2 = new JButton("ȷ��Ԥ��");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(numbei==0){
					JOptionPane.showMessageDialog(null, "����Ԥ������!");
				}else{
				
				String zhuohao=tzhuohao.getText();
				a3=TablebunmberToInt.Duquhangshu(zhuohao);
				data=tableDao.queryVectorZhuozi("Select * from zhuozi");
				row=a3;
				Vector line=(Vector)data.get(row);

				String zhuotaizhuangtai=line.get(2).toString();
				System.out.println("��������ӵ�״̬�ǣ�"+ zhuotaizhuangtai);
				//�ж��Ƿ����,�Ǳ�ʾ��Ԥ��,���ʾ����Ԥ��
				if(zhuotaizhuangtai.equals("Ԥ��")){

					returndata = new Vector();//���ȷ��Ԥ��ʱ�����š�������λ�á�ʱ��
					String tableno = tzhuohao.getText();
					String tablename = tzhuoming.getText();
					String location = tzhuoweizhi.getText();
					String time= tzhuokaitaishijian.getText();

					returndata.add(tableno);
					returndata.add(tablename);
					returndata.add(location);
					returndata.add(time);

					mainFrame.dataOftable = null;
					mainFrame.dataOftable= returndata;
					System.out.println("��̨�����淵�ص��������Ԥ��������Ϣ�ǣ�"+mainFrame.dataOftable);
					mainFrame.showDataOfTable();
					JOptionPane.showMessageDialog(null, "Ԥ���ɹ�!����Ե����");
					setVisible(false);//ȷ�����˳�����
				}else{
					JOptionPane.showMessageDialog(null,"ȷ��Ԥ��ʧ�ܣ�����Ҫ��Ԥ���������ſ���ȷ��Ԥ��","ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
				}

			}
			}
		});
		btnNewButton_2.setBounds(425, 625, 93, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("����");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				   //��ȡ��ǰ���ӵ���Ϣ
				 String zhuohao=tzhuohao.getText();
			        //������ת����int�Ͳ���ֵ�������������a1
				 changeTableFrame.a1=TablebunmberToInt.Duquhangshu(zhuohao);
				 changeTableFrame.main(null);
			}
		});
		btnNewButton.setBounds(301, 625, 93, 23);
		panel.add(btnNewButton);

		data=tableDao.queryVectorZhuozi("Select * from zhuozi");
		//row��ʼֵΪ0  ��ʾ��1������
		showTextData(row);

	}
	
	//************************************************** ���캯����ķ���********************************************

	public void Exit(){//�˳�
		String a=mainFrame.txtTableno.getText();
		if(a.equals("")){
			JOptionPane.showMessageDialog(null, "��ȷ��Ԥ���������˳�!");
		}else{
			System.err.println("******************************���뿪���Ĵ�������***********************************");
		this.setVisible(false);
		}
	}
	
	//���ı�����ʾ����
	public void showTextData(int row){
		Vector line=(Vector)data.get(row);
		tzhuohao.setText(line.get(0).toString());
		tzhuoming.setText(line.get(1).toString());
		tzhuozhuangtai.setText(line.get(2).toString());
		tzhuoleixing.setText(line.get(3).toString());
		tzhuoweizhi.setText(line.get(4).toString());
		tzhuokaitaishijian.setText(line.get(5).toString());
		tzhuojieshushijian.setText(line.get(6).toString());
		tbeizhu.setText(line.get(7).toString());
		lockText();//��ʾ����ʱҪ��ס
	}

	//��ס�ı��򷽷�
	public void lockText(){
		tzhuohao.setEnabled(false);
		tzhuoming.setEnabled(false);
		tzhuozhuangtai.setEnabled(false);
		tzhuoleixing.setEnabled(false);
		tzhuoweizhi.setEnabled(false);
		tzhuokaitaishijian.setEnabled(false);
		tzhuojieshushijian.setEnabled(false);
		tbeizhu.setEnabled(false);
	}
	
    protected void do_this_windowOpened(WindowEvent e) {
        final int weith = getWidth();// ��¼����߶�
        new Thread() {// �������߳�
            public void run() {
                Rectangle rec = getBounds();
                for (int i = 0; i < frameHeigth; i += 10) {// ѭ�����촰��
                    setBounds(rec.x , rec.y, weith, i);// x,y����,��Ҳ���仯���߶ȱ仯
                    //System.out.println(rec.x);
                    //System.out.println(rec.y);
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
