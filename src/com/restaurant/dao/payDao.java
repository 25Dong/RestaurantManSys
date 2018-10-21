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

	//往账单表插入信息（账单号，该账单的总收入金额，该账单的总销售数量，时间）
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
			System.out.println("执行了往数据库的账单表pay插入信息的操作");
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
	
	//找到结账表中目前最大的结账号
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
			System.out.println("执行查询菜单表pay得到的上一次的最大账单号 是："+max);
			return max;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return 0;
		}
	}
	
		//结账后把订餐表的付款情况改为已经已经付款。
		public static void update(String no){
			int meunno = Integer.parseInt(no);
			System.out.println(meunno);
			String sql = "update dingcan set pay=? where menuNo=?";
			try {
				conn = db.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, "是");
				ps.setInt(2, meunno);
				 System.out.println("结账模块正在执行修改订餐的的付款情况！");
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
		
		//更新桌子的转态：结账时把桌子的状态改为空闲,同时设置结束时间
		public static boolean updateZhuozi(String zhuohao,String time){
		    	conn = db.getConnection();
		    	String zhuotaizhuangtai = "空闲";
		    	String tableNo = zhuohao.substring(0, 3);
		    	String sql="update zhuozi set zhuotaizhuangtai='"+zhuotaizhuangtai+"',jieshushijian ='"+time+"' where zhuohao='"+tableNo+"'";
		    	System.out.println(sql);
		       try{
		           stmt=conn.createStatement();
		           int r=stmt.executeUpdate(sql);
		           System.out.println("结账模块正在执行修改桌子的状态和和结束时间！");
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
