package com.restaurant.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.restaurant.entity.User;

public class JDBC {
	 //�������ݿ����ӷ���
    public static Connection getConnection(){
        try{
        //-- 2 ������������
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // --3 ��������
            String url="jdbc:sqlserver://localhost:1433; databaseName=StudentDB";
            Connection conn = DriverManager.getConnection(url,"sa","dong");
            System.out.print("���ݿ����ӳɹ�!       ");
            return conn;
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
            System.out.println("���ݿ����������Ҳ���!");
            return null;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("���ݿ�����ʧ��!");
            return null;
        }
    }
    
   

}
