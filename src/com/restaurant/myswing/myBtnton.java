package com.restaurant.myswing;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class myBtnton extends JButton{

	private ImageIcon imageIcon;// ����һ��ͼƬ����
	/*public myJPanel(URL imgUrl) {
		super();// �̳и���Ĺ��췽��
		setLayout(new GridBagLayout());// �����ֹ������޸�Ϊ�����鲼��
		imageIcon = new ImageIcon(imgUrl);// ���ݴ����URL����ImageIcon����
		setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());// ���������ͼƬ�ȴ�
	}*/
	public myBtnton(URL imgUrl){
		super();
		imageIcon = new ImageIcon(imgUrl);
		setSize(imageIcon.getIconWidth(), imageIcon.getIconHeight());
	}
	protected void paintComponent(Graphics g) {// ��дJPanel���paintComponent()����
		super.paintComponent(g);// ����JPanel���paintComponent()����
		Image image = imageIcon.getImage();// ͨ��ImageIcon������Image����
		g.drawImage(image, 0, 0, null);// ����Image���󣬼���ͼƬ���Ƶ������
	}
}
