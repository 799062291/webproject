package org.lanqiao.entity;

public class KeyWord {

	 private String key;
	 private String desc;
	 
	public KeyWord() {
		super();
	}
	public KeyWord(String key, String desc) {
		super();
		this.key = key;
		this.desc = desc;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
