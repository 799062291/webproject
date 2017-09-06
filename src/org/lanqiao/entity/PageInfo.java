package org.lanqiao.entity;

import java.util.ArrayList;
import java.util.List;

public class PageInfo<T> {
	   private int pageSize;  //每页记录数
	   private int pageIndex;  //当前页码
	   private int totalNumber; //总记录数；
	   private List<T> datas = new ArrayList<T>(); //存放当前页的表格数据
	   //以下三个参数仅需内部算出，不需要有set方法：
	   private int totalPages; //总页数；
	   private String type;    //书籍种类
	
	   private boolean isFirstPage; //是否是第一页；
	   private boolean isLastPage;  //是否是最后一页；

	   
	     public boolean getIsFirstPage() {
			return isFirstPage;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public void setIsFirstPage(boolean isFirstPage) {
			this.isFirstPage = isFirstPage;
		}

		public boolean getIsLastPage() {
			return isLastPage;
		}

		public void setIsLastPage(boolean isLastPage) {
			this.isLastPage = isLastPage;
		}

		public void setTotalPages(int number){
			this.totalPages = number;
		}
		public int getTotalPages(){ //得到总页数；
	    	// totalPages = this.totalNumber % this.pageSize == 0? this.totalNumber /this.pageSize:(this.totalNumber /this.pageSize)+1;
	    	 return totalPages;
	     }
	     
	     public int getTotalNumber(){  //得到总记录数
	    	 return this.totalNumber;
	     }
	     
	     public void setTotalNumber(int totalNumber){ //设置总记录数；
	    	 this.totalNumber = totalNumber;
	     }

		 public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		public int getPageIndex() { //得到当前页；
			 return pageIndex;
		 }

		 public void setPageIndex(int pageIndex) { //设置当前页；
			 this.pageIndex = pageIndex;
		 }

		 public List<T> getDatas() {
			 return datas;
		 }

		 public void setDatas(List<T> datas) {
			 this.datas = datas;
		 }
		 
	     
}
