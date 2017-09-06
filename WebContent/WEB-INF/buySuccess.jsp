<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script type="text/javascript">
   window.onload = function(){
	   document.querySelector('#a1').onclick = function(){
		   window.location.go(-1); 
	   }
   }
</script>
</head>

<body class="main">

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
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp; <a href="news.html">特快专递送货上门(EMS)</a></td>
        </tr>
        <tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp; <a href="news.html">申通快递送货上门</a></td>
        </tr>
        <tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp; <a href="news.html">普通邮寄</a></td>
        </tr>
      </table></td>
      <td><div style="text-align:right; margin:5px 10px 5px 0px"><a href="index.html">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a href="my.html">&nbsp;我的帐户</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;修改成功</div>
        
		
		
		
		
        <table cellspacing="0" class="infocontent">
        <tr>
          <td style="padding:20px; text-align:center"><table width="80%" border="0" cellspacing="0" style="margin-top:70px">
            <tr>
              <td style="width:98"><img src="images/IconTexto_WebDev_009.jpg" width="128" height="128" /></td>
              <td style="padding-top:30px"><font style="font-weight:bold; color:#FF0000">已成功添加至购物车！</font><br />
                <br />
               <a id="a1" href="${pageContext.request.contextPath }"><img src="${pageContext.request.contextPath }/images/gwc_jx.gif" border="0" /></a>  &nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath }/DispacherServlet.do?type=cart"><font color="blue">前往购物车</font></a></td>
            </tr>
          </table>          
            <p>&nbsp;</p>
            </td>
        </tr>
      </table>
	  
	  
	  </td>
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
