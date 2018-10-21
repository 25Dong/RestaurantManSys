package com.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.restaurant.db.JDBC;

/*
 * 许遂全
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
			System.out.println("销售模块——获取菜单的全部菜号正在执行");
			rs.close();
			stmt.close();
			conn.close();
			return data;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
	}

	
}
