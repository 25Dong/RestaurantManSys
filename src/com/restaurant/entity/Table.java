package com.restaurant.entity;

public class Table {
    String zhuohao;
    String zhuoming;
    String zhuotaizhuangtai;
    String zhuotaileixing;
    String zhuotaiweizhi;
    String kaitaishijian;
    String jieshushijian;
    String beizhu;
    
    
    Table(){}
    
    Table(String a,String b,String c, String d,String e,String f,String g,String h){
        this.zhuohao=a;
        this.zhuoming=b;
        this.zhuotaizhuangtai=c;
        this.zhuotaileixing=d;
        this.zhuotaiweizhi=e;
        this.kaitaishijian=f;
        this.jieshushijian=g;
        this.beizhu=h;
    }
    
    public String getZhuohao() {
		return zhuohao;
	}

	public void setZhuohao(String zhuohao) {
		this.zhuohao = zhuohao;
	}

	public String getZhuoming() {
		return zhuoming;
	}

	public void setZhuoming(String zhuoming) {
		this.zhuoming = zhuoming;
	}

	public String getZhuotaizhuangtai() {
		return zhuotaizhuangtai;
	}

	public void setZhuotaizhuangtai(String zhuotaizhuangtai) {
		this.zhuotaizhuangtai = zhuotaizhuangtai;
	}

	public String getZhuotaileixing() {
		return zhuotaileixing;
	}

	public void setZhuotaileixing(String zhuotaileixing) {
		this.zhuotaileixing = zhuotaileixing;
	}

	public String getZhuotaiweizhi() {
		return zhuotaiweizhi;
	}

	public void setZhuotaiweizhi(String zhuotaiweizhi) {
		this.zhuotaiweizhi = zhuotaiweizhi;
	}

	public String getKaitaishijian() {
		return kaitaishijian;
	}

	public void setKaitaishijian(String kaitaishijian) {
		this.kaitaishijian = kaitaishijian;
	}

	public String getJieshushijian() {
		return jieshushijian;
	}

	public void setJieshushijian(String jieshushijian) {
		this.jieshushijian = jieshushijian;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public void printInfo(){
        System.out.println(zhuohao+"\t"+zhuoming+"\t"+zhuotaizhuangtai+"\t"+zhuotaileixing+"\t"+zhuotaiweizhi+"\t"+kaitaishijian+"\t"+jieshushijian+"\t"+beizhu);   
    }
    
}
