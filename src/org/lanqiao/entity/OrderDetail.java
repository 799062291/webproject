package org.lanqiao.entity;

public class OrderDetail {
    private String orderdetailid;
    private String gtitle;
    private double gsaleprice;
    private String gid;
    private int gnumber;
    private String orderid;
    public String getOrderdetailid() {
		return orderdetailid;
	}
	public void setOrderdetailid(String orderdetailid) {
		this.orderdetailid = orderdetailid;
	}
	public String getGtitle() {
		return gtitle;
	}
	public void setGtitle(String gtitle) {
		this.gtitle = gtitle;
	}
	public double getGsaleprice() {
		return gsaleprice;
	}
	public void setGsaleprice(double gsaleprice) {
		this.gsaleprice = gsaleprice;
	}
	public int getGnumber() {
		return gnumber;
	}
	public void setGnumber(int gnumber) {
		this.gnumber = gnumber;
	}
	public String getOrderid() {
		return orderid;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public OrderDetail(String orderdetailid, String gtitle, double gsaleprice,
			String gid, int gnumber, String orderid) {
		super();
		this.orderdetailid = orderdetailid;
		this.gtitle = gtitle;
		this.gsaleprice = gsaleprice;
		this.gid = gid;
		this.gnumber = gnumber;
		this.orderid = orderid;
	}
	public OrderDetail() {
		super();
	}
	
	
}
