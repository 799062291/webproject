<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <meta content="all" name="robots" />
    <meta name="author" content="Fisher" />
    <meta name="Copyright" content="Copyright 2007-2008, 版权所有 www.reefdesign.cn" />
    <meta name="description" content="reefdesign" />
    <meta name="keywords" content="reefdesign" />
    <title>电子书城</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath }/css/style.css" type="text/css" media="all" />
<style type="text/css">
span{
  float: right;
  color: skyblue;
  
}
</style>
</head>
<body class="main">
    
   <%@include file="/WEB-INF/header.jsp" %>
    <div id="divpagecontent">
        <table width="100%" border="0" cellspacing="0">
            <tr>
           
                <td>
                    <div style="text-align: right; margin: 5px 10px 5px 0px">
                        <a href="${pageContext.request.contextPath }/index.do">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath }/listServlet.do?cid=${pageInfo.datas[0].cid }&&pageIndex=1">${pageInfo.type}</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;书籍清单</div>
                    <table cellspacing="0" class="infocontent">
                        <tr>
                            <td>
                                <table width="100%" border="0" cellspacing="0">
                                    <tr>
                                        <td style="padding: 10px">
                                            以下 <strong>${pageInfo.totalNumber }</strong> 条结果按 <strong>销量</strong> 排列 每页显示<strong>${fn:length(pageInfo.datas) }</strong>条<hr />
                                              <c:set var="cid" value="${pageInfo.datas[0].cid }"></c:set>
                                              <c:forEach items="${pageInfo.datas }" var="goods">
                                                <c:set var="cid" value="${goods.cid }"></c:set>
                                                <table border="0" cellspacing="0" class="searchtable">
                                                <tr>
                                                    <td width="20%" rowspan="2">
                                                        <div class="divbookpic">
                                                            <p>
                                                                <a href="${pageContext.request.contextPath }/DispacherServlet.do?type=goods&&id=${goods.gid}">
                                                                    <img src="${pageContext.request.contextPath }/images/bookcover/${goods.gimg }.jpg" width="115" height="129" border="0" /></a></p>
                                                        </div>
                                                    </td>
                                                    <td colspan="2">
                                                        <font class="bookname">${goods.gtitle }</font><br />
                                                        作者：${goods.gauthor } 著<br />
                                                      ${goods.gdesc }
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        售价：<font color="#FF0000">￥${goods.ginprice }</font>&nbsp;&nbsp;&nbsp;&nbsp;原价：<s>￥${goods.gsaleprice }</s>
                                                    </td>
                                                    <td style="text-align: right">
                                                        <a href="${pageContext.request.contextPath }/cart.do?gid=${goods.gid}">
                                                            <img src="${pageContext.request.contextPath }/images/buy.gif" width="91" height="27" border="0" style="margin-bottom: -8px" /></a>
                                                    </td>
                                                </tr>
                                            </table>
                                              </c:forEach>   
                                           
                                           
                                            <div class="pagination" style="width: 800px">
                                                <ul>
                                                <c:if test="${pageInfo.isFirstPage }">
                                                  <li class="disablepage"><< 上一页 </li>
                                                   <li class="disablepage">首页 </li>
                                                </c:if>
                                                 <c:if test="${!pageInfo.isFirstPage }">
                                                   <c:if test="${model==1 }">
                                                     <li class="nextpage"><a href="${pageContext.request.contextPath }/listServlet.do?key=${key }&&type=look&&pageIndex=${pageIndex-1}"><< 上一页</a></li>
                                                     <li class="nextpage"><a href="${pageContext.request.contextPath }/listServlet.do?key=${key }&&type=look&&pageIndex=1">首页</a></li>
                                                   </c:if>
                                                    <c:if test="${model==0 }">
                                                  <li class="nextpage"><a href="${pageContext.request.contextPath }/listServlet.do?cid=${cid}&&pageIndex=${pageInfo.pageIndex-1}"><< 上一页</a> </li>
                                                  <li class="nextpage"><a href="${pageContext.request.contextPath }/listServlet.do?cid=${cid}&&pageIndex=1">首页</a> </li>
                                                </c:if>
                                                </c:if>
   <% 
 	int k = 1;
 %> 
     <c:set var="k" value="1"/>
     <c:if test="${ pageInfo.pageIndex>6}">
       
         <c:set var="k" value="${k+pageInfo.pageIndex- 6 }"/>
     </c:if>
     <c:if test="${pageInfo.totalPages!=0&&pageInfo.totalPages>=11}">
      <c:if test="${pageInfo.totalPages-pageInfo.pageIndex<=5 }">
       <c:set var="k" value="${pageInfo.totalPages-10}"/>
      </c:if>
      </c:if>
      <c:if test="${pageInfo.totalPages<11 }">
          <c:forEach begin="1" end="${pageInfo.totalPages }" >
        <c:if test="${k==pageInfo.pageIndex }">
           <li class="currentpage" href="${pageContext.request.contextPath }/listServlet.do?cid=${cid}&&pageIndex=${k}">${k }</li>
             
        </c:if>
         <c:if test="${k!=pageInfo.pageIndex }">
             <c:if test="${model==1 }">
               <li><a  href="${pageContext.request.contextPath }/listServlet.do?key=${key }&&type=look&&pageIndex=${k}">${k }</a></li>
            </c:if>
            <c:if test="${model==0 }">
             <li><a  href="${pageContext.request.contextPath }/listServlet.do?cid=${cid}&&pageIndex=${k}">${k }</a></li>
            </c:if>
        </c:if>
          <c:set var="k" value="${k+1}"/>
      </c:forEach>
      </c:if>
      
       <c:if test="${pageInfo.totalPages>=11 }">
         <c:forEach begin="1" end="11" >
        <c:if test="${k==pageInfo.pageIndex }">
           <li class="currentpage" href="${pageContext.request.contextPath }/listServlet.do?cid=${cid}&&pageIndex=${k}">${k }</li>
             
        </c:if>
         <c:if test="${k!=pageInfo.pageIndex }">
            <c:if test="${model==1 }">
               <li><a  href="${pageContext.request.contextPath }/listServlet.do?key=${key }&&type=look&&pageIndex=${k}">${k }</a></li>
            </c:if>
            <c:if test="${model==0 }">
             <li><a  href="${pageContext.request.contextPath }/listServlet.do?cid=${cid}&&pageIndex=${k}">${k }</a></li>
            </c:if>
         
        </c:if>
          <c:set var="k" value="${k+1}"/>
      </c:forEach>
      </c:if>

                                                  
                                                    <c:if test="${pageInfo.isLastPage }">
                                                     <li class="disablepage">尾页</li>
                                                  <li class="disablepage">下一页  >></li>
                                                </c:if>
                                                   <c:if test="${!pageInfo.isLastPage }">
                                                   
                                                   <c:if test="${model==1 }">
                                                     <li class="nextpage"><a href="${pageContext.request.contextPath }/listServlet.do?key=${key }&&type=look&&pageIndex=${pageInfo.totalPages}">尾页</a></li>
                                                     <li class="nextpage"><a href="${pageContext.request.contextPath }/listServlet.do?key=${key }&&type=look&&pageIndex=${pageIndex+1}">下一页 >></a></li>
                                                   </c:if>
                                                    <c:if test="${model==0 }">
                                                    <li class="nextpage"><a href="${pageContext.request.contextPath }/listServlet.do?cid=${cid}&&pageIndex=${pageInfo.totalPages}">尾页</a></li>
                                                  <li class="nextpage"><a href="${pageContext.request.contextPath }/listServlet.do?cid=${cid}&&pageIndex=${pageInfo.pageIndex+1}">下一页 >></a></li>
                                                </c:if>
                                                </c:if>
                                               <span><li>总计：${pageInfo.pageIndex }/${pageInfo.totalPages}</li></span>  
                                                </ul>
                                             
                                            </div>
                                        </td>
                                    </tr>
                                </table>
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
                <td rowspan="2" style="width: 10%">
                    <img src="images/bottomlogo.gif" width="195" height="50" style="margin-left: 175px" />
                </td>
                <td style="padding-top: 5px; padding-left: 50px">
                    <a href="#"><font color="#747556"><b>CONTACT US</b></font></a>
                </td>
            </tr>
            <tr>
                <td style="padding-left: 50px">
                    <font color="#CCCCCC"><b>COPYRIGHT 2008 &copy; eShop All Rights RESERVED.</b></font>
                </td>
            </tr>
        </table>
    </div>
</body>
</html>
