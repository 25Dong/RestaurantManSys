package com.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import com.restaurant.db.JDBC;
import com.restaurant.entity.Meun;
import com.restaurant.entity.Waiter;
import javax.swing.JOptionPane;
/*
 * 梁嘉健
 * 这个类主要提供一些用户在点餐界面点下按钮后的一些方法。
 */
public class meun {

	static Connection conn = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static JDBC db = new JDBC();

	//改类使用的到两个表为：点餐 表test_ 和 订单表:dingcan
	//这是一个查询点餐表test_所有的信息的方法。
	public static Vector meun(String a){
		conn = db.getConnection();
		Vector data = new Vector();
		try {
			ResultSet res = null;
			PreparedStatement pst = conn.prepareStatement("select * from test_ ");

			res = pst.executeQuery();
			while (res.next()) {
				Vector line = new Vector();
				for (int i = 1; i <= 7; i++) {
					line.add(res.getObject(i));
				}
				data.add(line);

			}
			System.out.println("点餐模块——执行了查询点餐表test的所有信息的操作");
			res.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "查询失败！");
		}
		return data;
	}

	/*//这是计算顾客用餐总价的方法，然后以表格形式展示出来。
	public static Vector meunn(String a){
		//Conn link = new Conn();
		// con = link.getConn();
		conn = db.getConnection();
		Vector data = new Vector();
		try {
			ResultSet res = null;
			PreparedStatement pst = conn.prepareStatement("select SUM(b.a) from (select money*number as a from test_) as b ");

			res = pst.executeQuery();
			while (res.next()) {
				Vector line = new Vector();
				int i = 1;
				line.add(res.getObject(i));
				data.add(line);

			}
			res.close();
			pst.close();
			conn.close();
			System.out.println("执行了查询点餐表test的计算一个点餐菜品的总价的操作（test保持只有一数据）");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "查询失败！");
		}

		return data;

	}*/

	//这是一个查询菜单表的方法。
	public static Vector meun(int a){
		conn = db.getConnection();
		Vector data = new Vector();
		try {
			ResultSet res = null;
			PreparedStatement pst = conn.prepareStatement("select * from menu ");
			res = pst.executeQuery();
			while (res.next()) {
				Vector line = new Vector();
				for (int i = 1; i <= 3; i++) {
					line.add(res.getObject(i));
				}
				data.add(line);
			}
			res.close();
			pst.close();
			conn.close();
			System.out.println("点餐模块——执行了查询菜单表的所有信息的操作");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "操作失败！");
		}

		return data;
	}

	//这是一个把用户点餐信息插入到test——表的方法。
	public static boolean sales_(String foodno,String foodname,String number,String money,String  tableno,
			String date,int no){
		conn = db.getConnection();
		int money1 = Integer.parseInt(money);
		try {
			PreparedStatement pstm = conn.prepareStatement("insert into test_ values(?,?,?,?,?,?,?)");
			pstm.setString(1, foodno);
			pstm.setString(2, foodname);
			pstm.setString(3, number);
			pstm.setInt(4, money1);
			pstm.setString(5, tableno);
			pstm.setString(6, date);
			pstm.setInt(7, no);
			int r = pstm.executeUpdate();
			pstm.close();
			conn.close();
			System.out.println("点餐模块——执行了点击添加按钮时用户点餐信息插入到test_表：");
			if (r > 0) {
				return true;
			} else {
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}
	
	//这是一个用户按点餐按钮后把订单插到订单表中的方法。
	public static boolean order_(String foodno,String foodname,String number,String money,String tableno,String date,int menuno,String pay){
		conn = db.getConnection();
		try {
			PreparedStatement pstm = conn.prepareStatement("insert into dingcan values(?,?,?,?,?,?,?,?)");
			pstm.setString(3, foodno);
			pstm.setString(4, foodname);
			pstm.setString(5, number);
			pstm.setString(6, money);
			pstm.setString(2, tableno);
			pstm.setString(7, date);
			pstm.setInt(1, menuno);
			pstm.setString(8, pay);

			int r = pstm.executeUpdate();
			pstm.close();
			conn.close();
			System.out.println("点菜模块——执行了往订单表digncan插入信息");
			if (r > 0) {
				return true;
			} else {
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}
	
	//这是一个清空点餐表的方法。
	public static boolean Del_list(){
		conn = db.getConnection();
		try {

			PreparedStatement pstm = conn.prepareStatement(
					"delete  from test_ ");
			int r = pstm.executeUpdate();
			pstm.close();
			conn.close();
			System.out.println("点菜模块——执行了清空test表的所有信息的操作");
			if (r > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}
	}

	//这是一个在点餐表中删除某个菜的方法。——点击退餐时调用
	public static boolean Del_select(String no){
		conn = db.getConnection();
		try {
			PreparedStatement pstm = conn.prepareStatement(
					"delete  from test_ where food_no = ?");
			pstm.setString(1, no);
			int r = pstm.executeUpdate();
			pstm.close();
			conn.close();
			if (r > 0) {
				System.out.println("点餐模块——执行了在test表退餐的操作成功");
				return true;
			} else {
				System.err.println("点餐模块——执行了在test表退餐的操作失败");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}
	}
	
	//这是一个把订餐表中没付款改成付款的方法
	/*public static boolean pay(String pay,int menuno){
		conn = db.getConnection();
		try{
			PreparedStatement pstm = conn.prepareStatement("update orderdata set pay = ? where menuno = ?");
			pstm.setString(1, pay);
			pstm.setInt(2, menuno);
			int r = pstm.executeUpdate();

			pstm.close();
			conn.close();
			if(r>0){
				return true;
			}else{
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}

	}*/

	//***********************************************易承东********************
	public static  Vector MenuTableNo(String tno,String time){
		Vector data = new Vector();

		String sql = "select * from dingcan where tableNO=? and date=?";//依据桌号和时间来查询
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,tno);
			ps.setString(2,time);
			rs = ps.executeQuery();

			while(rs.next()){
				Vector line  = new Vector();//注意第一维的line要放在里面
				for(int i = 1;i<=8;i++){
					line.add(rs.getObject(i));

				}
				data.add(line);
			}
			System.out.println("点菜模块——正在执行根剧桌号查询整个点餐表的所有信息；");
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
		return data;
	}
	
	//查询test-表
	public static Vector  TestInfor(){
		Vector data = new Vector();
		Vector line = new Vector();
		String sql="select * from test_";
		conn=db.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println("查询整个test_表的时候返回的记录是："+rs+"  条");

			while(rs.next()){
				line.add(rs.getObject(7));
				line.add(rs.getObject(5));
				line.add(rs.getObject(1));
				line.add(rs.getObject(2));
				line.add(rs.getObject(3));
				line.add(rs.getObject(4));
				line.add(rs.getObject(6));
			}
			rs.close();
			stmt.close();
			conn.close();
			return line;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
	}

	//插入菜单表meun-易承东
	public static  void insert(Meun m){
		String sql = "insert into menu values(?,?,?)";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, m.getFoodNo());
			ps.setString(2, m.getFoodName());
			ps.setInt(3, m.getMoney());

			ps.executeUpdate();

			System.out.println("执行了把一条新的信息插入meun表格的操作");
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

	//根据编号菜品
	public static void delete(String foodid){
		String sql = "delete from menu where foodno='"+foodid+"'";
		try {
			conn = db.getConnection();
			stmt = conn.createStatement();
			int rs = stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
			if(rs>0){
				System.out.println("管理菜单模块——正在执删除菜品的的操作");
			}else{
				System.out.println("管理菜单模块——删除菜品的的操作失败");
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

	//修改表
	public static void update(Meun m2) {
		// TODO 自动生成的方法存根
		String sql = "update menu set foodno=?,foodname=?,price=?";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, m2.getFoodNo());
			ps.setString(2, m2.getFoodName());
			ps.setInt(3, m2.getMoney());

			System.out.println("执行了修改menu表的操作");
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

	//取订餐表格最大的下单号
	public static int maxMeunNO(){
		int  data =0;
		int max =0;
		String sql="select * from dingcan";
		conn=db.getConnection();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				data = (int) rs.getObject(1);//取订餐表中目前最大的下单号
				if(max < data){
					max = data;
				}
			}
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("执行查询订餐表的最大的订单号  是："+max);
			return max;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	//取订餐表格最大的菜号
	public static int maxFoodNO(){
		int  data =0;
		int max =0;
		String sql="select * from menu";
		conn=db.getConnection();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				String data1 =  (String) rs.getObject(1);//取订餐表中目前最大的下菜号
				 data = Integer.parseInt(data1);
				if(max < data){
					max = data;
				}
			}

			rs.close();
			stmt.close();
			conn.close();
			System.out.println("执行查询订餐表的最大的菜号  是："+max);
			return max;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	//取test—表中的订餐号
	public static int TestMeunNO(){
		int  data =0;
		int max =0;
		String sql="select * from dingcan";
		conn=db.getConnection();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				data = (int) rs.getObject(7);

			}
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("执行查询菜单表text_的得到订单号  是："+data);
			return data;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return 0;
		}
	}

	//结账时调用
	public static  Vector MenuTable(String no){
		Vector data = new Vector();
		int no1 = Integer.parseInt(no);
		String sql = "select * from dingcan where menuNo=?";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1,no1);
			rs = ps.executeQuery();

			while(rs.next()){
				Vector line  = new Vector();//注意第一维的line要放在里面
				for(int i = 1;i<=1;i++){
					//line.add(rs.getObject(i));
					line.add(rs.getObject(3));
					line.add(rs.getObject(4));
					line.add(rs.getObject(5));
					line.add(rs.getObject(6));
				}
				data.add(line);
			}

			System.out.println("结账模块——正在执行查询信息");

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
		return data;
	}

	//显示没有付款的订单全部信息
	public static Vector firstData(String pay){
		Vector data =new Vector();
		String sql = "select * from dingcan where pay=?";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,pay);
			rs = ps.executeQuery();

			while(rs.next()){
				Vector line = new Vector();

				for(int i = 1;i<=8;i++){
					line.add(rs.getObject(i));
				}
				data.add(line);
			}


			System.out.println("主界面模块——正在执行查询全部没有付款的订单");
			rs.close();
			ps.close();

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
		return data;
	}


	//根据点单号和菜号删除点餐信息——点击取消订单是调用
	public static void deleDC(String meNo,String tableNo){
		int menuNo = Integer.parseInt(meNo);
		String sql = "delete from dingcan where menuNo=? and  tableNO=?";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1,menuNo);
			ps.setString(2, tableNo);
			int rows = ps.executeUpdate();
			System.out.println("主界面模块——删除点单正在执行");
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

}
