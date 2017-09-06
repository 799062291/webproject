package org.lanqiao.entity;

public class User {
     private String userid;
     private String uemail;
     private String uname;
     private String upassword;
     private String usex;
     private String utel;
     private String uaddress;
     private String uroleid;
     private String ustateid;
     
	public User() {
		super();
	}
	public User(String userid, String uemail, String uname, String upassword,
			String usex, String utel, String uaddress, String uroleid,
			String ustateid) {
		super();
		this.userid = userid;
		this.uemail = uemail;
		this.uname = uname;
		this.upassword = upassword;
		this.usex = usex;
		this.utel = utel;
		this.uaddress = uaddress;
		this.uroleid = uroleid;
		this.ustateid = ustateid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getUsex() {
		return usex;
	}
	public void setUsex(String usex) {
		this.usex = usex;
	}
	public String getUtel() {
		return utel;
	}
	public void setUtel(String utel) {
		this.utel = utel;
	}
	public String getUaddress() {
		return uaddress;
	}
	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}
	public String getUroleid() {
		return uroleid;
	}
	public void setUroleid(String uroleid) {
		this.uroleid = uroleid;
	}
	public String getUstateid() {
		return ustateid;
	}
	public void setUstateid(String ustateid) {
		this.ustateid = ustateid;
	}
}
