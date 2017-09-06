<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
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

 <%@include file="/WEB-INF/header.jsp" %>


<div id="divpagecontent">
  <table width="100%" border="0" cellspacing="0">
    <tr>
      <td width="25%"><table width="100%" border="0" cellspacing="0" style="margin-top:30px">
        <tr>
          <td class="listtitle">配送方式、时间及费用</td>
        </tr>
        <tr>
          <td class="listtd"><img src="${pageContext.request.contextPath }/images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="news.html">普通快递送货上门</a></td>
        </tr>
		
<tr>
          <td class="listtd"><img src="${pageContext.request.contextPath }/images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.html">特快专递送货上门(EMS)</a></td>
        </tr>
<tr>
          <td class="listtd"><img src="${pageContext.request.contextPath }/images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.html">申通快递送货上门</a></td>
        </tr>
<tr>
          <td class="listtd"><img src="${pageContext.request.contextPath }/images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
<a href="news.html">普通邮寄</a></td>
        </tr>

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
      </table></td>
      <td><div style="text-align:right; margin:5px 10px 5px 0px"><a href="${pageContext.request.contextPath }/index.do">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;购物车</div>
        
		
		
		
		
        <table cellspacing="0" class="infocontent">
        <tr>
          <td><img src="${pageContext.request.contextPath }/images/ad/page_ad.jpg" width="666" height="89" />
            <table width="100%" border="0" cellspacing="0">
              <tr>
                <td><img src="${pageContext.request.contextPath }/images/buy1.gif" width="635" height="38" /></td>
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
             
                    <td width="10%">取消</td>
                  </tr>
                </table>
                <c:set var="sum" value="0"></c:set>
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
                      <td width="10%"><a href="${pageContext.request.contextPath }/cart.do?did=delete&&id=${item.gid}" style="color:#FF0000; font-weight:bold">X</a></td>
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
				   <div style="text-align:right; margin-top:10px"><a href="${pageContext.request.contextPath }/index.do"><img src="images/gwc_jx.gif" border="0" /></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath }/DispacherServlet.do?type=order"><img src="images/gwc_buy.gif" border="0" /></a></div>
				  
				  </td>
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
      <td rowspan="2" style="width:10%"><img src="${pageContext.request.contextPath }/images/bottomlogo.gif" width="195" height="50" style="margin-left:175px"/></td>
      <td style="padding-top:5px; padding-left:50px"><a href="#"><font color="#747556"><b>CONTACT US</b></font></a></td>
    </tr>
    <tr>
      <td style="padding-left:50px"><font color="#CCCCCC"><b>COPYRIGHT 2008 &copy; eShop All Rights RESERVED.</b></font></td>
    </tr>
  </table>
</div>


</body>
</html>
