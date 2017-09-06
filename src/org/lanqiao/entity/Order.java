package org.lanqiao.entity;

import java.util.Date;

public class Order {
    private String orderid;
    private String uesrid;

	private double totalprice;
	private Date orderDate;
    public Order() {
		super();
	}
	
	public Order(String orderid, String uesrid, double totalprice,
			Date orderDate) {
		super();
		this.orderid = orderid;
		this.uesrid = uesrid;
		this.totalprice = totalprice;
		this.orderDate = orderDate;
	}

	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getUesrid() {
		return uesrid;
	}
	public void setUesrid(String uesrid) {
		this.uesrid = uesrid;
	}

	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public void setTotalprice(double totalprice) {
		this.totalprice = totalprice;
	}

}
