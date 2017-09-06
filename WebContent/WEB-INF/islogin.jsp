<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${ sessionScope.user==null}">
<jsp:forward page="login.jsp">
   <jsp:param name="msg" value="请登陆后再下单"/>
</jsp:forward>
</c:if>