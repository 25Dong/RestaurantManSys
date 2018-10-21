package com.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.restaurant.db.JDBC;
import com.restaurant.entity.Waiter;

public class waiterDao {

	static Connection conn = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static JDBC db = new JDBC();

	//查询服务员的全部信息
	public static Vector  waiterInfor(){
		Vector data = new Vector();
		String sql="select * from waiter";
		conn=db.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Vector line = new Vector();
				for(int i = 1;i<=7;i++){
					line.add(rs.getObject(i));
				}
				data.add(line);
			}
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("服务员管理模块——执行查询全部服务员的信息的操作");
			return data;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
	}

	//增加服务员
	public static  void insert(Waiter waiter1){
		String sql = "insert into waiter values(?,?,?,?,?,?,?)";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, waiter1.getWaiterno());
			ps.setString(2, waiter1.getWname());
			ps.setString(3, waiter1.getWsex());
			ps.setString(4, waiter1.getWid());
			ps.setString( 5, waiter1.getWphone());
			ps.setInt(6, waiter1.getWnomey());
			ps.setInt(7, waiter1.getWage());
			int rs = ps.executeUpdate();

			ps.close();
			conn.close();
			if(rs>0){
				System.out.println("服务员管理模块——执插入服务员的信息的操作成功");
			}else{
				System.err.println("服务员管理模块——执插入服务员的信息的操作失败");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	//根据编号删除服务员
	public static void delete(String waiterid){
		String sql = "delete from waiter where waiterno='"+waiterid+"'";
		try {
			conn = db.getConnection();
			stmt = conn.createStatement();
			int rs = stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
			if(rs>0){
				System.out.println("服务员管理模块——除服务员的的操作成功");
			}else{
				System.out.println("服务员管理模块——删除服务员的的操作失败");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	//更新表
	public static void update(Waiter waiter1){
		String sql = "update waiter set name=?,sex=?,id=?,number=?,money=?,age=? where waiterno=?";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);//

			ps.setString(1, waiter1.getWname());
			ps.setString(2, waiter1.getWsex());
			ps.setString(3, waiter1.getWid());
			ps.setString( 4, waiter1.getWphone());
			ps.setInt(5, waiter1.getWnomey());
			ps.setInt(6, waiter1.getWage());
			ps.setString(7, waiter1.getWaiterno());

			int rows = ps.executeUpdate();
			ps.close();
			conn.close();
			if(rows>0){
				System.out.println("服务员管理模块——正在执更新服务员信息的的操作");
			}else{
				System.err.println("服务员管理模块——更新服务员信息的的操作失败");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	//根据服务员的编号查找服务员
	public static  Vector findByWaiterNo(String wid){
		Vector line  = new Vector();
		String sql = "select * from waiter where waiterno=?";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,wid);
			rs = ps.executeQuery();
			while(rs.next()){
				for(int i = 1;i<=7;i++){
					line.add(rs.getObject(i));
				}
			}
			System.out.println("服务员管理模块——正在执根据编号查找服务员信息的的操作");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally{
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return line;
	}
}
