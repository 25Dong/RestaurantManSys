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
 * 张皓
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
		this.setTitle("餐桌预订 之 更换餐桌类型 界面");//设置窗口标题


		// 下面将创建一自定义带有背景面板对象并添加到窗口的容器中
		final myJPanel panel = new myJPanel(this.getClass().getResource(
				"/img/tablechangebg.png"));// 创建一个面板对象
		panel.setSize(434, 262);
		panel.setLocation(0, 0);
		panel.setLayout( null);//
		getContentPane().add(panel, BorderLayout.CENTER);
		contentPane.add(panel);


		JRadioButton rdbtnNewRadioyuan= new JRadioButton("圆桌");
		rdbtnNewRadioyuan.setBackground(SystemColor.info);
		rdbtnNewRadioyuan.setBounds(143, 114, 67, 23);
		panel.add(rdbtnNewRadioyuan);

		JRadioButton rdbtnNewRadiofang = new JRadioButton("方桌");
		rdbtnNewRadiofang.setBackground(SystemColor.info);
		rdbtnNewRadiofang .setBounds(212, 114, 67, 23);
		panel.add(rdbtnNewRadiofang );

		ButtonGroup radioGroupTable = new ButtonGroup();
		radioGroupTable.add(rdbtnNewRadioyuan);
		radioGroupTable.add(rdbtnNewRadiofang);
		radioGroupTable.setSelected(rdbtnNewRadioyuan.getModel(),true);


		JButton btnNewButton = new JButton("确定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		        data=tableDao.queryVectorZhuozi("Select * from zhuozi");
		        row=a1;
		        Vector line=(Vector)data.get(row);
		        String zhuohao=line.get(0).toString();
		        String zhuotaileixing=line.get(3).toString();
		        //获取单选按钮的值
		        if(rdbtnNewRadiofang.isSelected())
		            zhuotaileixing="方桌";
		        else 
		            if(rdbtnNewRadioyuan.isSelected())
		                zhuotaileixing="圆桌";
		        String sql="update zhuozi set zhuotaileixing='"+zhuotaileixing+"' where zhuohao='"+zhuohao+"'";
		        System.out.println(sql);
		          if(tableDao.updateZhuozi(sql)){
		            JOptionPane.showMessageDialog(null,"换桌成功！请重新点击桌子号码信息确认","系统提示", JOptionPane.INFORMATION_MESSAGE);
		        	
		            //关闭窗口
		           // this.dispose();
		        }else{
		            JOptionPane.showMessageDialog(null,"换桌失败！","系统提示", JOptionPane.ERROR_MESSAGE);
		          //  //关闭窗口
		            //this.dispose();
		        }
			}
		});
		btnNewButton.setBounds(107, 169, 93, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("退出");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(null,"您将要退出，确定吗？","退出系统",JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE)==JOptionPane.YES_OPTION){
					System.out.println((new Date()).toString()+"退出更换台桌的界面");
					//  System.exit(0);
					EXit();
				}
			}
		});
		btnNewButton_1.setBounds(231, 169, 93, 23);
		panel.add(btnNewButton_1);
	}
	

	//构造函数外的函数
	protected void EXit() {
		this.setVisible(false);
		// TODO 自动生成的方法存根

	}
}
