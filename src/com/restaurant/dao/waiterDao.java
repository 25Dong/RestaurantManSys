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

	//��ѯ����Ա��ȫ����Ϣ
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
			System.out.println("����Ա����ģ�顪��ִ�в�ѯȫ������Ա����Ϣ�Ĳ���");
			return data;
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			return null;
		}
	}

	//���ӷ���Ա
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
				System.out.println("����Ա����ģ�顪��ִ�������Ա����Ϣ�Ĳ����ɹ�");
			}else{
				System.err.println("����Ա����ģ�顪��ִ�������Ա����Ϣ�Ĳ���ʧ��");
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

	//���ݱ��ɾ������Ա
	public static void delete(String waiterid){
		String sql = "delete from waiter where waiterno='"+waiterid+"'";
		try {
			conn = db.getConnection();
			stmt = conn.createStatement();
			int rs = stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
			if(rs>0){
				System.out.println("����Ա����ģ�顪��������Ա�ĵĲ����ɹ�");
			}else{
				System.out.println("����Ա����ģ�顪��ɾ������Ա�ĵĲ���ʧ��");
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

	//���±�
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
				System.out.println("����Ա����ģ�顪������ִ���·���Ա��Ϣ�ĵĲ���");
			}else{
				System.err.println("����Ա����ģ�顪�����·���Ա��Ϣ�ĵĲ���ʧ��");
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

	//���ݷ���Ա�ı�Ų��ҷ���Ա
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
			System.out.println("����Ա����ģ�顪������ִ���ݱ�Ų��ҷ���Ա��Ϣ�ĵĲ���");
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
