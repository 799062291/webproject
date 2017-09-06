package org.lanqiao.entity;

public class PasswordAnswer {
     private String answerid;
     private String aquestion;
     private String answer;
     private String email;
     private String userid;
	public String getAnswerid() {
		return answerid;
	}
	public void setAnswerid(String answerid) {
		this.answerid = answerid;
	}
	public String getAquestion() {
		return aquestion;
	}
	public void setAquestion(String aquestion) {
		this.aquestion = aquestion;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public PasswordAnswer() {
		super();
	}
	public PasswordAnswer(String answerid, String aquestion, String answer,
			String email, String userid) {
		super();
		this.answerid = answerid;
		this.aquestion = aquestion;
		this.answer = answer;
		this.email = email;
		this.userid = userid;
	}
     
}
