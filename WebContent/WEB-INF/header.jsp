<%@page import="org.lanqiao.service.impl.CategoryServiceImpl"%>\
<%@page import="org.lanqiao.service.CategoryService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- 加载购物车数据 -->
<%@include file="cookiedata.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/web-project/admin/easyui/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="/web-project/admin/easyui/themes/icon.css" />
		<script src="/web-project/admin/easyui/jquery.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/web-project/admin/easyui/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="/web-project/admin/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/search.js">
<!--

//-->
</script>
</head>
<body>
<!-- Logo -->
<div id="divhead">
  <table cellspacing="0" class="headtable">
    <tr>
      <td><a href="${pageContext.request.contextPath }/index.do"><img src="${pageContext.request.contextPath }/images/logo.gif" width="95" height="30" border="0" /></a></td>
      <td style="text-align:right"><img src="${pageContext.request.contextPath }/images/cart.gif" width="26" height="23" style="margin-bottom:-4px"/>&nbsp;<a href="${pageContext.request.contextPath }/DispacherServlet.do?type=cart">购物车<font color="red"><c:if test="${cart==null }">(0)</c:if><c:if test="${cart!=null }">(${fn:length(cart)})</fn></c:if></font></a>　|　<a href="#">帮助中心</a>　|　<a href="${pageContext.request.contextPath }/DispacherServlet.do?type=login">我的帐户</a>　|　<a href="${pageContext.request.contextPath }/DispacherServlet.do?type=regedit">新用户注册</a></td>
    </tr>
  </table>
</div>
<!-- Logo end -->
<%
   CategoryService cs = new CategoryServiceImpl();
   request.setAttribute("cates", cs.categoryList());


%>
<!-- menu -->
<div id="divmenu">
    <c:forEach items="${cates }" var="item">
        <a href="${pageContext.request.contextPath }/listServlet.do?cid=${item.cid}&&pageIndex=1">${item.cname }</a>　　
    </c:forEach>
   　　<a href="product_list.html" style="color:#FFFF00">全部商品目录</a>
</div>
<!-- menu end -->
<!-- search -->
<div id="divsearch"><table width="100%" border="0" cellspacing="0">
  <tr>
    <td style="text-align:right; padding-right:220px">Search
  <input type="text" name="textfield" id="searchtext" class="inputtable" list="ds"/>
   <datalist id="ds"></datalist>
<!--<input name="searchbutton" type="image" src="images/serchbutton.gif" style=" margin-bottom:-4px"/>-->
<img src="${pageContext.request.contextPath }/images/serchbutton.gif" border="0" id="serbtn" style="margin-bottom:-4px"/></td>
  </tr>
</table>

</div>
<!-- search end -->
</body>
</html>