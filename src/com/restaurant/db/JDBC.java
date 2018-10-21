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
	 //创建数据库连接方法
    public static Connection getConnection(){
        try{
        //-- 2 加载驱动程序
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // --3 创建链接
            String url="jdbc:sqlserver://localhost:1433; databaseName=StudentDB";
            Connection conn = DriverManager.getConnection(url,"sa","dong");
            System.out.print("数据库链接成功!       ");
            return conn;
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
            System.out.println("数据库驱动程序找不到!");
            return null;
        }catch(SQLException ex){
            ex.printStackTrace();
            System.out.println("数据库链接失败!");
            return null;
        }
    }
    
   

}
