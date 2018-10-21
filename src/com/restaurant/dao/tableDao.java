package com.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.restaurant.db.JDBC;

public class tableDao {

	static Connection conn = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static JDBC db = new JDBC();


	//--��ѯ���ӱ���ȡ��άVector����
	public static Vector queryVectorZhuozi(String sql){
		conn = db.getConnection();
		Vector data=new Vector();
		try{
			//--2 ��ûỰ����
			stmt=conn.createStatement();
			//--3 ִ�в�ѯSQL���
			rs=stmt.executeQuery(sql);
			while(rs.next()){//�Ƿ�����һ����¼
				Vector line=new Vector();
				for(int i=1; i<=8; i++){
					line.add(rs.getString(i));
				}
				data.add(line);
			}
			rs.close();
			stmt.close();
			System.out.println("��������ģ�顪����ѯ������Ϣ");
			return data; 
		}catch(SQLException ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	// �������ӱ�
	public static boolean updateZhuozi(String sql){
		//--1 ��������
		conn = db.getConnection();
		//����ӿ�
		//  Statement stmt=null;
		try{
			//�����Ự����
			stmt=conn.createStatement();
			//ִ��һ�������SQL���,����Ӱ�������
			int r=stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
			System.out.println("��������ģ�顪������������Ϣ");
			if(r>0){
				return true;
			}else{
				return false;
			}
		}catch(SQLException ex){
			ex.printStackTrace();
			return false;
		}
	}
}
