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
			System.out.println("��ѯ����Tuser���ʱ�򷵻صļ�¼�ǣ�"+rs+"  ��");
			
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			return null;
		}
	}
	
	//�����¼����ĵ�¼��ťʱ
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
        	System.out.println("���ڳ�");
            e.printStackTrace();
        }
        return line;
        
    }
    
    
    //--���ע������ע�ᰴť
    public static boolean updateUser(String sql){
        //--1 ��������
    	conn = db.getConnection();
        //����ӿ�
        Statement stmt=null;
        try{
            //�����Ự����
            stmt=conn.createStatement();
            //ִ��һ�������SQL���,����Ӱ�������
            int r=stmt.executeUpdate(sql);
            if(r>0){
            	System.out.println("Ӱ���������"+r);
                return true;
            }else{
            	System.err.println("û�гɹ��޸��κε�����");
                return false;
            }

        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
	
    public static boolean isLogin(String Uname,String Pword){
        //--1 ��������
    	conn = db.getConnection();
        //����ӿ�
        Statement stmt=null;
        try{
            //�����Ự����
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
