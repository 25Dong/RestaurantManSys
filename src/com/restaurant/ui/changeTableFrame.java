package com.restaurant.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import com.restaurant.dao.tableDao;
import com.restaurant.myswing.myJPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.Vector;
import java.awt.SystemColor;

/*
 * ���
 */

public class changeTableFrame extends JFrame {

	private JPanel contentPane;
    Vector data=new Vector();
    public static int a1;
    int row=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					changeTableFrame frame = new changeTableFrame();
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
	public changeTableFrame() {
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(567, 10, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("����Ԥ�� ֮ ������������ ����");//���ô��ڱ���


		// ���潫����һ�Զ�����б�����������ӵ����ڵ�������
		final myJPanel panel = new myJPanel(this.getClass().getResource(
				"/img/tablechangebg.png"));// ����һ��������
		panel.setSize(434, 262);
		panel.setLocation(0, 0);
		panel.setLayout( null);//
		getContentPane().add(panel, BorderLayout.CENTER);
		contentPane.add(panel);


		JRadioButton rdbtnNewRadioyuan= new JRadioButton("Բ��");
		rdbtnNewRadioyuan.setBackground(SystemColor.info);
		rdbtnNewRadioyuan.setBounds(143, 114, 67, 23);
		panel.add(rdbtnNewRadioyuan);

		JRadioButton rdbtnNewRadiofang = new JRadioButton("����");
		rdbtnNewRadiofang.setBackground(SystemColor.info);
		rdbtnNewRadiofang .setBounds(212, 114, 67, 23);
		panel.add(rdbtnNewRadiofang );

		ButtonGroup radioGroupTable = new ButtonGroup();
		radioGroupTable.add(rdbtnNewRadioyuan);
		radioGroupTable.add(rdbtnNewRadiofang);
		radioGroupTable.setSelected(rdbtnNewRadioyuan.getModel(),true);


		JButton btnNewButton = new JButton("ȷ��");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		        data=tableDao.queryVectorZhuozi("Select * from zhuozi");
		        row=a1;
		        Vector line=(Vector)data.get(row);
		        String zhuohao=line.get(0).toString();
		        String zhuotaileixing=line.get(3).toString();
		        //��ȡ��ѡ��ť��ֵ
		        if(rdbtnNewRadiofang.isSelected())
		            zhuotaileixing="����";
		        else 
		            if(rdbtnNewRadioyuan.isSelected())
		                zhuotaileixing="Բ��";
		        String sql="update zhuozi set zhuotaileixing='"+zhuotaileixing+"' where zhuohao='"+zhuohao+"'";
		        System.out.println(sql);
		          if(tableDao.updateZhuozi(sql)){
		            JOptionPane.showMessageDialog(null,"�����ɹ��������µ�����Ӻ�����Ϣȷ��","ϵͳ��ʾ", JOptionPane.INFORMATION_MESSAGE);
		        	
		            //�رմ���
		           // this.dispose();
		        }else{
		            JOptionPane.showMessageDialog(null,"����ʧ�ܣ�","ϵͳ��ʾ", JOptionPane.ERROR_MESSAGE);
		          //  //�رմ���
		            //this.dispose();
		        }
			}
		});
		btnNewButton.setBounds(107, 169, 93, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("�˳�");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(null,"����Ҫ�˳���ȷ����","�˳�ϵͳ",JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION){
					System.out.println((new Date()).toString()+"�˳�����̨���Ľ���");
					//  System.exit(0);
					EXit();
				}
			}
		});
		btnNewButton_1.setBounds(231, 169, 93, 23);
		panel.add(btnNewButton_1);
	}
	

	//���캯����ĺ���
	protected void EXit() {
		this.setVisible(false);
		// TODO �Զ����ɵķ������

	}
}
