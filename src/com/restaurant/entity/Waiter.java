package com.restaurant.entity;

public class Waiter {
	private String waiterno;
	private String wname;
	private String wphone;
	private String wsex;
	private String wid;
	private int wage;
	private int wnomey;
	
	
	public Waiter(String waiterno, String wname, String wsex, String wid,
			int wage, int wnomey,String wphone) {
		super();
		this.waiterno = waiterno;
		this.wname = wname;
		this.wsex = wsex;
		this.wid = wid;
		this.wage = wage;
		this.wnomey = wnomey;
		this.wphone=wphone;
	}
	public Waiter(){}
	public String getWaiterno() {
		return waiterno;
	}
	public void setWaiterno(String waiterno) {
		this.waiterno = waiterno;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public String getWphone() {
		return wphone;
	}
	public void setWphone(String wphone) {
		this.wphone = wphone;
	}
	public String getWsex() {
		return wsex;
	}
	public void setWsex(String wsex) {
		this.wsex = wsex;
	}
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public int getWage() {
		return wage;
	}
	public void setWage(int string) {
		this.wage = string;
	}
	public int getWnomey() {
		return wnomey;
	}
	public void setWnomey(int string) {
		this.wnomey = string;
	}
	
	
	
	
	

}
