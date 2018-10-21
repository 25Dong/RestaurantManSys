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


	//--查询桌子表，获取二维Vector数据
	public static Vector queryVectorZhuozi(String sql){
		conn = db.getConnection();
		Vector data=new Vector();
		try{
			//--2 获得会话对象
			stmt=conn.createStatement();
			//--3 执行查询SQL语句
			rs=stmt.executeQuery(sql);
			while(rs.next()){//是否还有下一条记录
				Vector line=new Vector();
				for(int i=1; i<=8; i++){
					line.add(rs.getString(i));
				}
				data.add(line);
			}
			rs.close();
			stmt.close();
			System.out.println("餐桌管理模块——查询桌子信息");
			return data; 
		}catch(SQLException ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	// 更新桌子表
	public static boolean updateZhuozi(String sql){
		//--1 创建链接
		conn = db.getConnection();
		//定义接口
		//  Statement stmt=null;
		try{
			//创建会话对象
			stmt=conn.createStatement();
			//执行一条插入的SQL语句,返回影响的行数
			int r=stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
			System.out.println("餐桌管理模块——更新桌子信息");
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
