package com.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.restaurant.db.JDBC;

/*
 * ����ȫ
 */

public class salesDao {
	static Connection conn = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static JDBC db = new JDBC();
	
	public static Vector  menuFoodNo(){
		Vector data = new Vector();
		String sql="select * from menu";
		conn=db.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Vector line = new Vector();
				for(int i = 1;i<=3;i++){
					line.add(rs.getObject(i));
				}
				data.add(line);
			}
			System.out.println("����ģ�顪����ȡ�˵���ȫ���˺�����ִ��");
			rs.close();
			stmt.close();
			conn.close();
			return data;
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			return null;
		}
	}

	
}
