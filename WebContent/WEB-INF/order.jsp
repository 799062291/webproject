<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta content="all" name="robots"/>
<meta name="author" content="Fisher" />
<meta name="Copyright" content="Copyright 2007-2008, 版权所有 www.reefdesign.cn" />
<meta name="description" content="reefdesign" />
<meta name="keywords" content="reefdesign" />
<title>电子书城</title>
<link rel="shortcut icon" href="favicon.ico" >
<link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />
</head>

<body class="main">
<%@include file="/WEB-INF/islogin.jsp"%>
<%@include file="/WEB-INF/header.jsp"%>


<div id="divpagecontent">
  <table width="100%" border="0" cellspacing="0">
    <tr>
      <td width="25%"><table width="100%" border="0" cellspacing="0" style="margin-top:30px">
        <tr>
          <td class="listtitle">配送方式、时间及费用</td>
        </tr>
        <tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="news.html">普通快递送货上门</a></td>
        </tr>
		
<tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.html">特快专递送货上门(EMS)</a></td>
        </tr>
<tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.html">申通快递送货上门</a></td>
        </tr>
<tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.html">普通邮寄</a></td>
        </tr>

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
      </table></td>
      <td><div style="text-align:right; margin:5px 10px 5px 0px"><a href="${pageContext.request.contextPath }/index.do">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/DispacherServlet.do?type=cart">&nbsp;购物车</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;订单</div>
        
		
		
		
		
        <table cellspacing="0" class="infocontent">
        <tr>
          <td><table width="100%" border="0" cellspacing="0">
              <tr>
                <td><img src="images/buy2.gif" width="635" height="38" />
                  <p>早上好：${user.uname }<c:if test="${user.usex=='男' }">先生</c:if><c:if test="${user.usex=='女' }">女士</c:if>！欢迎您来到商城结算中心</p></td>
              </tr>
              <tr>
                <td><table cellspacing="1" class="carttable">
                  <tr>
                    <td width="10%">序号</td>
                    <td width="40%">商品名称</td>
                    <td width="10%">市场价</td>
                    <td width="10%">优惠价</td>
                    <td width="10%">数量</td>
                    <td width="10%">小计</td>
                 
                  </tr>
                </table>
                   <c:if test="${cart!=null&&fn:length(cart)!=0 }">
                <c:forEach items="${cart }" var="item">
                 <table width="100%" border="0" cellspacing="0">
                 
                    <tr>
                      <td width="10%">${item.gid }</td>
                      <td width="40%">${item.gtitle }</td>
                      <td width="10%">${item.ginprice }</td>
                      <td width="10%">${item.gsaleprice }</td>
                      <td width="10%"><input name="text" type="text" value="${item.amount }" style="width:20px"/></td>
                      <td width="10%">${item.amount*item.gsaleprice }</td>
                      <c:set var="sum" value="${sum+item.amount*item.gsaleprice }"></c:set>
                     
                    </tr>
                  </table>
                
                </c:forEach>
               </c:if>
               <c:if test="${cart==null || fn:length(cart)==0 }">
                 <table width="100%" border="0" cellspacing="0">
                    <tr><td>当前购物车为空</td></tr>
                 </table>
               </c:if>
				 
				   <table cellspacing="1" class="carttable">
                     <tr>
                       <td style="text-align:right; padding-right:40px;"><font style="color:#FF6600; font-weight:bold">合计：&nbsp;&nbsp;<c:if test="${cart==null || fn:length(cart)==0 }">0</c:if><c:if test="${ cart!=null&&fn:length(cart)!=0 }">${sum }</c:if>元</font></td>
                      </tr>
                   </table> 
				   
				   <p>收货地址：<input name="text" type="text" value="${user.uaddress }" style="width:350px"/>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">【确认】</a><br/>
				   收货人：&nbsp;&nbsp;&nbsp;&nbsp;<input name="text" type="text" value="${user.uname }" style="width:150px"/>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">【确认】</a><br/>
				   联系方式：<input name="text" type="text" value="${user.utel }" style="width:150px"/>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">【确认】</a>
				   
				   </p>
				  <hr/> 
				   <p style="text-align:right"><a href="${pageContext.request.contextPath }/DispacherServlet.do?type=orderfinal"><img src="images/gif53_029.gif" width="204" height="51" border="0" /></a></p></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
    </tr>
  </table>
</div>



<div id="divfoot">
  <table width="100%" border="0" cellspacing="0">
    <tr>
      <td rowspan="2" style="width:10%"><img src="images/bottomlogo.gif" width="195" height="50" style="margin-left:175px"/></td>
      <td style="padding-top:5px; padding-left:50px"><a href="#"><font color="#747556"><b>CONTACT US</b></font></a></td>
    </tr>
    <tr>
      <td style="padding-left:50px"><font color="#CCCCCC"><b>COPYRIGHT 2008 &copy; eShop All Rights RESERVED.</b></font></td>
    </tr>
  </table>
</div>


</body>
</html>
