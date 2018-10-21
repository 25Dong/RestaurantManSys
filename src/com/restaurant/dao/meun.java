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
 * ���ν�
 * �������Ҫ�ṩһЩ�û��ڵ�ͽ�����°�ť���һЩ������
 */
public class meun {

	static Connection conn = null;
	static PreparedStatement ps = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	static JDBC db = new JDBC();

	//����ʹ�õĵ�������Ϊ����� ��test_ �� ������:dingcan
	//����һ����ѯ��ͱ�test_���е���Ϣ�ķ�����
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
			System.out.println("���ģ�顪��ִ���˲�ѯ��ͱ�test��������Ϣ�Ĳ���");
			res.close();
			pst.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "��ѯʧ�ܣ�");
		}
		return data;
	}

	/*//���Ǽ���˿��ò��ܼ۵ķ�����Ȼ���Ա����ʽչʾ������
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
			System.out.println("ִ���˲�ѯ��ͱ�test�ļ���һ����Ͳ�Ʒ���ܼ۵Ĳ�����test����ֻ��һ���ݣ�");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "��ѯʧ�ܣ�");
		}

		return data;

	}*/

	//����һ����ѯ�˵���ķ�����
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
			System.out.println("���ģ�顪��ִ���˲�ѯ�˵����������Ϣ�Ĳ���");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
		}

		return data;
	}

	//����һ�����û������Ϣ���뵽test������ķ�����
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
			System.out.println("���ģ�顪��ִ���˵����Ӱ�ťʱ�û������Ϣ���뵽test_��");
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
	
	//����һ���û�����Ͱ�ť��Ѷ����嵽�������еķ�����
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
			System.out.println("���ģ�顪��ִ������������digncan������Ϣ");
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
	
	//����һ����յ�ͱ�ķ�����
	public static boolean Del_list(){
		conn = db.getConnection();
		try {

			PreparedStatement pstm = conn.prepareStatement(
					"delete  from test_ ");
			int r = pstm.executeUpdate();
			pstm.close();
			conn.close();
			System.out.println("���ģ�顪��ִ�������test���������Ϣ�Ĳ���");
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

	//����һ���ڵ�ͱ���ɾ��ĳ���˵ķ�������������˲�ʱ����
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
				System.out.println("���ģ�顪��ִ������test���˲͵Ĳ����ɹ�");
				return true;
			} else {
				System.err.println("���ģ�顪��ִ������test���˲͵Ĳ���ʧ��");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}
	}
	
	//����һ���Ѷ��ͱ���û����ĳɸ���ķ���
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

	//***********************************************�׳ж�********************
	public static  Vector MenuTableNo(String tno,String time){
		Vector data = new Vector();

		String sql = "select * from dingcan where tableNO=? and date=?";//�������ź�ʱ������ѯ
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,tno);
			ps.setString(2,time);
			rs = ps.executeQuery();

			while(rs.next()){
				Vector line  = new Vector();//ע���һά��lineҪ��������
				for(int i = 1;i<=8;i++){
					line.add(rs.getObject(i));

				}
				data.add(line);
			}
			System.out.println("���ģ�顪������ִ�и������Ų�ѯ������ͱ��������Ϣ��");
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
	
	//��ѯtest-��
	public static Vector  TestInfor(){
		Vector data = new Vector();
		Vector line = new Vector();
		String sql="select * from test_";
		conn=db.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println("��ѯ����test_���ʱ�򷵻صļ�¼�ǣ�"+rs+"  ��");

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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			return null;
		}
	}

	//����˵���meun-�׳ж�
	public static  void insert(Meun m){
		String sql = "insert into menu values(?,?,?)";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, m.getFoodNo());
			ps.setString(2, m.getFoodName());
			ps.setInt(3, m.getMoney());

			ps.executeUpdate();

			System.out.println("ִ���˰�һ���µ���Ϣ����meun���Ĳ���");
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

	//���ݱ�Ų�Ʒ
	public static void delete(String foodid){
		String sql = "delete from menu where foodno='"+foodid+"'";
		try {
			conn = db.getConnection();
			stmt = conn.createStatement();
			int rs = stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
			if(rs>0){
				System.out.println("����˵�ģ�顪������ִɾ����Ʒ�ĵĲ���");
			}else{
				System.out.println("����˵�ģ�顪��ɾ����Ʒ�ĵĲ���ʧ��");
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

	//�޸ı�
	public static void update(Meun m2) {
		// TODO �Զ����ɵķ������
		String sql = "update menu set foodno=?,foodname=?,price=?";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, m2.getFoodNo());
			ps.setString(2, m2.getFoodName());
			ps.setInt(3, m2.getMoney());

			System.out.println("ִ�����޸�menu��Ĳ���");
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

	//ȡ���ͱ�������µ���
	public static int maxMeunNO(){
		int  data =0;
		int max =0;
		String sql="select * from dingcan";
		conn=db.getConnection();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				data = (int) rs.getObject(1);//ȡ���ͱ���Ŀǰ�����µ���
				if(max < data){
					max = data;
				}
			}
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("ִ�в�ѯ���ͱ�����Ķ�����  �ǣ�"+max);
			return max;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	//ȡ���ͱ�����Ĳ˺�
	public static int maxFoodNO(){
		int  data =0;
		int max =0;
		String sql="select * from menu";
		conn=db.getConnection();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				String data1 =  (String) rs.getObject(1);//ȡ���ͱ���Ŀǰ�����²˺�
				 data = Integer.parseInt(data1);
				if(max < data){
					max = data;
				}
			}

			rs.close();
			stmt.close();
			conn.close();
			System.out.println("ִ�в�ѯ���ͱ�����Ĳ˺�  �ǣ�"+max);
			return max;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	//ȡtest�����еĶ��ͺ�
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
			System.out.println("ִ�в�ѯ�˵���text_�ĵõ�������  �ǣ�"+data);
			return data;
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			return 0;
		}
	}

	//����ʱ����
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
				Vector line  = new Vector();//ע���һά��lineҪ��������
				for(int i = 1;i<=1;i++){
					//line.add(rs.getObject(i));
					line.add(rs.getObject(3));
					line.add(rs.getObject(4));
					line.add(rs.getObject(5));
					line.add(rs.getObject(6));
				}
				data.add(line);
			}

			System.out.println("����ģ�顪������ִ�в�ѯ��Ϣ");

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

	//��ʾû�и���Ķ���ȫ����Ϣ
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


			System.out.println("������ģ�顪������ִ�в�ѯȫ��û�и���Ķ���");
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


	//���ݵ㵥�źͲ˺�ɾ�������Ϣ�������ȡ�������ǵ���
	public static void deleDC(String meNo,String tableNo){
		int menuNo = Integer.parseInt(meNo);
		String sql = "delete from dingcan where menuNo=? and  tableNO=?";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1,menuNo);
			ps.setString(2, tableNo);
			int rows = ps.executeUpdate();
			System.out.println("������ģ�顪��ɾ���㵥����ִ��");
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
