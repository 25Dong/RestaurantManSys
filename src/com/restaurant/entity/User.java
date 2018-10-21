//这是一个登录用户类
package com.restaurant.entity;

public class User {
	
	private String Uname;
	private String password;
	private static String job;
	
	
	
	
	
	public User(String uname, String password) {
		super();
		Uname = uname;
		this.password = password;
	}
	public String getUname() {
		return Uname;
	}
	public void setUname(String uname) {
		Uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static  String getJob() {
		return job;
	}
	public static void setJob(String job1) {
		job = job1;
	}
	
	
	


}
