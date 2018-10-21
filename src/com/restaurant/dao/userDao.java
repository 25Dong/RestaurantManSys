package com.restaurant.dao;

import java.sql.*;
import java.util.Vector;

import com.restaurant.db.JDBC;

public class userDao {
	
	static Connection conn = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static JDBC db = new JDBC();
	
	public static Vector  userInfor(){
		Vector data = new Vector();
		String sql="select * from Tuser";
		
		conn=db.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println("查询整个Tuser表的时候返回的记录是："+rs+"  条");
			
			while(rs.next()){
				Vector line = new Vector();
				for(int i = 1;i<=2;i++){
					line.add(rs.getObject(i));
				}
				data.add(line);
				
				
			}
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
	
	//点击登录界面的登录按钮时
    public static Vector prepareQueryDate(String name,String pw){
    	conn = db.getConnection();
        //Vector<Vector> data = new Vector<Vector>();
        Vector line = new Vector();
        try{
            ResultSet res = null;
            PreparedStatement pst = conn.prepareStatement("select * from Tuser where uname = ? and password = ?");
            pst.setString(1, name);
            pst.setString(2, pw);
            
            res = pst.executeQuery();
            System.out.println(res);
       
           while(res.next()){
                
                for(int i = 1;i<=3;i++){
                    line.add(res.getObject(i));
                }
               // data.add(line);
                //System.out.println("lll" + data);
            }
            
            
            res.close();
            pst.close();
            conn.close();
        }catch(SQLException e){
        	System.out.println("由于常");
            e.printStackTrace();
        }
        return line;
        
    }
    
    
    //--点击注册界面的注册按钮
    public static boolean updateUser(String sql){
        //--1 创建链接
    	conn = db.getConnection();
        //定义接口
        Statement stmt=null;
        try{
            //创建会话对象
            stmt=conn.createStatement();
            //执行一条插入的SQL语句,返回影响的行数
            int r=stmt.executeUpdate(sql);
            if(r>0){
            	System.out.println("影响的行数是"+r);
                return true;
            }else{
            	System.err.println("没有成功修改任何的行数");
                return false;
            }

        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
	
    public static boolean isLogin(String Uname,String Pword){
        //--1 创建链接
    	conn = db.getConnection();
        //定义接口
        Statement stmt=null;
        try{
            //创建会话对象
            stmt=conn.createStatement();
            rs = stmt.executeQuery("select * from Tuser");
            while(rs.next()){
            	String uname = (rs.getObject(1).toString());
            	String pword = (rs.getObject(2).toString());
            	if(Uname.equals(uname.trim())&&Pword.equals(pword.trim())){
            		return true;
            	}
            }
        rs.close();
        stmt.close();
        conn.close();
        return false;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    
	
	

}
