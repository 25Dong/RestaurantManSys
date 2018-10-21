package com.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.restaurant.db.JDBC;
import com.restaurant.entity.Pay;
import com.restaurant.entity.Waiter;

public class payDao {
	static Connection conn = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static JDBC db = new JDBC();

	//���˵��������Ϣ���˵��ţ����˵�������������˵���������������ʱ�䣩
	public static  void insert(Pay pay){
		String sql = "insert into pay values(?,?,?,?,?)";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pay.getPayNo());
			ps.setString(2, pay.getMeunNO());
			ps.setInt(3, pay.getSumMenoy());
			ps.setInt(4, pay.getSumNumber());
			ps.setString(5,pay.getTime() );
			ps.executeUpdate();
			System.out.println("ִ���������ݿ���˵���pay������Ϣ�Ĳ���");
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
	
	//�ҵ����˱���Ŀǰ���Ľ��˺�
	public static int payNO(){
		int  data =0;
		int max =0;
		String sql="select * from pay";
		conn=db.getConnection();
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				data =  (int) rs.getObject(1);
				if(max < data){
					max = data;
				}
			}
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("ִ�в�ѯ�˵���pay�õ�����һ�ε�����˵��� �ǣ�"+max);
			return max;
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			return 0;
		}
	}
	
		//���˺�Ѷ��ͱ�ĸ��������Ϊ�Ѿ��Ѿ����
		public static void update(String no){
			int meunno = Integer.parseInt(no);
			System.out.println(meunno);
			String sql = "update dingcan set pay=? where menuNo=?";
			try {
				conn = db.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, "��");
				ps.setInt(2, meunno);
				 System.out.println("����ģ������ִ���޸Ķ��͵ĵĸ��������");
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
		
		//�������ӵ�ת̬������ʱ�����ӵ�״̬��Ϊ����,ͬʱ���ý���ʱ��
		public static boolean updateZhuozi(String zhuohao,String time){
		    	conn = db.getConnection();
		    	String zhuotaizhuangtai = "����";
		    	String tableNo = zhuohao.substring(0, 3);
		    	String sql="update zhuozi set zhuotaizhuangtai='"+zhuotaizhuangtai+"',jieshushijian ='"+time+"' where zhuohao='"+tableNo+"'";
		    	System.out.println(sql);
		       try{
		           stmt=conn.createStatement();
		           int r=stmt.executeUpdate(sql);
		           System.out.println("����ģ������ִ���޸����ӵ�״̬�ͺͽ���ʱ�䣡");
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
