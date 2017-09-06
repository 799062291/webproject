<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta content="all" name="robots" />
<meta name="author" content="Fisher" />
<meta name="Copyright"
	content="Copyright 2007-2008, 版权所有 www.reefdesign.cn" />
<meta name="description" content="reefdesign" />
<meta name="keywords" content="reefdesign" />
<title>电子书城</title>
<link rel="shortcut icon" href="favicon.ico">
	<link rel="stylesheet" rev="stylesheet"
		href="${pageContext.request.contextPath }/css/style.css"
		type="text/css" media="all" />
</head>

<body class="main">
     
    <c:set var="i" value="${param.id }"></c:set>
	<%@include file="/WEB-INF/header.jsp"%>

	<div id="divcontent">
		<table width="900px" border="0" cellspacing="0">
			<tr>
				<td style="padding: 30px"><div style="height: 470px">
						<b>&nbsp;&nbsp;首页&nbsp;&raquo;&nbsp;个人用户登录</b>
						<div>
							<table width="85%" border="0" cellspacing="0">
								<tr>
									<td>
										<form action="${pageContext.request.contextPath }/login.do"
											method="post">
											<div id="logindiv">
										  <c:if test="${cookie.uloginid==null &&sessionScope.user==null}">
												<table width="100%" border="0" cellspacing="0">
													<tr>
														<td style="text-align: center; padding-top: 20px"><img
															src="images/logintitle.gif" width="150" height="30" /></td>
													</tr>
													<tr>
														<td style="text-align: center"><table width="80%"
																border="0" cellspacing="0"
																style="margin-top: 15px; margin-left: auto; margin-right: auto">
																<tr>
																	<td
																		style="text-align: right; padding-top: 5px; width: 25%">用户名：</td>
																	<td style="text-align: left"><input name="uname"
																		type="text" class="textinput" /></td>
																</tr>
																<tr>
																	<td style="text-align: right; padding-top: 5px">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
																	<td style="text-align: left"><input
																		name="upassword" type="text" class="textinput" /></td>
																</tr>
																<tr>
																	<td colspan="2" style="text-align: center"><input
																		type="checkbox" name="chk" value="checkbox" />
																		&nbsp;&nbsp;记住我的登录状态</td>
																		
																</tr>
																<tr>
																  <td colspan="2" style="text-align: center">
																           <c:if test="${i!=2&&i!=1 }">
																              &nbsp;
																           </c:if>
																		   <c:if test="${i==1 }">
																		      <font color="red">账号未激活</font>
																		   </c:if>
																		    <c:if test="${i==2 }">
																		      <font color="red">账号不存在或密码错误</font>
																		   </c:if>
																	</td>
																</tr>
																<tr>
																	<td colspan="2"
																		style="padding-top: 10px; text-align: center"><input
																		name="image" type="image" src="${pageContext.request.contextPath }/images/loginbutton.gif"
																		width="83" height="22" /></td>
																</tr>
																<tr>
																	<td colspan="2"
																		style="padding-top: 10px; text-align: center">登录帮助&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;帮助中心&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;忘记密码</td>
																</tr>
																<tr>
																	<td colspan="2" style="padding-top: 10px"><img
																		src="images/loginline.gif" width="241" height="10" /></td>
																</tr>
																<tr>
																	<td colspan="2"
																		style="padding-top: 10px; text-align: center"><a
																		href="${pageContext.request.contextPath }/DispacherServlet.do?type=regedit"><img
																			name="image" type="image"
																			src="${pageContext.request.contextPath }/images/signupbutton.gif"
																			width="135" height="33" /></a></td>
																</tr>
															</table></td>
													</tr>
												</table>
												</c:if>
											
												<c:if test="${cookie.uloginid!=null||sessionScope.user!=null }">
												        &nbsp;  &nbsp; 欢迎：${sessionScope.user.uname}</br>
												     &nbsp; &nbsp;<a href="${pageContext.request.contextPath }/DispacherServlet.do?type=my">用户信息</a>
												</c:if>
											</div>
											</form>
									</td>


									  <c:if test="${cookie.uloginid==null &&sessionScope.user==null}">
									<td style="text-align: left; padding-top: 30px; width: 60%"><h1>您还没有注册？</h1>
										<p>注册新会员，享受更优惠价格!</p>
										<p>千种图书，供你挑选！注册即享受丰富折扣和优惠，便宜有好货！超过万本图书任您选。</p>
										<p>超人气社区！精彩活动每一天。买卖更安心！支付宝交易超安全。</p>
										<p style="text-align: right">
											<a
												href="${pageContext.request.contextPath }/DispacherServlet.do?type=regedit"><input
												name="image" type="image"
												src="${pageContext.request.contextPath }/images/signupbutton.gif"
												width="135" height="33" /></a>
										</p></td>
										</c:if>
										<c:if test="${cookie.uloginid!=null||sessionScope.user!=null }">
											<td style="text-align: left; padding-top: 30px; width: 60%"><h1>欢迎：尊敬的会员</h1>
										<p>畅游书海，博览群书，限时VIP书籍抢先看！</p>
										<p>全场购书享受8折优惠！开启盛夏狂欢！</p>
										<p>线上交易免除手续费！时刻轻松买买买！</p>
										<p style="text-align: right;">
											<a style="color:red;"
												href="${pageContext.request.contextPath }/delete.do">注销账号</a>
										</p></td>
										</c:if>
								</tr>
							</table>
						</div>
					</div></td>
			</tr>
		</table>
	</div>



	<div id="divfoot">
		<table width="100%" border="0" cellspacing="0">
			<tr>
				<td rowspan="2" style="width: 10%"><img
					src="images/bottomlogo.gif" width="195" height="50"
					style="margin-left: 175px" /></td>
				<td style="padding-top: 5px; padding-left: 50px"><a href="#"><font
						color="#747556"><b>CONTACT US</b></font></a></td>
			</tr>
			<tr>
				<td style="padding-left: 50px"><font color="#CCCCCC"><b>COPYRIGHT
							2008 &copy; eShop All Rights RESERVED.</b></font></td>
			</tr>
		</table>
	</div>


</body>
</html>
