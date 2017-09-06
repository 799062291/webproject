package org.lanqiao.entity;

public class Goods {

	 private String gid;
	 private String gtitle;
	 private String gauthor;
	 private double gsaleprice;
	 private double ginprice;
	 private String gdesc;
	 private String gimg;  //图片--数据库中存放图片名称；
	 private int gclick;
	 private String pulish;  //出版社名称
	 public String getPulish() {
		return pulish;
	}
	public void setPulish(String pulish) {
		this.pulish = pulish;
	}
	private String cid;
	 private String pid;
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getGtitle() {
		return gtitle;
	}
	public void setGtitle(String gtitle) {
		this.gtitle = gtitle;
	}
	public String getGauthor() {
		return gauthor;
	}
	public void setGauthor(String gauthor) {
		this.gauthor = gauthor;
	}
	public double getGsaleprice() {
		return gsaleprice;
	}
	public void setGsaleprice(double gsaleprice) {
		this.gsaleprice = gsaleprice;
	}
	public double getGinprice() {
		return ginprice;
	}
	public void setGinprice(double ginprice) {
		this.ginprice = ginprice;
	}
	public String getGdesc() {
		return gdesc;
	}
	public void setGdesc(String gdesc) {
		this.gdesc = gdesc;
	}
	public String getGimg() {
		return gimg;
	}
	public void setGimg(String gimg) {
		this.gimg = gimg;
	}
	public int getGclick() {
		return gclick;
	}
	public void setGclick(int gclick) {
		this.gclick = gclick;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Goods(String gid, String gtitle, String gauthor, double gsaleprice,
			double ginprice, String gdesc, String gimg, int gclick, String cid,
			String pid) {
		super();
		this.gid = gid;
		this.gtitle = gtitle;
		this.gauthor = gauthor;
		this.gsaleprice = gsaleprice;
		this.ginprice = ginprice;
		this.gdesc = gdesc;
		this.gimg = gimg;
		this.gclick = gclick;
		this.cid = cid;
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "Goods [gid=" + gid + ", gtitle=" + gtitle + ", gauthor="
				+ gauthor + ", gsaleprice=" + gsaleprice + ", ginprice="
				+ ginprice + ", gdesc=" + gdesc + ", gimg=" + gimg
				+ ", gclick=" + gclick + ", pulish=" + pulish + ", cid=" + cid
				+ ", pid=" + pid + "]";
	}
	 
	 
}
